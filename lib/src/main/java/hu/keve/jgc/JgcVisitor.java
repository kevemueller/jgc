package hu.keve.jgc;

import hu.keve.jgc.dao.Account;
import hu.keve.jgc.dao.Book;
import hu.keve.jgc.dao.Commodity;

public interface JgcVisitor {
	boolean book(Book book, Object context);

	boolean commodity(Commodity commodity, Object context);

	boolean account(Account account, Object context);
}
