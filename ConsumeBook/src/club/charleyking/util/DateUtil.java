package club.charleyking.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	static long milliSecondsOfOneDay = 1000*60*60*24;
	public static java.sql.Date util2sql(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	public static Date getMonthBegin() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	public static Date getMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONDAY, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	public static int getThisMonthTotalDay() {
		long lastDayMilliSeconds = getMonthEnd().getTime();
		long firstDayMilliSeconds = getMonthBegin().getTime();
		return (int) ((lastDayMilliSeconds-firstDayMilliSeconds)/milliSecondsOfOneDay);
	}
	public static int getThisMonthLeftDay() {
		long lastDayMilliSeconds = getMonthEnd().getTime();
		long todayMilliSeconds = getToday().getTime();
		return (int) ((lastDayMilliSeconds-todayMilliSeconds)/milliSecondsOfOneDay);
	}
	
	public static void main(String[] args) {
		System.out.println(getToday());
		System.out.println(getMonthBegin());
		System.out.println(getMonthEnd());
		System.out.println(getThisMonthTotalDay());
		System.out.println(getThisMonthLeftDay());
	}
}
