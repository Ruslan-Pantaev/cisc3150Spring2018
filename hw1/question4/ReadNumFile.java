/**
 * question4
 * Read in a file with numbers and print
 * note: works with or without emptyline at end of .txt file
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

// ref <https://www.geeksforgeeks.org/different-ways-reading-text-file-java/>

import java.io.File;
import java.util.Scanner;

public class ReadNumFile {

	/* If I do not include <throws Exception> I get the following error:
	 * "error: unreported exception FileNotFoundException; must be caught or declared to be thrown"
	 * that is the reason I am using it
	 * In the future, I will refer to <error: unreported exception FileNotFoundException; must be caught or declared to be thrown>
	 *
	 * I am not renaming these vars because that is what I always name them myself
	 */

	public static void main(String[] args) throws Exception {
		// TODO The following doesn't work
		// if (args.length == 0) {
		// 	throw new IllegalArgumentException("Please specify your .txt file!");
		// }

		// loading user-specified file into a File obj
		File file = new File(args[0]);
		// creating Scanner obj to parse user's file <args[0]>
		Scanner sc = new Scanner(file);

		// checking for eof like line 81 in CircleArea.java
		while (sc.hasNextLine())
		// no need to store numbers, simply print
		System.out.println(sc.nextLine());
	}
}
