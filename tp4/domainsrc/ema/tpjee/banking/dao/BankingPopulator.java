package ema.tpjee.banking.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ema.tpjee.banking.model.Bank;
import ema.tpjee.banking.model.Customer;
import ema.tpjee.utils.RandomString;

public class BankingPopulator {
	
	static Random rand = new Random();
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	public static List<Customer> populate(Bank b) {
		//TODO  il faudrait utiliser le DAO pour cela
		List<Customer> cts = new ArrayList<Customer>();
		Customer c = new Customer("Internet Customer", "10, avenue de la Californie", "13000",
				"7890123");
		//c.setBank(b);
		b.addCustomer(c);
		cts.add(c);
		Customer c2 = new Customer("Foobar Customer", "11, avenue du Nevada", "14000",
		"89652147");
		b.addCustomer(c2);
		cts.add(c2);
		return cts;
		//return getList(b);
	}

	
	public static List<Bank> populate() {
		//TODO  il faudrait utiliser le DAO pour cela
		
		List<Bank> bqs = new ArrayList<Bank>();

		bqs.add(new Bank("Credit Arboricole", "3, rue des sapins", "75000",
				"123456"));

		bqs.add(new Bank("Internet Bank", "10, avenue de la Californie", "13000",
				"7890123"));

		bqs.add(new Bank("Société Géniale", "1, parvis de la Défonse", "92400",
				"45698741"));

		for (int i = 0; i < 10; i++) {
			bqs.add(createRandom());
		}		
		return bqs;
	}


	private static Bank createRandom() {
		Bank b = new Bank();
		b.setName("Banque " + RandomString.generateStringAlpha(10));
		b.setAddress(rand.nextInt(100) + " rue "
				+ RandomString.generateStringAlpha(10));
		b.setPhone(rand.nextInt(100) + "." + rand.nextInt(100) + "."
				+ rand.nextInt(100) + "." + rand.nextInt(100) + "."
				+ rand.nextInt(100));
		b.setZipCode(Integer.toString(30000 + rand.nextInt(1000)));
		return b;
	}


}
