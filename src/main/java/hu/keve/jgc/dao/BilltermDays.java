package hu.keve.jgc.dao;

import hu.keve.jgc.util.Fraction;

public interface BilltermDays {

	long getDueDays();

	long getDiscountDays();

	Fraction getDiscount();

}