package hu.keve.jgc.dao.jaxb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gnucash.xml.cmdty.CmdtyType;
import org.gnucash.xml.gnc.CountData;
import org.gnucash.xml.gnc.GncV2BookType;

import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.GnuCashDAO;

public abstract class AbstractGnuCashJAXB implements GnuCashDAO {
	protected abstract void setBook(GncV2BookType value);

	public abstract List<CountData> getCountData();

	private Map<String, AbstractGuidType> guidMap = new HashMap<String, AbstractGuidType>();

	@Override
	public Book createBook(String rootCommoditySpace, String rootCommodityMnemonic) {
		assert (null == getBook());
		GncV2BookType book = GCUtilJAXB.gncObjectFactory.createGncV2BookType();
		book.setWrappedGuid(book.getInternalGuid());
		book.setVersion(GCUtilJAXB.GC_VERSION);
		register(book);
		CmdtyType commodity = book.createCommodity(rootCommoditySpace, rootCommodityMnemonic);
		book.createAccount(null, "Root account", AccountTypes.ROOT, commodity);
		GCUtilJAXB.increment(getCountData(), GCUtilJAXB.GC_CD_BOOK);
		setBook(book);
		return book;
	}

	public void register(String guid, AbstractGuidType object) {
		Object oldObject = guidMap.put(guid, object);
		if (null != oldObject) {
			System.err.println("guid changed!" + oldObject + " to " + object);
		}
		assert (null == oldObject);
	}

	public void register(AbstractGuidType object) {
		register(object.getWrappedGuid().getValue(), object);
		object.setGuidRoot(this);
	}

	@SuppressWarnings("unchecked")
	public <T> T resolve(String guid, Class<T> targetType) {
		return (T) guidMap.get(guid);
	}

}
