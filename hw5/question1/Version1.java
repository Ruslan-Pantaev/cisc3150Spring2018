/**
 * question1 - version1
 * Let's say that you draw a square around the top right 
 * quadrant of a circle. If the circle has a radius r, then the square that 
 * covers the top right quadrant of the circle will cover an area r^2.
 * 
 * The area of a circle is Pi*r^2. Given that we're dealing with only the top 
 * right quadrant, the area will be (Pi*r^2)/4. The ratio of the area of the 
 * top right quadrant of the circle to the area of the square that covers it 
 * would be...?
 * 
 * Generate 4 billion random dots (yes, it has to be exactly 4 billion) that 
 * fall within that square. Figure out how many of them fall inside the 
 * circle, and how many fall outside of it (if a point is right on the 
 * perimeter of the circle, it's considered inside). If you divide the number 
 * of dots that fall within the circle by the total number of dots you 
 * generated, you should get roughly the same ratio as the one you computed 
 * in the second paragraph. From there, you should be able to calculate the 
 * value of PI. Print that.
 * 
 * The important thing is that you'll be timing your code. The very first 
 * statement in your main should be:
 * long before = System.currentTimeMillis();
 * And the very last statement in your main should be:
 * System.out.println(System.currentTimeMillis()-before);
 * 
 * While this is not an accurate way to measure the speed of your code, it is 
 * a pretty good benchmark for your code on your computer. Write this program 
 * in your normal programming style and calculate your base time. Save this 
 * source code as version 1. Then research optimization techniques and 
 * improve your code the best that you can. I'm interested in your first run, 
 * and your best run. Provide the source code for each version.
 * 
 * Figure out how fast your code got as a percentage and put that in the body 
 * of the email. Also summarize and provide sources for the optimization 
 * techniques you used.
 * 
 * @date	2018-3-2
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.*;
import java.math.BigInteger;

public final class Version1 extends CircleArea {

	/* In Version1, I thought calling Math.random() 4billion
	 * times in a loop was a bad idea so I tried storing all
	 * those coords before in arrays and accessing that.
	 * Obviously this is a bad idea - better to call Math.random()
	 * in a destructive loop scope to avoid java heap space
	 * memory conflicts.
	 * 
	 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	 * at Version1.<clinit>(Version1.java:59)
	 */

	// max array size is based on data type
	// int is 2^31 - 1 (2,147,483,647)
	// BigInteger's arrays are defined by int though
	// static to protect excessive mem-usage
	static int[][] randCoords1 = new int[2_000_000_000][2_000_000_000];
	static int[][] randCoords2 = new int[2_000_000_000][2_000_000_000];

	// or this, just for learning purposes
	// ref <https://stackoverflow.com/questions/47185616/how-do-i-increment-a-for-loop-using-bigdecimal>
	// BigDecimal max = BigDecimal.valueOf(4_000_000_000.0);
	// for (BigDecimal i=BigDecimal.ZERO; i.compareTo(max) < 0; i.add(BigDecimal.ONE)) {}

	private static void genRandCoords() {
		for (int[] coord : randCoords1) {
			// x coord
			coord[0] = (int)Math.random();
			// y coord
			coord[1] = (int)Math.random();
		}
		for (int[] coord : randCoords2) {
			coord[0] = (int)Math.random();
			coord[1] = (int)Math.random();
		}
	}

	public Version1() {
		genRandCoords();
	}

	public static void printCoords() {
		for (int[] coord : randCoords1) {
			System.out.print(coord[0] + coord[1]);
		}
		for (int[] coord : randCoords2) {
			System.out.print(coord[0] + coord[1]);
		}	
	}

	public static void main(String[] args) {

		long before = System.currentTimeMillis();

		double radius = 4.0;

		CircleArea circle = new CircleArea();
		circle.SetRadius(radius);
		double sqArea = radius * radius;
		System.out.printf("radius:\t\t\t%.2f\ncircle area:\t\t%.2f\nsquare area:\t\t%.2f\n", radius, circle.GetArea(), sqArea);

		// same as square in circle area
		double circleQuadrantArea = circle.GetArea() / 4.0;
		System.out.printf("circle quadrant area:\t%.2f\n", circleQuadrantArea);

		double ratio = sqArea / circleQuadrantArea;
		System.out.printf("quad to sq area ratio:\t1:%.2f\n", ratio);

		Version1 v1 = new Version1();
		// v1.printCoords();


		System.out.printf("\nruntime:\t\t%dms\n", System.currentTimeMillis()-before);
	}
}
