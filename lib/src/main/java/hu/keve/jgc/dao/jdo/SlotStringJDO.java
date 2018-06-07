package hu.keve.jgc.dao.jdo;

public class SlotStringJDO extends SlotJDO {
	String stringVal;

	@Override
	public SlotType getType() {
		return SlotType.STRING;
	}

	@Override
	public Object getValue() {
		return stringVal;
	}
}
