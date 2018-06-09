package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.TaxtableEntry;
import hu.keve.jgc.util.Fraction;

public class TaxtableEntryJDO implements TaxtableEntry {
	public static final String NAME = "taxtable_entries";
	public static final int VERSION = 3;

	TaxtableJDO taxtable;
	AccountJDO account;
	Fraction amount;
	TaxtableEntryType type;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.TaxtableEntry#getAccount()
	 */
	@Override
	public AccountJDO getAccount() {
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.TaxtableEntry#getAmount()
	 */
	@Override
	public Fraction getAmount() {
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.TaxtableEntry#getType()
	 */
	@Override
	public TaxtableEntryType getType() {
		return type;
	}

}
