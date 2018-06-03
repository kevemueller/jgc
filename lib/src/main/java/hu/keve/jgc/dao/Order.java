package hu.keve.jgc.dao;

import java.time.LocalDateTime;
import java.util.Date;

public interface Order {
	String getId();

	String getNotes();

	String getReference();

	boolean isActive();

	LocalDateTime getDateOpened();

	LocalDateTime getDateClosed();

	Owner getOwner();

}