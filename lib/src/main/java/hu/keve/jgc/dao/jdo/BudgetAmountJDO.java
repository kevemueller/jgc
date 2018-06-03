package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.BudgetAmount;
import hu.keve.jgc.util.Fraction;

public final class BudgetAmountJDO implements BudgetAmount {
//	int id;
	
	BudgetJDO budget;
	
	AccountJDO account;
	int periodNum;
	
	Fraction amount;

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BudgetAmount#getAccount()
	 */
	@Override
	public AccountJDO getAccount() {
		return account;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BudgetAmount#getPeriodNum()
	 */
	@Override
	public int getPeriodNum() {
		return periodNum;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BudgetAmount#getAmount()
	 */
	@Override
	public Fraction getAmount() {
		return amount;
	}			
}
