package hu.keve.jgc.dao.jdo;

import java.io.File;
import java.util.Properties;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import hu.keve.jgc.GnuCash;
import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Account.AccountTypes;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.DaoUtil;
import hu.keve.jgc.dao.Slot;

public class GnuCashJDO implements GnuCash {
	private static final class TypeMetaData {
		final Class<?> daoInterface;
		final Class<?> jdoImplementation;
		final String name;
		final int version;

		TypeMetaData(final Class<?> daoInterface, final Class<?> jdoImplementation, final String name,
				final int version) {
			this.daoInterface = daoInterface;
			this.jdoImplementation = jdoImplementation;
			this.name = name;
			this.version = version;
		}
	}

	static TypeMetaData[] typeMetadata = {
			new TypeMetaData(Account.class, AccountJDO.class, AccountJDO.NAME, AccountJDO.VERSION),
			new TypeMetaData(Slot.class, SlotJDO.class, SlotJDO.NAME, SlotJDO.VERSION) };

	private final File file;
	private final PersistenceManagerFactory persistenceManagerFactory;
	private final PersistenceManager persistenceManager;
	private Transaction tx;

	private GnuCashJDO(final File file, final PersistenceManagerFactory persistenceManagerFactory) {
		this.file = file;
		this.persistenceManagerFactory = persistenceManagerFactory;
		this.persistenceManager = persistenceManagerFactory.getPersistenceManager();
		this.tx = persistenceManager.currentTransaction();
		tx.begin();

		// TODO: validate schema
		// TODO: validate versions
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
	public Book createBook(String rootCommoditySpace, String rootCommodityMnemonic) {
		Extent<BookJDO> books = persistenceManager.getExtent(BookJDO.class);
		if (books.iterator().hasNext()) {
			throw new IllegalArgumentException("can store only one book");
		}
		BookJDO book = new BookJDO();
		book.setGuid(DaoUtil.makeGuid());
		CommodityJDO commodity = new CommodityJDO();
		commodity.newGuid();
		commodity.setNamespace(rootCommoditySpace);
		commodity.setMnemonic(rootCommodityMnemonic);
		AccountJDO account = new AccountJDO();
		account.newGuid();
		account.setName("Root account");
		account.setAccountType(AccountTypes.ROOT);
		account.setCommodity((CommodityJDO) commodity);
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
//				Object oid = persistenceManager.newObjectIdInstance(tmd.jdoImplementation, id);
//				return (T) persistenceManager.getObjectById(tmd.jdoImplementation, oid);
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
		persistenceManager.close();
		persistenceManagerFactory.close();
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
		// properties.setProperty("datanucleus.rdbms.allowColumnReuse", "true");

		if (create) {
			properties.setProperty("datanucleus.generateSchema.database.mode", "drop-and-create");
		}
		return new GnuCashJDO(file, JDOHelper.getPersistenceManagerFactory(properties, "jgc"));
	}

	public static GnuCash fromPostgreSQL(String host, String db, String username, String password) {
		Properties properties = new Properties();
		String url = "jdbc:postgresql://" + host + ":5432/" + db;
		properties.setProperty("javax.jdo.option.ConnectionDriverName", "org.postgresql.Driver");
		properties.setProperty("javax.jdo.option.Mapping", "postgresql");
		properties.setProperty("javax.jdo.option.ConnectionURL", url);
		properties.setProperty("javax.jdo.option.ConnectionUserName", username);
		properties.setProperty("javax.jdo.option.ConnectionPassword", password);
		properties.setProperty("datanucleus.readOnlyDatastore", "true");
		return new GnuCashJDO(null, JDOHelper.getPersistenceManagerFactory(properties, "jgc"));
	}

	public static GnuCash fromMySQL(String host, String db, String username, String password) {
		throw new UnsupportedOperationException("implement!");
	}

	PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}
}
