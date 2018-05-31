package hu.keve.jgc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.sql.SQLException;

import javax.jdo.Extent;
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
		File file = new File("CJ.sqlite3.gnucash");
		// properties.setProperty("datanucleus.rdbms.allowColumnReuse", "true");
		PersistenceManagerFactory pmf = GnuCashDBSource.openSQLite3(file);

		PersistenceManager pm = pmf.getPersistenceManager();

		SlotJDO slot = pm.getObjectById(SlotJDO.class, 581);
		System.out.println(slot);
		for (SlotJDO x : slot.getSlots(pm)) {
			System.out.println(x);
		}
	}
}
