package hu.keve.jgc.dao.jaxb;

import hu.keve.jgc.dao.Slot;

public interface SlotJAXB extends Slot {

	@Override
	default SlotType getType() {
		return null;
	}
}
