package hu.keve.jgc.dao.jdo;

import java.time.LocalDateTime;

import hu.keve.jgc.dao.Price;
import hu.keve.jgc.util.Fraction;

public final class PriceJDO extends AbstractGuidTypeJDO implements Price {
	CommodityJDO commodity;
	CommodityJDO currency;
	LocalDateTime date;
	String source;

	PriceTypes type;
	Fraction value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Price#getCommodity()
	 */
	@Override
	public CommodityJDO getCommodity() {
		return commodity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Price#getCurrency()
	 */
	@Override
	public CommodityJDO getCurrency() {
		return currency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Price#getDate()
	 */
	@Override
	public LocalDateTime getDate() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Price#getSource()
	 */
	@Override
	public String getSource() {
		return source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Price#getType()
	 */
	@Override
	public PriceTypes getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.keve.jgc.dao.jdo.Price#getValue()
	 */
	@Override
	public Fraction getValue() {
		return value;
	}

}
