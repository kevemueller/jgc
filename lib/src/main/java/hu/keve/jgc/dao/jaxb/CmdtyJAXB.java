package hu.keve.jgc.dao.jaxb;

import org.gnucash.xml.IdType;

import hu.keve.jgc.dao.Commodity;

public interface CmdtyJAXB extends Commodity, GWG, GuidUnwrapper {
	AbstractGnuCashJAXB getGuidRoot();

	void setVersion(String version);

	void setNamespace(String space);

	void setMnemonic(String id);

	IdType getInternalGuid();

	default IdType getWrappedGuid() {
		return getInternalGuid();
	}

	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

	default void copy(Commodity commodity) {
		setVersion("2.0.0");
		// setGuid(commodity.getGuid());
		setNamespace(commodity.getNamespace());
		setMnemonic(commodity.getMnemonic());
	}
	// public static CmdtyType copy(Commodity commodity) {
	// <xs:element name="space" type="xs:string" />
	// <xs:element name="id" type="xs:string" />
	//
	// <xs:element minOccurs="0" name="name" type="xs:string" />
	// <xs:element minOccurs="0" name="xcode" type="xs:string" />
	// <xs:element minOccurs="0" name="fraction"
	// type="xs:unsignedInt" />
	// <xs:element minOccurs="0" name="get_quotes"
	// type="xs:boolean" nillable="true" default="false" /> <!-- used as a tagger
	// -->
	// <xs:element minOccurs="0" name="quote_source"
	// type="xs:string" />
	// <xs:element minOccurs="0" name="quote_tz" type="xs:string" />
	// <xs:element minOccurs="0" name="slots"
	// type="slot:slotsType" />
	//
	// return cmdty;
	// }
}
