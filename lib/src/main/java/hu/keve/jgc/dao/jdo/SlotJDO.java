package hu.keve.jgc.dao.jdo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

import javax.jdo.PersistenceManager;

import hu.keve.jgc.dao.Slot;
import hu.keve.jgc.util.Fraction;

public final class SlotJDO implements Slot {
	//	int id;

	String obj;

	SlotType slotType;

	String name;
	String stringVal;

	String guidVal;

	// @Element(column="obj_guid")
	// @ForeignKey(members= {"guid_val"})
	// Collection<Slot> slots;

	Long int64Val;
	Double doubleVal;
	LocalDateTime timespecVal;
	
	Fraction numericVal;
	LocalDate gdateVal;

	/* (non-Javadoc)
	 * @see hu.keve.jgc.dao.jdo.Slot#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String value;
		switch (slotType) {
		case INTEGER:
			value = String.valueOf(int64Val);
			break;
		case DOUBLE:
			value = String.valueOf(doubleVal);
			break;
		case NUMERIC:
			value = Objects.toString(numericVal);
			break;
		case STRING:
			value = stringVal;
			break;
		case FRAME:
		case GUID:
			value = guidVal;
			break;
		case TIMESPEC:
			value = Objects.toString(timespecVal);
			break;
		case GDATE:
			value = Objects.toString(gdateVal);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return "Slot[" + slotType + "]:" + name + " = " + value;
	}

	public Collection<SlotJDO> getSlots(PersistenceManager pm) {
		return (Collection<SlotJDO>) pm.newQuery(SlotJDO.class, "obj == :guidVal").execute(guidVal);
		// return slots;
	}
}
