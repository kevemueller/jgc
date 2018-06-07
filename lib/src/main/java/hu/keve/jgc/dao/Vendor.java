package hu.keve.jgc.dao;

public interface Vendor extends GuidType {
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
}