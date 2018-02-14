/**
 * question6
 * "In our class, we talked about how the Scanner class doesn't have a method 
 * for tokenizing individual characters. But you're hell bent on forcing the 
 * Scanner class to tokenize the individual characters. What should you set 
 * your delimiter to?" -Prof
 * 
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class TokenizeChar {
	public static void main(String[] args) {

		System.out.print("Please enter your word: ");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		// ref <https://stackoverflow.com/questions/28766377/how-do-i-use-a-delimiter-in-java-scanner>
		sc.useDelimiter("\n");

		char[] charTokens = new char[word.length()];

		for (int i=0; i<word.length(); i++) {
			charTokens[i] = word.charAt(i);
		}

		System.out.println("using a for loop and .charAt() method");
		for (char t : charTokens) {
			System.out.println(t);
		}

		// OR simply..........
		
		System.out.println("\nsimply .toCharArray() method");
		char[] charTokens2 = word.toCharArray();
		for (char t : charTokens2) {
			System.out.println(t);
		}
		System.out.print("\n\n");
	}
}
