package hu.keve.jgc.dao;

import java.util.Collection;

public interface Lot {

//	String getGuid();
//
//	Account getAccount();
//
//	boolean isClosed();

	Collection<? extends Slot> getSlots();

}