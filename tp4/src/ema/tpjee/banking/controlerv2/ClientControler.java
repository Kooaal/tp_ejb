package ema.tpjee.banking.controlerv2;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ema.tpjee.banking.dao.CustomerDao;
import ema.tpjee.banking.dao.CustomerDaoImpl;
import ema.tpjee.banking.model.Bank;
import ema.tpjee.banking.model.Customer;
import ema.tpjee.utils.ServletUtils;

/**
 * Servlet implementation class for Servlet: ClientControler
 * 
 */
public class ClientControler extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	private CustomerDao customerDao;
	
	
	public void createEjbDao() {
		System.out.println("creating EjbDao");
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		System.setProperty(Context.PROVIDER_URL, "localhost:1099");
		System.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");
		try {
			customerDao = (CustomerDao) new InitialContext()
					.lookup("CustomerDaoImpl/remote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	public void createLocalDao() {
		customerDao = new CustomerDaoImpl();
	}
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		if (CustomerDao.IS_EJB)
		   createEjbDao();
		else
			createLocalDao();
		super.init(config);
	}	
	

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ClientControler() {
		super();
		System.out.println("creating ClientControler");
		//customerDao = new CustomerDaoImpl();

	}

	private void populate(HttpServletRequest request,Bank currentb){
		if (request.getSession().getAttribute("initcustomer") == null) {
			request.getSession().setAttribute("initcustomer", "done");
			customerDao.populate(currentb);
		}	
	}
	
	

	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("controler-class", this.getClass().getName());
		ServletUtils.debugRequestParameters(this.getClass(),request);
		ServletUtils.debugSessionAttributes(this.getClass(),request); 
		String cmd = request.getParameter("cmd");
		System.out.println("cmd = " + cmd);
		
		Bank currentb = (Bank) request.getSession().getAttribute("currentbank");
		populate(request,currentb);
	
		String fw = "/pages/client.jsp";
		if (currentb != null) {
			List<Customer> custs = customerDao.getList(currentb);
			request.getSession().setAttribute("customers", custs);
			if (custs.size()>0){
				request.getSession().setAttribute("currentcustomer", custs.get(0));
			}
		}
		System.out.println("forwarding to: "+fw);
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
}