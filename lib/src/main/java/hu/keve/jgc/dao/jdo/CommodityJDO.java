package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.PersistenceCapable;

import hu.keve.jgc.dao.Commodity;

@PersistenceCapable
public final class CommodityJDO extends GuidTypeJDO implements Commodity  {
	String namespace; // TODO: Enum
	String mnemonic;
	String fullname;
	String cusip;
	int fraction;
	boolean quoteFlag;
	String quoteSource;
	String quoteTz;
	
	Collection<SlotJDO> slots;

	
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
		return (long)fraction;
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
