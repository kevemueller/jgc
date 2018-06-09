package hu.keve.jgc.dao.jaxb;

import java.util.Currency;

import javax.xml.bind.JAXBElement;

import org.gnucash.xml.IdType;

import hu.keve.jgc.dao.Commodity;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;

public interface CmdtyJAXB extends Commodity, GWG, GuidUnwrapper {
	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

	@Override
	default String getCusip() {
		if ("CURRENCY".equals(getNamespace())) {
			try {
				Currency currency = Currency.getInstance(getMnemonic());
				return currency.getNumericCodeAsString();
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	default void setCusip(String cusip) {
		// this is not stored in XML
	}

	Long getFractionOrDefault();

	void setFractionOrDefault(Long fraction);

	@Override
	default long getFraction() {
		Long fraction = getFractionOrDefault();
		return null == fraction ? 100 : fraction;
	}

	@Override
	default void setFraction(long fraction) {
		setFractionOrDefault(fraction);
	}

	void setQuoteFlag(JAXBElement<Boolean> value);

	@Override
	default void setQuoteFlag(boolean quoteFlag) {
		if (quoteFlag) {
			setQuoteFlag(GCUtilJAXB.cmdtyObjectFactory.createCmdtyTypeGetQuotes(null));
		} else {
			setQuoteFlag(null);
		}
	}

	AbstractGnuCashJAXB getGuidRoot();

	void setVersion(String version);

	void setNamespace(String space);

	void setMnemonic(String id);

	IdType getInternalGuid();

	default IdType getWrappedGuid() {
		return getInternalGuid();
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
