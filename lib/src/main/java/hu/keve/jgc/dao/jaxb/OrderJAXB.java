package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;

import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.Order;
import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;

public interface OrderJAXB extends Order, GuidUnwrapper {
	TsType getWrappedDateOpened();

	TsType getWrappedDateClosed();

	@Override
	default LocalDateTime getDateOpened() {
		return TsDateTypeUtil.unwrap(getWrappedDateOpened());
	}

	@Override
	default LocalDateTime getDateClosed() {
		return TsDateTypeUtil.unwrap(getWrappedDateClosed());
	}

}
