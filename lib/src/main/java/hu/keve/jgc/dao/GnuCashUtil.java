package hu.keve.jgc.dao;

import java.util.UUID;

public final class GnuCashUtil {
	private GnuCashUtil() {

	}
	
	public static String makeGuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
