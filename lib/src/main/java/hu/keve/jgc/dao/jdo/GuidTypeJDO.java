package hu.keve.jgc.dao.jdo;

import javax.jdo.PersistenceManager;

import org.datanucleus.enhancement.Persistable;

import hu.keve.jgc.dao.DaoUtil;
import hu.keve.jgc.dao.GuidType;

public abstract class GuidTypeJDO implements GuidType {
	String guid;

	public final String getGuid() {
		return guid;
	}
	
	final void newGuid() {
		this.guid = DaoUtil.makeGuid();
	}
	final void setGuid(String guid) {
		this.guid = guid;
	}

	protected PersistenceManager getPersistenceManager() {
		return (PersistenceManager) ((Persistable)(Object)this).dnGetExecutionContext().getOwner();
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
