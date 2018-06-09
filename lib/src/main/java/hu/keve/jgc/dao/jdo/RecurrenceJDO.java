package hu.keve.jgc.dao.jdo;

import java.time.LocalDate;

import hu.keve.jgc.dao.Recurrence;

public final class RecurrenceJDO implements Recurrence {
	public static final String NAME = "recurrences";
	public static final int VERSION = 2;

	String obj;
	int mult;

	RecurrencePeriodTypes periodType;
	LocalDate periodStart;
	WeekendAdjustTypes weekendAdjust;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Recurrence#getRecurrenceMult()
	 */
	@Override
	public int getMult() {
		return mult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Recurrence#getRecurrencePeriodType()
	 */
	@Override
	public RecurrencePeriodTypes getPeriodType() {
		return periodType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Recurrence#getRecurrencePeriodStart()
	 */
	@Override
	public LocalDate getPeriodStart() {
		return periodStart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Recurrence#getRecurrenceWeekendAdjust()
	 */
	@Override
	public WeekendAdjustTypes getWeekendAdjust() {
		return weekendAdjust;
	}

}
