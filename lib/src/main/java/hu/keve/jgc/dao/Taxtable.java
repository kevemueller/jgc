package hu.keve.jgc.dao;

import java.util.Collection;

public interface Taxtable extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}

	String getName();

	long getRefcount();

	boolean isInvisible();

	Taxtable getParent();

	Collection<? extends TaxtableEntry> getEntries();
}