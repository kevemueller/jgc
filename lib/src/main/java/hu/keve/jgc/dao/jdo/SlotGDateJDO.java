package hu.keve.jgc.dao.jdo;

import java.time.LocalDate;

public class SlotGDateJDO extends SlotJDO {
	LocalDate gdateVal;

	@Override
	public SlotTypes getType() {
		return SlotTypes.GDATE;
	}

	@Override
	public Object getValue() {
		return gdateVal;
	}
}
