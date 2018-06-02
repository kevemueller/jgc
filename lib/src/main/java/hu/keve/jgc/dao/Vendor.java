package hu.keve.jgc.dao;

import java.util.Collection;

public interface Vendor {
	String getGuid();
	
	String getName();

	String getId();

	String getNotes();

	Commodity getCurrency();

	boolean isActive();

	boolean isTaxOverride();

	Address getAddr();

	Billterm getTerms();

	TaxIncludedTypes getTaxIncluded();

	Taxtable getTaxtable();

	Collection<? extends Slot> getSlots();
}