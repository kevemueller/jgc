package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.Commodity;

public final class BookJDO extends GuidTypeJDO implements Book {
	AccountJDO rootAccount;
	AccountJDO rootTemplate;

	Collection<SlotJDO> slots;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.BookI#getRootAccount()
	 */
	@Override
	public AccountJDO getRootAccount() {
		return rootAccount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.BookI#getRootTemplate()
	 */
	@Override
	public AccountJDO getRootTemplate() {
		return rootTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.BookI#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

	void setRootAccount(AccountJDO rootAccount) {
		this.rootAccount = rootAccount;
	}

	void setRootTemplate(AccountJDO rootTemplate) {
		this.rootTemplate = rootTemplate;
	}

	@Override
	public CommodityJDO createCommodity(String namespace, String mnemonic) {
		PersistenceManager pm = getPersistenceManager();
		CommodityJDO commodity = new CommodityJDO();
		commodity.setNamespace(namespace);
		commodity.setMnemonic(mnemonic);
		Extent<CommodityJDO> commodities = pm.getExtent(CommodityJDO.class);
		for (CommodityJDO c : commodities) {
			if (c.equalsUnique(commodity)) {
				return c;
			}
		}
		commodity.newGuid();
		return pm.makePersistent(commodity);
	}

	@Override
	public AccountJDO createAccount(Account parent, String name, AccountTypes type, Commodity commodity) {
		PersistenceManager pm = getPersistenceManager();
		AccountJDO account = new AccountJDO();
		account.newGuid();
		// account.setParent(parent);
		account.setName(name);
		account.setCommodity((CommodityJDO) commodity);
		return pm.makePersistent(account);
	}

}
