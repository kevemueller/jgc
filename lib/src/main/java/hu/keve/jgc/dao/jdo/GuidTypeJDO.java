package hu.keve.jgc.dao.jdo;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.COMPLETE_TABLE)
public abstract class GuidTypeJDO {
	@PrimaryKey
	String guid;

	public final String getGuid() {
		return guid;
	}

//	public static final IdType getIdType(final PersistenceManager pm, final String guid) {
//		try {
//			Book obj = pm.getObjectById(Book.class, guid);
//			return obj;
//		} catch (JDOObjectNotFoundException ex) {
//			try {
//				Account obj = pm.getObjectById(Account.class, guid);
//				return obj;
//			} catch (JDOObjectNotFoundException ex2) {
//				return null;
//			}
//		}
//	}
}
