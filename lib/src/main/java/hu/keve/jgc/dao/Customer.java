package hu.keve.jgc.dao;

import hu.keve.jgc.util.Fraction;

public interface Customer extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	String getName();

	String getId();

	String getNotes();

	boolean isActive();

	Fraction getDiscount();

	Fraction getCredit();

	Commodity getCurrency();

	boolean isTaxOverride();

	Address getAddr();

	Address getShipaddr();

	Billterm getTerms();

	TaxIncludedTypes getTaxIncluded();

	Taxtable getTaxtable();

}