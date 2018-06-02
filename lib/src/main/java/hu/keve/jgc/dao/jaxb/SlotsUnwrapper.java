package hu.keve.jgc.dao.jaxb;

import java.util.Collection;

import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.slot.SlotsType;

public interface SlotsUnwrapper {
	void setWrappedSlots(SlotsType value);
	SlotsType getWrappedSlots();

	default Collection<SlotType> getSlots() {
		SlotsType wrappedSlots = getWrappedSlots();
		if (null == wrappedSlots) {
			wrappedSlots = new SlotsType();
			setWrappedSlots(wrappedSlots);
		}
		return wrappedSlots.getSlot();
	}
}
