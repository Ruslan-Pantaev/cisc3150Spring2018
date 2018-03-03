/**
 * question1 - version2
 * 
 * @date	2018-3-2
 * @version	2.0
 * @author	Ruslan Pantaev
 * @notes	Using print 4billion times took more than 4 hours!
 *        I didn't have patience to wait so I used multithreading
 *        and it still took forever. Maybe this was b/c I called
 *        threads[t].join() but no, still took forever. I soon
 *        remembered Prof mentioning that System.out.println()
 *        calls toString() and I thought aha. I realized that
 *        console's PrintStream takes a while, so I simply removed
 *        print statements from loops and voila, it worked in 9sec.
 *        Calling random 8billion times (x_coord * 4billion + y_coord * 4billion)
 *        proved to be not so bad. Old school Random obj probably
 *        would be much slower.
 *
 * 	Version1 runtime: 	heap space error
 * 	Version2 w/print:	estimated 28800sec (8hrs)
 * 	Version2 final:		averaging 9.2sec
 *
 * 	Performance Optimization:	313,043.48% Faster
 * 								That's an order of magnitude of three-hundred-thousand!
 * 								Or 0.00000319444% of original speed
 */

// import java.util.*;
// ref <https://docs.oracle.com/javase/8/docs/api/?java/util/concurrent/package-summary.html>
import java.util.concurrent.ThreadLocalRandom;
// or prior to Java 1.7 instantiate a Random obj

public final class Version2 extends CircleArea {
	
	protected double radius;
	protected double[] coord;
	protected long dotsCount;

	public Version2(double r) {
		this.radius = r;
		this.coord = new double[2];
		this.dotsCount = 0L;
	}

	boolean isInside() {
		// ref <https://www.geeksforgeeks.org/find-if-a-point-lies-inside-or-on-circle/>
		if (Math.sqrt(Math.pow(this.coord[0], 2) + Math.pow(this.coord[1], 2)) <= this.radius)
			return true;
		else
			return false;
	}

	public void genCoords() {
		// second method sig is exclusive, but since it's a double, I'm not adding 1.0
		// think asymptotic relationship
		this.coord[0] = ThreadLocalRandom.current().nextDouble(0, this.radius);
		this.coord[1] = ThreadLocalRandom.current().nextDouble(0, this.radius);

		if (isInside()) {
			this.dotsCount += 1L;
		}
	}

	public long getCount() {
		return this.dotsCount;
	}

	public static void main(String[] args) {

		double radius = 0;
		try {
			radius = Double.parseDouble(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("Please provide a radius argument of type double!");
			System.exit(1);
		}
		if (radius <= 0) {
			System.err.println("Please provide a radius > 0");
			System.exit(1);	
		}

		long before = System.currentTimeMillis();

		CircleArea circle = new CircleArea();
		circle.SetRadius(radius);
		double sqArea = radius * radius;
		System.out.printf("radius:\t\t\t%f\ncircle area:\t\t%f\nsquare area:\t\t%f\n", radius, circle.GetArea(), sqArea);

		// same as square in circle area
		double circleQuadrantArea = circle.GetArea() / 4.0;
		System.out.printf("circle quadrant area:\t%f\n", circleQuadrantArea);

		double ratio = sqArea / circleQuadrantArea;
		System.out.printf("quad to sq area ratio:\t1:%f\n", ratio);

		// ----------------------------------------------------------
		 
		long SIZE = 4_000_000_000L;
		System.out.printf("\niterations:\t\t%d\n", SIZE);

		// best practice suggests leaving an available Processor/hyperthread
		// but this pgm takes 9 seconds so... gonna hog the machine :)
		int threadCount = Runtime.getRuntime().availableProcessors();
		System.out.printf("running thread count:\t%d\n", threadCount);
		long taskSize = SIZE / threadCount;

		Thread[] threads = new Thread[threadCount]; 
		ThreadTask[] tasks = new ThreadTask[threadCount];
		
		for (int t = 0; t < threadCount; t++) {
			// new ThreadTask obj for each thread to be read/write thread-safe
			// 1-to-1 correspondence
			tasks[t] = new ThreadTask(radius, taskSize);
			threads[t] = new Thread(tasks[t]);
			threads[t].start();
		}

		// gotta catch em' all!
		for (int t = 0; t < threadCount; t++) {
			try {
				threads[t].join();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
				System.exit(1);
			}
		}
	
		long count = 0L;
		for (int t = 0; t < threadCount; t++) {
			count += tasks[t].getCount();
		}
		System.out.printf("\ndots in circle quad:\t%d\n", count);
		double estimatedPI = 4 * ((double)count / (double)SIZE);
		System.out.printf("estimation of PI:\t%f", estimatedPI);

		System.out.printf("\nruntime:\t\t%dms\n", System.currentTimeMillis()-before);
	}
}

final class ThreadTask implements Runnable {

	protected double radius;
	protected long taskSize;
	protected Version2 v2;

	public ThreadTask(double radius, long taskSize) {
		this.taskSize = taskSize;
		this.radius = radius;
		this.v2 = new Version2(this.radius);
	}

	public long getCount() {
		return this.v2.dotsCount;
	}
	
	public void run() {
		for (long i=0; i<taskSize; i++) {
			this.v2.genCoords();
			/*
			 * kept this println in here just as a reminder
			 * lol
			 * console's PrintStream coupled with
			 * toString(), etc is sooooo slow
			 * System.out.println(i);
			 */
		}
	}
}
