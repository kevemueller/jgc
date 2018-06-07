package hu.keve.jgc.dao.jdo;

public class SlotDoubleJDO extends SlotJDO {
	Double doubleVal;

	@Override
	public SlotType getType() {
		return SlotType.DOUBLE;
	}

	@Override
	public Object getValue() {
		return doubleVal;
	}
}
