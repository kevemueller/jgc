package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;

public class SlotTimespecJDO extends SlotJDO {
	LocalDateTime timespecVal;

	@Override
	public SlotTypes getType() {
		return SlotTypes.TIMESPEC;
	}

	@Override
	public Object getValue() {
		return timespecVal;
	}
}
