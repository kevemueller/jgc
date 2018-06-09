package hu.keve.jgc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.params.ParameterizedTest;

import hu.keve.jgc.GnuCash;
import hu.keve.jgc.Jgc;
import hu.keve.jgc.util.Util;
import hu.keve.jgc.util.junit.PathsSource;

class TestSimple {
	public String ID = "f3e24833be9744aab8d8d983b1a5ff6a";

	@ParameterizedTest
	@PathsSource(path = "src/test/resources", regexp = ".*Simple\\.(sqlite3|xml)\\.gnucash")
	void testOpen(Path path) throws Exception {
		File file = path.toFile();
		assertTrue(file.exists());
		try (GnuCash gc = Jgc.fromFile(file, true)) {

		}
	}

	@ParameterizedTest
	@PathsSource(path = "src/test/resources", regexp = ".*Simple\\.(sqlite3|xml)\\.gnucash")
	void testCount(Path path) throws Exception {
		File file = path.toFile();
		assertTrue(file.exists());
		try (GnuCash gc = Jgc.fromFile(file, true)) {
			Book book = gc.getBook();
			// assertEquals(1, Util.size(book.getAllCommodities()));
			// TODO: how do we handle the spurious template/template commodity
			// assertEquals(9, Util.size(book.getAllAccounts()));
			// TODO: how do we handle the root template mismatch
			assertEquals(3, Util.size(book.getAllTransactions()));
		}
	}

	private static class AccountName {
		private final String name;
		private final Set<AccountName> children;

		AccountName(String name) {
			this(name, new AccountName[0]);
		}

		AccountName(String name, AccountName[] children) {
			this.name = name;
			this.children = Set.of(children);
		}

		AccountName(Account account) {
			this.name = account.getName();
			this.children = new HashSet<AccountName>();
			account.getChildren().forEach(a -> {
				children.add(new AccountName(a));
			});
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, children);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (null == obj) {
				return false;
			}
			if (obj instanceof AccountName) {
				return Objects.equals(name, ((AccountName) obj).name) && children.equals(((AccountName) obj).children);
			}
			return false;
		}
	}

	AccountName expectedRoot = new AccountName("Root Account",
			new AccountName[] {
					new AccountName("Assets",
							new AccountName[] { new AccountName("Current Assets",
									new AccountName[] { new AccountName("Checking Account") }), }),
					new AccountName("Equity", new AccountName[] { new AccountName("Opening Balances") }),
					new AccountName("Expenses"), new AccountName("Income") });

	@ParameterizedTest
	@PathsSource(path = "src/test/resources", regexp = ".*Simple\\.(sqlite3|xml)\\.gnucash")
	void testAccountHierarchy(Path path) throws Exception {
		File file = path.toFile();
		assertTrue(file.exists());
		try (GnuCash gc = Jgc.fromFile(file, true)) {
			Book book = gc.getBook();
			Account rootAccount = book.getRootAccount();

			AccountName rootAccountName = new AccountName(rootAccount);
			assertEquals(expectedRoot, rootAccountName);
		}
	}

	@ParameterizedTest
	@PathsSource(path = "src/test/resources", regexp = ".*Simple\\.(sqlite3|xml)\\.gnucash")
	void testTransactions(Path path) throws Exception {
		File file = path.toFile();
		assertTrue(file.exists());
		try (GnuCash gc = Jgc.fromFile(file, true)) {
			Book book = gc.getBook();
			Iterable<? extends Transaction> tx = book.getAllTransactions();
			assertEquals(3, Util.size(tx));
			assertEquals(0, Util.size(book.getTransactionsBetween(LocalDateTime.parse("2017-01-01T00:00:00"),
					LocalDateTime.parse("2018-01-01T00:00:00"))));
			assertEquals(0, Util.size(book.getTransactionsBetween(null, LocalDateTime.parse("2018-01-01T00:00:00"))));

			assertEquals(0, Util.size(book.getTransactionsBetween(LocalDateTime.parse("2018-05-01T00:00:00"), null)));
			assertEquals(0, Util.size(book.getTransactionsBetween(LocalDateTime.parse("2017-01-01T00:00:00"),
					LocalDateTime.parse("2018-01-05T00:00:00"))));
			assertEquals(1, Util.size(book.getTransactionsBetween(LocalDateTime.parse("2017-01-01T00:00:00"),
					LocalDateTime.parse("2018-01-06T00:00:00"))));
			assertEquals(1, Util.size(book.getTransactionsBetween(LocalDateTime.parse("2018-01-05T00:00:00"),
					LocalDateTime.parse("2018-01-06T00:00:00"))));
			assertEquals(2, Util.size(book.getTransactionsBetween(LocalDateTime.parse("2018-01-05T00:00:00"),
					LocalDateTime.parse("2018-01-08T00:00:00"))));
		}
	}

	@ParameterizedTest
	@PathsSource(path = "src/test/resources", regexp = ".*Simple\\.(sqlite3|xml)\\.gnucash")
	void testSlot(final Path path) throws Exception {
		File file = path.toFile();
		assertTrue(file.exists());
		try (GnuCash gc = Jgc.fromFile(file, true)) {
			Account account = gc.getById(Account.class, ID);
			assertEquals("Assets", account.getName());
			assertEquals("true", account.getSlotValue("placeholder"));
			assertEquals(1, account.getSlots().size());
			Entry<String, Object> slotEntry = account.getSlots().entrySet().iterator().next();
			assertEquals("placeholder", slotEntry.getKey());
			assertEquals("true", slotEntry.getValue());

			Book book = gc.getBook();

			assertTrue(book.getSlotValue("options") instanceof Map);
			assertEquals(1, book.getSlots().size());
			slotEntry = book.getSlots().entrySet().iterator().next();
			assertEquals("options", slotEntry.getKey());
			Map<String, Object> frame = (Map<String, Object>) slotEntry.getValue();
			assertEquals(1, frame.size());
			Entry<String, Object> frameEntry = frame.entrySet().iterator().next();
			assertEquals("Budgeting", frameEntry.getKey());
			assertEquals(Collections.emptyMap(), frameEntry.getValue());
		}
	}
}
