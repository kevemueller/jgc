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
		String prefix = getName()+"/";
		slots.forEach(slot -> {
			String slotName = slot.getName();
			if (slotName.startsWith(prefix)) {
				slotName = slotName.substring(prefix.length());
			} else {
				System.err.println("Expected slotname to start with parent prefix");
			}
			slotMap.put(slotName, slot.getValue());
		});
		return slotMap;
	}

}
