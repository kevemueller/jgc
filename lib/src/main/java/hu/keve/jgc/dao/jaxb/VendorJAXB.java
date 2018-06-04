package hu.keve.jgc.dao.jaxb;

import java.util.Collection;

import org.gnucash.xml.slot.SlotType;

import hu.keve.jgc.dao.Vendor;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;
import hu.keve.jgc.dao.jaxb.unwrapper.SlotsUnwrapper;

public interface VendorJAXB extends Vendor, GuidUnwrapper, SlotsUnwrapper {
	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

	@Override
	default Collection<SlotType> getSlots() {
		return SlotsUnwrapper.super.getSlots();
	}
}
