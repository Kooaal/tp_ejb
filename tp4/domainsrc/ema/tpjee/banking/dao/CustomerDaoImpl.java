package ema.tpjee.banking.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import ema.tpjee.banking.model.Bank;
import ema.tpjee.banking.model.Customer;
import ema.tpjee.utils.Paging;




public class CustomerDaoImpl implements CustomerDao, Serializable {


	private static final long serialVersionUID = 572652622633344240L;


	public CustomerDaoImpl() {
		super();
		System.out.println("creating CustomerDaoImpl");
	}



	public Customer add(Bank b,Customer c) {
		b.addCustomer(c);
		return c;
	}

	private Customer copyCustomer(Customer c) {
		Customer newc = new Customer();
		if (c.getAddress() != null)
			newc.setAddress(c.getAddress());
		if (c.getName() != null)
			newc.setName(c.getName());
		if (c.getForName() != null)
			newc.setForName(c.getForName());
		// if (b.getZipCode() != 0)
		newc.setZipCode(c.getZipCode());
		// copier aussi les comptes
		return newc;
	}

	public Customer clone(Bank b,Customer c) {
		Customer cloned = copyCustomer(c);
		add(b,cloned);
		return cloned;
	}
	
	public Customer clone(Customer c) {
		Customer cloned = copyCustomer(c);
		return cloned;
	}
	
	public Customer dodelete(List<Customer> customers, Customer c) {
		Customer result = Paging.prior(customers, c);
		if (result==null)
			result = Paging.next(customers, c);
		//c.getBank().getCustomers().remove(c);
		customers.remove(c);
	    return result;
	}

	public Customer delete(Bank b,Customer c) {
		return dodelete(b.getCustomers(), c);
	}


	public Customer find(Bank b,int id) {
		for (Iterator<Customer> i = b.getCustomers().iterator(); i.hasNext();) {
			Customer current = i.next();
			if (current.getId() == id)
				return current;
		}
		return null;
	}
	

	public Customer first(Bank b) {
		if (b.getCustomers().iterator().hasNext())
			return b.getCustomers().iterator().next();
		else
			return null;
	}

	public Customer foobar(Customer c) {
		return null;
	}

	public Customer last(Bank b) {
		return b.getCustomers().get(b.getCustomers().size() - 1);
	}

	public List<Customer> getList(Bank b) {
		return b.getCustomers();
	}


	public Customer save(Bank b,Customer c) {
		Customer result = find(b,c.getId());
		if (result != null) {
			if (c.getAddress() != null)
				result.setAddress(c.getAddress());
			if (c.getName() != null)
				result.setName(c.getName());
			if (c.getForName() != null)
				result.setForName(c.getForName());
			// if (b.getZipCode() != 0)
			result.setZipCode(c.getZipCode());
			// comptes ??
		} else
			throw new RuntimeException("la modification de " + c + " a échoué");
		return result;
	}

	public Customer create(Bank b) {
		Customer c = new Customer();
		c.setName("nouveau Customer " + c.getId());
		add(b,c);
		return c;
	}
	
	public Customer next(Bank b,Customer c) {
		return Paging.next(b.getCustomers(), c);
	}

	public Customer prior(Bank b,Customer c) {
		return Paging.prior(b.getCustomers(), c);
	}

	public Collection<Customer> reverseList(Bank b) {
		List<Customer> rev = new ArrayList<Customer>();
		for (Iterator<Customer> i = b.getCustomers().iterator(); i.hasNext();) {
			Customer c = i.next();
			rev.add(c);	
		}
		Collections.reverse(rev);
		return rev;
	}

	public boolean isEmpty(Bank b) {
		return b.getCustomers().size()==0;
	}

	public java.util.List getItems(Bank b) {
		return b.getCustomers();
	}


	public Customer foobar(Bank b, Customer c) {
		// TODO Auto-generated method stub
		return c;
	}


	public Customer merge(Bank b,Customer c) {
		return save(b,c);
	}


	public List<Customer> populate(Bank b) {
		return BankingPopulator.populate(b);
	}


}
