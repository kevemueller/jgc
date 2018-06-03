package hu.keve.jgc.dao;

public enum TaxIncludedTypes {
	YES(1), NO(2), USEGLOBAL(3);
	private final int intValue;

	private TaxIncludedTypes(final int intValue) {
		this.intValue = intValue;
	}

	public String toValue() {
		return name();
	}

	public int toIntValue() {
		return intValue;
	}

	public static String toValue(TaxIncludedTypes v) {
		return null == v ? null : v.toValue();
	}

	public static TaxIncludedTypes fromValue(String v) {
		return null == v ? null : valueOf(v);
	}
}