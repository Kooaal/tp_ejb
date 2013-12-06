package ema.tpjee.banking.model;

import java.io.Serializable;


public class Saving extends BankAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 288016344069101263L;

	public Saving() {
	}

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
	
	public Saving(Bank application,String num, double initAmount, double rate) {
		super(application,num, initAmount, rate);

	}

	public String accountType() {
		return "Saving Account:       ";
	}

	public void addInterest() {
		deposit(getInterestRate() * getBalance());
	}

}
