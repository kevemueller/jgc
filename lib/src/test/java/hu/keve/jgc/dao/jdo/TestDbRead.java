package hu.keve.jgc.dao.jdo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.metadata.TypeMetadata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import hu.keve.jgc.GnuCash;

class TestDbRead {
	/**
	 * Open SQLite database file and go through all of the extents, throws Exception
	 * if DAO maps incorrectly to the DB tables.
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	@DisplayName("Load JDO resource")
	@ParameterizedTest(name = "{index} => resource=''{0}''")
	@ValueSource(strings = { "src/test/resources/Simple.sqlite3.gnucash", "src/test/resources/Full.sqlite3.gnucash" })
	void testJDO(String fileName) throws Exception {
		File file = new File(fileName);
		assertTrue(file.exists());

		// GnuCashJDO.fromPostgreSQL("u45002", "gcfull", "keve", "");

		try (GnuCash gc = GnuCashJDO.fromFile(file, true, false)) {
			PersistenceManagerFactory pmf = ((GnuCashJDO) gc).getPersistenceManagerFactory();
			PersistenceManager pm = pmf.getPersistenceManagerProxy();
			for (Class<?> managedClass : pmf.getManagedClasses()) {
				TypeMetadata mcmd = pmf.getMetadata(managedClass.getName());
				if (null != mcmd.getTable()) {
					System.out.println(managedClass);
					Extent<?> extent = pm.getExtent(managedClass, true);
					for (Object o : extent) {
						System.out.println(o);
					}
				}
			}
		}
	}

	
}
