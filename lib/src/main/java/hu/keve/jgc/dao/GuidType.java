package hu.keve.jgc.dao;

import java.util.Map;

public interface GuidType {
	String getGuid();
	
	String getBusinessKey();
	
	default Object getSlotValue(String key) {
		Map<String, Object> slots = getSlots();
		return null == slots ? null : slots.get(key);
	}

	Map<String, Object> getSlots();
}
