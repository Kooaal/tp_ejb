package ema.tpjee.banking.dao;

import java.util.Iterator;
import java.util.List;

import ema.tpjee.banking.model.Bank;
import ema.tpjee.banking.model.Customer;

//import ema.tpjee.banking.session.SessionFacade;

public class Test {
	public static void main(String[] a) {

		BankDao bdao = new BankDaoImpl();
		List<Bank> bks = bdao.populate();
		CustomerDao cdao = new CustomerDaoImpl();
		cdao.populate(bks.get(0));
		test(bks.get(0));
		List<Customer> csts = cdao.getList(bks.get(0));

		System.out.println("************ all banks");
		for (Bank b : bks) {
			System.out.println(b);
		}
		System.out.println("-----------------------");

		System.out.println("************ bank[4]");
		Bank b = bks.get(4);
		Customer newc = cdao.create(b);
		newc.setName("Durant");
		newc.setForName("Pierre");
		newc.setAddress("20 rue des lilas");
		newc.setZipCode("30000");
		System.out.println(b);
		System.out.println("-----------------------");

		System.out.println("************ new bank");

		Bank newb = bdao.create();
		newb.setName("Crédit Illimité");
		newb.setAddress("10 rue des euros");
		newb.setPhone("99 99 99 99 99");
		newb.setZipCode("34000");

		Customer newc1 = cdao.create(newb);
		newc1.setName("Dupont");
		newc1.setForName("Joe");
		newc1.setAddress("20 rue des roses");
		newc1.setZipCode("34000");
		System.out.println(newb);
		System.out.println("-----------------------");

		System.out.println("************ prior bank");
		Bank prev = bdao.prior(newb);
		System.out.println(prev);
		System.out.println("-----------------------");



	}

	private static void test(Bank bank) {
		// TODO Auto-generated method stub

	}

}
