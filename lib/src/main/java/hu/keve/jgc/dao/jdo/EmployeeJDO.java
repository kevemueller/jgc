package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Employee;
import hu.keve.jgc.util.Fraction;

public final class EmployeeJDO extends GuidTypeJDO implements Employee {
	String username;
	String id;
	String language;
	String acl;
	boolean active;

	CommodityJDO currency;
	AccountJDO ccard;

	Fraction workday;
	Fraction rate;

	AddressJDO addr;

	Collection<SlotJDO> slots;


	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getLanguage()
	 */
	@Override
	public String getLanguage() {
		return language;
	}

	public String getAcl() {
		return acl;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getCurrency()
	 */
	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getCcard()
	 */
	@Override
	public AccountJDO getCcard() {
		return ccard;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getWorkday()
	 */
	@Override
	public Fraction getWorkday() {
		return workday;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getRate()
	 */
	@Override
	public Fraction getRate() {
		return rate;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getAddr()
	 */
	@Override
	public AddressJDO getAddr() {
		return addr;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

}
