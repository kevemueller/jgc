package hu.keve.jgc.dao.jaxb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlTransient;

import org.gnucash.xml.ObjectFactory;
import org.gnucash.xml.cmdty.CmdtyType;
import org.gnucash.xml.gnc.CountData;
import org.gnucash.xml.gnc.GncV2BookType;
import org.gnucash.xml.gnc.GncV2Type;

import hu.keve.jgc.GnuCash;
import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.xml.GnuCashXMLSource;

public abstract class AbstractGnuCashJAXB implements GnuCash {
	protected abstract void setBook(GncV2BookType value);

	public abstract List<CountData> getCountData();

	@Override
	public abstract BookJAXB getBook();

	@XmlTransient
	protected File file;
	@XmlTransient
	protected boolean readOnly;
	@XmlTransient
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

	@Override
	public void close() throws Exception {
		// TODO: remove lock file
	}

	@Override
	public void commit() throws JAXBException {
		if (readOnly) {
			throw new IllegalArgumentException();
		}
		Marshaller marshaller = GCUtilJAXB.getMarshaller();
		JAXBElement<GncV2Type> element = new ObjectFactory().createGncV2((GncV2Type) this);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(element, file);
	}

	@Override
	public <T> T getById(Class<T> idType, Object id) {
		return resolve((String) id, idType);
	}

	@SuppressWarnings("unchecked")
	public static GnuCash fromFile(File file, boolean readOnly, boolean create) throws IOException, JAXBException {
		GncV2Type gncv2;
		if (create) {
			gncv2 = new GncV2Type();
		} else {
			InputStream in = GnuCashXMLSource.openStream(file);
			Unmarshaller unmarshaller = GCUtilJAXB.getUnmarshaller();
			gncv2 = ((JAXBElement<GncV2Type>) unmarshaller.unmarshal(in)).getValue();
		}
		gncv2.file = file;
		gncv2.readOnly = readOnly;
		return gncv2;
	}
}
