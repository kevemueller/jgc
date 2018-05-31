package hu.keve.jgc.dao.jaxb2;

import java.util.List;

import org.gnucash.xml.gnc.RecurrencesType;
import org.gnucash.xml.recurrence.RecurrenceType;
import org.gnucash.xml.slot.SlotType;
import org.gnucash.xml.slot.SlotsType;
import org.gnucash.xml.split.SplitType;
import org.gnucash.xml.trn.SplitsType;

public abstract class AbstractGuidType {
	public void setWrappedSlots(SlotsType value) {

	}

	public SlotsType getWrappedSlots() {
		return null;
	}

	public List<SlotType> getSlots() {
		SlotsType wrappedSlots = getWrappedSlots();
		if (null == wrappedSlots) {
			wrappedSlots = new SlotsType();
			setWrappedSlots(wrappedSlots);
		}
		return wrappedSlots.getSlot();
	}

	public void setWrappedSplits(SplitsType value) {
	}

	public SplitsType getWrappedSplits() {
		return null;
	}

	public List<SplitType> getSplits() {
		SplitsType wrappedSplits = getWrappedSplits();
		if (null == wrappedSplits) {
			wrappedSplits = new SplitsType();
			setWrappedSplits(wrappedSplits);
		}
		return wrappedSplits.getSplit();
	}

	public void setWrappedRecurrences(RecurrencesType value) {

	}

	public RecurrencesType getWrappedRecurrences() {
		return null;
	}

	public List<RecurrenceType> getRecurrences() {
		RecurrencesType wrappedRecurrences = getWrappedRecurrences();
		if (null == wrappedRecurrences) {
			wrappedRecurrences = new RecurrencesType();
			setWrappedRecurrences(wrappedRecurrences);
		}
		return wrappedRecurrences.getRecurrence();
	}
}
