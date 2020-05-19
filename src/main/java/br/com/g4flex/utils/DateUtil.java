package br.com.g4flex.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {

	public final static String PATTERN_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public final static String PATTERN_DATE = "yyyy-MM-dd";
	public final static String PATTERN_SCREEN_DATE = "dd/MM/yyyy";
	public final static String PATTERN_HOUR = "HH:mm";

	public static int secondBetween(String finalDate, String initialDate) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(PATTERN_TIMESTAMP);
		DateTime dtFinal = formatter.parseDateTime(finalDate);
		DateTime dtInicial = formatter.parseDateTime(initialDate);
		Seconds seconds = Seconds.secondsBetween(dtInicial, dtFinal);
		return seconds.getSeconds();
	}

	public static String convert(long seconds) {
		seconds = Math.abs(seconds);
		int day = (int) TimeUnit.SECONDS.toDays(seconds);
		long hrs = TimeUnit.SECONDS.toHours(seconds) - TimeUnit.DAYS.toHours(day);
		long min = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.DAYS.toMinutes(day) - TimeUnit.HOURS.toMinutes(hrs);
		long sec = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.DAYS.toSeconds(day) - TimeUnit.HOURS.toSeconds(hrs)
				- TimeUnit.MINUTES.toSeconds(min);
		hrs += 24 * day;
		return String.format("%02d:%02d:%02d", hrs, min, sec);
	}

	public static Date stringToDate(String dateText) {
		return stringToDate(dateText, PATTERN_TIMESTAMP);
	}

	public static Date stringToDate(String dateText, String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		Date data = null;
		try {
			data = fmt.parse(dateText);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return data;
	}

	public static String dateToString(Date date) {
		return dateToString(date, PATTERN_TIMESTAMP);
	}

	public static String dateToString(Date date, String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		String dateText = fmt.format(date);
		return dateText;
	}

	public static String getTodayText() {
		return DateUtil.dateToString(new Date(), PATTERN_DATE);
	}
}
