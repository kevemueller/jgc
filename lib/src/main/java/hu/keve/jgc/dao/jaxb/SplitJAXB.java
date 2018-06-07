package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;

import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.Split;
import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;

public interface SplitJAXB extends Split {
	TsType getWrappedDate();

	@Override
	default LocalDateTime getReconcileDate() {
		return TsDateTypeUtil.unwrap(getWrappedDate());
	}

}
