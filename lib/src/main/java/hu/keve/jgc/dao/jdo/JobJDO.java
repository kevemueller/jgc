package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Job;

@PersistenceCapable(table = "jobs")
public class JobJDO implements Job {
	@PrimaryKey
	String guid;

	String id;
	String name;
	String reference;
	boolean active;

	int ownerType;
	@Column(name = "owner_guid")
	OwnerJDO owner;
	
	
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Job#getGuid()
	 */
	@Override
	public String getGuid() {
		return guid;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Job#getId()
	 */
	@Override
	public String getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Job#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Job#getReference()
	 */
	@Override
	public String getReference() {
		return reference;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Job#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Job#getOwnerType()
	 */
	@Override
	public int getOwnerType() {
		return ownerType;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Job#getOwner()
	 */
	@Override
	public OwnerJDO getOwner() {
		return owner;
	}
	
	
	
}
