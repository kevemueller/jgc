package hu.keve.jgc.dao;

public enum TaxIncludedTypes {
	YES, NO, USEGLOBAL;

	public String toValue() {
		return name();
	}

	public static String toValue(TaxIncludedTypes v) {
		return null == v ? null : v.toValue();
	}

	public static TaxIncludedTypes fromValue(String v) {
		return null == v ? null : valueOf(v);
	}
}