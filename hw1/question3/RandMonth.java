/**
 * question3
 * Generates random int 1-12 and displays according month
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.Random;
import java.text.DateFormatSymbols;

public class RandMonth {

	public static void main(String[] args) {
		// ref <https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java>
		Random rand = new Random();
		int x = rand.nextInt(12) + 1;

		// ref <https://stackoverflow.com/questions/5799140/java-get-month-string-from-integer>
		String month = new DateFormatSymbols().getMonths()[x-1];
		System.out.println("Your month is: " + month);
	}
}
