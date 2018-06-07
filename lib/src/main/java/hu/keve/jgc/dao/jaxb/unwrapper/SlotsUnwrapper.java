package hu.keve.jgc.dao.jaxb.unwrapper;

import java.util.Collection;

import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.slot.SlotsType;

public interface SlotsUnwrapper {
	void setWrappedSlots(SlotsType value);

	SlotsType getWrappedSlots();

	default Collection<SlotType> getSlotsList() {
		SlotsType wrappedSlots = getWrappedSlots();
		if (null == wrappedSlots) {
			wrappedSlots = new SlotsType();
			setWrappedSlots(wrappedSlots);
		}
		return wrappedSlots.getSlot();
	}
}
