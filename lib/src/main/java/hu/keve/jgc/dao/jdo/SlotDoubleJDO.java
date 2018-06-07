package hu.keve.jgc.dao.jdo;

public class SlotDoubleJDO extends SlotJDO {
	Double doubleVal;

	@Override
	public SlotTypes getType() {
		return SlotTypes.DOUBLE;
	}

	@Override
	public Object getValue() {
		return doubleVal;
	}
}
