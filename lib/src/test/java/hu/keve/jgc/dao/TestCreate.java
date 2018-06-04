package hu.keve.jgc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import hu.keve.jgc.GnuCash;
import hu.keve.jgc.dao.jaxb.AbstractGnuCashJAXB;
import hu.keve.jgc.dao.jdo.GnuCashJDO;

class TestCreate {

	static enum Modes {
		XMLFILE, SQLITEFILE// PGSQLDB;
	}

	File file;

	@BeforeEach
	void removeFile() {
		file = new File("test.gnucash");
		if (file.exists()) {
			file.delete();
		}
	}

	GnuCash create(Modes mode) throws IOException, JAXBException {
		switch (mode) {
		case XMLFILE:
			return AbstractGnuCashJAXB.fromFile(file, false, true);
		case SQLITEFILE:
			return GnuCashJDO.fromFile(file, false, true);
		default:
			throw new IllegalArgumentException();
		}
	}

	@ParameterizedTest
	@EnumSource(Modes.class)
	void testSimpleBook(Modes mode) throws Exception {
		try (GnuCash gc = create(mode)) {
			Book book = gc.createBook("CURRENCY", "JPY");
			gc.commit();
		}
		assertTrue(file.exists());
	}

	@ParameterizedTest
	@EnumSource(Modes.class)
	void testNoBook(Modes mode) throws Exception {
		Throwable ex = assertThrows(IllegalArgumentException.class, () -> {
			try (GnuCash gc = create(mode)) {
			}
		});
		assertEquals("must have a book", ex.getMessage());
		assertFalse(file.exists());
	}

	@ParameterizedTest
	@EnumSource(Modes.class)
	void testOneBook(Modes mode) throws Exception {
		Throwable ex = assertThrows(IllegalArgumentException.class, () -> {
			try (GnuCash gc = create(mode)) {
				Book book = gc.createBook("CURRENCY", "JPY");
				Book book2 = gc.createBook("CURRENCY", "JPY");
				gc.commit();
			}
		});
		assertEquals("can store only one book", ex.getMessage());
		// assertFalse(file.exists());
	}

	@ParameterizedTest
	@EnumSource(Modes.class)
	void testDedup(Modes mode) throws Exception {
		try (GnuCash gc = create(mode)) {
			Book book = gc.createBook("CURRENCY", "EUR");
			Commodity xtsCommodity = book.createCommodity("CURRENCY", "XTS");
			Commodity eurCommodity = book.createCommodity("CURRENCY", "EUR");
			Account rootAccount = book.getRootAccount();
			Account expensesAccount = book.createAccount(rootAccount, "Expenses", Account.AccountTypes.EXPENSE,
					rootAccount.getCommodity());
			book.createAccount(expensesAccount, "Expenses XTS", Account.AccountTypes.EXPENSE, xtsCommodity);
			book.createAccount(expensesAccount, "Expenses EUR", Account.AccountTypes.EXPENSE, eurCommodity);
			gc.commit();
		}
	};
}
