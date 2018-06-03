package hu.keve.jgc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.sql.SQLException;
import java.util.Properties;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import hu.keve.jgc.dao.jdo.InvoiceJDO;
import hu.keve.jgc.dao.jdo.SlotJDO;

class TestSQLite {
	public TestSQLite() throws SQLException {
	}

	
	@Test
	void testJDOCreate() {
		File file = new File("test.db");
		if (file.exists()) {
			file.delete();
		}
		Properties properties = new Properties();
		String url = "jdbc:sqlite:" + file.toString();
		properties.setProperty("javax.jdo.option.ConnectionURL", url);
		properties.setProperty("datanucleus.generateSchema.database.mode", "drop-and-create");
		properties.setProperty("datanucleus.rdbms.allowColumnReuse", "true");

		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(properties, "jgc");
	}
	
	
	/**
	 * Open SQLite database file and go through all of the extents, throws Exception
	 * if DAO maps incorrectly to the DB tables.
	 * 
	 * @param fileName
	 * @throws SQLException
	 */
	@DisplayName("Load JDO resource")
	@ParameterizedTest(name = "{index} => resource=''{0}''")
	@ValueSource(strings = { "src/test/resources/Simple.sqlite3.gnucash", "src/test/resources/Full.sqlite3.gnucash",
			"CJ.sqlite3.gnucash", "PhA.sqlite3.gnucash" })
	void testJDO(String fileName) throws SQLException {
		File file = new File(fileName);
		assertTrue(file.exists());
		PersistenceManagerFactory pmf = GnuCashDBSource.openSQLite3(file);
		PersistenceManager pm = pmf.getPersistenceManager();

		for (Class<?> managedClass : pmf.getManagedClasses()) {
			System.out.println(managedClass);
			if (InvoiceJDO.class.equals(managedClass)) {
				System.out.println("here");
			}
			Extent<?> extent = pm.getExtent(managedClass, true);
			for (Object slot : extent) {
				System.out.println(slot);
			}
		}
	}

	@Test
	void testJDOSlot() throws SQLException {
		File file = new File("src/test/resources/Full.sqlite3.gnucash");
		// properties.setProperty("datanucleus.rdbms.allowColumnReuse", "true");
		PersistenceManagerFactory pmf = GnuCashDBSource.openSQLite3(file);

		PersistenceManager pm = pmf.getPersistenceManager();

		SlotJDO slot = pm.getObjectById(SlotJDO.class, 1);
		System.out.println(slot);
		for (SlotJDO x : slot.getSlots(pm)) {
			System.out.println(x);
		}
	}
}
