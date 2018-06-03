package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;
import java.util.Collection;

import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.Invoice;
import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;

public interface InvoiceJAXB extends Invoice, GuidUnwrapper, SlotsUnwrapper {
	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}
	
	TsType getWrappedDateOpened();
	TsType getWrappedDatePosted();

	@Override
	default LocalDateTime getDateOpened() {
		return TsDateTypeUtil.unwrap(getWrappedDateOpened());
	}
	@Override
	default LocalDateTime getDatePosted() {
		return TsDateTypeUtil.unwrap(getWrappedDatePosted());
	}

	@Override
	default Collection<SlotType> getSlots() {
		return SlotsUnwrapper.super.getSlots();
	}
}
