package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;

import hu.keve.jgc.dao.Invoice;
import hu.keve.jgc.util.Fraction;

public final class InvoiceJDO extends AbstractGuidTypeJDO implements Invoice {
	String id;

	LocalDateTime dateOpened;
	LocalDateTime datePosted;
	String notes;
	boolean active;
	CommodityJDO currency;

	OwnerJDO owner;

	BilltermJDO terms;
	String billingId;
	TransactionJDO postTransaction;
	LotJDO postLot;
	AccountJDO postAccount;
	OwnerJDO billto;

	Fraction chargeAmt;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public LocalDateTime getDateOpened() {
		return dateOpened;
	}

	@Override
	public LocalDateTime getDatePosted() {
		return datePosted;
	}

	public String getNotes() {
		return notes;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	@Override
	public OwnerJDO getOwner() {
		return owner;
	}

	@Override
	public BilltermJDO getTerms() {
		return terms;
	}

	@Override
	public String getBillingId() {
		return billingId;
	}

	@Override
	public TransactionJDO getPostTxn() {
		return postTransaction;
	}

	@Override
	public LotJDO getPostLot() {
		return postLot;
	}

	@Override
	public AccountJDO getPostAcct() {
		return postAccount;
	}

	@Override
	public OwnerJDO getBillto() {
		return billto;
	}

	@Override
	public Fraction getChargeAmt() {
		return chargeAmt;
	}
}
