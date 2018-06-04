package hu.keve.jgc.dao;

import java.util.UUID;

public final class DaoUtil {
	private DaoUtil() {

	}
	
	public static String makeGuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
