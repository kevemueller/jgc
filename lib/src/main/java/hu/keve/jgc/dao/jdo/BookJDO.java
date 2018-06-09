package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;
import java.util.Currency;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.Commodity;
import hu.keve.jgc.dao.Transaction;
import hu.keve.jgc.util.jdo.GCDateTimeLocalDateTimeConverter;

public final class BookJDO extends AbstractGuidTypeJDO implements Book {
	public static final String NAME = "books";
	public static final int VERSION = 1;

	AccountJDO rootAccount;
	AccountJDO rootTemplate;

	@Override
	public AccountJDO getRootAccount() {
		return rootAccount;
	}

	@Override
	public AccountJDO getRootTemplate() {
		return rootTemplate;
	}

	@Override
	public Iterable<? extends Account> getAllAccounts() {
		return getPersistenceManager().getExtent(AccountJDO.class);
	}

	@Override
	public Iterable<? extends Commodity> getAllCommodities() {
		return getPersistenceManager().getExtent(CommodityJDO.class);
	}

	@Override
	public Iterable<? extends Transaction> getTransactionsBetween(LocalDateTime fromInclusive,
			LocalDateTime toExclusive) {
		String filter = "";
		Object[] parameters;
		if (null != fromInclusive) {
			if (null != toExclusive) {
				filter = "WHERE post_date BETWEEN ? AND ?";
				parameters = new String[] { GCDateTimeLocalDateTimeConverter.INSTANCE.convertToDatastore(fromInclusive),
						GCDateTimeLocalDateTimeConverter.INSTANCE.convertToDatastore(toExclusive) };
			} else {
				filter = "WHERE post_date >= ?";
				parameters = new String[] {
						GCDateTimeLocalDateTimeConverter.INSTANCE.convertToDatastore(fromInclusive) };
			}
		} else {
			if (null != toExclusive) {
				filter = "WHERE post_date < ?";
				parameters = new String[] { GCDateTimeLocalDateTimeConverter.INSTANCE.convertToDatastore(toExclusive) };
			} else {
				return getPersistenceManager().getExtent(TransactionJDO.class);
			}
		}
		Query<TransactionJDO> query = getPersistenceManager().newQuery("javax.jdo.query.SQL",
				"SELECT guid FROM transactions " + filter);
		query.setClass(TransactionJDO.class);
		return query.setParameters(parameters).executeList();
	}

	/** special setters **/

	void setRootAccount(AccountJDO rootAccount) {
		this.rootAccount = rootAccount;
	}

	void setRootTemplate(AccountJDO rootTemplate) {
		this.rootTemplate = rootTemplate;
	}

	/** creators **/

	@Override
	public CommodityJDO createCommodity(Currency currency) {
		CommodityJDO commodity = new CommodityJDO();
		commodity.newGuid();
		commodity.setNamespace("CURRENCY");
		commodity.setMnemonic(currency.getCurrencyCode());
		commodity.setCurrency(currency);
		return mergeCommodity(commodity);
	}

	private CommodityJDO mergeCommodity(CommodityJDO commodity) {
		String bk = commodity.getBusinessKey();
		PersistenceManager pm = getPersistenceManager();
		Extent<CommodityJDO> commodities = pm.getExtent(CommodityJDO.class);
		for (CommodityJDO c : commodities) {
			if (c.getBusinessKey().equals(bk)) {
				// merge callback
				return c;
			}
		}
		return pm.makePersistent(commodity);
	}

	@Override
	public CommodityJDO createCommodity(String namespace, String mnemonic) {
		CommodityJDO commodity = new CommodityJDO();
		commodity.newGuid();
		commodity.setNamespace(namespace);
		commodity.setMnemonic(mnemonic);
		return mergeCommodity(commodity);
	}

	@Override
	public AccountJDO createAccount(Account parent, String name, AccountTypes type, Commodity commodity) {
		PersistenceManager pm = getPersistenceManager();
		AccountJDO account = new AccountJDO();
		account.newGuid();
		// account.setParent(parent);
		account.setName(name);
		account.setAccountType(type);
		account.setCommodity((CommodityJDO) commodity);
		return pm.makePersistent(account);
	}

}
