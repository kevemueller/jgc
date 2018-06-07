package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;

import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.Invoice;
import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;

public interface InvoiceJAXB extends Invoice {
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
}
