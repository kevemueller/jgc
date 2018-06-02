package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;

import hu.keve.jgc.dao.Budget;

@PersistenceCapable(table = "budgets")
public final class BudgetJDO extends GuidTypeJDO implements Budget {	
	String name;
	String description;
	int numPeriods;

	@Element(column="obj_guid")
	Collection<RecurrenceJDO> recurrence;

	@Element(column="budget_guid")
	Collection<BudgetAmountJDO> budgetAmounts;	

	@Element(column="obj_guid")
	Collection<SlotJDO> slots;

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Budget#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Budget#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Budget#getNumPeriods()
	 */
	@Override
	public int getNumPeriods() {
		return numPeriods;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Budget#getRecurrence()
	 */
	@Override
	public Collection<RecurrenceJDO> getRecurrence() {
		return recurrence;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Budget#getBudgetAmounts()
	 */
	@Override
	public Collection<BudgetAmountJDO> getBudgetAmounts() {
		return budgetAmounts;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Budget#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

}
