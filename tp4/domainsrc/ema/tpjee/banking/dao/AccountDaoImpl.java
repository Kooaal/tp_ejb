package ema.tpjee.banking.dao;

import java.util.Iterator;
import java.util.List;

import ema.tpjee.banking.model.BankAccount;
import ema.tpjee.banking.model.Customer;

/**
 * 
 * @author pfister
 * 
 *
 */
public class AccountDaoImpl implements AccountDao {
// TODO implémenter cette classe
	
	public BankAccount clone(BankAccount cnt) {
		// TODO Auto-generated method stub
		return null;
	}

	public BankAccount clone(Customer c, BankAccount cnt) {
		// TODO Auto-generated method stub
		return null;
	}

	public BankAccount create(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	public BankAccount delete(Customer c, BankAccount cnt) {
		// TODO Auto-generated method stub
		return null;
	}

	public BankAccount find(Customer c, int id) {
		for (Iterator<BankAccount> i = getList(c).iterator(); i.hasNext();) {
			BankAccount current = i.next();
			if (current.getId() == id)
				return current;
		}
		return null;
	}

	public BankAccount first(Customer c) {
		return getList(c).get(0);
	}

	public BankAccount foobar(Customer c, BankAccount cnt) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BankAccount> getList(Customer c) {
		return c.getAccounts();
	}

	public BankAccount last(Customer c) {
		return getList(c).get(getList(c).size()-1);
	}

	public BankAccount merge(Customer c, BankAccount cnt) {
		BankAccount result = find(c,cnt.getId());
		result.setAccountNumber(cnt.getAccountNumber());
		result.setBalance(cnt.getBalance());
		result.setBank(cnt.getBank());
		result.setInterestRate(cnt.getInterestRate());
		return result;
	}

	public BankAccount next(Customer c, BankAccount cnt) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BankAccount> populate(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	public BankAccount prior(Customer c, BankAccount cnt) {
		// TODO Auto-generated method stub
		return null;
	}

}
