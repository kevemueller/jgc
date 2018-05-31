package hu.keve.jgc.dao;

import java.util.Collection;
import java.util.Date;

import hu.keve.jgc.dao.Price.PriceTypes;
import hu.keve.jgc.util.Fraction;

public interface Split {

//	Transaction getTx();

	Account getAccount();

	String getMemo();

	String getAction();

	ReconciledStateTypes getReconcileState();

	Date getReconcileDate();

	Fraction getValue();

	Fraction getQuantity();

	Collection<? extends Slot> getSlots();

	Lot getLot();

	public static enum ReconciledStateTypes {
		Y, N, C, F, V;

		public String toValue() {
			return name().toLowerCase();
		}

		public static String toValue(ReconciledStateTypes v) {
			return null == v ? null : v.toValue();
		}

		public static ReconciledStateTypes fromValue(String v) {
			return null == v ? null : Enum.valueOf(ReconciledStateTypes.class, v.toUpperCase());
		}
	}
}