package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;
import java.util.Collection;

import hu.keve.jgc.dao.Split;
import hu.keve.jgc.util.Fraction;

public class SplitJDO extends GuidTypeJDO implements Split {
	TransactionJDO tx;
	AccountJDO account;

	String memo;
	String action;
	
	ReconciledStateTypes reconcileState;
	LocalDateTime reconcileDate;
	Fraction value;	
	Fraction quantity;

	Collection<SlotJDO> slots;
	LotJDO lot;

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
	public LocalDateTime getReconcileDate() {
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
