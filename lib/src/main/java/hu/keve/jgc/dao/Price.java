package hu.keve.jgc.dao;

import java.util.Date;

import hu.keve.jgc.util.Fraction;

public interface Price {
	
	String getGuid();

	Commodity getCommodity();

	Commodity getCurrency();

	Date getDate();

	String getSource();

	PriceTypes getType();

	Fraction getValue();

	public enum PriceTypes {
		BID, ASK, LAST, NAV, TRANSACTION, UNKNOWN;

		public String toValue() {
			return name().toLowerCase();
		}

		public static String toValue(PriceTypes v) {
			return null == v ? null : v.toValue();
		}

		public static PriceTypes fromValue(String v) {
			return null == v ? null : Enum.valueOf(PriceTypes.class, v.toUpperCase());
		}
	}
}