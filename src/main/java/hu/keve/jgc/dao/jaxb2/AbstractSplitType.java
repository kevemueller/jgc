package hu.keve.jgc.dao.jaxb2;

import java.util.List;

import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.slot.SlotsType;

public abstract class AbstractSplitType {
	public abstract SlotsType getWrappedSlots();

	public List<SlotType> getSlots() {
		return getWrappedSlots().getSlot();
	}
}
