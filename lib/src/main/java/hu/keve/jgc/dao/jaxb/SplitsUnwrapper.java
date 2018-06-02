package hu.keve.jgc.dao.jaxb;

import java.util.Collection;

import org.gnucash.xml.split.SplitType;
import org.gnucash.xml.trn.SplitsType;

public interface SplitsUnwrapper {
	void setWrappedSplits(SplitsType value);
	SplitsType getWrappedSplits();

	default Collection<SplitType> getSplits() {
		SplitsType wrappedSplits = getWrappedSplits();
		if (null == wrappedSplits) {
			wrappedSplits = new SplitsType();
			setWrappedSplits(wrappedSplits);
		}
		return wrappedSplits.getSplit();
	}
}
