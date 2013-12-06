package ema.tpjee.banking.model;

import java.io.Serializable;

public class History implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3207935403717783933L;

	private int id;
	
	private int transaction;

	private String msg;
	
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
	

	public History() {
		transaction = 0;
	}

	public int getTransaction() {
		return transaction;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String val) {
		this.msg = val;
	}

	public void incrementTransaction() {
		transaction++;
		setMsg("   Transaction count is " + transaction);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTransaction(int transaction) {
		//this.transaction = transaction;
	}

}
