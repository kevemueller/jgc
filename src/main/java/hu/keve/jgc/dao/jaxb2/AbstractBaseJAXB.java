package hu.keve.jgc.dao.jaxb2;

import java.util.List;

import javax.xml.namespace.QName;

import org.gnucash.xml.IdType;
import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.gnc.CountData;

import hu.keve.jgc.dao.GnuCashUtil;

public abstract class AbstractBaseJAXB {
	protected static final org.gnucash.xml.ObjectFactory objectFactory = new org.gnucash.xml.ObjectFactory();
	protected static final org.gnucash.xml.act.ObjectFactory actObjectFactory = new org.gnucash.xml.act.ObjectFactory();
	protected static final org.gnucash.xml.cmdty.ObjectFactory cmdtyObjectFactory = new org.gnucash.xml.cmdty.ObjectFactory();
	protected static final org.gnucash.xml.gnc.ObjectFactory gncObjectFactory = new org.gnucash.xml.gnc.ObjectFactory();

	protected static final String GC_VERSION = "2.0.0";
	protected static final QName GC_CD_ACCOUNT = new QName("account");
	protected static final QName GC_CD_BOOK = new QName("book");
	protected static final QName GC_CD_COMMODITY = new QName("commodity");
	
	protected static final QName GC_CD_GNC_BILL_TERM = new QName(GCDefaultXMLPrefixes.GNC_NAMESPACE_GNC, "GncBillTerm");

	protected abstract List<CountData> getCountData();

	protected IdType createGuid() {
		IdType id = objectFactory.createIdType();
		id.setType(IdTypeEnum.GUID);
		id.setValue(GnuCashUtil.makeGuid());
		return id;
	}

	protected void increment(QName gcCd) {
		for (CountData cd : getCountData()) {
			if (gcCd.equals(cd.getType())) {
				cd.setValue(cd.getValue() + 1);
				return;
			}
		}
		CountData cd = gncObjectFactory.createCountData();
		cd.setType(gcCd);
		cd.setValue(1);
		getCountData().add(cd);
	}
}
