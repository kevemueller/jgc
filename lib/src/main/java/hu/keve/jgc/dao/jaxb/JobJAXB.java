package hu.keve.jgc.dao.jaxb;

import hu.keve.jgc.dao.Job;
import hu.keve.jgc.dao.jaxb.unwrapper.GuidUnwrapper;

public interface JobJAXB extends Job, GuidUnwrapper{
	@Override
	default String getGuid() {
		return GuidUnwrapper.super.getGuid();
	}
}
