package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;
import java.util.Collection;

import hu.keve.jgc.dao.Invoice;
import hu.keve.jgc.util.Fraction;

public final class InvoiceJDO extends GuidTypeJDO implements Invoice {
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

	Collection<SlotJDO> slots;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getDateOpened()
	 */
	@Override
	public LocalDateTime getDateOpened() {
		return dateOpened;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getDatePosted()
	 */
	@Override
	public LocalDateTime getDatePosted() {
		return datePosted;
	}

	public String getNotes() {
		return notes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getCurrency()
	 */
	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getOwnerType()
	 */
	@Override
	public OwnerJDO getOwner() {
		return owner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getTerms()
	 */
	@Override
	public BilltermJDO getTerms() {
		return terms;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getBillingId()
	 */
	@Override
	public String getBillingId() {
		return billingId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getPostTxn()
	 */
	@Override
	public TransactionJDO getPostTxn() {
		return postTransaction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getPostLot()
	 */
	@Override
	public LotJDO getPostLot() {
		return postLot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getPostAcct()
	 */
	@Override
	public AccountJDO getPostAcct() {
		return postAccount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getBilltoType()
	 */
	@Override
	public OwnerJDO getBillto() {
		return billto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getCharge_amt()
	 */
	@Override
	public Fraction getChargeAmt() {
		return chargeAmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

}
