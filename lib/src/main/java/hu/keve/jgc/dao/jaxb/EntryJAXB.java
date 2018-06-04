package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;

import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.Entry;
import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;
import hu.keve.jgc.dao.jaxb.unwrapper.DateUnwrapper;

public interface EntryJAXB extends Entry, DateUnwrapper {
	TsType getWrappedDateEntered();

	@Override
	default LocalDateTime getDate() {
		return DateUnwrapper.super.getDate();
	}

	@Override
	default LocalDateTime getDateEntered() {
		return TsDateTypeUtil.unwrap(getWrappedDateEntered());
	}
}
