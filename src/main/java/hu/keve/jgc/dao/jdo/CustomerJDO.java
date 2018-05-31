package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Customer;
import hu.keve.jgc.dao.TaxIncludedTypes;
import hu.keve.jgc.util.Fraction;

@PersistenceCapable(table = "customers")
public class CustomerJDO implements Customer {
	@PrimaryKey
	String guid;

	String name;
	String id;
	String notes;
	boolean active;
	
	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "discount_num"),
			@Persistent(name = "denom", column = "discount_denom") })
	Fraction discount;	

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "credit_num"),
			@Persistent(name = "denom", column = "credit_denom") })
	Fraction credit;	
	
	CommodityJDO currency;
	boolean taxOverride;
	@Embedded(members = { @Persistent(name = "name", column = "addr_name"),
			@Persistent(name = "addr1", column = "addr_addr1"), @Persistent(name = "addr2", column = "addr_addr2"),
			@Persistent(name = "addr3", column = "addr_addr3"), @Persistent(name = "addr4", column = "addr_addr4"),
			@Persistent(name = "addrPhone", column = "addr_phone"), @Persistent(name = "addrFax", column = "addr_fax"),
			@Persistent(name = "addrEmail", column = "addr_email") })
	AddressJDO addr;

	@Embedded(members = { @Persistent(name = "name", column = "shipaddr_name"),
			@Persistent(name = "addr1", column = "shipaddr_addr1"),
			@Persistent(name = "addr2", column = "shipaddr_addr2"),
			@Persistent(name = "addr3", column = "shipaddr_addr3"),
			@Persistent(name = "addr4", column = "shipaddr_addr4"),
			@Persistent(name = "addrPhone", column = "shipaddr_phone"),
			@Persistent(name = "addrFax", column = "shipaddr_fax"),
			@Persistent(name = "addrEmail", column = "shipaddr_email") })
	AddressJDO shipaddr;

	BilltermJDO terms;
	TaxIncludedTypes taxIncluded;
	TaxtableJDO taxtable;
	
	
	public String getGuid() {
		return guid;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getId()
	 */
	@Override
	public String getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getNotes()
	 */
	@Override
	public String getNotes() {
		return notes;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getDiscount()
	 */
	@Override
	public Fraction getDiscount() {
		return discount;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getCredit()
	 */
	@Override
	public Fraction getCredit() {
		return credit;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getCurrency()
	 */
	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#isTaxOverride()
	 */
	@Override
	public boolean isTaxOverride() {
		return taxOverride;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getAddr()
	 */
	@Override
	public AddressJDO getAddr() {
		return addr;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getShipaddr()
	 */
	@Override
	public AddressJDO getShipaddr() {
		return shipaddr;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getTerms()
	 */
	@Override
	public BilltermJDO getTerms() {
		return terms;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#isTaxIncluded()
	 */
	@Override
	public TaxIncludedTypes getTaxIncluded() {
		return taxIncluded;
	}
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Customer#getTaxtable()
	 */
	@Override
	public TaxtableJDO getTaxtable() {
		return taxtable;
	}
		
}
