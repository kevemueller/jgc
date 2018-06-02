package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Employee;
import hu.keve.jgc.util.Fraction;

@PersistenceCapable(table = "employees")
public class EmployeeJDO implements Employee {
	@PrimaryKey
	String guid;

	String username;
	String id;
	String language;
	String acl;
	boolean active;

	CommodityJDO currency;
	@Column(name = "ccard_guid")
	AccountJDO ccard;

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "workday_num"),
			@Persistent(name = "denom", column = "workday_denom") })
	Fraction workday;

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "rate_num"),
			@Persistent(name = "denom", column = "rate_denom") })
	Fraction rate;

	@Embedded(members = { @Persistent(name = "name", column = "addr_name"),
			@Persistent(name = "addr1", column = "addr_addr1"), @Persistent(name = "addr2", column = "addr_addr2"),
			@Persistent(name = "addr3", column = "addr_addr3"), @Persistent(name = "addr4", column = "addr_addr4"),
			@Persistent(name = "addrPhone", column = "addr_phone"), @Persistent(name = "addrFax", column = "addr_fax"),
			@Persistent(name = "addrEmail", column = "addr_email") })
	AddressJDO addr;

	@Element(column = "obj_guid")
	Collection<SlotJDO> slots;

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Employee#getGuid()
	 */
	@Override
	public String getGuid() {
		return guid;
	}

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
