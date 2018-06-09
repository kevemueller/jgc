package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;

import hu.keve.jgc.dao.Split;
import hu.keve.jgc.util.Fraction;

public class SplitJDO extends AbstractGuidTypeJDO implements Split {
	public static final String NAME = "splits";
	public static final int VERSION = 4;
	
	TransactionJDO tx;
	AccountJDO account;

	String memo;
	String action;

	ReconciledStateTypes reconcileState;
	LocalDateTime reconcileDate;
	Fraction value;
	Fraction quantity;

	LotJDO lot;

	public TransactionJDO getTx() {
		return tx;
	}

	@Override
	public AccountJDO getAccount() {
		return account;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public String getAction() {
		return action;
	}

	@Override
	public ReconciledStateTypes getReconcileState() {
		return reconcileState;
	}

	@Override
	public LocalDateTime getReconcileDate() {
		return reconcileDate;
	}

	@Override
	public Fraction getValue() {
		return value;
	}

	@Override
	public Fraction getQuantity() {
		return quantity;
	}

	@Override
	public LotJDO getLot() {
		return lot;
	}
}
