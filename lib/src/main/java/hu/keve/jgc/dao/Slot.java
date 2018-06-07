package hu.keve.jgc.dao;

public interface Slot {
	String getName();

	SlotTypes getType();

	Object getValue();

	public static enum SlotTypes {
		INTEGER(1), DOUBLE(2), NUMERIC(3), STRING(4), GUID(5), TIMESPEC(6), LIST(-7), FRAME(9), GDATE(10), BINARY(-11);
		private int intValue;

		private SlotTypes(final int intValue) {
			this.intValue = intValue;
		}

		public String toValue() {
			return name().toLowerCase();
		}

		public int toIntValue() {
			return intValue;
		}

		public static SlotTypes fromValue(int dbValue) {
			for (SlotTypes tte : values()) {
				if (dbValue == tte.intValue) {
					return tte;
				}
			}
			throw new IllegalArgumentException();
		}

		public static SlotTypes fromValue(String xmlValue) {
			return null == xmlValue ? null : Enum.valueOf(SlotTypes.class, xmlValue.toUpperCase());
		}
		
		public static String toValue(SlotTypes v) {
			return v.toValue();
		}
	}

}