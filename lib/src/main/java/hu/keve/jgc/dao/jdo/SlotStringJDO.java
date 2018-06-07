package hu.keve.jgc.dao.jdo;

public class SlotStringJDO extends SlotJDO {
	String stringVal;

	@Override
	public SlotTypes getType() {
		return SlotTypes.STRING;
	}

	@Override
	public Object getValue() {
		return stringVal;
	}
}
