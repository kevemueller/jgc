package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;

import hu.keve.jgc.dao.Entry;
import hu.keve.jgc.util.Fraction;

public class EntryJDO extends AbstractGuidTypeJDO implements Entry {
	LocalDateTime date;
	LocalDateTime dateEntered;
	String description;
	String action;
	String notes;
	Fraction quantity;
	AccountJDO iAcct;
	Fraction iPrice;
	Fraction iDiscount;
	InvoiceJDO invoice;
	DiscTypes iDiscType;
	DiscHowTypes iDiscHow;
	Boolean iTaxable;
	Boolean iTaxincluded;
	TaxtableJDO iTaxtable;
	AccountJDO bAcct;
	Fraction bPrice;
	InvoiceJDO bill;
	Boolean bTaxable;
	Boolean bTaxincluded;
	TaxtableJDO bTaxtable;
	PayTypes bPaytype;
	Boolean billable;
	OwnerJDO billTo;
	OrderJDO order;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getDate()
	 */
	@Override
	public LocalDateTime getDate() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Entry#getDateEntered()
	 */
	@Override
	public LocalDateTime getDateEntered() {
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
