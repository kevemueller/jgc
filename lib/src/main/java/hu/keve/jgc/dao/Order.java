package hu.keve.jgc.dao;

import java.util.Date;

public interface Order {
	String getId();

	String getNotes();

	String getReference();

	boolean isActive();

	Date getDateOpened();

	Date getDateClosed();

	Owner getOwner();

}