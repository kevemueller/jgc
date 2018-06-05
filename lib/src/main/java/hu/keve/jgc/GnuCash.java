package hu.keve.jgc;

import hu.keve.jgc.dao.Book;

public interface GnuCash extends AutoCloseable {
	public enum Backends {
		XML, SQLITE, PGSQL, MYSQL
	};
	
	
	default void visit(JgcVisitor visitor, Object context) {
		getBook().visit(visitor, context);
	}
	
	Book getBook();

	Book createBook(String rootCommoditySpace, String rootCommodityMnemonic);

	void commit() throws Exception;

	<T> T getById(Class<T> idType, Object id);
}
