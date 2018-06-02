package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.TaxIncludedTypes;
import hu.keve.jgc.dao.Vendor;

@PersistenceCapable(table = "vendors")
public class VendorJDO implements Vendor {
	@PrimaryKey
	String guid;

	String name;
	String id;
	String notes;
	CommodityJDO currency;
	boolean active;
	boolean taxOverride;
	@Embedded(members = { @Persistent(name = "name", column = "addr_name"),
			@Persistent(name = "addr1", column = "addr_addr1"), @Persistent(name = "addr2", column = "addr_addr2"),
			@Persistent(name = "addr3", column = "addr_addr3"), @Persistent(name = "addr4", column = "addr_addr4"),
			@Persistent(name = "addrPhone", column = "addr_phone"), @Persistent(name = "addrFax", column = "addr_fax"),
			@Persistent(name = "addrEmail", column = "addr_email") })
	AddressJDO addr;
	BilltermJDO terms;
	@Column(name="tax_included")
	TaxIncludedTypes taxIncluded;
	@Column(name="tax_table")
	TaxtableJDO taxtable;
	@Element(column = "obj_guid")
	Collection<SlotJDO> slots;
	
	

	public String getGuid() {
		return guid;
	}
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
