package ema.tpjee.banking.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ema.tpjee.banking.model.Bank;
import ema.tpjee.banking.model.City;
import ema.tpjee.utils.Paging;

public class BankDaoImpl implements BankDao, Serializable {

	private static final long serialVersionUID = -5443611226894567253L;
	private List<Bank> list = new ArrayList<Bank>();

	public BankDaoImpl() {
		super();
		System.out.println("creating BankDaoImpl");
	}

	public Bank add(Bank b) {
		list.add(b);
		;
		return b;
	}

	public List<Bank> populate() {
		List<Bank> bs = BankingPopulator.populate();
		list.addAll(bs);
		return list;
	}

	public Collection<Bank> reverseList() {
		List<Bank> rev = new ArrayList<Bank>();
		for (Iterator<Bank> i = list.iterator(); i.hasNext();) {
			Bank b = i.next();
			rev.add(b);
		}
		Collections.reverse(rev);
		return rev;
	}

	public Bank find(int id) {
		for (Iterator<Bank> i = list.iterator(); i.hasNext();) {
			Bank b = i.next();
			if (b.getId() == id)
				return b;
		}
		return null;
	}

	/**
	 * after deletion returns the previous if exists else the next if exists
	 * else null
	 */
	public Bank delete(Bank b) {
		Bank result = Paging.prior(list, b);
		if (result == null)
			result = Paging.next(list, b);
		list.remove(b);
		return result;
	}

	public Bank first() {
		return list.iterator().next();
	}

	public Bank last() {
		return list.get(list.size() - 1);
	}

	private Bank copyBank(Bank b) {
		Bank newb = new Bank();
		if (b.getAddress() != null)
			newb.setAddress(b.getAddress());
		if (b.getName() != null)
			newb.setName(b.getName());
		if (b.getPhone() != null)
			newb.setPhone(b.getPhone());
		newb.setZipCode(b.getZipCode());
		return newb;
	}

	public Bank clone(Bank b) {
		Bank cloned = copyBank(b);
		list.add(cloned);
		return cloned;
	}

	public Bank foobar(Bank b) {
		// if (b.getZipCode()>150000)
		// throw new RuntimeException("foobar is too big!");
		return b;
	}

	public Bank save(Bank b) {
		Bank bb = find(b.getId());
		if (bb != null) {
			if (b.getAddress() != null)
				bb.setAddress(b.getAddress());
			if (b.getName() != null)
				bb.setName(b.getName());
			if (b.getPhone() != null)
				bb.setPhone(b.getPhone());
			bb.setZipCode(b.getZipCode());
		} else
			throw new RuntimeException("la modification de " + b + " a échoué");
		return bb;
	}

	public Bank next(Bank b) {
		return Paging.next(list, b);
	}

	public Bank prior(Bank b) {
		return Paging.prior(list, b);
	}

	public Bank create() {
		Bank b = new Bank();
		b.setName("nouvelle banque " + b.getId());
		list.add(b);
		return b;
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}

	public List<Bank> getList() {
		return list;
	}

	public void setList(List<Bank> banks) {

	}

	public Bank merge(Bank b) {
		return save(b);
	}

}
