package hu.keve.jgc.dao.jaxb;

public final class GCDefaultXMLPrefixes {
	
	public static final String GNC_NAMESPACE_GNC = "http://www.gnucash.org/XML/gnc";
	
	private static final NamespacePrefixMapper namespacePrefixMapper = new NamespacePrefixMapper();
	static {
			namespacePrefixMapper.add(GNC_NAMESPACE_GNC,"gnc",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/act","act",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/book","book",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/cd","cd",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/cmdty","cmdty",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/price","price",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/slot","slot",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/split","split",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/sx","sx",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/trn","trn",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/ts","ts",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/fs","fs",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/bgt","bgt",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/recurrence","recurrence",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/lot","lot",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/addr","addr",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/billterm","billterm",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/bt-days","bt-days",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/bt-prox","bt-prox",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/cust","cust",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/employee","employee",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/entry","entry",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/invoice","invoice",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/job","job",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/order","order",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/owner","owner",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/taxtable","taxtable",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/tte","tte",true);
			namespacePrefixMapper.add("http://www.gnucash.org/XML/vendor","vendor",true);
	}
	public static NamespacePrefixMapper getNamespacePrefixMapper() {
		return namespacePrefixMapper;
	}
}
