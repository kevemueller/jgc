package hu.keve.jgc.dao;

import java.time.LocalDateTime;
import java.util.Collection;

public interface Transaction extends GuidType {
	public Commodity getCurrency();

	public String getNum();

	public LocalDateTime getDatePosted();

	public LocalDateTime getDateEntered();

	public String getDescription();

	public Collection<? extends Split> getSplits();
}