package hu.keve.jgc.dao;

import hu.keve.jgc.util.Fraction;

public interface Employee extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	String getUsername();

	String getId();

	String getLanguage();

	// String getAcl();

	boolean isActive();

	Commodity getCurrency();

	Account getCcard();

	Fraction getWorkday();

	Fraction getRate();

	Address getAddr();
}