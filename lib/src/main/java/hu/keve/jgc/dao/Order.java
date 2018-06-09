package hu.keve.jgc.dao;

import java.time.LocalDateTime;

public interface Order extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
	String getId();

	String getNotes();

	String getReference();

	boolean isActive();

	LocalDateTime getDateOpened();

	LocalDateTime getDateClosed();

	Owner getOwner();

}