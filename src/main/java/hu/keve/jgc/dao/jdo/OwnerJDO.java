package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;

import hu.keve.jgc.dao.Owner;

@PersistenceCapable(embeddedOnly = "true")
public class OwnerJDO implements Owner {
	@Extension(vendorName="datanucleus", key="enum-value-getter", value="toIntValue")
	OwnerType type;
	String guid;

	public OwnerType getType() {
		return type;
	}

	public String getGuid() {
		return guid;
	}
}
