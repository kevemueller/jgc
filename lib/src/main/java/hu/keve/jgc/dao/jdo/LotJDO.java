package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Lot;

public final class LotJDO extends GuidTypeJDO implements Lot {
	AccountJDO account;
	boolean isClosed;

	Collection<SlotJDO> slots;
	
	public AccountJDO getAccount() {
		return account;
	}

	public boolean isClosed() {
		return isClosed;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Lot#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}
}
