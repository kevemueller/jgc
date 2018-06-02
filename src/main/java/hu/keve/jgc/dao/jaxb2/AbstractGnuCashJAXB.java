package hu.keve.jgc.dao.jaxb2;

import org.gnucash.xml.cmdty.CmdtyType;
import org.gnucash.xml.gnc.GncV2BookType;

import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.GnuCashDAO;

public abstract class AbstractGnuCashJAXB extends AbstractBaseJAXB implements GnuCashDAO {
	protected abstract void setBook(GncV2BookType value);

	@Override
	public Book createBook(String rootCommoditySpace, String rootCommodityMnemonic) {
		assert (null == getBook());
		GncV2BookType book = gncObjectFactory.createGncV2BookType();
		book.setId(createGuid());
		book.setVersion(GC_VERSION);
		CmdtyType commodity = book.createCommodity(rootCommoditySpace, rootCommodityMnemonic);
		book.createAccount(null, "Root account", AccountTypes.ROOT, commodity);
		increment(GC_CD_BOOK);
		setBook(book);
		return book;
	}

}
