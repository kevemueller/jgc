package hu.keve.jgc.dao.jdo;

import java.util.Date;

import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Order;

@PersistenceCapable(table = "orders")
public class OrderJDO implements Order {
	@PrimaryKey
	String guid;

	String id;
	String notes;
	String reference;
	boolean active;
	Date dateOpened;
	Date dateClosed;

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "type", column = "owner_type"),
			@Persistent(name = "guid", column = "owner_guid") })
	OwnerJDO owner;


	public String getGuid() {
		return guid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Order#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Order#getNotes()
	 */
	@Override
	public String getNotes() {
		return notes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Order#getReference()
	 */
	@Override
	public String getReference() {
		return reference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Order#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Order#getDateOpened()
	 */
	@Override
	public Date getDateOpened() {
		return dateOpened;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Order#getDateClosed()
	 */
	@Override
	public Date getDateClosed() {
		return dateClosed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Order#getOwner()
	 */
	@Override
	public OwnerJDO getOwner() {
		return owner;
	}

}
