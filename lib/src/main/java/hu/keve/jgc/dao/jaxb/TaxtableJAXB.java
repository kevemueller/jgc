package hu.keve.jgc.dao.jaxb;

import java.util.List;

import org.gnucash.xml.gnc.GncTaxTableEntriesType;
import org.gnucash.xml.tte.TteType;

import hu.keve.jgc.dao.Taxtable;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;

public interface TaxtableJAXB extends Taxtable, GuidUnwrapper {
	GncTaxTableEntriesType getWrappedEntries();

	default List<TteType> getEntries() {
		return getWrappedEntries().getGncTaxTableEntry();
	}

	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

}
