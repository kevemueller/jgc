package hu.keve.jgc.dao.jdo;

import hu.keve.jgc.dao.Owner;

public class OwnerJDO implements Owner {
	OwnerType type;
	String guid;

	public OwnerType getType() {
		return type;
	}

	public String getGuid() {
		return guid;
	}
}
