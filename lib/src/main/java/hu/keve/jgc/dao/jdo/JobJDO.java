package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Job;

public class JobJDO extends AbstractGuidTypeJDO implements Job {
	public static final String NAME = "jobs";
	public static final int VERSION = 1;
	
	String id;
	String name;
	String reference;
	boolean active;

	OwnerJDO owner;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Job#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Job#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Job#getReference()
	 */
	@Override
	public String getReference() {
		return reference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Job#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Job#getOwner()
	 */
	@Override
	public OwnerJDO getOwner() {
		return owner;
	}

}
