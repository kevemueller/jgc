package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Taxtable;

@PersistenceCapable(table = "taxtables")
public class TaxtableJDO implements Taxtable {
	@PrimaryKey
	String guid;

	String name;
	short refcount;
	boolean invisible;
	TaxtableJDO parent;
	
	@Column(name="taxtable_entries")
	@Element(column="taxtable")
	Collection<TaxtableEntryJDO> entries;

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getGuid()
	 */
	@Override
	public String getGuid() {
		return guid;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getRefcount()
	 */
	@Override
	public short getRefcount() {
		return refcount;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Taxtable#isInvisible()
	 */
	@Override
	public boolean isInvisible() {
		return invisible;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getParent()
	 */
	@Override
	public TaxtableJDO getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Taxtable#getTaxtableEntries()
	 */
	@Override
	public Collection<TaxtableEntryJDO> getEntries() {
		return entries;
	}		
	
}
