package hu.keve.jgc.dao.jdo;

import java.util.Collection;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;

import hu.keve.jgc.dao.Book;

@PersistenceCapable(table = "books")
public final class BookJDO extends GuidTypeJDO implements Book {	
	@Column(name = "root_account_guid")
	AccountJDO rootAccount;
	@Column(name = "root_template_guid")
	AccountJDO rootTemplate;

	@Element(column="obj_guid")
	Collection<SlotJDO> slots;
	
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BookI#getRootAccount()
	 */
	@Override
	public AccountJDO getRootAccount() {
		return rootAccount;
	}

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BookI#getRootTemplate()
	 */
	@Override
	public AccountJDO getRootTemplate() {
		return rootTemplate;
	}
	
	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.BookI#getSlots()
	 */
	@Override
	public Collection<SlotJDO> getSlots() {
		return slots;
	}
}
