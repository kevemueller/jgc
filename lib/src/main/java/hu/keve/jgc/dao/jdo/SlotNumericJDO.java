package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.util.Fraction;

public class SlotNumericJDO extends SlotJDO {
	Fraction numericVal;

	@Override
	public SlotType getType() {
		return SlotType.NUMERIC;
	}

	@Override
	public Object getValue() {
		return numericVal;
	}
}
