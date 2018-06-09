package hu.keve.jgc.dao;

import java.time.LocalDateTime;
import java.util.Collection;

public interface Transaction extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Commodity getCurrency();

	public String getNum();

	public LocalDateTime getDatePosted();

	public LocalDateTime getDateEntered();

	public String getDescription();

	public Collection<? extends Split> getSplits();
}