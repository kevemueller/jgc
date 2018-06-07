package hu.keve.jgc.dao.jdo;

public class SlotLongJDO extends SlotJDO {
	Long int64Val;

	@Override
	public SlotTypes getType() {
		return SlotTypes.INTEGER;
	}

	@Override
	public Object getValue() {
		return int64Val;
	}
}
