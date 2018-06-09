package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Billterm;

public class BilltermJDO extends AbstractGuidTypeJDO implements Billterm {
	public static final String NAME = "billterms";
	public static final int VERSION = 2;
	
	String name;
	String description;
	int refcount;
	boolean invisible;
	BilltermJDO parent;
	String type;

	BtDaysJDO days;
	Integer cutoff;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Billterm#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Billterm#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Billterm#getRefcount()
	 */
	@Override
	public long getRefcount() {
		return refcount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Billterm#isInvisible()
	 */
	@Override
	public boolean isInvisible() {
		return invisible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Billterm#getParent()
	 */
	@Override
	public BilltermJDO getParent() {
		return parent;
	}

	public String getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Billterm#getDays()
	 */
	@Override
	public BtDaysJDO getDays() {
		return days;
	}

	public int getCutoff() {
		return cutoff;
	}

}
