package hu.keve.jgc.util;

public class Fraction {
	long num;
	long denom;

	public Fraction() {
	}

	public Fraction(int num) {
		this(num, 1);
	}

	public Fraction(final int num, final int denom) {
		this.num = num;
		this.denom = denom;
	}

	@Override
	public String toString() {
		return "" + num + "/" + denom;
	}

	public static Fraction fromValue(String id) {
		int i = id.indexOf('/');
		if (-1 == i) {
			return new Fraction(Integer.valueOf(id));
		} else {
			return new Fraction(Integer.valueOf(id.substring(0, i), Integer.valueOf(id.substring(i + 1))));
		}
	}

	public static String toValue(Fraction id) {
		return null == id ? null : id.toString();
	}
}
