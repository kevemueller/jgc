package hu.keve.jgc.dao.jaxb;

import hu.keve.jgc.dao.Billterm;

public interface BilltermJAXB extends Billterm, GuidUnwrapper {
	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}
}
