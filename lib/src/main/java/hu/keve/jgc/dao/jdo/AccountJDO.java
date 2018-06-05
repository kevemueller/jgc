package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Account;

public final class AccountJDO extends GuidTypeJDO implements Account {
	static final String NAME = "accounts";
	static final int VERSION = 1;

	String name;
	AccountTypes accountType;
	CommodityJDO commodity;
	int commodityScu;
	boolean nonStdScu;
	AccountJDO parent;
	String code;
	String description;
	Boolean hidden;
	Boolean placeholder;

	Collection<SlotJDO> slots;
	Collection<LotJDO> lots;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public AccountTypes getAccountType() {
		return accountType;
	}

	@Override
	public CommodityJDO getCommodity() {
		return commodity;
	}

	@Override
	public Long getCommodityScu() {
		return (long) commodityScu;
	}

	@Override
	public boolean isSetNonStandardScu() {
		return nonStdScu;
	}

	@Override
	public AccountJDO getParent() {
		return parent;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public boolean isHidden() {
		return hidden;
	}

	public boolean isPlaceholder() {
		return placeholder;
	}

	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

	@Override
	public Iterable<? extends Account> getChildren() {
		return getPersistenceManager().newQuery(AccountJDO.class, "parent == :self").setParameters(this)
				.executeList();
	}

	/*** Setters ***/
	void setParent(AccountJDO parent) {
		this.parent = parent;
	}

	void setName(String name) {
		this.name = name;
	}

	void setAccountType(AccountTypes accountType) {
		this.accountType = accountType;
	}

	void setCommodity(CommodityJDO commodity) {
		this.commodity = commodity;
	}
}
