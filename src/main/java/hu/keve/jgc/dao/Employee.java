package hu.keve.jgc.dao;

import java.util.Collection;

import hu.keve.jgc.util.Fraction;

public interface Employee {

	String getGuid();

	String getUsername();

	String getId();

	String getLanguage();

//	String getAcl();

	boolean isActive();

	Commodity getCurrency();

	Account getCcard();

	Fraction getWorkday();

	Fraction getRate();

	Address getAddr();

	Collection<? extends Slot> getSlots();
}