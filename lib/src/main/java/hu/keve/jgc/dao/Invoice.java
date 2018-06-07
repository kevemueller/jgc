package hu.keve.jgc.dao;

import java.time.LocalDateTime;

import hu.keve.jgc.util.Fraction;

public interface Invoice extends GuidType {
	String getId();

	LocalDateTime getDateOpened();

	LocalDateTime getDatePosted();

	// String getNotes();

	boolean isActive();

	Commodity getCurrency();

	Owner getOwner();

	Billterm getTerms();

	String getBillingId();

	Transaction getPostTxn();

	Lot getPostLot();

	Account getPostAcct();

	Owner getBillto();

	Fraction getChargeAmt();

}