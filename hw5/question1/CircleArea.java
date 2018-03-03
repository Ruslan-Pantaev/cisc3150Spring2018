/**
 * question2
 * Reads user input for radius until EOF and calculates circle area
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.Scanner;
// import java.lang.Math; // possibly redundant

public class CircleArea {
	/* For exercise purposes I will include javadoc comments,
	 * though Uncle Bob's Clean Code approach of letting code
	 * be self descriptive seems fair.  Please let me know if
	 * you would prefer me to continue with comments or not
	 *
	 * using <http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#tag>
	 */

	/**
	 * @param radius
	 */
	private double radius;
	
	/**
	 * Default Constructor
	 */
	public CircleArea() {
		this.radius = 0;
	}

	/**
	 * Overloaded Constructor
	 * @param r to set radius
	 */
	public CircleArea(double r) {
		this.radius = r;
	}

	/**
	 * Setter method
	 * @param r to set radius
	 */
	public void SetRadius(double r) {
		this.radius = r;
	}

	/**
	 * Getter method
	 * @return user-specified radius
	 */
	public double GetRadius() {
		return this.radius;
	}

	/**
	 * @retun circle area
	 * note: works with radius up to 1784 on my system,
	 * then double truncates and prodeuces wrong result
	 *
	 * If it is better practice, I can simply create another
	 * private var for area and a void function to calculate area
	 */
	public double GetArea() {
		return Math.PI * Math.pow(this.radius, 2);
	}

	// ref <https://www.cs.utexas.edu/~scottm/cs307/javacode/codeSamples/ScannerAndKeyboard.java>
	public static void main(String[] args) {
		// creating scanner obj
		Scanner sc = new Scanner(System.in);
		// creating CircleArea obj
		// or I could have made methods static
		CircleArea ca = new CircleArea();

		// prompting user for input
		System.out.println("Please enter your radius: ");
		// in c++ !eof() was convenient, for here I used ref below
		// ref <https://stackoverflow.com/questions/8270926/while-eof-in-java>
		while (sc.hasNextLine()) {
			// storing user's input
			// ref <https://www.mkyong.com/java/java-convert-string-to-int/>
			ca.SetRadius( Double.parseDouble(sc.nextLine()) );
			// calling GetArea to calculate area and printing it
			System.out.print("Circle Area: ");
			System.out.println(ca.GetArea());
			// re-prompting user
			System.out.println("Please enter your radius: ");
		}
	}
}
