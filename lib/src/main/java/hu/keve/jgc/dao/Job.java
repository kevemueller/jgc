package hu.keve.jgc.dao;

public interface Job extends GuidType {
	@Override
	default String getBusinessKey() {
		// TODO Auto-generated method stub
		return null;
	}


	String getId();

	String getName();

	String getReference();

	boolean isActive();

	Owner getOwner();

}