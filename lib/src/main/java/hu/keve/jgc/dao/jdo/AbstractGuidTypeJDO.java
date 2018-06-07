package hu.keve.jgc.dao.jdo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;

import org.datanucleus.enhancement.Persistable;

import hu.keve.jgc.dao.DaoUtil;
import hu.keve.jgc.dao.GuidType;

public abstract class AbstractGuidTypeJDO implements GuidType {
	String guid;

	@Override
	public final String getGuid() {
		return guid;
	}

	@Override
	public final Object getSlotValue(String key) {
		return getPersistenceManager().newQuery(SlotJDO.class).filter("objGuid == :guid && name == :key")
				.setParameters(guid, key).executeUnique();
	}

	@Override
	public final Map<String, Object> getSlots() {
		List<SlotJDO> slots = getPersistenceManager().newQuery(SlotJDO.class).filter("objGuid == :guid")
				.setParameters(guid).executeList();
		HashMap<String, Object> slotMap = new HashMap<String, Object>();
		slots.forEach(slot -> {
			slotMap.put(slot.getName(), slot.getValue());
		});
		return slotMap;
	}

	final void newGuid() {
		this.guid = DaoUtil.makeGuid();
	}

	final void setGuid(String guid) {
		this.guid = guid;
	}

	protected PersistenceManager getPersistenceManager() {
		return (PersistenceManager) ((Persistable) (Object) this).dnGetExecutionContext().getOwner();
	}

	// public static final IdType getIdType(final PersistenceManager pm, final
	// String guid) {
	// try {
	// Book obj = pm.getObjectById(Book.class, guid);
	// return obj;
	// } catch (JDOObjectNotFoundException ex) {
	// try {
	// Account obj = pm.getObjectById(Account.class, guid);
	// return obj;
	// } catch (JDOObjectNotFoundException ex2) {
	// return null;
	// }
	// }
	// }
}
