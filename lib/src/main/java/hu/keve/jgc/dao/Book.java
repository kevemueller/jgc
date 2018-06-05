package hu.keve.jgc.dao;

import java.util.Collection;

import hu.keve.jgc.JgcVisitor;
import hu.keve.jgc.Visitable;
import hu.keve.jgc.dao.Account.AccountTypes;

public interface Book extends Visitable {
	String getGuid();
	
	Account getRootAccount();

	Account getRootTemplate();
	
	@Override
	default void visit(JgcVisitor visitor, Object context) {
		for (Commodity commodity : getAllCommodities()) {
			visitor.commodity(commodity, context);			
		}
		for (Account account : getAllAccounts()) {
			visitor.account(account, context);
		}
	}

	Iterable<? extends Commodity> getAllCommodities();

	Iterable<? extends Account> getAllAccounts();

	Collection<? extends Slot> getSlots();

	Commodity createCommodity(String space, String mnemonic);

	Account createAccount(Account parent, String name, AccountTypes type, Commodity commodity);

}