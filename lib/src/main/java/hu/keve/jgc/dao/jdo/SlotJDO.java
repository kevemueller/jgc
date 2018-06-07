package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Slot;

public abstract class SlotJDO implements Slot {
	static final String NAME = "slots";
	static final int VERSION = 4;

	String objGuid;
	String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Slot[ " + objGuid + "]." + name + "/" + getType() + "/=" + getValue();
	}
}
