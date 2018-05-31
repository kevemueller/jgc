package hu.keve.jgc.dao;

public interface Billterm {

	String getGuid();

	String getName();

	String getDescription();

	long getRefcount();

	boolean isInvisible();

	Billterm getParent();

//	String getType();

	BilltermDays getDays();

//	int getCutoff();

}