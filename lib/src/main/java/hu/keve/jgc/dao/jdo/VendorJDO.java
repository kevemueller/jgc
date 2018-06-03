package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.TaxIncludedTypes;
import hu.keve.jgc.dao.Vendor;

public final class VendorJDO extends GuidTypeJDO implements Vendor {
	String name;
	String id;
	String notes;
	CommodityJDO currency;
	boolean active;
	boolean taxOverride;
	AddressJDO addr;
	BilltermJDO terms;
	TaxIncludedTypes taxIncluded;
	TaxtableJDO taxtable;
	Collection<SlotJDO> slots;
	
	
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getId()
	 */
	@Override
	public String getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getNotes()
	 */
	@Override
	public String getNotes() {
		return notes;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getCurrency()
	 */
	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#isTaxOverride()
	 */
	@Override
	public boolean isTaxOverride() {
		return taxOverride;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getAddr()
	 */
	@Override
	public AddressJDO getAddr() {
		return addr;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getTerms()
	 */
	@Override
	public BilltermJDO getTerms() {
		return terms;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getTaxInc()
	 */
	@Override
	public TaxIncludedTypes getTaxIncluded() {
		return taxIncluded;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getTaxTable()
	 */
	@Override
	public TaxtableJDO getTaxtable() {
		return taxtable;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Vendor#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

	

}
