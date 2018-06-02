package hu.keve.jgc.dao;

import java.util.Collection;

public interface Budget {

	String getName();

	String getDescription();

	int getNumPeriods();

	Collection<? extends Recurrence> getRecurrence();

	Collection<? extends BudgetAmount> getBudgetAmounts();

	Collection<? extends Slot> getSlots();
}