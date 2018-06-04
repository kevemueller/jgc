package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;
import java.util.Collection;

import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.Split;
import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;
import hu.keve.jgc.dao.jaxb.unwrapper.SlotsUnwrapper;

public interface SplitJAXB extends Split, GuidUnwrapper, SlotsUnwrapper {
	@Override
	default Collection<SlotType> getSlots() {
		return SlotsUnwrapper.super.getSlots();
	}

	TsType getWrappedDate();

	@Override
	default LocalDateTime getReconcileDate() {
		return TsDateTypeUtil.unwrap(getWrappedDate());
	}

	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}
}
