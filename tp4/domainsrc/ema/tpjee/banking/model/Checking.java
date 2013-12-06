package ema.tpjee.banking.model;

import java.io.Serializable;

import ema.tpjee.banking.model.exceptions.NoAvailableFundsException;



public class Checking extends BankAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1575986666936254595L;
	private static final double overdraftPenalty = 25.00;
	
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

	public Checking() {
	}

	public Checking(Bank b, String num, double initAmount) {
		super(b, num, initAmount);
	}

	public void withdraw(double val) throws NoAvailableFundsException {
		super.withdraw(val);
		if (getBalance() <= val)
			deductOverDraftPenalty();
	}

	public String accountType() {
		return "Checking Account:       ";
	}

	public String toString() {
		return super.toString() + " " + getMessage();
	}

	private double getOverdraftPenalty() {
		return overdraftPenalty;
	}

	private void deductOverDraftPenalty() {
		if (LOG)
			System.out.println(
							"try to deduct overdraft penality "
									+ getOverdraftPenalty());
		try {
			super.withdraw(getOverdraftPenalty());
		} catch (NoAvailableFundsException e) {
			System.out.println("NoAvailableFundsException thrown ");
		}
	}

}
