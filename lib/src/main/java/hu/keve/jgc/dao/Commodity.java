package hu.keve.jgc.dao;

import java.util.Currency;

public interface Commodity extends GuidType {
	/** business equals/key **/
//	default boolean equalsUnique(Commodity other) {
//		return null != other && Objects.equals(getNamespace(), other.getNamespace())
//				&& Objects.equals(getMnemonic(), other.getMnemonic());
//	}

	@Override
	default String getBusinessKey() {
		return String.join("/", getNamespace(), getMnemonic());
	}

	/** getters **/
	String getNamespace();

	String getMnemonic();

	String getFullname();

	String getCusip();

	long getFraction();

	boolean isSetQuoteFlag();

	String getQuoteSource();

	String getQuoteTz();

	/** setters **/
	void setFullname(String fullname);

	void setCusip(String cusip);

	void setFraction(long fraction);

	void setQuoteFlag(boolean quoteFlag);

	void setQuoteSource(String quoteSource);

	void setQuoteTz(String quoteTz);

	/** convenience setters **/
	default void setCurrency(Currency currency) {
		if (!"CURRENCY".equals(getNamespace()) || !currency.getCurrencyCode().equals(getMnemonic())) {
			throw new IllegalArgumentException();
		}
		setFullname(currency.getDisplayName());
		setCusip(currency.getNumericCodeAsString());
		setFraction((long) Math.pow(10, currency.getDefaultFractionDigits()));
		setQuoteFlag(true);
		setQuoteSource("currency");
		setQuoteTz("");
	}

}