package hu.keve.jgc;

import java.io.File;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class GnuCashDBSource {
	private GnuCashDBSource() {
	}

	public static PersistenceManagerFactory openSQLite3(final File file) {
		Properties properties = new Properties();
		String url = "jdbc:sqlite:" + file.toString() + "?" + String.join("&", "open_mode=1");
		properties.setProperty("javax.jdo.option.ConnectionURL", url);
		properties.setProperty("datanucleus.readOnlyDatastore", "true");

		return JDOHelper.getPersistenceManagerFactory(properties, "jgc");
	}
}
