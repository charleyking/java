import java.util.Scanner;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SmallCalendar {
	public static void main(String args[]) {
		System.out.println("Please input date(format: 2001-12-25): ");
		Scanner in = new Scanner(System.in);
		String time = in.nextLine();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = format.parse(time);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);

			int day = calendar.get(Calendar.DATE);
			calendar.set(Calendar.DATE, 1);
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			int maxDay = calendar.getActualMaximum(Calendar.DATE);

			System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
			int flag = 0;
			for(int i=1; i<week; i++) {
				System.out.print("\t");
				flag ++;
			}
			for(int i=1; i<=maxDay; i++) {
				if(i==day) {
					System.out.print('*');
				}
				System.out.print(i + "\t");
				flag ++;
				if(flag%7==0) {
					System.out.println();
				}
			}
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
}