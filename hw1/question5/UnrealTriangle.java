/**
 * question5
 * Prompt user for 3 coordinates (a,b,c), calculate lengths from a,b,c and
 * evaluate if it is a real triangle
 * "For a triangle to be real, the sum of any two sides needs to be greater than the third." -Prof
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

// import java.io.*;
import java.util.Scanner;

public class UnrealTriangle {

	// I prefer procedural pgm, so I like using static here
	// creating a 2D array to store coordinates for a,b,c
	// ref <https://stackoverflow.com/questions/12231453/syntax-for-creating-a-two-dimensional-array>
	private static int[][] coord = new int[3][2];
	private static double sideA;
	private static double sideB;
	private static double sideC;
	private static boolean isRealTriangle;

	public static void main(String[] args) {

		System.out.println("Please enter three 2D (x,y) coordinates: ");
		Scanner sc = new Scanner(System.in);

		// simple 2D nested for loops to store user-specified x,y coordinates
		for (int i=0; i<3; ++i) {
			System.out.println("coordinates " + (i+1) + ": ");
			for (int j=0; j<2; ++j) {
				coord[i][j] = Integer.parseInt(sc.nextLine());
			}
		}

		// sqrt((x2-x1)^2+(y2-y1)^2) -Prof
		// ref = <http://www.teacherschoice.com.au/Maths_Library/Trigonometry/triangle_given_3_points.htm>

		// take x,y of coord[0][] and coord[1][]
		sideA = Math.sqrt( Math.pow( (coord[1][0]-coord[0][0]), 2) +
			Math.pow( (coord[1][1]-coord[0][1]), 2) );

		// take x,y of coord[0][] and coord[2][]
		sideB = Math.sqrt( Math.pow( (coord[2][0]-coord[0][0]), 2) +
			Math.pow( (coord[2][1]-coord[0][1]), 2) );

		// take x,y of coord[1][] and coord[2][]
		sideC = Math.sqrt( Math.pow( (coord[2][0]-coord[1][0]), 2) +
			Math.pow( (coord[2][1]-coord[1][1]), 2) );

		
		System.out.println("Side A length: " + sideA);
		System.out.println("Side B length: " + sideB);
		System.out.println("Side C length: " + sideC);

		// calculating if it is a real triangle
		// ref <https://www.varsitytutors.com/hotmath/hotmath_help/topics/triangle-inequality-theorem>
		if (sideA + sideB > sideC && sideA + sideC > sideB && sideB + sideC > sideA) {
			isRealTriangle = true;
			System.out.println("Your triangle is real!");
		} else {
			isRealTriangle = false;
			System.out.println("Sorry, your triangle is not real...");
		}

		// ref <https://stackoverflow.com/questions/2338765/is-there-a-way-to-make-a-link-clickable-in-the-osx-terminal>
		String anglesLink = "https://ostermiller.org/calc/triangle.html";
		System.out.println("\nPlease visit this site to calculate your angles!\n" +
			"(On Mac, press cmd + double-click to open page from terminal)\n\n" + anglesLink + "\n");
	}
}
