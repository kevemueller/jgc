package hu.keve.jgc.dao;

import java.util.Collection;

public interface Budget extends GuidType {

	String getName();

	String getDescription();

	int getNumPeriods();

	Collection<? extends Recurrence> getRecurrence();

	Collection<? extends BudgetAmount> getBudgetAmounts();
}