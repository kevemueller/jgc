package hu.keve.jgc.dao.jaxb.unwrapper;

import java.time.LocalDateTime;

import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;

public interface DateUnwrapper {
	TsType getWrappedDate();

	default LocalDateTime getDate() {
		return TsDateTypeUtil.unwrap(getWrappedDate());
	}
}
