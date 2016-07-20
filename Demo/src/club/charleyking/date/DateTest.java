package club.charleyking.date;

import java.util.Date;
import java.util.Calendar;

public class DateTest {
	public static void main(String[] args) {
		Date a = new Date(0);
		Calendar c = Calendar.getInstance();
		Date b = (Date) c.getTime();
		System.out.println(c.getTimeInMillis());
		System.out.println(System.currentTimeMillis());
	}
	
}
