package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;

import hu.keve.jgc.dao.Order;

public class OrderJDO extends GuidTypeJDO implements Order {
	String id;
	String notes;
	String reference;
	boolean active;
	LocalDateTime dateOpened;
	LocalDateTime dateClosed;
	OwnerJDO owner;


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
	public LocalDateTime getDateOpened() {
		return dateOpened;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Order#getDateClosed()
	 */
	@Override
	public LocalDateTime getDateClosed() {
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
