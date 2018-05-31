package hu.keve.jgc.dao;

public interface Owner {
	public OwnerType getType();

	public static enum OwnerType {
		GNC_OWNER_NONE(null, 0), GNC_OWNER_UNDEFINED(null, 1), GNC_OWNER_CUSTOMER("gncCustomer",
				2), GNC_OWNER_JOB("gncJob", 3), GNC_OWNER_VENDOR("gncVendor", 4), GNC_OWNER_EMPLOYEE("gncEmployee", 5);
		private final String value;
		private final int intValue;

		private OwnerType(final String value, final int intValue) {
			this.value = value;
			this.intValue = intValue;
		}

		public String toValue() {
			return value;
		}

		public int toIntValue() {
			return intValue;
		}

		public static OwnerType fromValue(String v) {
			if (null == v) {
				return null;
			}
			for (OwnerType c : OwnerType.values()) {
				if (c.toValue().equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static String toValue(OwnerType v) {
			return null == v ? null : v.toValue();
		}
	}
}