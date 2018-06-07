package hu.keve.jgc.dao.jdo;

import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.datanucleus.enhancement.Persistable;

public class SlotFrameJDO extends SlotJDO {
	String guidVal;

	@Override
	public SlotTypes getType() {
		return SlotTypes.FRAME;
	}

	@Override
	public Object getValue() {
		PersistenceManager pm = (PersistenceManager) ((Persistable) (Object) this).dnGetExecutionContext().getOwner();
		List<SlotJDO> slots = pm.newQuery(SlotJDO.class, "objGuid == :guidVal").setParameters(guidVal).executeList();
		HashMap<String, Object> slotMap = new HashMap<String, Object>();
		slots.forEach(slot -> {
			slotMap.put(slot.getName(), slot.getValue());
		});
		return slotMap;
	}

}
