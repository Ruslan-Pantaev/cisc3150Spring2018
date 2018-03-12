import java.util.*;
import java.util.concurrent.*;

public class PIFast_mod{
	public static void main(String[] args) throws Throwable{
		long before=System.currentTimeMillis();

		loops1 l1 = new loops1();
		loops1 l2 = new loops1();
		loops1 l3 = new loops1();
		loops1 l4 = new loops1();
		loops1 l5 = new loops1();
		loops1 l6 = new loops1();
		loops1 l7 = new loops1();
		loops1 l8 = new loops1();

		Thread a = new Thread(l1);
		Thread b = new Thread(l2);
		Thread c = new Thread(l3);
		Thread d = new Thread(l4);
		Thread e = new Thread(l5);
		Thread f = new Thread(l6);
		Thread g = new Thread(l7);
		Thread h = new Thread(l8);

		//hog the CPU.
		a.setPriority(Thread.MAX_PRIORITY);
		b.setPriority(Thread.MAX_PRIORITY);
		c.setPriority(Thread.MAX_PRIORITY);
		d.setPriority(Thread.MAX_PRIORITY);
		e.setPriority(Thread.MAX_PRIORITY);
		f.setPriority(Thread.MAX_PRIORITY);
		g.setPriority(Thread.MAX_PRIORITY);
		h.setPriority(Thread.MAX_PRIORITY);

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
		f.start();
		g.start();
		h.start();

		a.join();
		b.join();
		c.join();
		d.join();
		e.join();
		f.join();
		g.join();
		h.join();

		double pi = (l1.hits + l2.hits + l3.hits + l4.hits
			+ l5.hits + l6.hits + l7.hits + l8.hits);

		System.out.printf("PI: %11.10f\n", pi); 
		
		System.out.println((System.currentTimeMillis()-before)/1000.0 + " seconds.");

	}
}

class loops1 implements Runnable{

	public double hits = 0.0;

	public void run(){
		// int i;			//either initialize i here or in the loop. But not both.
		double x,y;		//why initialize x & y if they'll be overwritten anyway?
		int miss = 0;

		ThreadLocalRandom tlr = ThreadLocalRandom.current();	//save the static reference instead of computing it again & again.

		for(int i=0; i < 500_000_000; i++){	//constant used to avoid a memory hit every comparison. Though one would hope that JVM is smarter than that.
		
			x=tlr.nextInt()/2147483647.0;	//generate ints cuz doubles are slower. Then normalize them down to the unit circle by dividing by max int value
			y=tlr.nextInt()/2147483647.0;	//we are using constants in the hope that we won't need to hit a memory location just to read a value that isn't gonna change anyway.
//			x=x*x + y*y;			//can be uncommented. Doesn't make a huge difference. If uncommented, the if statement should just be x < 1.0000001
//			x--;				//not needed because we have modified our if statement. This saves us roughly 1-3 statements.

/*
			the following double comparison is correct. My intention is to run the distance formula
				sqrt((x2-x1)^2 + (y2-y1)^2)
			but since x1, and y1 is zero, it's just x2^2 + y2^2.
			There's no need to call the Math.pow() function because that would be a useless function call.
			Just do x*x + y*y.
			And since I'm working with the unit circle, the only time this dot will be inside the circle
			is if my x*x + y*y adds up to a 1. And since sqrt(1) = 1, I don't even need to do the sqrt.

			The correct way of doing a doubles comparison is ((current value - target) < error). But given
			that my taget is 1, and my error is .00000000001, and I'm using a constant, I can just combine
			the two things into 1.00000000001.

			Additionally, instead of counting hits, I should count failures. Because the circle covers most
			of the area of the square (78.5% to be exact), if the dots are random, there will be less failures
			than there will be hits. My counter runs 31.5% of the time as opposed to 78.5% of the time.
*/
			if(x*x + y*y <= 0.50000000001){
				miss++;
			}
		}
		hits = (500000000 - miss)/500000000.0;	//don't touch the class variable hits inside the loop. That's slower. Calculate the result outside.
	}
}

