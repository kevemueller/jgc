package hu.keve.jgc.dao.jaxb;

import java.time.LocalDate;

import org.gnucash.xml.GdateType;

import hu.keve.jgc.dao.Recurrence;

public interface RecurrenceJAXB extends Recurrence {
	GdateType getWrappedPeriodStart();
	
	@Override
	default LocalDate getPeriodStart() {
		GdateType gdate = getWrappedPeriodStart();
		return null == gdate ? null : gdate.getGdate();
	}

}
