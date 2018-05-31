package hu.keve.jgc.dao.jaxb2;

import java.util.List;

import org.gnucash.xml.gnc.GncTaxTableEntriesType;
import org.gnucash.xml.tte.TteType;

public abstract class AbstractTaxtable {
	public abstract GncTaxTableEntriesType getWrappedEntries();

	public List<TteType> getEntries() {
		return getWrappedEntries().getGncTaxTableEntry();
	}
}
