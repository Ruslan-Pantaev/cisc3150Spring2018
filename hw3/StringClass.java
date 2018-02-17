/**
 * hw3
 * 
 * Create your own version of the String class. Call it MyString. This class 
 * should be immutable. Make sure to encapsulate your data properly. Provide 
 * an implementation for the following methods:
 * 
 * public MyString(char[] chars);
 * public char charAt(int index);
 * public int length();
 * public MyString substring(int begin, int end);
 * public MyString toLowerCase();
 * public MyString toUpperCase();
 * public boolean equals(MyString s);
 * public MyString getMyString();
 * public String toString();	
 * public static MyString valueOf(int i);
 * 
 * Do not use any function from the String class. The only time you're 
 * allowed to use the String class is in the toString() method. Even then, 
 * you may only use the String's constructor, but not the methods.
 * 
 * Make sure to provide a driver class, too, and show the output.
 * 
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class StringClass {
	public static void main(String[] args) {

		// System.out.print("Please enter your word: ");
		// Scanner sc = new Scanner(System.in);
		// String word = sc.nextLine();
		// // ref <https://stackoverflow.com/questions/28766377/how-do-i-use-a-delimiter-in-java-scanner>
		// sc.useDelimiter("\n");

		// char[] charTokens = new char[word.length()];

		// for (int i=0; i<word.length(); i++) {
		// 	charTokens[i] = word.charAt(i);
		// }

		// System.out.println("using a for loop and .charAt() method");
		// for (char t : charTokens) {
		// 	System.out.println(t);
		// }

		// // OR simply..........
		
		// System.out.println("\nsimply .toCharArray() method");
		// char[] charTokens2 = word.toCharArray();
		// for (char t : charTokens2) {
		// 	System.out.println(t);
		// }
		// System.out.print("\n\n");
	}
}
