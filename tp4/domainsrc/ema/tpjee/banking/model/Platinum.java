package ema.tpjee.banking.model;

import java.io.Serializable;

import ema.tpjee.banking.model.exceptions.NoAvailableFundsException;



public class Platinum extends BankAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3040518592606380604L;

	private static final double overdraftLimit = 2500.00;

	private double availableFunds;
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}		

	public Platinum() {
	}

	public Platinum(Bank application, String num, double initAmount) {
		super(application, num, initAmount);
		availableFunds = overdraftLimit + getBalance();
	}

	public String accountType() {
		return "Platinume Account:       ";
	}

	public void withdraw(double val) throws NoAvailableFundsException {
		if (val > getBalance()) {
			useOverdraftLimit(val);
		} else
			super.withdraw(val);
	}

	public double getOverDraftLimit() {
		return overdraftLimit;
	}

	private void useOverdraftLimit(double val) {
		availableFunds -= val;
	}

	public double getAvailableFunds() {
		return availableFunds;
	}

}
