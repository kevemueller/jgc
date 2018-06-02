package hu.keve.jgc.dao;

import java.util.Objects;

public interface Commodity {
	String getGuid();
	
	String getNamespace();

	String getMnemonic();

	String getFullname();

	Long getFraction();

	boolean isSetQuoteFlag();

	String getQuoteSource();

	String getQuoteTz();

	default boolean equalsUnique(Commodity other) {
		return null != other && Objects.equals(getNamespace(), other.getNamespace())
				&& Objects.equals(getMnemonic(), other.getMnemonic());
	}	
}