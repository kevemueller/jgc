package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Commodity;

@PersistenceCapable(table = "commodities")
public final class CommodityJDO implements Commodity  {
	@PrimaryKey
	String guid;
	String namespace; // TODO: Enum
	String mnemonic;
	String fullname;
	String cusip;
	long fraction;
	boolean quoteFlag;
	String quoteSource;
	String quoteTz;
	
	@Element(column="obj_guid")
	Collection<SlotJDO> slots;

	public String getGuid() {
		return guid;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Commodity#getNamespace()
	 */
	@Override
	public String getNamespace() {
		return namespace;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Commodity#getMnemonic()
	 */
	@Override
	public String getMnemonic() {
		return mnemonic;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Commodity#getFullname()
	 */
	@Override
	public String getFullname() {
		return fullname;
	}

	public String getCusip() {
		return cusip;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Commodity#getFraction()
	 */
	@Override
	public Long getFraction() {
		return fraction;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Commodity#isQuoteFlag()
	 */
	@Override
	public boolean isSetQuoteFlag() {
		return quoteFlag;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Commodity#getQuoteSource()
	 */
	@Override
	public String getQuoteSource() {
		return quoteSource;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Commodity#getQuoteTz()
	 */
	@Override
	public String getQuoteTz() {
		return quoteTz;
	}
	
}
