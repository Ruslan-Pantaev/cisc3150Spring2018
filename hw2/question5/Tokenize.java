/**
 * question5
 * "Change Scanner's delimiter, and tokenize comma separated values entered by 
 * the keyboard. -Prof"
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class Tokenize {
	public static void main(String[] args) {

		System.out.print("Please enter your word: ");
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		// ref <https://stackoverflow.com/questions/28766377/how-do-i-use-a-delimiter-in-java-scanner>
		sc.useDelimiter("\n");
		StringTokenizer st = new StringTokenizer(word, ",");
		// ref <https://www.developer.com/java/data/exploring-the-java-string-tokenizer.html>
		for (int i = 1; st.hasMoreTokens(); i++) {
			System.out.println("Token " + i + ":" + st.nextToken());
		}
	}
}

/*
abc…    Letters
123…    Digits
\d      Any Digit
\D      Any Non-digit character
.       Any Character
\.      Period
[abc]   Only a, b, or c
[^abc]  Not a, b, nor c
[a-z]   Characters a to z
[0-9]   Numbers 0 to 9
\w      Any Alphanumeric character
\W      Any Non-alphanumeric character
{m}     m Repetitions
{m,n}   m to n Repetitions
*       Zero or more repetitions
+       One or more repetitions
?       Optional character
\s      Any Whitespace
\S      Any Non-whitespace character
^…$     Starts and ends
(…)     Capture Group
(a(bc)) Capture Sub-group
(.*)    Capture all
(ab|cd) Matches ab or cd
*/
