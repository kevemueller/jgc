package hu.keve.jgc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.io.IOException;
import java.util.Currency;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import hu.keve.jgc.GnuCash;
import hu.keve.jgc.dao.jaxb.AbstractGnuCashJAXB;
import hu.keve.jgc.dao.jdo.GnuCashJDO;

class TestCreate {
	File file;

	@BeforeEach
	void removeFile() {
		file = new File("test.gnucash");
		if (file.exists()) {
			file.delete();
		}
		assertFalse(file.exists());
	}

	GnuCash create(GnuCash.Backends mode) throws IOException, JAXBException {
		assumeTrue(mode.isLocal());
		switch (mode) {
		case XML:
			return AbstractGnuCashJAXB.fromFile(file, false, true);
		case SQLITE:
			return GnuCashJDO.fromFile(file, false, true);
		default:
			throw new IllegalArgumentException();
		}
	}

	@ParameterizedTest
	@EnumSource(GnuCash.Backends.class)
	void testSimpleBook(GnuCash.Backends mode) throws Exception {
		try (GnuCash gc = create(mode)) {
			Book book = gc.createBook(Currency.getInstance("JPY"));
			gc.commit();
		}
		assertTrue(file.exists());
	}

	@ParameterizedTest
	@EnumSource(GnuCash.Backends.class)
	void testNoBook(GnuCash.Backends mode) throws Exception {
		assumeTrue(mode.isLocal());
		Throwable ex = assertThrows(IllegalArgumentException.class, () -> {
			try (GnuCash gc = create(mode)) {
			}
		});
		assertEquals("must have a book", ex.getMessage());
		assertFalse(file.exists());
	}

	@ParameterizedTest
	@EnumSource(GnuCash.Backends.class)
	void testOneBook(GnuCash.Backends mode) throws Exception {
		assumeTrue(mode.isLocal());
		Throwable ex = assertThrows(IllegalArgumentException.class, () -> {
			try (GnuCash gc = create(mode)) {
				Book book = gc.createBook(Currency.getInstance("JPY"));
				Book book2 = gc.createBook(Currency.getInstance("JPY"));
				gc.commit();
			}
		});
		assertEquals("can store only one book", ex.getMessage());
		// assertFalse(file.exists());
	}

	@ParameterizedTest
	@EnumSource(GnuCash.Backends.class)
	void testDedup(GnuCash.Backends mode) throws Exception {
		try (GnuCash gc = create(mode)) {
			Book book = gc.createBook(Currency.getInstance("EUR"));
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
