package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Lot;

@PersistenceCapable(table = "lots")
public class LotJDO implements Lot {
	@PrimaryKey
	String guid;

	@Column(name = "account_guid")
	AccountJDO account;

	boolean isClosed;

	@Element(column = "obj_guid")
	Collection<SlotJDO> slots;
	
	public String getGuid() {
		return guid;
	}

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
