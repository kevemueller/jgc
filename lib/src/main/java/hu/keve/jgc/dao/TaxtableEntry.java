package hu.keve.jgc.dao;

import hu.keve.jgc.util.Fraction;

public interface TaxtableEntry {

	Account getAccount();

	Fraction getAmount();

	TaxtableEntryType getType();

	public static enum TaxtableEntryType {
		VALUE(1), PERCENT(2);
		private int intValue;

		private TaxtableEntryType(int intValue) {
			this.intValue = intValue;
		}

		public String toValue() {
			return name();
		}

		public int toIntValue() {
			return intValue;
		}

		public static String toValue(TaxtableEntryType v) {
			return null == v ? null : v.name();
		}

		public static TaxtableEntryType fromValue(String v) {
			return null == v ? null : valueOf(v);
		}

		public static TaxtableEntryType fromValue(Integer v) {
			if (null == v) {
				return null;
			}
			for (TaxtableEntryType e : values()) {
				if (v == e.intValue) {
					return e;
				}
			}
			throw new IllegalArgumentException();
		}
	}
}