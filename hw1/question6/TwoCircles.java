/**
 * question6
 * "Write a program that prompts the user to enter the center coordinates and 
 * radiuses of two circles. Determine whether the circles are separate from 
 * each other, touching each other, overlapping each other, or completely 
 * within one another. Test each case and show the output." -Prof
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

// import java.io.*;
import java.util.Scanner;

public class TwoCircles {

	// Similar idea to my UnrealTriangle.java pgm
	// 2D array where:
	//	index 0 = x coordinate
	//	index 1 = y coordinate
	//	index 2 = circle radius
	private static int[][] coord = new int[2][3]; // the 2 here represents the 2 circles

	private static double circle1Area;
	private static double circle2Area;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int i=0; i<2; ++i) {
			System.out.println("\nPlease enter circle " + (i+1) + "\'s center 2D (x,y) coordinates: ");
			
			// storing user-specified x,y coordinates
			for (int j=0; j<2; ++j) {
				System.out.println("enter coordinate " + (j+1) + ": ");
				coord[i][j] = Integer.parseInt(sc.nextLine());
			}
			System.out.println("enter radius: ");
				coord[i][2] = Integer.parseInt(sc.nextLine());
		}

		circle1Area = Math.PI * Math.pow(coord[0][2], 2);
		circle2Area = Math.PI * Math.pow(coord[1][2], 2);

		System.out.println("\nCircle 1's area: " + circle1Area);
		System.out.println("Circle 2's area: " + circle2Area + "\n");

		// ---------------------------------------------------------------------------------
		// TODO
		// separate
		// touching
		// overlapping
		// completely_within

		// if ( (coord[0][0]/*circle1 x*/ + coord[0][2]/*circle1 radius*/ > 
		// 	coord[1][0]/*circle2 x*/ + coord[1][2]/*circle2 radius*/) && 
		// 	(coord[0][1]/*circle1 y*/ + coord[0][2]/*circle1 radius*/ > 
		// 	coord[1][1]/*circle2 y*/ + coord[1][2]/*circle2 radius*/) ) {
		// }

		// I tried the above but then sought help below
		// ref <https://www.geeksforgeeks.org/check-two-given-circles-touch-intersect/>

		// int distance = (coord[0][0]/*circle1 x*/ - coord[1][0]/*circle2 x*/) +
		// 	(coord[0][1]/*circle1 y*/ - coord[1][1]/*circle2 y*/);

		// int sumOfRadius = coord[0][2]/*circle1 radius*/ + coord[1][2]/*circle2 radius*/;
		// ---------------------------------------------------------------------------------
		
		/* I got different results when performing with or without to the power of 2,
		 * so I am following geeksforgeeks' suggested squaring approach
		 *
		 * Ex: distance (2-1)^2 + (3-1)^2 and radius (5+4)^2 is NOT proportional to
		 *	distance (2-1) + (3-1) and radius (5+4)
		 */
		
		double distance = Math.pow( (coord[0][0]/*circle1 x*/ - coord[1][0]/*circle2 x*/), 2 ) +
			Math.pow( (coord[0][1]/*circle1 y*/ - coord[1][1]/*circle2 y*/), 2 );

		double sumOfRadius = Math.pow( (coord[0][2]/*circle1 radius*/ + coord[1][2]/*circle2 radius*/), 2 );

		if ( coord[0][0] == coord[1][0] && coord[0][1] == coord[1][1] && coord[0][2] == coord[1][2] ) {
			System.out.println("Circles are completely within one another!");
		}
		else if (distance == sumOfRadius) { System.out.println("Circles are touching!"); }
		else if (distance > sumOfRadius) { System.out.println("Circles are separate!"); }
		else if (distance < sumOfRadius) { System.out.println("Circles are overlapping!"); }

		System.out.println("");
	}
}
