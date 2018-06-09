package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Lot;

public final class LotJDO extends AbstractGuidTypeJDO implements Lot {
	public static final String NAME = "lots";
	public static final int VERSION = 2;
	
	AccountJDO account;
	boolean isClosed;

	@Override
	public AccountJDO getAccount() {
		return account;
	}

	@Override
	public boolean isClosed() {
		return isClosed;
	}
}
