package hu.keve.jgc.dao;

public interface Job {

	String getGuid();

	String getId();

	String getName();

	String getReference();

	boolean isActive();

	int getOwnerType();

	Customer getOwner();

}