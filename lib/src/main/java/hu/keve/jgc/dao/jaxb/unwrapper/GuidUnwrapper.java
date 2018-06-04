package hu.keve.jgc.dao.jaxb.unwrapper;

import org.gnucash.xml.IdType;

public interface GuidUnwrapper {	
	IdType getWrappedGuid();
	
	default String getGuid() {
		IdType idType = getWrappedGuid();
		return null == idType ? null : idType.getValue();
		
	}
}
