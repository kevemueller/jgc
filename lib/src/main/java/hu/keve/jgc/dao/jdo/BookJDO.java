package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.Commodity;

public final class BookJDO extends GuidTypeJDO implements Book {	
	AccountJDO rootAccount;
	AccountJDO rootTemplate;

	Collection<SlotJDO> slots;
	
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BookI#getRootAccount()
	 */
	@Override
	public AccountJDO getRootAccount() {
		return rootAccount;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BookI#getRootTemplate()
	 */
	@Override
	public AccountJDO getRootTemplate() {
		return rootTemplate;
	}
	
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BookI#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}
	
	@Override
	public Commodity createCommodity(String space, String mnemonic) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Account createAccount(Account parent, String name, AccountTypes type, Commodity commodity) {
		// TODO Auto-generated method stub
		return null;
	}
}
