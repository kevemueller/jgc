package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Employee;
import hu.keve.jgc.util.Fraction;

public final class EmployeeJDO extends AbstractGuidTypeJDO implements Employee {
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

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	public String getAcl() {
		return acl;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	@Override
	public AccountJDO getCcard() {
		return ccard;
	}

	@Override
	public Fraction getWorkday() {
		return workday;
	}

	@Override
	public Fraction getRate() {
		return rate;
	}

	@Override
	public AddressJDO getAddr() {
		return addr;
	}
}
