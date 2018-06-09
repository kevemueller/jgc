package hu.keve.jgc.dao;

public interface Budget extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	String getName();

	String getDescription();

	int getNumPeriods();

//TODO:	Collection<? extends Recurrence> getRecurrence();

//TODO:	Collection<? extends BudgetAmount> getBudgetAmounts();
}