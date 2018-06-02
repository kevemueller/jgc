package hu.keve.jgc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.AttributeConverter;

/**
 * Class to handle the conversion between java.util.Date and a String form. The
 * String form follows the format "YYYYMMDD".
 */
public class GCDateStringConverter implements AttributeConverter<Date, String> {
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	@Override
	public String convertToDatastore(Date attributeValue) {
		return null == attributeValue ? null : formatter.format(attributeValue);
	}

	@Override
	public Date convertToAttribute(String datastoreValue) {
		try {
			return null == datastoreValue ? null : formatter.parse(datastoreValue);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}