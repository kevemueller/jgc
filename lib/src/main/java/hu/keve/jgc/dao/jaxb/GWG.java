package hu.keve.jgc.dao.jaxb;

import org.gnucash.xml.IdType;

public interface GWG {
	default IdType getWrappedGuid() {
		return null;
	}
}
