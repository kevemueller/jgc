package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.BilltermDays;
import hu.keve.jgc.util.Fraction;

public class BtDaysJDO implements BilltermDays {
	Integer dueDays;
	Integer discountDays;

	Fraction discount;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.BilltermDays#getDueDays()
	 */
	@Override
	public long getDueDays() {
		return dueDays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.BilltermDays#getDiscountDays()
	 */
	@Override
	public long getDiscountDays() {
		return discountDays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.BilltermDays#getDiscount()
	 */
	@Override
	public Fraction getDiscount() {
		return discount;
	}

}
