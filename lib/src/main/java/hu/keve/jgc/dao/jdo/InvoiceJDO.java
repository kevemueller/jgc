package hu.keve.jgc.dao.jdo;

import java.util.Collection;
import java.util.Date;

import hu.keve.jgc.dao.Invoice;
import hu.keve.jgc.util.Fraction;

// JDO mapping in package.jdo due to problem with embedded enum-value-getter
//@PersistenceCapable(table = "invoices")
public class InvoiceJDO implements Invoice {
	// @PrimaryKey
	String guid;

	String id;

	Date dateOpened;
	Date datePosted;
	String notes;
	boolean active;
	CommodityJDO currency;

	// @Persistent(defaultFetchGroup = "true")
	// @Embedded(members = {
	// @Persistent(name = "type", column = "owner_type", extensions = {
	// @Extension(vendorName = "datanucleus", key = "enum-value-getter", value =
	// "toIntValue") }),
	// @Persistent(name = "guid", column = "owner_guid") })
	OwnerJDO owner;

	BilltermJDO terms;
	String billingId;
	TransactionJDO postTxn;
	LotJDO postLot;
	AccountJDO postAcct;

	// @Persistent(defaultFetchGroup = "true")
	// @Embedded(members = {
	// @Persistent(name = "type", column = "billto_type", extensions = {
	// @Extension(vendorName = "datanucleus", key = "enum-value-getter", value =
	// "toIntValue") }),
	// @Persistent(name = "guid", column = "billto_guid") })
	OwnerJDO billto;

	// @Persistent(defaultFetchGroup = "true")
	// @Embedded(members = { @Persistent(name = "num", column = "charge_amt_num"),
	// @Persistent(name = "denom", column = "charge_amt_denom") })
	Fraction chargeAmt;

	// @Element(column = "obj_guid")
	Collection<SlotJDO> slots;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getGuid()
	 */
	@Override
	public String getGuid() {
		return guid;
	}

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
	public Date getDateOpened() {
		return dateOpened;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Invoice#getDatePosted()
	 */
	@Override
	public Date getDatePosted() {
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
		return postTxn;
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
		return postAcct;
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
