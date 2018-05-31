package hu.keve.jgc.dao;

import java.util.Collection;
import java.util.Date;

public interface Transaction {
	public String getGuid();

	public Commodity getCurrency();

	public String getNum();

	public Date getDatePosted();

	public Date getDateEntered();

	public String getDescription();

	public Collection<? extends Slot> getSlots();

	public Collection<? extends Split> getSplits();
}