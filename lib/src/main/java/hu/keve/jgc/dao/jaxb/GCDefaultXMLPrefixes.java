package hu.keve.jgc.dao.jaxb;

import hu.keve.jgc.xml.GnuCashConstants;

public final class GCDefaultXMLPrefixes {

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

	public static NamespacePrefixMapper getNamespacePrefixMapper() {
		return namespacePrefixMapper;
	}
}
