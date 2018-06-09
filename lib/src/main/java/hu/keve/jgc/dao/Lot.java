package hu.keve.jgc.dao;

public interface Lot extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	Account getAccount();

	boolean isClosed();

}