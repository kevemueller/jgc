package hu.keve.jgc.dao.jaxb;

import java.util.Collection;

import org.gnucash.xml.slot.SlotType;

import hu.keve.jgc.dao.Lot;

public interface LotJAXB extends Lot, GuidUnwrapper, SlotsUnwrapper {	
	@Override
	default Collection<SlotType> getSlots() {
		return SlotsUnwrapper.super.getSlots();
	}
}
