package hu.keve.jgc.dao;

public interface Billterm extends GuidType {
	@Override
	default String getBusinessKey() {
		return getName();
	}

	/** getters **/
	String getName();

	String getDescription();

	long getRefcount();

	boolean isInvisible();

	Billterm getParent();

	// String getType();

	BilltermDays getDays();

	// int getCutoff();

}