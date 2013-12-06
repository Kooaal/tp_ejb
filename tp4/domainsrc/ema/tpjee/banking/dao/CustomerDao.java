package ema.tpjee.banking.dao;


import java.util.List;


import ema.tpjee.banking.model.Bank;
import ema.tpjee.banking.model.Customer;


public interface CustomerDao {
	public static final boolean IS_EJB = false; //il faudrait utiliser l'introspection pour détecter l'ejb
	public List<Customer> getList(Bank b);
	public Customer find(Bank b,int id);
	public Customer delete(Bank b,Customer c);
	public Customer clone(Customer c);
	public Customer clone(Bank b,Customer c);
	public Customer first(Bank b);
	public Customer last(Bank b);
	public Customer prior(Bank b,Customer c);
	public Customer next(Bank b,Customer c);
	public Customer create(Bank b);
	public Customer merge(Bank b,Customer c);
	public Customer foobar(Bank b,Customer c);	
	public List<Customer> populate(Bank b);
}
