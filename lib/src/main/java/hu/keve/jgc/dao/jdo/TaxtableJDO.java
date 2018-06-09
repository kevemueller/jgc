package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Taxtable;

public class TaxtableJDO extends AbstractGuidTypeJDO implements Taxtable {
	public static final String NAME = "taxtables";
	public static final int VERSION = 2;
	
	String name;
	long refcount;
	boolean invisible;
	TaxtableJDO parent;

	Collection<TaxtableEntryJDO> entries;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getRefcount()
	 */
	@Override
	public long getRefcount() {
		return refcount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Taxtable#isInvisible()
	 */
	@Override
	public boolean isInvisible() {
		return invisible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getParent()
	 */
	@Override
	public TaxtableJDO getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getTaxtableEntries()
	 */
	@Override
	public Collection<TaxtableEntryJDO> getEntries() {
		return entries;
	}

}
