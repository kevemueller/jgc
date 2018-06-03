package hu.keve.jgc.dao;

import java.util.Collection;

public interface Taxtable {

	String getGuid();

	String getName();

	long getRefcount();

	boolean isInvisible();

	Taxtable getParent();

	Collection<? extends TaxtableEntry> getEntries();
}