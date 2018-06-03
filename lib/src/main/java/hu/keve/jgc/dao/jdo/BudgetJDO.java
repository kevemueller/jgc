package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import hu.keve.jgc.dao.Budget;

public final class BudgetJDO extends GuidTypeJDO implements Budget {
	String name;
	String description;
	int numPeriods;

	Collection<RecurrenceJDO> recurrences;
	Collection<BudgetAmountJDO> budgetAmounts;
	Collection<SlotJDO> slots;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Budget#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Budget#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Budget#getNumPeriods()
	 */
	@Override
	public int getNumPeriods() {
		return numPeriods;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Budget#getRecurrence()
	 */
	@Override
	public Collection<RecurrenceJDO> getRecurrence() {
		return recurrences;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Budget#getBudgetAmounts()
	 */
	@Override
	public Collection<BudgetAmountJDO> getBudgetAmounts() {
		return budgetAmounts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Budget#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

}
