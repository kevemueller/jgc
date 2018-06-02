package hu.keve.jgc.dao.jaxb;

import java.util.List;

import org.gnucash.xml.recurrence.RecurrenceType;

import hu.keve.jgc.dao.Schedxaction;

public interface SchedxactionJAXB extends Schedxaction, RecurrencesUnwrapper {

	@Override
	default List<RecurrenceType> getRecurrences() {
		return RecurrencesUnwrapper.super.getRecurrences();
	}	
}
