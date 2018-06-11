package hu.keve.jgc.dao.jdo;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import hu.keve.jgc.GnuCash;
import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Billterm;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.Budget;
import hu.keve.jgc.dao.BudgetAmount;
import hu.keve.jgc.dao.Commodity;
import hu.keve.jgc.dao.Customer;
import hu.keve.jgc.dao.DaoUtil;
import hu.keve.jgc.dao.Employee;
import hu.keve.jgc.dao.Entry;
import hu.keve.jgc.dao.Invoice;
import hu.keve.jgc.dao.Job;
import hu.keve.jgc.dao.Lot;
import hu.keve.jgc.dao.Order;
import hu.keve.jgc.dao.Price;
import hu.keve.jgc.dao.Recurrence;
import hu.keve.jgc.dao.Schedxaction;
import hu.keve.jgc.dao.Slot;
import hu.keve.jgc.dao.Split;
import hu.keve.jgc.dao.Taxtable;
import hu.keve.jgc.dao.TaxtableEntry;
import hu.keve.jgc.dao.Transaction;
import hu.keve.jgc.dao.Vendor;

public class GnuCashJDO implements GnuCash {
	public static final String NAME = "Gnucash";
	public static final int VERSION = 3000001;
	public static final String NAME2 = "Gnucash-Resave";
	public static final int VERSION2 = 19920;

	private static final class TypeMetaData<T> {
		final Class<T> daoInterface;
		final Class<? extends T> jdoImplementation;
		final String name;
		final int version;

		TypeMetaData(final Class<T> daoInterface, final Class<? extends T> jdoImplementation, final String name,
				final int version) {
			this.daoInterface = daoInterface;
			this.jdoImplementation = jdoImplementation;
			this.name = name;
			this.version = version;
		}
	}

	static TypeMetaData<?>[] typeMetadata = {
			new TypeMetaData<GnuCash>(GnuCash.class, GnuCashJDO.class, GnuCashJDO.NAME, GnuCashJDO.VERSION),
			new TypeMetaData<GnuCash>(GnuCash.class, GnuCashJDO.class, GnuCashJDO.NAME2, GnuCashJDO.VERSION2),
			new TypeMetaData<Account>(Account.class, AccountJDO.class, AccountJDO.NAME, AccountJDO.VERSION),
			new TypeMetaData<Billterm>(Billterm.class, BilltermJDO.class, BilltermJDO.NAME, BilltermJDO.VERSION),
			new TypeMetaData<Book>(Book.class, BookJDO.class, BookJDO.NAME, BookJDO.VERSION),
			new TypeMetaData<BudgetAmount>(BudgetAmount.class, BudgetAmountJDO.class, BudgetAmountJDO.NAME,
					BudgetAmountJDO.VERSION),
			new TypeMetaData<Budget>(Budget.class, BudgetJDO.class, BudgetJDO.NAME, BudgetJDO.VERSION),
			new TypeMetaData<Commodity>(Commodity.class, CommodityJDO.class, CommodityJDO.NAME, CommodityJDO.VERSION),
			new TypeMetaData<Customer>(Customer.class, CustomerJDO.class, CustomerJDO.NAME, CustomerJDO.VERSION),
			new TypeMetaData<Employee>(Employee.class, EmployeeJDO.class, EmployeeJDO.NAME, EmployeeJDO.VERSION),
			new TypeMetaData<Entry>(Entry.class, EntryJDO.class, EntryJDO.NAME, EntryJDO.VERSION),
			new TypeMetaData<Invoice>(Invoice.class, InvoiceJDO.class, InvoiceJDO.NAME, InvoiceJDO.VERSION),
			new TypeMetaData<Job>(Job.class, JobJDO.class, JobJDO.NAME, JobJDO.VERSION),
			new TypeMetaData<Lot>(Lot.class, LotJDO.class, LotJDO.NAME, LotJDO.VERSION),
			new TypeMetaData<Order>(Order.class, OrderJDO.class, OrderJDO.NAME, OrderJDO.VERSION),
			new TypeMetaData<Price>(Price.class, PriceJDO.class, PriceJDO.NAME, PriceJDO.VERSION),
			new TypeMetaData<Recurrence>(Recurrence.class, RecurrenceJDO.class, RecurrenceJDO.NAME,
					RecurrenceJDO.VERSION),
			new TypeMetaData<Schedxaction>(Schedxaction.class, SchedxactionJDO.class, SchedxactionJDO.NAME,
					SchedxactionJDO.VERSION),
			new TypeMetaData<Slot>(Slot.class, SlotJDO.class, SlotJDO.NAME, SlotJDO.VERSION),
			new TypeMetaData<Split>(Split.class, SplitJDO.class, SplitJDO.NAME, SplitJDO.VERSION),
			new TypeMetaData<TaxtableEntry>(TaxtableEntry.class, TaxtableEntryJDO.class, TaxtableEntryJDO.NAME,
					TaxtableEntryJDO.VERSION),
			new TypeMetaData<Taxtable>(Taxtable.class, TaxtableJDO.class, TaxtableJDO.NAME, TaxtableJDO.VERSION),
			new TypeMetaData<Transaction>(Transaction.class, TransactionJDO.class, TransactionJDO.NAME,
					TransactionJDO.VERSION),
			new TypeMetaData<Vendor>(Vendor.class, VendorJDO.class, VendorJDO.NAME, VendorJDO.VERSION), };

