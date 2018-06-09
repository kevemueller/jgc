package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.TaxIncludedTypes;
import hu.keve.jgc.dao.Vendor;

public final class VendorJDO extends AbstractGuidTypeJDO implements Vendor {
	public static final String NAME = "vendors";
	public static final int VERSION = 1;
	
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public boolean isTaxOverride() {
		return taxOverride;
	}

	@Override
	public AddressJDO getAddr() {
		return addr;
	}

	@Override
	public BilltermJDO getTerms() {
		return terms;
	}

	@Override
	public TaxIncludedTypes getTaxIncluded() {
		return taxIncluded;
	}

	@Override
	public TaxtableJDO getTaxtable() {
		return taxtable;
	}
}
