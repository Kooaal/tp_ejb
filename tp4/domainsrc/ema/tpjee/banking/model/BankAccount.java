package ema.tpjee.banking.model;

import java.io.Serializable;

import ema.tpjee.banking.model.exceptions.NoAvailableFundsException;



public abstract class BankAccount implements Account ,Serializable{

	private int id;
	
	private double balance;

	private String accountNumber;

	private History mHistory = new History();

	private double interestRate;

	private Customer customer;

	protected Bank bank;

	
	
	public Customer getCustomer() {
		return customer;
	}

	/** Creates a new instance of BankAccount */
	public BankAccount() {
	}

	public BankAccount(Bank bank, String accNumber, double initialAmount) {
		this.bank = bank;
		balance = initialAmount;
		accountNumber = accNumber;
		this.bank.addAccount(this);
		if (LOG)
			System.out.println("Account created: " + this.toString());
	}

	public BankAccount(Bank bank, String accNumber,
			double initialAmount, double rate) {
		this.bank = bank;
		balance = initialAmount;
		accountNumber = accNumber;
		interestRate = rate;
		this.bank.addAccount(this);
		if (LOG)
			System.out.println("Account created: " + this.toString());

	}

	public double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void withdraw(double val)
			throws NoAvailableFundsException {
		if (LOG)
			System.out.println(
					customer.toString() + " " + accountType() + " "
							+ getAccountNumber() + " withdraw " + val);
		if (getBalance() >= val) {
			setBalance(balance - val);
			mHistory.incrementTransaction();
		} else
			noAvailableFunds();
	}

	public void deposit(double val) {
		if (LOG)
			System.out.println(
					customer.toString() + " " + accountType() + " "
							+ getAccountNumber() + " deposit " + val);
		setBalance(balance + val);
	}

	public void setBalance(double val) {
		this.balance = val;
	}

	public void setAccountNumber(String val) {
		this.accountNumber = val;
	}

	public boolean equals(Object o) {
		return this == o
				|| ((o instanceof BankAccount) && ((BankAccount) o)
						.getAccountNumber() == getAccountNumber());
	}

	public int hashCode() {
		return getAccountNumber().hashCode();
	}

	private void noAvailableFunds() throws NoAvailableFundsException {
		if (LOG)
			System.out.println(
					customer.toString() + " " + accountType() + " "
							+ getAccountNumber() + " noAvailableFunds ");
		throw new NoAvailableFundsException(
				"Not enough funds for this account  " + customer.toString()
						+ " " + getAccountNumber());
	}

	public String toLog() {
		return "customer: " + customer.toString() + " " + accountType()
				+ " Number:  " + getAccountNumber() + "\nBalance:  "
				+ getBalance();
	}

	@Override
	public String toString() {
		return accountType() + " Number:  " + getAccountNumber()
				+ "\nBalance:  " + getBalance();
	}

	public History getHistory() {
		return mHistory;
	}

	public void setHistory(History val) {
		this.mHistory = val;
	}

	public String getMessage() {
		String result = mHistory.getMsg();
		if (result == null)
			result = "history is empty";
		return result;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double val) {
		this.interestRate = val;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public History getMHistory() {
		return mHistory;
	}

	public void setMHistory(History history) {
		mHistory = history;
	}


	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
