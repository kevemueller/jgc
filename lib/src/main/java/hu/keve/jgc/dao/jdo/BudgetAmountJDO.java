package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.BudgetAmount;
import hu.keve.jgc.util.Fraction;

@PersistenceCapable(table = "budget_amounts")
public final class BudgetAmountJDO implements BudgetAmount {
	@PrimaryKey
	int id;
	
	@Column(name="account_guid")
	AccountJDO account;
	int periodNum;
	
	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "amount_num"),
			@Persistent(name = "denom", column = "amount_denom") })
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
