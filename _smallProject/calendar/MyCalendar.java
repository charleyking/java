public class MyCalendar {

	private static byte dayadd[] = {1,-2,1,0,1,0,1,1,0,1,0,1,};  // basic days of a month is 30
	private static String weeks[] = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
	private static String months[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

	public static void main(String[] args) {
		printCalendar(2016);
		System.out.println("\n\n" + "the day you input is the " + getDayOfYear(2016,5,21) + "th of the year.");
		System.out.println("this day is the " + getWeekOfYear(2016,5,21) + "th week, " + weeks[getDayOfWeek(2016,5,21)]);
	}

	public static void printCalendar(int year) {
		int wkpoint = getDayOfWeek(year,1,1);
		int addDayOnFeb = 0;
		int tab = 0;
		
		for (int month=1; month<13; month++) {
			addDayOnFeb = 0;  // reinit the variable on every loop
			tab = 0;  // reinit the variable on every loop
			if ((month==2)&&(isLeap(year))) {
				addDayOnFeb = 1;
			}
			System.out.println("\n\n\n\t\t\t" + months[month-1] + " " + year + "\n");
			for (int j = 0; j < 7; j++) {
				System.out.print(weeks[j] + "\t");
			}
			System.out.println();
			while(tab < wkpoint) {
				System.out.print('\t');
				tab ++;
			}
			for (int j=1; j<=(30+dayadd[month-1]+addDayOnFeb); j++) {
				System.out.print(j+"\t");
				if (wkpoint == 6) {
					wkpoint = 0;
					System.out.print('\n');
				} else {
					wkpoint ++;
				}
			}
		}
	}

	public static boolean isLeap(int year) {
		return ((year%400==0)||((year%4==0)&&(year%100!=0)));
	}

	public static int getDayOfYear(int year, int month, int day) {
		int daycount = 0;
		for (int i = 0; i < month; i++) {
			daycount += (30 + dayadd[i]);
		}
		daycount += day;
		if (month > 2 && isLeap(year)) {
			return daycount + 1; 
		} else {
			return daycount;
		}
	}

	public static int getDayOfWeek(int year, int month, int day) {
		if (month < 3) {
			month += 12; 
			year --;
		} 
		return (day+2*month+3*(month+1)/5+year+year/4-year/100+year/400)%7;
	}

	public static int getWeekOfYear(int year, int month, int day) {
		int dayOfYear = getDayOfYear(year, month, day);
		int weekminus = getDayOfWeek(year, month, day)-getDayOfWeek(year,1,1);
		int weekOfYear = 0;
		if (dayOfYear%7==0) {
			weekOfYear =  dayOfYear/7+((weekminus>0)?1:0);
		} else {
			weekOfYear = dayOfYear/7+((weekminus>0)?2:1);
		}
		return weekOfYear;
	}
}