	private final File file;
	private final boolean readOnly;
	private final PersistenceManagerFactory persistenceManagerFactory;
	private final PersistenceManager persistenceManager;
	private javax.jdo.Transaction tx;

	private GnuCashJDO(final File file, final PersistenceManagerFactory persistenceManagerFactory,
			final boolean readOnly) {
		this.file = file;
		this.persistenceManagerFactory = persistenceManagerFactory;
		this.readOnly = readOnly;
		this.persistenceManager = persistenceManagerFactory.getPersistenceManager();
		this.tx = persistenceManager.currentTransaction();
		tx.begin();

		obtainLock();
		// TODO: validate schema
		validateVersions();
	}

	private GncLockJDO getLockObject() {
		try {
			return new GncLockJDO(InetAddress.getLocalHost().getHostName(), ProcessHandle.current().pid());
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void obtainLock() {
		if (readOnly) {
			return;
		}
		Iterator<GncLockJDO> lock = persistenceManager.getExtent(GncLockJDO.class).iterator();
		if (lock.hasNext()) {
			throw new IllegalArgumentException("Locked: " + lock.next());
		}
		persistenceManager.makePersistent(getLockObject());
		tx.commit();
		tx.begin();
	}

	private void validateVersions() {
		HashMap<String, Integer> versions = new HashMap<String, Integer>();
		persistenceManager.getExtent(VersionJDO.class).forEach(v -> {
			versions.put(v.tableName, v.tableVersion);
		});
		for (TypeMetaData<?> tmd : typeMetadata) {
			Integer dbVersion = versions.get(tmd.name);
			if (null == dbVersion) {
				persistenceManager.makePersistent(new VersionJDO(tmd.name, tmd.version));
			} else {
				if (tmd.version != dbVersion) {
					throw new IllegalArgumentException("DB schema mismatch " + tmd.name);
				}
			}
		}
	}

	@Override
	public Book getBook() {
		Extent<BookJDO> books = persistenceManager.getExtent(BookJDO.class);
		BookJDO book = null;
		for (BookJDO b : books) {
			if (null != book) {
				throw new IllegalArgumentException("can store only one book");
			}
			book = b;
		}
		return book;
	}

	@Override
	public Book createBook(Currency rootCurrency) {
		Extent<BookJDO> books = persistenceManager.getExtent(BookJDO.class);
		if (books.iterator().hasNext()) {
			throw new IllegalArgumentException("can store only one book");
		}
		BookJDO book = new BookJDO();
		book.setGuid(DaoUtil.makeGuid());
		CommodityJDO commodity = new CommodityJDO();
		commodity.newGuid();
		commodity.setNamespace("CURRENCY");
		commodity.setMnemonic(rootCurrency.getCurrencyCode());
		commodity.setCurrency(rootCurrency);
		AccountJDO account = new AccountJDO();
		account.newGuid();
		account.setName("Root account");
		account.setAccountType(AccountTypes.ROOT);
		account.setCommodity(commodity);
		book.setRootAccount(account);
		account = new AccountJDO();
		account.newGuid();
		account.setName("Template ROOT");
		account.setAccountType(AccountTypes.ROOT);
		book.setRootTemplate(account);
		return persistenceManager.makePersistent(book);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Class<T> idType, Object id) {
		for (TypeMetaData tmd : typeMetadata) {
			if (idType.isAssignableFrom(tmd.daoInterface)) {
				// Object oid = persistenceManager.newObjectIdInstance(tmd.jdoImplementation,
				// id);
				// return (T) persistenceManager.getObjectById(tmd.jdoImplementation, oid);
				return (T) persistenceManager.getObjectById(tmd.jdoImplementation, id);
			}
		}
		throw new IllegalArgumentException(idType.getName());
	}

	@Override
	public void commit() {
		if (tx.isActive()) {
			tx.commit();
			tx.begin();
		}
	}

	@Override
	public void close() {
		if (tx.isActive()) {
			tx.rollback();
		}
		tx.begin();
		Book book = getBook();
		GncLockJDO myLock = getLockObject();
		for (GncLockJDO lock : persistenceManager.getExtent(GncLockJDO.class)) {
			if (lock.equals(myLock)) {
				persistenceManager.deletePersistent(lock);
				break;
			}
		}
		tx.commit();
		persistenceManager.close();
		persistenceManagerFactory.close();
		if (null == book) {
			file.delete();
			throw new IllegalArgumentException("must have a book");
		}
	}

	public static GnuCash fromFile(File file, boolean readOnly, boolean create) {
		Properties properties = new Properties();
		String url = "jdbc:sqlite:" + file.toString();
		if (readOnly) {
			url += "?" + String.join("&", "open_mode=1");
			properties.setProperty("datanucleus.readOnlyDatastore", "true");
		}
		properties.setProperty("javax.jdo.option.ConnectionDriverName", "org.sqlite.JDBC");
		properties.setProperty("javax.jdo.option.Mapping", "sqlite");
		properties.setProperty("javax.jdo.option.ConnectionURL", url);

		if (create) {
			properties.setProperty("datanucleus.generateSchema.database.mode", "drop-and-create");
		}
		return new GnuCashJDO(file, JDOHelper.getPersistenceManagerFactory(properties, "jgc"), readOnly);
	}

	public static GnuCash fromDB(Backends backend, String host, String db, String username, String password,
			boolean readOnly, boolean create) {
		Properties properties = new Properties();
		String url;
		switch (backend) {
		case PGSQL:
			url = "jdbc:postgresql://" + host + ":5432/" + db;
			properties.setProperty("javax.jdo.option.ConnectionDriverName", "org.postgresql.Driver");
			properties.setProperty("javax.jdo.option.Mapping", "postgresql");
			break;
		case MYSQL:
			url = "jdbc:mysql://" + host + ":3306/" + db;
//			properties.setProperty("javax.jdo.option.ConnectionDriverName", "com.mysql.cj.jdbc.Driver");
			properties.setProperty("javax.jdo.option.Mapping", "mysql");
//			properties.setProperty("datanucleus.mapping.Catalog", db);
			break;
		default:
			throw new IllegalArgumentException();
		}
		properties.setProperty("javax.jdo.option.ConnectionURL", url);
		properties.setProperty("javax.jdo.option.ConnectionUserName", username);
		properties.setProperty("javax.jdo.option.ConnectionPassword", password);
		if (readOnly) {
			properties.setProperty("datanucleus.readOnlyDatastore", "true");
		}
		if (create) {
			properties.setProperty("datanucleus.generateSchema.database.mode", "drop-and-create");
		}
		return new GnuCashJDO(null, JDOHelper.getPersistenceManagerFactory(properties, "jgc"), readOnly);
	}

	public static GnuCash fromMySQL(String host, String db, String username, String password) {
		throw new UnsupportedOperationException("implement!");
	}

	PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}
}
