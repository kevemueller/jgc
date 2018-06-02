package hu.keve.jgc.dao.jaxb;

import java.util.List;

import org.gnucash.xml.gnc.RecurrencesType;
import org.gnucash.xml.recurrence.RecurrenceType;

public interface RecurrencesUnwrapper {	
	void setWrappedRecurrences(RecurrencesType value);
	RecurrencesType getWrappedRecurrences();

	default List<RecurrenceType> getRecurrences() {
		RecurrencesType wrappedRecurrences = getWrappedRecurrences();
		if (null == wrappedRecurrences) {
			wrappedRecurrences = new RecurrencesType();
			setWrappedRecurrences(wrappedRecurrences);
		}
		return wrappedRecurrences.getRecurrence();
	}	
}
