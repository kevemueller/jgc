package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import hu.keve.jgc.dao.BilltermDays;
import hu.keve.jgc.util.Fraction;

@PersistenceCapable(embeddedOnly = "true")
public class BtDaysJDO implements BilltermDays {
	@Column(name = "duedays")
	long dueDays;
	@Column(name = "discountdays")
	long discountDays;

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "discount_num"),
			@Persistent(name = "denom", column = "discount_denom") })
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
