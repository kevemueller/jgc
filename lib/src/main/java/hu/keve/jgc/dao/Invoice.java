package hu.keve.jgc.dao;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import hu.keve.jgc.util.Fraction;

public interface Invoice {

	String getGuid();

	String getId();

	LocalDateTime getDateOpened();

	LocalDateTime getDatePosted();

//	String getNotes();

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

	Collection<? extends Slot> getSlots();

}