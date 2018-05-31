package hu.keve.jgc.dao.jdo;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Convert;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import hu.keve.jgc.dao.Slot;
import hu.keve.jgc.util.Fraction;
import hu.keve.jgc.util.GCDateStringConverter;

@PersistenceCapable(table = "slots")
public class SlotJDO implements Slot {
	@PrimaryKey
	int id;

	String objGuid;

	@Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "toIntValue")
	SlotType slotType;

	String name;
	String stringVal;

	String guidVal;
	// @Element(column="obj_guid")
	// @ForeignKey(members= {"guid_val"})
	// Collection<Slot> slots;

	@Column(name = "int64_val")
	Integer int64Val;
	Double doubleVal;
	Date timespecVal;

	@Persistent(defaultFetchGroup = "true")
	@Embedded(members = { @Persistent(name = "num", column = "numeric_val_num"),
			@Persistent(name = "denom", column = "numeric_val_denom") })
	Fraction numericVal;

	@Convert(GCDateStringConverter.class)
	Date gdateVal;

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
		return (Collection<SlotJDO>) pm.newQuery(SlotJDO.class, "objGuid == :guidVal").execute(guidVal);
		// return slots;
	}
}
