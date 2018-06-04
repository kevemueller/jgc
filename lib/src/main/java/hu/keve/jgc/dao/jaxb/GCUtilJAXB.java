package hu.keve.jgc.dao.jaxb;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Unmarshaller.Listener;
import javax.xml.namespace.QName;

import org.gnucash.xml.IdType;
import org.gnucash.xml.IdTypeEnum;
import org.gnucash.xml.cmdty.CmdtyType;
import org.gnucash.xml.gnc.CountData;
import org.gnucash.xml.owner.OwnerType;

import com.sun.xml.bind.IDResolver;

import hu.keve.jgc.dao.DaoUtil;
import hu.keve.jgc.dao.jaxb.adapters.ActRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.BilltermRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.CmdtyRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.InvoiceRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.LotRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.OrderRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.TaxtableRefTypeAdapter;
import hu.keve.jgc.dao.jaxb.adapters.TrnRefTypeAdapter;
import hu.keve.jgc.util.jaxb.IDResolver2;
import hu.keve.jgc.util.jaxb.NamespacePrefixMapper;
import hu.keve.jgc.xml.GnuCashConstants;

public final class GCUtilJAXB {
	private static JAXBContext jaxbContext;

	static final String GC_VERSION = "2.0.0";
	static final QName GC_CD_ACCOUNT = new QName("account");
	static final QName GC_CD_BOOK = new QName("book");
	static final QName GC_CD_COMMODITY = new QName("commodity");

	static final QName GC_CD_GNC_BILL_TERM = new QName(GnuCashConstants.GNC_NS_URI, "GncBillTerm");

	static final org.gnucash.xml.ObjectFactory objectFactory = new org.gnucash.xml.ObjectFactory();
	static final org.gnucash.xml.act.ObjectFactory actObjectFactory = new org.gnucash.xml.act.ObjectFactory();
	static final org.gnucash.xml.cmdty.ObjectFactory cmdtyObjectFactory = new org.gnucash.xml.cmdty.ObjectFactory();
	static final org.gnucash.xml.gnc.ObjectFactory gncObjectFactory = new org.gnucash.xml.gnc.ObjectFactory();

	static IdType createGuid() {
		IdType id = objectFactory.createIdType();
		id.setType(IdTypeEnum.GUID);
		id.setValue(DaoUtil.makeGuid());
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

	private static final NamespacePrefixMapper namespacePrefixMapper = new NamespacePrefixMapper();

	static {
		namespacePrefixMapper.add(GnuCashConstants.GNC_NS_URI, GnuCashConstants.GNC_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.GNC_NS_URI, GnuCashConstants.GNC_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.ACT_NS_URI, GnuCashConstants.ACT_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.BOOK_NS_URI, GnuCashConstants.BOOK_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.CD_NS_URI, GnuCashConstants.CD_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.CMDTY_NS_URI, GnuCashConstants.CMDTY_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.PRICE_NS_URI, GnuCashConstants.PRICE_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.SLOT_NS_URI, GnuCashConstants.SLOT_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.SPLIT_NS_URI, GnuCashConstants.SPLIT_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.SX_NS_URI, GnuCashConstants.SX_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.TRN_NS_URI, GnuCashConstants.TRN_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.TS_NS_URI, GnuCashConstants.TS_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.FS_NS_URI, GnuCashConstants.FS_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.BGT_NS_URI, GnuCashConstants.BGT_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.RECURRENCE_NS_URI, GnuCashConstants.RECURRENCE_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.LOT_NS_URI, GnuCashConstants.LOT_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.ADDR_NS_URI, GnuCashConstants.ADDR_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.BILLTERM_NS_URI, GnuCashConstants.BILLTERM_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.BT_DAYS_NS_URI, GnuCashConstants.BT_DAYS_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.BT_PROX_NS_URI, GnuCashConstants.BT_PROX_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.CUST_NS_URI, GnuCashConstants.CUST_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.EMPLOYEE_NS_URI, GnuCashConstants.EMPLOYEE_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.ENTRY_NS_URI, GnuCashConstants.ENTRY_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.INVOICE_NS_URI, GnuCashConstants.INVOICE_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.JOB_NS_URI, GnuCashConstants.JOB_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.ORDER_NS_URI, GnuCashConstants.ORDER_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.OWNER_NS_URI, GnuCashConstants.OWNER_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.TAXTABLE_NS_URI, GnuCashConstants.TAXTABLE_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.TTE_NS_URI, GnuCashConstants.TTE_NS_PREFIX, true);
		namespacePrefixMapper.add(GnuCashConstants.VENDOR_NS_URI, GnuCashConstants.VENDOR_NS_PREFIX, true);
	}

	private GCUtilJAXB() {

	}

	public static Unmarshaller getUnmarshaller() throws JAXBException {
		if (null == jaxbContext) {
			jaxbContext = JAXBContext.newInstance("org.gnucash.xml:org.gnucash.xml.ts");
		}
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		IDResolver2 idResolver = new IDResolver2();
		unmarshaller.setProperty(IDResolver.class.getName(), idResolver);
		unmarshaller.setAdapter(new ActRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new BilltermRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new CmdtyRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new InvoiceRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new LotRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new OrderRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new TaxtableRefTypeAdapter(idResolver));
		unmarshaller.setAdapter(new TrnRefTypeAdapter(idResolver));
		Listener listener = new Listener() {
			AbstractGnuCashJAXB root;

			@Override
			public void beforeUnmarshal(Object target, Object parent) {
				// System.err.println("Before: " + target + " -> " + parent);
				if (target instanceof AbstractGnuCashJAXB) {
					System.err.println("Setting root!");
					root = (AbstractGnuCashJAXB) target;
				}
				if (target instanceof AbstractGuidType) {
					// System.err.println("Setting guidroot!" + root);
					((AbstractGuidType) target).setGuidRoot(root);
				}
				super.beforeUnmarshal(target, parent);
			}

			@Override
			public void afterUnmarshal(Object target, Object parent) {
				// System.err.println("After: " + target + " -> " + parent);
				if (target instanceof IdType) {
					if (parent instanceof OwnerType) {

					} else {
						idResolver.bind(((IdType) target).getValue(), parent);
						root.register(((IdType) target).getValue(), (AbstractGuidType) parent);
					}
				} else if (target instanceof CmdtyType) {
					String id = CmdtyRefTypeAdapter.makeId((CmdtyType) target);
					idResolver.bind(id, target);
					root.register((CmdtyType) target);
				}
				super.afterUnmarshal(target, parent);
			}
		};
		unmarshaller.setListener(listener);
		return unmarshaller;
	}

	public static Marshaller getMarshaller() throws JAXBException {
		if (null == jaxbContext) {
			jaxbContext = JAXBContext.newInstance("org.gnucash.xml:org.gnucash.xml.ts");
		}
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", namespacePrefixMapper);

		IDResolver2 idResolver = null;
		marshaller.setAdapter(new ActRefTypeAdapter(idResolver));
		marshaller.setAdapter(new BilltermRefTypeAdapter(idResolver));
		marshaller.setAdapter(new CmdtyRefTypeAdapter(idResolver));
		marshaller.setAdapter(new InvoiceRefTypeAdapter(idResolver));
		marshaller.setAdapter(new LotRefTypeAdapter(idResolver));
		marshaller.setAdapter(new OrderRefTypeAdapter(idResolver));
		marshaller.setAdapter(new TaxtableRefTypeAdapter(idResolver));
		marshaller.setAdapter(new TrnRefTypeAdapter(idResolver));

		return marshaller;
	}
}
