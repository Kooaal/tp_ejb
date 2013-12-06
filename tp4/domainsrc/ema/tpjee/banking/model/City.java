package ema.tpjee.banking.model;

import java.io.Serializable;

public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8670891852570175109L;
	int id;
	String zipCode;
	String name;
	String country = "France";
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public City(int id, String name, String zipCode, String country) {
		super();
		this.id = id;
		this.zipCode = zipCode;
		this.name = name;
		this.country = country;
	}

	public String toString() {
		return id + " " + name + " " + zipCode + " " + country;
	}
}
