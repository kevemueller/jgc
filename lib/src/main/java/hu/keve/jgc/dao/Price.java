package hu.keve.jgc.dao;

import java.time.LocalDateTime;

import hu.keve.jgc.util.Fraction;

public interface Price extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	Commodity getCommodity();

	Commodity getCurrency();

	LocalDateTime getDate();

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