package ema.tpjee.banking.controlerv2;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ema.tpjee.banking.dao.BankDao;
import ema.tpjee.banking.model.Bank;
import ema.tpjee.utils.AppLocator;
import ema.tpjee.utils.ServletUtils;

/**
 * Servlet implementation class for Servlet: BankControler
 * 
 */
public class BankControler extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	private BankDao bankDao;
	
	
	public void createLocalDao() {
		System.out.println("creating LocalDao");
		bankDao = AppLocator.getInstance().getComponent("ema.tpjee.banking.dao.BankDaoImpl");
	}
	
	public void createEjbDao() {
		System.out.println("creating EjbDao");
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		System.setProperty(Context.PROVIDER_URL, "localhost:1099");
		System.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");
		try {
			bankDao = (BankDao) new InitialContext()
					.lookup("BankDaoImpl/remote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		if (BankDao.IS_EJB)
		    createEjbDao();
		else
			createLocalDao();
		super.init(config);
	}

	/**
	 * Constructeur Le DAO est stateless, il peut donc être lié à la servlet
	 * (pas de pb d'accès concurrent)
	 */
	public BankControler() {
		super();
		System.out.println("creating BankControler");
	}

	/**
	 * reconstituer le nouvel objet à partir des paramètres de la request les
	 * frameworks le font par introspection
	 * 
	 * @param request
	 * @return
	 */
	private Bank formValue(HttpServletRequest request) {
		if (request.getParameter("id") == null)
			return null;
		Bank result = new Bank();
		try {
			int rid = Integer.parseInt(request.getParameter("id"));
			result.setId(rid);
		} catch (Exception e) {

		}
		result.setName(request.getParameter("name"));
		result.setAddress(request.getParameter("address"));
		result.setPhone(request.getParameter("phone"));
		try {
			result.setZipCode(request.getParameter("zipCode"));
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * validation x On souhaiterait une hiérarchie d'exceptions
	 * 
	 * @param b
	 */
	private void validate(Bank b) {
		if (b.getName().contains("supercrash")
				|| b.getName().contains("forbidden"))
			throw new RuntimeException("the name is forbidden!");
	}

	/**
	 * validation y On souhaiterait une hiérarchie d'exceptions
	 * 
	 * @param b
	 */
	private void validateFoobar(Bank b) {
		if (Integer.parseInt(b.getZipCode()) > 150000)
			throw new RuntimeException("foobar is too big!");
	}

	/**
	 * modifier l'objet au sein du modèle après validation
	 * 
	 * @param request
	 * @param b
	 * @return
	 */
	private Bank doModify(HttpServletRequest request, Bank b) {
		Bank result = null;
		try {
			validate(b);
			result = bankDao.merge(b); 
			// le modèle est mis à jour, si pas d'exception
		} catch (Exception e) {
			result = b; 
			// formBean is returned but not modified in persistence layer
			System.out.println("error " + e.getMessage());
			request.setAttribute("error", "erreur " + e.getMessage());
		}
		return result;
	}

	/**
	 * foobar est une action métier (autre qu'une simple opération CRUD) exemple
	 * d'une contrainte susceptible de lever une exception sémantique, pour
	 * tester les mécanismes de validation
	 * 
	 * A noter: Adhérence de la couche métier avec la couche web (paramètre
	 * request) On souhaiterait un mécanisme générique de gestion d'erreurs
	 * 
	 * @param request
	 * @param b
	 *            the bank to foobar
	 * @return the foobared bank
	 */
	private Bank foobar(HttpServletRequest request, Bank b) {
		Bank result = null;
		int foobar = 0;
		try {
			Collection<Bank> bks = bankDao.getList();
			for (Iterator<Bank> i = bks.iterator(); i.hasNext();) {
				Bank bb = i.next();
				foobar += Integer.parseInt(bb.getZipCode());
			}
			b.setZipCode(Integer.toString(foobar));
			result = bankDao.foobar(b); 
			// la RG peut être déléguée au composant
			validateFoobar(b); 
			// la RG peut être vérifiée par le contrôleur
			result = bankDao.merge(b); 
			// si pas d'exception, le modèle est mis à jour
		} catch (Exception e) {
			result = b; 
			// formBean is returned but not modified in persistence layer
			System.out.println("error " + e.getMessage());
			request.setAttribute("error", "erreur " + e.getMessage());
		}
		return result;
	}

	/**
	 * Méthode recevant l'action en provednance du client On souhaiterait
	 * généraliser le mécanisme Cette généralisation est réalisée par les
	 * frameworks (struts, spring, etc..) et par JSF
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("controler-class", this.getClass().getName());
		ServletUtils.debugRequestParameters(this.getClass(), request);
		ServletUtils.debugSessionAttributes(this.getClass(), request);

		// étape 1 décoder les événements depuis la request

		String page = request.getParameter("page");
		String cmd = request.getParameter("cmd");

		System.out.println("cmd = " + cmd);

		// étape 2 reconstituer l'objet de domaine depuis la request

		Bank oldBean = null; 
		// l'ancienne valeur
		Bank newBean = null; 
		// la nouvelle valeur, celle qui sera présentée à la vue

		Bank formBean = formValue(request); 
		// la valeur reçue par la requête (avant validation)

		// étape 3 retrouver la valeur de l'objet du domaine avant l'action

		if (formBean != null)
			oldBean = bankDao.find(formBean.getId()); 
		// de préférence dans la couche de persistance
		else
			oldBean = (Bank) request.getSession().getAttribute("currentbank");
		// sinon dans notre contexte web

		if (oldBean == null)
			oldBean = bankDao.first(); 
		// par défaut, prendre le premier de la liste

		// étape 4
		// 4a appliquer les RG
		// 4b gérer les erreurs de validation, générer les messages d'erreur
		// 4c appliquer la nouvelle valeur sur le modèle si ok
		// 4d préparer l'objet validé et modifié ou invalidé pour correction, à
		// présenter dans la vue (newbean)

		if ("editer".equals(cmd))
			newBean = oldBean;
		else if ("suivant".equals(cmd))
			newBean = bankDao.next(oldBean);
		else if ("precedent".equals(cmd))
			newBean = bankDao.prior(oldBean);
		else if ("dupliquer".equals(cmd))
			newBean = bankDao.clone(oldBean);
		else if ("supprimer".equals(cmd))
			newBean = bankDao.delete(oldBean);
		else if ("ajouter".equals(cmd))
			newBean = bankDao.create();
		else if ("annuler".equals(cmd))
			newBean = oldBean;
		else if ("modifier".equals(cmd))
			newBean = doModify(request, formBean);
		else if ("foobar".equals(cmd))
			newBean = foobar(request, formBean);


		if (newBean != null)
			request.getSession().setAttribute("currentbank", newBean);
		else
			request.getSession().setAttribute("currentbank", oldBean);
		request.getSession().setAttribute("banks", bankDao.getList());// reverseListBank());

		// étape 5 appliquer les règles de navigation, diriger la requête vers la vue
		String fw = null;

		if ("clients".equals(cmd))
			fw = "/customer.do"; 
		// les controleurs ne se connaissent pas entre eux. On passe par l'uri. On
		// utilise le contexte de session ou de requête pour passer les paramètres
		else if (page != null)
			fw = "/pages/" + page + ".jsp";
		else
			fw = "/pages/bank.jsp";

		// étape 6 faire suivre l'exécution vers la vue
		System.out.println("forwarding to: " + fw);
		getServletContext().getRequestDispatcher(fw).forward(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		bankDao.populate();
		super.init();
	}
}