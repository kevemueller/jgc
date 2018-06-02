package hu.keve.jgc.dao.jaxb;

import hu.keve.jgc.dao.Price;

public interface PriceJAXB extends Price, GuidUnwrapper {
	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}
}
