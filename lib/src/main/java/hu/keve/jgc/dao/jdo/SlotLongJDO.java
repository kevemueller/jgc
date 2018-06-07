package hu.keve.jgc.dao.jdo;

public class SlotLongJDO extends SlotJDO {
	Long int64Val;

	@Override
	public SlotType getType() {
		return SlotType.INTEGER;
	}

	@Override
	public Object getValue() {
		return int64Val;
	}
}
