package hu.keve.jgc.dao;

import java.util.Collection;

public interface Book {

	Account getRootAccount();

	Account getRootTemplate();

	Collection<? extends Slot> getSlots();

}