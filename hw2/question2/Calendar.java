/**
 * question2
 * "Write a program that displays a calendar on the screen. It asks the user 
 * the year, what day the first of January fell on, then displays every month 
 * from January to December. Make sure to get the leap years and the number 
 * of days in each month right. Your months should resemble the following 
 * format: " -Prof
 * 
 *    February 2015      
 * Su Mo Tu We Th Fr Sa  
 *  1  2  3  4  5  6  7  
 *  8  9 10 11 12 13 14  
 * 15 16 17 18 19 20 21  
 * 22 23 24 25 26 27 28
 * 
 * @date	2018-2-14
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.io.*;
import java.util.Scanner;
import java.text.DateFormatSymbols;
import java.time.YearMonth;
// import java.util.Calendar; // already defined, but included for ref

public class Calendar {
	public static void main(String[] args) {
		// for testing check <https://www.timeanddate.com/calendar/?year=2004&country=1>
		//
		// tutorial for another cool way to do this:
		// <http://www.dreamincode.net/forums/topic/25042-creating-a-calendar-viewer-application/>

		System.out.print("Please enter Year: ");
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
		// using an int bypasses ambiguities with user input
		System.out.println("Please enter corresponding digit for first weekday of year: ");
		
		// ref <https://stackoverflow.com/questions/16541438/in-java-how-to-get-strings-of-days-of-week-sun-mon-sat-with-systems-d>
		String[] weekdays = DateFormatSymbols.getInstance().getWeekdays();
		// ref <https://stackoverflow.com/questions/85190/how-does-the-java-for-each-loop-work>
		int i = -1;
		for (String weekday : weekdays) {
			if (i == -1) {}
			else { System.out.println((i+1) + " - " + weekday); }
			i++;
		}
		int firstWeekDay = sc.nextInt() -1;
		sc.close();

		// Jan starts at 0 in this array
		String[] months = DateFormatSymbols.getInstance().getMonths();
		// for testing
		// int j = 1;
		// for (String month : months) {
		// 	if (j == 13) break;
		// 	System.out.println(j + " - " + month);
		// 	j++;
		// }

		// TODO consider <https://docs.oracle.com/javase/8/docs/api/java/time/YearMonth.html#atDay-int->
		// to avoid asking user for firstWeekDay
		// NOT WORKING
		// Calendar cal = new Calendar(); // or Calendar.getInstance();
		// cal.set(Calendar.YEAR, year);
		// cal.set(Calendar.DAY_OF_YEAR, 1);    
		// Date autoFirstWeekDay = cal.getTime();
		
		// Jan starts at 1 in this java.time class
		// ref <https://stackoverflow.com/questions/8940438/number-of-days-in-particular-month-of-particular-year>
		YearMonth yearMonth;
		int k = 0;

		for (int m=0; m<12; m++) {
			System.out.println("     " + months[m] + " " + year);
			System.out.println(" Su Mo Tu We Th Fr Sa ");
			// System.out.printf("%" + (firstWeekDay-1) + "s", "");
			yearMonth = YearMonth.of(year, m+1);
			int daysInMonth = yearMonth.lengthOfMonth();
			/* or simply...
			if ( (year % 4 == 0) && (year % 100 != 0) || year % 400 == 0 ) {
				daysInMonth = 29;
			} else {
				daysInMonth = 28;
			}*/
			int date = 0;
			if (m==0) { 	// starting off January
				for ( ; k<(daysInMonth+firstWeekDay); k++) {
					if (date<=daysInMonth && k>=firstWeekDay) { date++; }
					if (date!=0) {
						System.out.printf("%3d", date);
					} else {
						System.out.printf("%3s", " ");
					}
					if ( (k+1)%7==0 && k!=0) { System.out.println(); }
				}
			} else {
				// fill up blank spaces
				for (int blank=0; blank<(k%7); blank++) {
					System.out.printf("%3s", " ");
				}
				date = 0;
				int offset = daysInMonth + k;
				for ( ; k<offset; k++) {
					if (date<=daysInMonth) { date++; }
					if (date!=0) {
						System.out.printf("%3d", date);
					} else {
						System.out.printf("%3s", " ");
					}
					if ( (k+1)%7==0) { System.out.println(); }
				}
			}
			System.out.print("\n\n");
		}
		System.out.println();
	}
}
