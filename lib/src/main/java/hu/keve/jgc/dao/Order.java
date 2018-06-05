package hu.keve.jgc.dao;

import java.time.LocalDateTime;

public interface Order {
	String getId();

	String getNotes();

	String getReference();

	boolean isActive();

	LocalDateTime getDateOpened();

	LocalDateTime getDateClosed();

	Owner getOwner();

}