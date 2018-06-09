package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Budget;

public final class BudgetJDO extends AbstractGuidTypeJDO implements Budget {
	public static final String NAME = "budgets";
	public static final int VERSION = 1;

	
	String name;
	String description;
	int numPeriods;

	Collection<RecurrenceJDO> recurrences;
	Collection<BudgetAmountJDO> budgetAmounts;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getNumPeriods() {
		return numPeriods;
	}

//TODO:	@Override
	public Collection<RecurrenceJDO> getRecurrence() {
		return recurrences;
	}

//TODO:	@Override
	public Collection<BudgetAmountJDO> getBudgetAmounts() {
		return budgetAmounts;
	}
}
