/**
 * question3
 * "Write a program to check whether a string is a palindrome (a string that's 
 * the same forward and backwards, for eg, madamimadam)." -Prof
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

// import java.io.*;
import java.util.Scanner;

public class CheckPalindrome {

	public static boolean check(String x) {
		int start = 0;
		int end = x.length() - 1;

		// while start and end match, keep checking
		while (start < end) {
			if (x.charAt(start++) != x.charAt(end--)) {
				System.out.printf("sorry, %s isn't a palindrome", x);
				return false;
			}
		}
		System.out.printf("yep, %s is a palindrome", x);
		return true;
	}

	public static void main(String[] args) {
		System.out.print("Please enter your word: ");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		// storing in a bool in case we later wanna use it
		boolean isIt = check(word);
		System.out.print("\n\n");
	}
}
