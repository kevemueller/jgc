package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.PersistenceCapable;

import hu.keve.jgc.dao.Commodity;

@PersistenceCapable
public final class CommodityJDO extends AbstractGuidTypeJDO implements Commodity {
	public static final String NAME = "commodities";
	public static final int VERSION = 1;

	String namespace; // TODO: Enum
	String mnemonic;
	String fullname;
	String cusip;
	int fraction;
	boolean quoteFlag;
	String quoteSource;
	String quoteTz;

	/** getters **/

	@Override
	public String getNamespace() {
		return namespace;
	}

	@Override
	public String getMnemonic() {
		return mnemonic;
	}

	@Override
	public String getFullname() {
		return fullname;
	}

	@Override
	public String getCusip() {
		return cusip;
	}

	@Override
	public long getFraction() {
		return fraction;
	}

	@Override
	public boolean isSetQuoteFlag() {
		return quoteFlag;
	}

	@Override
	public String getQuoteSource() {
		return quoteSource;
	}

	@Override
	public String getQuoteTz() {
		return quoteTz;
	}

	/** restricted setters **/
	void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	/** setters */
	@Override
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	@Override
	public void setFraction(long fraction) {
		this.fraction = (int) fraction;
	}

	@Override
	public void setQuoteFlag(boolean quoteFlag) {
		this.quoteFlag = quoteFlag;
	}

	@Override
	public void setQuoteSource(String quoteSource) {
		this.quoteSource = quoteSource;
	}

	@Override
	public void setQuoteTz(String quoteTz) {
		this.quoteTz = quoteTz;
	}
}
