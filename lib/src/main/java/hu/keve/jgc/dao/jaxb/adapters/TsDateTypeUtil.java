package hu.keve.jgc.dao.jaxb.adapters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import org.gnucash.xml.ts.TsType;

/**
 * 2018-05-31 12:59:00 +0200
 * 
 * @author keve
 *
 */
public final class TsDateTypeUtil {
	private static final DateTimeFormatter GC_LOCAL_DATE_TIME_OPT = new DateTimeFormatterBuilder()
			.append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral(' ').append(DateTimeFormatter.ISO_LOCAL_TIME)
			.optionalStart().appendLiteral(' ').appendOffsetId().optionalEnd().toFormatter(Locale.US);

	private TsDateTypeUtil() {
	}

	public static LocalDateTime parse(String v) {
		return null == v ? null : LocalDateTime.parse(v.substring(0, 19), GC_LOCAL_DATE_TIME_OPT);
	}

	public static String print(LocalDateTime v) {
		return null == v ? null : GC_LOCAL_DATE_TIME_OPT.format(v);
	}

	public static LocalDateTime unwrap(TsType tsType) {
		return null == tsType ? null : tsType.getDate();
	}
}
