package hu.keve.jgc.dao;

public interface Account extends GuidType {
	String getName();

	AccountTypes getAccountType();

	Commodity getCommodity();

	Long getCommodityScu();

	boolean isSetNonStandardScu();

	Account getParent();

	String getCode();

	String getDescription();

	// boolean isPlaceholder();

	Iterable<? extends Account> getChildren();

	public static enum AccountTypes {
		NONE, BANK, CASH, CREDIT, ASSET, LIABILITY, STOCK, MUTUAL, CURRENCY, INCOME, EXPENSE, EQUITY, RECEIVABLE, PAYABLE, ROOT, TRADING, CHECKING, SAVINGS, MENYMRKT, CREDITLINE;

		public String toValue() {
			return name();
		}

		public static String toValue(AccountTypes v) {
			return null == v ? null : v.toValue();
		}

		public static AccountTypes fromValue(String v) {
			return null == v ? null : valueOf(v);
		}
	}
}