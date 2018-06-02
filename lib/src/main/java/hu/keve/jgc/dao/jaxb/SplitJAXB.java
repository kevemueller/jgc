package hu.keve.jgc.dao.jaxb;

import java.util.Collection;

import org.gnucash.xml.slot.SlotType;

import hu.keve.jgc.dao.Split;

public interface SplitJAXB extends Split, GuidUnwrapper, SlotsUnwrapper {
	@Override
	default Collection<SlotType> getSlots() {
		return SlotsUnwrapper.super.getSlots();
	}

	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}
}
