package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Budget;

public final class BudgetJDO extends AbstractGuidTypeJDO implements Budget {
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

	@Override
	public Collection<RecurrenceJDO> getRecurrence() {
		return recurrences;
	}

	@Override
	public Collection<BudgetAmountJDO> getBudgetAmounts() {
		return budgetAmounts;
	}
}
