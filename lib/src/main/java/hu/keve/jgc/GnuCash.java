package hu.keve.jgc;

import java.util.Currency;

import hu.keve.jgc.dao.Book;

public interface GnuCash extends AutoCloseable {
	public enum Backends {
		XML(true), SQLITE(true), PGSQL(false), MYSQL(false);
		private final boolean local;

		private Backends(boolean local) {
			this.local = local;
		}

		public boolean isLocal() {
			return local;
		}
	};

	default void visit(JgcVisitor visitor, Object context) {
		getBook().visit(visitor, context);
	}

	Book getBook();

	Book createBook(Currency currencyCode);

	void commit() throws Exception;

	<T> T getById(Class<T> idType, Object id);
}
