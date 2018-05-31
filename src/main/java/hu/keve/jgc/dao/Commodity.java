package hu.keve.jgc.dao;

public interface Commodity {
	
	String getNamespace();

	String getMnemonic();

	String getFullname();

	Long getFraction();

	boolean isSetQuoteFlag();

	String getQuoteSource();

	String getQuoteTz();

}