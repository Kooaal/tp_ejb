
package ema.tpjee.banking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Customer implements Serializable{
    

	private static final long serialVersionUID = 7204194554910037494L;
	
	private static int instanceCount;
	
	private int id;
	private String name;
	private String forName;
	private String address;
	private String zipCode; //TODO remplacer par une référence vers City
	
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
	
	
	private List<BankAccount> accounts = new ArrayList<BankAccount>();
    
	private Bank bank; //TODO supprimer, autoriser plusieurs banques par Customer
	
    public Customer(String name, String forName, String address,
			String zipCode) {
		super();
		this.id = instanceCount++;
		this.name = name;
		this.forName = forName;
		this.address = address;
		this.zipCode = zipCode;
	}


	public Customer() {
		this.id = instanceCount++;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		//this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getForName() {
		return forName;
	}


	public void setForName(String forName) {
		this.forName = forName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public List<BankAccount> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}
	
	
	public void addAccount(BankAccount account) {
		this.accounts.add(account);
		account.setCustomer(this);
	}
	
	public String toString(){
		return id+";"+forName+";"+name+";"+address+";"+zipCode;
	}


	public Bank getBank() {
		return bank;
	}


	public void setBank(Bank bank) {
		this.bank = bank;
	}
    
}
