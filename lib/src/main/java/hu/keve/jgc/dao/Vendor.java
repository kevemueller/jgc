package hu.keve.jgc.dao;

public interface Vendor extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
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