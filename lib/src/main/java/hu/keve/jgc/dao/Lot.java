package hu.keve.jgc.dao;

public interface Lot extends GuidType {
	Account getAccount();

	boolean isClosed();

}