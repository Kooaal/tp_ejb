package ema.tpjee.banking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bank implements Serializable{


	private static final long serialVersionUID = 3498685794448352925L;
	
	public static final boolean IS_EJB = false;	

	
	private int id;
	private String name;
	private String address;
	private String phone;
	private String zipCode; //TODO replacer par une référence sur City

	List<Customer> customers = new ArrayList<Customer>();
	List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

	private static int instanceCount;

	//public StringBuffer logBuffer = new StringBuffer();

	
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

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
		customer.setBank(this);
	}

	public void addAccount(BankAccount account) {
		bankAccounts.add(account);
	}

	public String toString() {
		String result = "";
		result = id + " " + name + " " + address + " " + zipCode + "\n";
		for (Iterator<Customer> i = customers.iterator(); i.hasNext();) {
			Customer cust = i.next();
			result += cust.toString() + "\n";
			for (Iterator<BankAccount> j = cust.getAccounts().iterator(); j
					.hasNext();) {
				BankAccount account = j.next();
				result += account.toString();
				result += "\n";
			}
			result += "------------\n";
		}
		return result;
	}

	
	public Bank() {
		super();
		id = instanceCount++;
	}

	public Bank(String name) {
		id = instanceCount++;
		this.name = name;
	}

	public Bank(String name, String adddress, String zipCode, String phone) {
		id = instanceCount++;
		this.name = name;
		this.address = adddress;
		this.zipCode = zipCode;
		this.phone = phone;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		// this.customers = customers;
		// do nothing
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}





}
