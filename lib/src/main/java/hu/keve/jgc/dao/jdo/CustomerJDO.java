package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Customer;
import hu.keve.jgc.dao.TaxIncludedTypes;
import hu.keve.jgc.util.Fraction;

public class CustomerJDO extends AbstractGuidTypeJDO implements Customer {
	public static final String NAME = "customers";
	public static final int VERSION = 2;
	
	String name;
	String id;
	String notes;
	boolean active;

	Fraction discount;
	Fraction credit;

	CommodityJDO currency;
	boolean taxOverride;

	AddressJDO addr;
	AddressJDO shipaddr;

	BilltermJDO terms;
	TaxIncludedTypes taxIncluded;
	TaxtableJDO taxtable;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getNotes()
	 */
	@Override
	public String getNotes() {
		return notes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getDiscount()
	 */
	@Override
	public Fraction getDiscount() {
		return discount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getCredit()
	 */
	@Override
	public Fraction getCredit() {
		return credit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getCurrency()
	 */
	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#isTaxOverride()
	 */
	@Override
	public boolean isTaxOverride() {
		return taxOverride;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getAddr()
	 */
	@Override
	public AddressJDO getAddr() {
		return addr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getShipaddr()
	 */
	@Override
	public AddressJDO getShipaddr() {
		return shipaddr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getTerms()
	 */
	@Override
	public BilltermJDO getTerms() {
		return terms;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#isTaxIncluded()
	 */
	@Override
	public TaxIncludedTypes getTaxIncluded() {
		return taxIncluded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Customer#getTaxtable()
	 */
	@Override
	public TaxtableJDO getTaxtable() {
		return taxtable;
	}

}
