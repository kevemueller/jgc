package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;

public class SlotTimespecJDO extends SlotJDO {
	LocalDateTime timespecVal;

	@Override
	public SlotType getType() {
		return SlotType.TIMESPEC;
	}

	@Override
	public Object getValue() {
		return timespecVal;
	}
}
