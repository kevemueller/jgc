package hu.keve.jgc.dao;

import java.time.LocalDateTime;
import java.util.Collection;

public interface Transaction {
	public String getGuid();

	public Commodity getCurrency();

	public String getNum();

	public LocalDateTime getDatePosted();

	public LocalDateTime getDateEntered();

	public String getDescription();

	public Collection<? extends Slot> getSlots();

	public Collection<? extends Split> getSplits();
}