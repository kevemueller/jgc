package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Account;

public final class AccountJDO extends GuidTypeJDO implements Account {
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

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#getAccountType()
	 */
	@Override
	public AccountTypes getAccountType() {
		return accountType;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#getCommodity()
	 */
	@Override
	public CommodityJDO getCommodity() {
		return commodity;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#getCommodityScu()
	 */
	@Override
	public Long getCommodityScu() {
		return (long)commodityScu;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#isNonStdScu()
	 */
	@Override
	public boolean isSetNonStandardScu() {
		return nonStdScu;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#getParent()
	 */
	@Override
	public AccountJDO getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#getCode()
	 */
	@Override
	public String getCode() {
		return code;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#getDescription()
	 */
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

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Account#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}
}
