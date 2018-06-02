package hu.keve.jgc.dao.jaxb;

import java.util.Collection;

import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.split.SplitType;

import hu.keve.jgc.dao.Transaction;

public interface TransactionJAXB extends Transaction, GuidUnwrapper, SlotsUnwrapper, SplitsUnwrapper {
	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

	@Override
	default Collection<SlotType> getSlots() {
		return SlotsUnwrapper.super.getSlots();
	}

	@Override
	default Collection<SplitType> getSplits() {
		return SplitsUnwrapper.super.getSplits();
	}
}
