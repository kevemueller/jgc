package hu.keve.jgc.dao.jdo;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Price;
import hu.keve.jgc.util.Fraction;

@PersistenceCapable(table = "prices")
public final class PriceJDO implements Price {
	@PrimaryKey
	String guid;

	@Column(name = "commodity_guid")
	CommodityJDO commodity;
	@Column(name = "currency_guid")
	CommodityJDO currency;
	Date date;
	String source;
	
	@Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "toValue")
	PriceTypes type;

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "value_num"),
			@Persistent(name = "denom", column = "value_denom") })
	Fraction value;

	public String getGuid() {
		return guid;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Price#getCommodity()
	 */
	@Override
	public CommodityJDO getCommodity() {
		return commodity;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Price#getCurrency()
	 */
	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Price#getDate()
	 */
	@Override
	public Date getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Price#getSource()
	 */
	@Override
	public String getSource() {
		return source;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Price#getType()
	 */
	@Override
	public PriceTypes getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Price#getValue()
	 */
	@Override
	public Fraction getValue() {
		return value;
	}
	
	
}
