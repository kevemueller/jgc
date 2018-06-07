package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.util.Fraction;

public class SlotNumericJDO extends SlotJDO {
	Fraction numericVal;

	@Override
	public SlotTypes getType() {
		return SlotTypes.NUMERIC;
	}

	@Override
	public Object getValue() {
		return numericVal;
	}
}
