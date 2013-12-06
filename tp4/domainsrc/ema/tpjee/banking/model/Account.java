package ema.tpjee.banking.model;

import ema.tpjee.banking.model.exceptions.NoAvailableFundsException;



public interface Account {
	static final boolean LOG = true;

	public String accountType();
	
	public double getBalance();

	public String getAccountNumber();

	public void withdraw(double val)
			throws NoAvailableFundsException;

	public void deposit(double val);
}
