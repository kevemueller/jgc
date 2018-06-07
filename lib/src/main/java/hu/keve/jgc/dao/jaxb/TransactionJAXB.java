package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;
import java.util.Collection;

import org.gnucash.xml.split.SplitType;
import org.gnucash.xml.ts.TsType;

import hu.keve.jgc.dao.Transaction;
import hu.keve.jgc.dao.jaxb.adapters.TsDateTypeUtil;
import hu.keve.jgc.dao.jaxb.unwrapper.SplitsUnwrapper;

public interface TransactionJAXB extends Transaction, SplitsUnwrapper {
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
