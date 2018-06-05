package hu.keve.jgc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.params.ParameterizedTest;

import hu.keve.jgc.GnuCash;
import hu.keve.jgc.Jgc;
import hu.keve.jgc.util.Util;
import hu.keve.jgc.util.junit.PathsSource;

class TestSimple {

	@ParameterizedTest
	@PathsSource(path = "src/test/resources", regexp = ".*Simple.*gnucash")
	void testOpen(Path path) throws Exception {
		File file = path.toFile();
		assertTrue(file.exists());
		try (GnuCash gc = Jgc.fromFile(file, true)) {

		}
	}

	@ParameterizedTest
	@PathsSource(path = "src/test/resources", regexp = ".*Simple.*gnucash")
	void testCount(Path path) throws Exception {
		File file = path.toFile();
		assertTrue(file.exists());
		try (GnuCash gc = Jgc.fromFile(file, true)) {
			Book book = gc.getBook();
			assertEquals(1, Util.size(book.getAllCommodities()));
			assertEquals(9, Util.size(book.getAllAccounts()));
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
	@PathsSource(path = "src/test/resources", regexp = ".*Simple.*gnucash")
	void testAccount(Path path) throws Exception {
		File file = path.toFile();
		assertTrue(file.exists());
		try (GnuCash gc = Jgc.fromFile(file, true)) {
			Book book = gc.getBook();
			Account rootAccount = book.getRootAccount();

			AccountName rootAccountName = new AccountName(rootAccount);
			assertEquals(expectedRoot, rootAccountName);
		}
	}

}
