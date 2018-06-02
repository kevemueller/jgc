package hu.keve.jgc.dao.jaxb;

import java.util.List;

import javax.xml.namespace.QName;

import org.gnucash.xml.IdType;
import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.gnc.CountData;

import hu.keve.jgc.dao.GnuCashUtil;

public final class GCUtilJAXB {
	static final String GC_VERSION = "2.0.0";
	static final QName GC_CD_ACCOUNT = new QName("account");
	static final QName GC_CD_BOOK = new QName("book");
	static final QName GC_CD_COMMODITY = new QName("commodity");

	static final QName GC_CD_GNC_BILL_TERM = new QName(GCDefaultXMLPrefixes.GNC_NAMESPACE_GNC, "GncBillTerm");
	
	
	static final org.gnucash.xml.ObjectFactory objectFactory = new org.gnucash.xml.ObjectFactory();
	static final org.gnucash.xml.act.ObjectFactory actObjectFactory = new org.gnucash.xml.act.ObjectFactory();
	static final org.gnucash.xml.cmdty.ObjectFactory cmdtyObjectFactory = new org.gnucash.xml.cmdty.ObjectFactory();
	static final org.gnucash.xml.gnc.ObjectFactory gncObjectFactory = new org.gnucash.xml.gnc.ObjectFactory();

	static IdType createGuid() {
		IdType id = objectFactory.createIdType();
		id.setType(IdTypeEnum.GUID);
		id.setValue(GnuCashUtil.makeGuid());
		return id;
	}

	static void increment(final List<CountData> cdl, final QName gcCd) {
		for (CountData cd : cdl) {
			if (gcCd.equals(cd.getType())) {
				cd.setValue(cd.getValue() + 1);
				return;
			}
		}
		CountData cd = gncObjectFactory.createCountData();
		cd.setType(gcCd);
		cd.setValue(1);
		cdl.add(cd);
	}

	private GCUtilJAXB() {

	}

}
