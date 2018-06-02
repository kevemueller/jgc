package hu.keve.jgc.dao;

import hu.keve.jgc.util.Fraction;

public interface BudgetAmount {

	Account getAccount();

	int getPeriodNum();

	Fraction getAmount();

}