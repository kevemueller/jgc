package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;
import java.util.Collection;

import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.split.SplitType;
import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.Transaction;
import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;

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
	
	TsType getWrappedDateEntered();
	TsType getWrappedDatePosted();

	@Override
	default LocalDateTime getDateEntered() {
		return TsDateTypeUtil.unwrap(getWrappedDateEntered());
	}
	@Override
	default LocalDateTime getDatePosted() {
		return TsDateTypeUtil.unwrap(getWrappedDatePosted());
	}

}
