package hu.keve.jgc.dao.jaxb;

import java.time.LocalDate;
import java.util.List;

import org.gnucash.xml.GdateType;
import org.gnucash.xml.recurrence.RecurrenceType;

import hu.keve.jgc.dao.Schedxaction;

public interface SchedxactionJAXB extends Schedxaction, RecurrencesUnwrapper {
    GdateType getWrappedStartDate();
    GdateType getWrappedEndDate();
    GdateType getWrappedLastOccur();

	@Override
	default List<RecurrenceType> getRecurrences() {
		return RecurrencesUnwrapper.super.getRecurrences();
	}
	
	@Override
	default LocalDate getStartDate() {
		GdateType gdate = getWrappedStartDate();
		return null == gdate ? null : gdate.getGdate();
	}
	
	@Override
	default LocalDate getEndDate() {
		GdateType gdate = getWrappedEndDate();
		return null == gdate ? null : gdate.getGdate();
	}

	@Override
	default LocalDate getLastOccur() {
		GdateType gdate = getWrappedLastOccur();
		return null == gdate ? null : gdate.getGdate();
	}
}
