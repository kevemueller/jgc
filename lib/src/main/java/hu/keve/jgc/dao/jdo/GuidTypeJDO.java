package hu.keve.jgc.dao.jdo;

public abstract class GuidTypeJDO {
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
