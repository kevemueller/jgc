package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;

import hu.keve.jgc.dao.Account;

@PersistenceCapable(table = "accounts")
public final class AccountJDO extends GuidTypeJDO implements Account {
	String name;
	AccountTypes accountType;
	@Column(name = "commodity_guid")
	CommodityJDO commodity;
	long commodityScu;
	boolean nonStdScu;
	@Column(name = "parent_guid")
	AccountJDO parent;
	String code;
	String description;
	boolean hidden;
	boolean placeholder;

	@Element(column = "obj_guid")
	Collection<SlotJDO> slots;

	@Element(column = "account_guid", mappedBy = "account")
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
		return commodityScu;
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
