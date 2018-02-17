/**
 * @date	2018-2-15
 * @version	1.0
 * @author	Ruslan Pantaev
 * Hacking java's pass-by-value with an array wrapper
 */

public class test {
	public static void main(String[] args) {
		int[] a = {5};
		int[] b = {10};
		System.out.println("before a = " + a[0]);
		swap(a, b);
		System.out.println("after a = " + a[0]);
	}
	public static void swap(int[] a, int[] b) {
			int temp = a[0];
			a[0] = b[0];
			b[0] = temp;
	}
}
