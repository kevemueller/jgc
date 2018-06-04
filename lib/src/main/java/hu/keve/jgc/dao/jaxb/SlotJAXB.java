package hu.keve.jgc.dao.jaxb;

import java.util.Collection;

import hu.keve.jgc.dao.Slot;

public interface SlotJAXB extends Slot {
	@Override
	default Collection<? extends Slot> getSlots() {
		return null;
	}
}
