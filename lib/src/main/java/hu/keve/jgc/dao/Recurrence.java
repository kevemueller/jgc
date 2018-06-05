package hu.keve.jgc.dao;

import java.time.LocalDate;

public interface Recurrence {

	int getMult();

	RecurrencePeriodTypes getPeriodType();

	LocalDate getPeriodStart();

	WeekendAdjustTypes getWeekendAdjust();

	
	public static enum RecurrencePeriodTypes {
		ONCE, DAY, WEEK, MONTH, END_OF_MONTH, NTH_WEEKDAY, LAST_WEEKDAY, YEAR;

		public String toValue() {
			return name().replace('_', ' ').toLowerCase();
		}

		public static String toValue(RecurrencePeriodTypes v) {
			return null == v ? null : v.toValue();
		}

		public static RecurrencePeriodTypes fromValue(String v) {
			return null == v ? null : Enum.valueOf(RecurrencePeriodTypes.class, v.replace(' ', '_').toUpperCase());
		}
	}

	public static enum WeekendAdjustTypes {
		NONE, BACK, FORWARD;
		
		public String toValue() {
			return name().toLowerCase();
		}

		public static String toValue(WeekendAdjustTypes v) {
			return null == v ? null : v.toValue();
		}

		public static WeekendAdjustTypes fromValue(String v) {
			return null == v ? null : Enum.valueOf(WeekendAdjustTypes.class, v.toUpperCase());
		}
		
	}

}