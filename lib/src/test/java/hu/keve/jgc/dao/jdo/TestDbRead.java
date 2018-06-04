package hu.keve.jgc.dao.jdo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.metadata.TypeMetadata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import hu.keve.jgc.GnuCash;
import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Slot;

class TestDbRead {

	public String ID = "19cf223f9aeb4238878eaab32affdf80";

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
		
//		GnuCashJDO.fromPostgreSQL("u45002", "gcfull", "keve", "");

		try (GnuCash gc = GnuCashJDO.fromFile(file, true, false)) {
			PersistenceManagerFactory pmf = ((GnuCashJDO) gc).getPersistenceManagerFactory();
			PersistenceManager pm = pmf.getPersistenceManagerProxy();
			for (Class<?> managedClass : pmf.getManagedClasses()) {
				TypeMetadata mcmd = pmf.getMetadata(managedClass.getName());
				if (null != mcmd.getTable()) {
					System.out.println(managedClass);
					Extent<?> extent = pm.getExtent(managedClass, true);
					for (Object slot : extent) {
						System.out.println(slot);
					}
				}
			}
		}
	}

	@Test
	void testJDOSlot() throws Exception {
		File file = new File("src/test/resources/Full.sqlite3.gnucash");
		assertTrue(file.exists());
		try (GnuCash gc = GnuCashJDO.fromFile(file, true, false)) {
			System.out.println("Account with slots");

			Account account = gc.getById(Account.class, ID);
			System.out.println(account.getName());
			for (Slot aslot : account.getSlots()) {
				System.out.println(aslot);
			}

			System.out.println("Slot with slots");
			Slot slot = gc.getById(Slot.class, 1);
			System.out.println(slot);
			for (Slot xslot : slot.getSlots()) {
				System.out.println(xslot);
			}
		}
	}
}
