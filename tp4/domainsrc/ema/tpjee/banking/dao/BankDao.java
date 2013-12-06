package ema.tpjee.banking.dao;

import java.util.List;

import ema.tpjee.banking.model.Bank;



public interface BankDao {
	public static final boolean IS_EJB = false; //il faudrait utiliser l'introspection pour détecter l'ejb

	public List<Bank> getList();
	public Bank add(Bank b);
	public Bank find(int id);
	public Bank delete(Bank b);
	public Bank first();
	public Bank last();
	public Bank prior(Bank b);
	public Bank next(Bank b);
	public Bank clone(Bank b);
	public Bank create();
	public Bank merge(Bank b);
	public Bank foobar(Bank b);
	public List<Bank> populate();
}
