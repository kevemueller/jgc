package hu.keve.jgc.dao.jaxb;

import java.util.HashSet;

import org.gnucash.xml.act.ActType;

import hu.keve.jgc.dao.Account;

public interface AccountJAXB extends Account, BookRef {

	@Override
	default boolean isPlaceholder() {
		String placeholder = (String) getSlotValue("placeholder");
		return null == placeholder ? false : Boolean.parseBoolean(placeholder);
	}

	@Override
	default Iterable<ActType> getChildren() {
		HashSet<ActType> children = new HashSet<ActType>();
		BookJAXB book = getBook();
		book.getAllAccounts().forEach(a -> {
			if (this.equals(a.getParent())) {
				children.add(a);
			}
		});
		return children;
	}
}
