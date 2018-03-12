/**
 * question1 - version4
 * 
 * @date	2018-3-7
 * @version	4.0
 * @author	Ruslan Pantaev
 * @notes	<http://ieeexplore.ieee.org/document/7429325/?reload=true>
 *        	<https://pdfs.semanticscholar.org/c90a/c17b1f1ec821c4d0ed1addb5b20abe171b59.pdf>
 */

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;
// import java.util.function.*;
// import java.awt.*;
import java.util.concurrent.atomic.AtomicLongArray;

public class Version4StreamApi {
	public static void main(String[] args) {
		long before = System.currentTimeMillis();
		
		double[] coords = new double[2];
		long[] data = new long[2];	// index 0 for misses | index 1 for testing count
		// atomic arrays takes as long as running single thread
		// AtomicLongArray atomicData = new AtomicLongArray(2);
		// atomicData.set(0,0); // setting to zero just in case
		// atomicData.set(1,0);
		LongStream.range(0, 4_000_000_000L).parallel().forEach(i -> {
			coords[0] = ThreadLocalRandom.current().nextInt()/2147483647.0;
			coords[1] = ThreadLocalRandom.current().nextInt()/2147483647.0;
			if (coords[0]*coords[0] + coords[1]*coords[1] > 0.50000000001) {
				data[0]++;
				// atomicData.incrementAndGet(0);
			}
			//data[1]++; // produces inconsistent data; java arrays are not thread-safe
			//data[1]=i+1;
			// atomicData.incrementAndGet(1);
		});
		// System.out.printf("misses:\t\t%d\n", data[0]);
		// System.out.printf("loop count:\t%d\n", atomicData.get(1));
		double pi = (double)((1000000000L - data[0])/100000000.0);
		System.out.printf("PI:\t\t%f\n", pi);

		System.out.printf("\nruntime:\t%dms\n", (System.currentTimeMillis()-before));
	}
}
