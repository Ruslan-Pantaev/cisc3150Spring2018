/**
 * question1
 * "Write a program that asks the user how tall of a pyramid do they want to 
 * display on the screen, and then displays the pyramid that counts up on the 
 * left, and counts down on the right. For eg, if the user enters 4, the 
 * program shows: " -Prof
 * 
 *         1
 *       1 2 1
 *     1 2 3 2 1
 *   1 2 3 4 3 2 1
 *   
 * @date	2018-2-14
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.Scanner;

public class Pyramid {
	public static void main(String[] args) {
		System.out.print("Please enter size of pyramid: ");
		Scanner sc = new Scanner(System.in);
		int SIZE = Integer.parseInt(sc.next());
		sc.close();

		System.out.println();
		// handling base-cases
		if (SIZE < 1) {
			System.exit(0);
		} else if (SIZE == 1) {
			System.out.printf("%1c\n\n", '1');
			System.exit(0);
		}

		// fine-tuned 3 to be a good value for handling small and large pyramids
		int spaces = SIZE * 3;
		System.out.printf("%" + (spaces + 1) + "c\n", '1');
		int spacing = 2;
		if (SIZE > 9) {
			// spacing out by 3 to accomodate two-digit integers
			spacing = 3;
		}

		// ref <https://www.geeksforgeeks.org/programs-printing-pyramid-patterns-java/>
		// note: i++ vs ++i makes a big difference here
		// starting at index 2 since base case of 1 is already covered
		for (int i=2; i<=SIZE; i++) {
			spaces = spaces - spacing;
			// initial spacing per line
			System.out.printf("%" + spaces + "s", "");
			// inner loop1 to print first half of integers
			for (int j=1; j<i; j++) {
				System.out.print(j);
				// spacing error handling
				if (spacing == 2) {
					System.out.print(" ");
				} else {
					if (j >= 9) {
						System.out.print(" ");
					} else {
						System.out.print("  ");
					}
				}
			}
			// inner loop2 to reverse print second half of integers
			for (int k=i; k>0; k--) {
				// spacing error handling
				if (k==i) {
					System.out.printf("%1d", k);
				} else {
					System.out.printf("%" + spacing + "d", k);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
