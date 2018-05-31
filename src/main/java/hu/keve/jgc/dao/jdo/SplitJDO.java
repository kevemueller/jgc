package hu.keve.jgc.dao.jdo;

import java.util.Collection;
import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Split;
import hu.keve.jgc.util.Fraction;

@PersistenceCapable(table = "splits")
public class SplitJDO implements Split {
	@PrimaryKey
	String guid;

	@Column(name = "tx_guid")
	TransactionJDO tx;

	@Column(name = "account_guid")
	AccountJDO account;

	String memo;
	String action;
	
	@Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "toValue")	
	ReconciledStateTypes reconcileState;
	Date reconcileDate;
	
	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "value_num"),
			@Persistent(name = "denom", column = "value_denom") })
	Fraction value;

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "quantity_num"),
			@Persistent(name = "denom", column = "quantity_denom") })
	Fraction quantity;

	@Element(column = "obj_guid")
	Collection<SlotJDO> slots;

	@Column(name = "lot_guid")
	LotJDO lot;

	public String getGuid() {
		return guid;
	}

	public TransactionJDO getTx() {
		return tx;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getAccount()
	 */
	@Override
	public AccountJDO getAccount() {
		return account;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getMemo()
	 */
	@Override
	public String getMemo() {
		return memo;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getAction()
	 */
	@Override
	public String getAction() {
		return action;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getReconcileState()
	 */
	@Override
	public ReconciledStateTypes getReconcileState() {
		return reconcileState;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getReconcileDate()
	 */
	@Override
	public Date getReconcileDate() {
		return reconcileDate;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getValue()
	 */
	@Override
	public Fraction getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getQuantity()
	 */
	@Override
	public Fraction getQuantity() {
		return quantity;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Split#getLot()
	 */
	@Override
	public LotJDO getLot() {
		return lot;
	}	
}
