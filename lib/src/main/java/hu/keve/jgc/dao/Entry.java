package hu.keve.jgc.dao;

import java.time.LocalDateTime;

import hu.keve.jgc.util.Fraction;

public interface Entry {

	LocalDateTime getDate();

	LocalDateTime getDateEntered();

	String getDescription();

	String getAction();

	String getNotes();

	Fraction getQuantity();

	Account getIAcct();

	Fraction getIPrice();

	Fraction getIDiscount();

	Invoice getInvoice();

	DiscTypes getIDiscType();

	DiscHowTypes getIDiscHow();

	Boolean isITaxable();

	Boolean isITaxincluded();

	Taxtable getITaxtable();

	Account getBAcct();

	Fraction getBPrice();

	Invoice getBill();

	Boolean isBTaxable();

	Boolean isBTaxincluded();

	Taxtable getBTaxtable();

	PayTypes getBPaytype();

	Boolean isBillable();

	Owner getBillTo();

	Order getOrder();

	public static enum DiscTypes {
		VALUE, PERCENT;

		public static String toValue(DiscTypes v) {
			return v.name();
		}

		public static DiscTypes fromValue(String v) {
			return Enum.valueOf(DiscTypes.class, v);
		}
	}

	public static enum DiscHowTypes {
		PRETAX, POSTTAX, SAMETIME;

		public static String toValue(DiscHowTypes v) {
			return v.name();
		}

		public static DiscHowTypes fromValue(String v) {
			return Enum.valueOf(DiscHowTypes.class, v);
		}
	}

	public static enum PayTypes {
		CASH(0), CARD(1);
		private int intValue;

		private PayTypes(final int intValue) {
			this.intValue = intValue;
		}

		public String toValue() {
			return name();
		}

		public int toIntValue() {
			return intValue;
		}

		public static String toValue(PayTypes v) {
			return v.name();
		}

		public static PayTypes fromValue(String v) {
			return Enum.valueOf(PayTypes.class, v);
		}

	}
}