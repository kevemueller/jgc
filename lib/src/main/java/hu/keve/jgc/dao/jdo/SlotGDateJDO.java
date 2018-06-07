package hu.keve.jgc.dao.jdo;

import java.time.LocalDate;

public class SlotGDateJDO extends SlotJDO {
	LocalDate gdateVal;

	@Override
	public SlotType getType() {
		return SlotType.GDATE;
	}

	@Override
	public Object getValue() {
		return gdateVal;
	}
}
