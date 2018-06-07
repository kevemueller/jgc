package hu.keve.jgc.dao.jaxb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.slot.SlotValueType;

import hu.keve.jgc.dao.Slot;

public abstract class AbstractSlotJAXB implements Slot {
	public abstract SlotValueType getWrappedValue();

	public static abstract class SlotValueJAXB {
		public abstract SlotTypes getType();

		public abstract Object getValue();

	}

	public static class Simple extends SlotValueJAXB {
		private final SlotTypes type;
		private final Object value;

		public Simple(final SlotTypes type, Object value) {
			this.type = type;
			this.value = value;
		}

		public Simple(String value) {
			this(SlotTypes.STRING, value);
		}

		@Override
		public SlotTypes getType() {
			return type;
		}

		@Override
		public Object getValue() {
			return value;
		}
	}

	public static class Frame extends SlotValueJAXB {
		private final Map<String, Object> frameValue;

		public Frame(Map<String, Object> frameValue) {
			this.frameValue = frameValue;
		}

		@Override
		public SlotTypes getType() {
			return SlotTypes.FRAME;
		}

		@Override
		public Object getValue() {
			return frameValue;
		}
	}

	@Override
	public SlotTypes getType() {
		return getWrappedValue().getType();
	}

	@Override
	public Object getValue() {
		SlotValueType wrappedValue = getWrappedValue();
		if (null == wrappedValue) {
			return null;
		}
		List<Serializable> content = wrappedValue.getContent();
		switch (wrappedValue.getType()) {
		case STRING:
			StringBuffer sb = new StringBuffer();
			content.forEach(s -> {
				sb.append((String) s);
			});
			return sb.toString();
		case FRAME:
			HashMap<String, Object> frame = new HashMap<String, Object>();
			for (Serializable s : content) {
				if (s instanceof String) {
					String trimmedString = ((String) s).trim();
					if (!trimmedString.isEmpty()) {
						throw new IllegalArgumentException("unexpected: " + trimmedString);
					}
				} else if (s instanceof JAXBElement) {
					SlotType slot = (SlotType) ((JAXBElement) s).getValue();
					frame.put(slot.getName(), slot.getValue());
				}			
			}
			return frame;
		}
		throw new IllegalArgumentException("Unhandled type " + wrappedValue.getType());
	}

	@Override
	public String toString() {
		return "Slot[]" + getName() + "/" + getType() + "/= " + getValue();
	}
}
