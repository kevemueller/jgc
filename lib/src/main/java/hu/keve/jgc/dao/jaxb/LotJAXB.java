package hu.keve.jgc.dao.jaxb;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Lot;

public interface LotJAXB extends Lot {
	default Account getAccount() {
		return null;
	}
	default boolean isClosed() {
		return false;
	}
}
