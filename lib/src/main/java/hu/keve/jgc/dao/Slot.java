package hu.keve.jgc.dao;

public interface Slot {

	String getName();

	
	public static enum SlotType {
		INTEGER(1), DOUBLE(2), NUMERIC(3), STRING(4), GUID(5), TIMESPEC(6), LIST(-7), FRAME(9), GDATE(10), BINARY(-11);
		private int intValue;

		private SlotType(final int intValue) {
			this.intValue = intValue;
		}

		public String toValue() {
			return name().toLowerCase();
		}

		public int toIntValue() {
			return intValue;
		}

		public static SlotType fromValue(int dbValue) {
			for (SlotType tte : values()) {
				if (dbValue == tte.intValue) {
					return tte;
				}
			}
			throw new IllegalArgumentException();
		}

		public static SlotType fromValue(String xmlValue) {
			return null == xmlValue ? null : Enum.valueOf(SlotType.class, xmlValue.toUpperCase());
		}
	}
	
}