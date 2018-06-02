package hu.keve.jgc.dao.jdo;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Entry;
import hu.keve.jgc.util.Fraction;

@PersistenceCapable(table = "entries")
public class EntryJDO implements Entry {
	@PrimaryKey
	String guid;

	Date date;
	Date dateEntered;
	String description;
	String action;
	String notes;
	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "quantity_num"),
			@Persistent(name = "denom", column = "quantity_denom") })
	Fraction quantity;
	AccountJDO iAcct;
	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "i_price_num"),
			@Persistent(name = "denom", column = "i_price_denom") })
	Fraction iPrice;
	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "i_discount_num"),
			@Persistent(name = "denom", column = "i_discount_denom") })
	Fraction iDiscount;
	InvoiceJDO invoice;
	DiscTypes iDiscType;
	DiscHowTypes iDiscHow;
	boolean iTaxable;
	boolean iTaxincluded;
	TaxtableJDO iTaxtable;
	AccountJDO bAcct;
	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "b_price_num"),
			@Persistent(name = "denom", column = "b_price_denom") })
	Fraction bPrice;
	InvoiceJDO bill;
	boolean bTaxable;
	boolean bTaxincluded;
	TaxtableJDO bTaxtable;
	@Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "toIntValue")
	PayTypes bPaytype;
	boolean billable;

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "type", column = "billto_type"),
			@Persistent(name = "guid", column = "billto_guid") })
	OwnerJDO billTo;

	@Column(name = "order_guid")
	OrderJDO order;

	public String getGuid() {
		return guid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getDate()
	 */
	@Override
	public Date getDate() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getDateEntered()
	 */
	@Override
	public Date getDateEntered() {
		return dateEntered;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getAction()
	 */
	@Override
	public String getAction() {
		return action;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getNotes()
	 */
	@Override
	public String getNotes() {
		return notes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getQuantity()
	 */
	@Override
	public Fraction getQuantity() {
		return quantity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getiAcct()
	 */
	@Override
	public AccountJDO getIAcct() {
		return iAcct;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getiPrice()
	 */
	@Override
	public Fraction getIPrice() {
		return iPrice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getiDiscount()
	 */
	@Override
	public Fraction getIDiscount() {
		return iDiscount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getInvoice()
	 */
	@Override
	public InvoiceJDO getInvoice() {
		return invoice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getiDiscType()
	 */
	@Override
	public DiscTypes getIDiscType() {
		return iDiscType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getiDiscHow()
	 */
	@Override
	public DiscHowTypes getIDiscHow() {
		return iDiscHow;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#isiTaxable()
	 */
	@Override
	public Boolean isITaxable() {
		return iTaxable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#isiTaxincluded()
	 */
	@Override
	public Boolean isITaxincluded() {
		return iTaxincluded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getiTaxtable()
	 */
	@Override
	public TaxtableJDO getITaxtable() {
		return iTaxtable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getbAcct()
	 */
	@Override
	public AccountJDO getBAcct() {
		return bAcct;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getbPrice()
	 */
	@Override
	public Fraction getBPrice() {
		return bPrice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getBill()
	 */
	@Override
	public InvoiceJDO getBill() {
		return bill;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#isbTaxable()
	 */
	@Override
	public Boolean isBTaxable() {
		return bTaxable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#isbTaxincluded()
	 */
	@Override
	public Boolean isBTaxincluded() {
		return bTaxincluded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getbTaxtable()
	 */
	@Override
	public TaxtableJDO getBTaxtable() {
		return bTaxtable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getbPaytype()
	 */
	@Override
	public PayTypes getBPaytype() {
		return bPaytype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#isBillable()
	 */
	@Override
	public Boolean isBillable() {
		return billable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getBilltoType()
	 */
	@Override
	public OwnerJDO getBillTo() {
		return billTo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getOrder()
	 */
	@Override
	public OrderJDO getOrder() {
		return order;
	}

}
