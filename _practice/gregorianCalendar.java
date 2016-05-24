import java.util.*;
public class test {
	public static void main(String args[]) {
		String months[] = {"Jan", "feb", "mar", "apr", "may", "jul", "jun",
		"aug", "sep", "oct", "nov", "dec"};
		GregorianCalendar a = new GregorianCalendar();
		int year = a.get(Calendar.YEAR);
		System.out.print("Date: ");
		System.out.print(months[a.get(Calendar.MONTH)]);
		System.out.print(" " + a.get(Calendar.DATE) + " ");
		System.out.print(a.get(Calendar.YEAR) + "\n");
		System.out.print( "Time: ");
		System.out.print(a.get(Calendar.HOUR) + ":");
		System.out.print(a.get(Calendar.MINUTE) + ":");
		System.out.print(a.get(Calendar.SECOND) + "\n");
		if(a.isLeapYear(year)) {
			System.out.println("The current year is a leap year");
		} else {
			System.out.println("The current year is not a leap year");
		}
	}
}