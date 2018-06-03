package hu.keve.jgc.dao;

import java.time.LocalDateTime;
import java.util.Collection;

import hu.keve.jgc.util.Fraction;

public interface Split {

	String getGuid();
	
//	Transaction getTx();

	Account getAccount();

	String getMemo();

	String getAction();

	ReconciledStateTypes getReconcileState();

	LocalDateTime getReconcileDate();

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