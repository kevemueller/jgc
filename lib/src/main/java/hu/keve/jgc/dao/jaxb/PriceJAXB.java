package hu.keve.jgc.dao.jaxb;

import java.time.LocalDateTime;

import hu.keve.jgc.dao.Price;
import hu.keve.jgc.dao.jaxb.unwrapper.DateUnwrapper;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;

public interface PriceJAXB extends Price, GuidUnwrapper, DateUnwrapper {
	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}

	@Override
	default LocalDateTime getDate() {
		return DateUnwrapper.super.getDate();
	}
}
