package hu.keve.jgc.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import javax.jdo.AttributeConverter;

/**
 * Class to handle the conversion between the GC String form of LocalDateTime
 * (e.g. 1970-01-01 00:00:00) to LocalDateTime.
 */
public class GCDateTimeLocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {
	private static final DateTimeFormatter GC_LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
			.append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral(' ').append(DateTimeFormatter.ISO_LOCAL_TIME)
			.toFormatter(Locale.US);

	@Override
	public String convertToDatastore(LocalDateTime attributeValue) {
		return null == attributeValue ? null : GC_LOCAL_DATE_TIME.format(attributeValue);
	}

	@Override
	public LocalDateTime convertToAttribute(String datastoreValue) {
		return null == datastoreValue ? null : LocalDateTime.parse(datastoreValue, GC_LOCAL_DATE_TIME);
	}

}