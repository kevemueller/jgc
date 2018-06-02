package hu.keve.jgc.dao.jdo;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Convert;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Recurrence;
import hu.keve.jgc.util.GCDateStringConverter;

@PersistenceCapable(table = "recurrences")
public final class RecurrenceJDO implements Recurrence {
	@PrimaryKey
	int id;

	String objGuid;
	@Column(name="recurrence_mult")
	short mult;

	@Column(name="recurrence_period_type")
	@Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "toValue")
	RecurrencePeriodTypes periodType;
	
	@Column(name="recurrence_period_start")
	@Convert(GCDateStringConverter.class)
	Date periodStart;
	
	@Column(name="recurrence_weekend_adjust")
	@Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "toValue")
	WeekendAdjustTypes weekendAdjust;


	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Recurrence#getRecurrenceMult()
	 */
	@Override
	public short getMult() {
		return mult;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Recurrence#getRecurrencePeriodType()
	 */
	@Override
	public RecurrencePeriodTypes getPeriodType() {
		return periodType;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Recurrence#getRecurrencePeriodStart()
	 */
	@Override
	public Date getPeriodStart() {
		return periodStart;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Recurrence#getRecurrenceWeekendAdjust()
	 */
	@Override
	public WeekendAdjustTypes getWeekendAdjust() {
		return weekendAdjust;
	}

}
