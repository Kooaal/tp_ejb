package ema.tpjee.banking.dao;

import java.util.List;

import ema.tpjee.banking.model.BankAccount;
import ema.tpjee.banking.model.Customer;

public interface AccountDao {
	public static final boolean IS_EJB = false; //il faudrait utiliser l'introspection pour détecter l'ejb
	public List<BankAccount> getList(Customer b);
	public BankAccount find(Customer b,int id);
	public BankAccount delete(Customer b,BankAccount c);
	public BankAccount clone(BankAccount c);
	public BankAccount clone(Customer b,BankAccount c);
	public BankAccount first(Customer b);
	public BankAccount last(Customer b);
	public BankAccount prior(Customer b,BankAccount c);
	public BankAccount next(Customer b,BankAccount c);
	public BankAccount create(Customer b);
	public BankAccount merge(Customer b,BankAccount c);
	public BankAccount foobar(Customer b,BankAccount c);	
	public List<BankAccount> populate(Customer b);

}
