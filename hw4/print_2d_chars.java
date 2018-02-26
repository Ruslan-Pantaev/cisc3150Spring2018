/**
 * hw4
 * Write a program that given a two dimensional array of characters prints 
 * out the combination of every letter with every other letter. For eg, if 
 * you had:
 * 
 * {{a, b},{c, d},{e, f}}
 * 
 * Your output would be:
 * ace
 * acf
 * ade
 * adf
 * bce
 * bcf
 * bde
 * bdf
 * 
 * NOTE: 
 * 1) Your program should be able to handly ANY two dimensional character 
 * array.
 * 
 * 2) Get the 2D array at runtime. Either from the user or a file. To make 
 * your life easy, your file can have the following format:
 * 
 * 3	//how many rows your arr[][] will have.
 * 4	//length of your arr[0]
 * a b c d	//values of arr[0]
 * 2	//length of your arr[1]
 * e f	//values of arr[1]
 * 3	//length of your arr[2]
 * g h i	//values of arr[2]
 * 
 * The comments are there just to explain what each line means. Don't put 
 * them in your data file.  This format will help you create your jagged 
 * arrays because it tells you the sizes of each one of them.
 * 
 * 3) Do not use recursion.
 * 
 * 
 * Fun fact! This was a real interview question.
 * 
 * @date	2018-2-24
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.*;

public class print_2d_chars {

	public static void main(String[] args) {

		System.out.println("We need to get some data to load up your 2D array...");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter how many rows your array will have: ");
		int rows = sc.nextInt();
		char[][] chars_arr = new char[rows][];
		
		for (int i=0; i!=rows; ++i) {
			System.out.printf("\nEnter length of array[%d]: ", i);
			int col = sc.nextInt();
			// creating multidimensional array with various sizes
			chars_arr[i] = new char[col];
			for (int j=0; j!=col; ++j) {
				System.out.printf("\nEnter value for index %d: ", j);
				// ref <https://stackoverflow.com/questions/13942701/take-a-char-input-from-the-scanner>
				// even if a user enters a string, only the first char will be stored
				chars_arr[i][j] = sc.next().charAt(0);
			}
		}

		// I spelled out the first 4 combos from given example to get
		// an intuition for how to cycle through the combinations
		// System.out.print(chars_arr[0][0]);
		// System.out.print(chars_arr[1][0]);
		// System.out.print(chars_arr[2][0]);
		// ----------------------------------
		// System.out.print(chars_arr[0][0]);
		// System.out.print(chars_arr[1][0]);
		// System.out.print(chars_arr[2][1]);
		// ----------------------------------
		// System.out.print(chars_arr[0][0]);
		// System.out.print(chars_arr[1][1]);
		// System.out.print(chars_arr[2][0]);
		// ----------------------------------
		// System.out.print(chars_arr[0][0]);
		// System.out.print(chars_arr[1][1]);
		// System.out.print(chars_arr[2][1]);
		// 
		// I then played with a few examples of various sized 2d arrays
		// to calculate the total number of combinations
		// 
		// To calculate the total number of unique combinations
		// we can multiply the length of each col
		// This will also give us a col_lengths array which is useful later
		
		int col_lengths[] = new int[rows];
		for (int i=0; i!=rows; ++i) {
			col_lengths[i] = chars_arr[i].length;
		}
		// init total to 1 so on first pass we do 1 * col_lengths[i]
		int total = 1;
		for (int i=0; i!=rows; ++i) {
			total *= col_lengths[i];
		}
		if (rows == 0) total = 0;
		System.out.printf("\nTotal number of combinations: %d\n", total);

		// After several attempts, I researched for some ideas without using recursion
		// Big thanks to Bobulous' suggestion to use an inner_index_arr to keep track of 
		// each inner array's index and to create a list of strings to hold all combinations
		// List is a good data structure here b/c adding an el is O(1) and we
		// don't care about its size
		// ref <https://stackoverflow.com/questions/15868914/how-to-get-2d-array-possible-combinations>

		int[] inner_index_arr = new int[rows];
		List<String> combinations_list = new ArrayList<String>(total);

		for (int i=total; i!=0; --i) {
			String temp = "";
			for (int j=0; j!=rows; ++j) {
				// I knew that the length of each combo had to be the size of rows,
				// so this for loop concats chars to a temp str of length rows
				// Calling an array within an array
				temp = temp + chars_arr[j][inner_index_arr[j]];
			}
			// we've found a combo! let's store it in our ArrayList
			combinations_list.add(temp);

			for (int k=rows-1; k>= 0; --k) {
				if (inner_index_arr[k] + 1 < col_lengths[k]) {
					inner_index_arr[k]++;
					// counter_arr[k] starts off life as a zero, here we increment it
					// breaking here to avoid resetting to zero
					break;
				}
				// we didn't reach our break in prev if statement
				// so it means we have to start our counter over
				inner_index_arr[k] = 0;
			}
		}
		// printing a list just like in C#, love it
		for (String combo : combinations_list) {
			System.out.println(combo);
		}

		System.exit(0);
	}
}
