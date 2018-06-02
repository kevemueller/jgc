package hu.keve.jgc.dao;

import java.util.Collection;

import hu.keve.jgc.dao.Account.AccountTypes;

public interface Book {
	String getGuid();
	
	Account getRootAccount();

	Account getRootTemplate();

	Collection<? extends Slot> getSlots();

	Commodity createCommodity(String space, String mnemonic);

	Account createAccount(Account parent, String name, AccountTypes type, Commodity commodity);
}