package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Lot;

public final class LotJDO extends AbstractGuidTypeJDO implements Lot {
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
