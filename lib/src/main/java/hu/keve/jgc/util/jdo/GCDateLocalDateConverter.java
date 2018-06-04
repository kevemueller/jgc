package hu.keve.jgc.util.jdo;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import javax.jdo.AttributeConverter;

/**
 * Class to handle the conversion between the GC String form of LocalDate (e.g.
 * 19700101) to LocalDate.
 */
public class GCDateLocalDateConverter implements AttributeConverter<LocalDate, String> {

	private static final DateTimeFormatter GC_LOCAL_DATE = new DateTimeFormatterBuilder().appendValue(YEAR, 4)
			.appendValue(MONTH_OF_YEAR, 2).appendValue(DAY_OF_MONTH, 2).toFormatter(Locale.US);

	@Override
	public String convertToDatastore(LocalDate attributeValue) {
		return null == attributeValue ? null : GC_LOCAL_DATE.format(attributeValue);
	}

	@Override
	public LocalDate convertToAttribute(String datastoreValue) {
		return null == datastoreValue ? null : LocalDate.parse(datastoreValue, GC_LOCAL_DATE);
	}

}