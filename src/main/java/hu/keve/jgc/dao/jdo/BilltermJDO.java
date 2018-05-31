package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Billterm;

@PersistenceCapable(table = "billterms")
public class BilltermJDO implements Billterm {
	@PrimaryKey
	String guid;

	String name;
	String description;
	long refcount;
	boolean invisible;
	BilltermJDO parent;
	String type;

	// this is mapped in Billterm.jdo
	@Persistent(defaultFetchGroup = "true")
	BtDaysJDO days;
	int cutoff;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Billterm#getGuid()
	 */
	@Override
	public String getGuid() {
		return guid;
	}

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
