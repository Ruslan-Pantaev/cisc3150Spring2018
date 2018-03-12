/**
 * hw6
 * 
 * Write a program that finds and prints all the solutions to the N-Queens 
 * problem for a given N. At the end of your program, print how many 
 * solutions were found.
 * 
 * What is the N-Queens problem, you ask? 
 * 1) Google it. 
 * 2) It's the same as N-1 Queens problem. But with one more Queen.
 * 
 * Note: Backtracking is the easy way to do this. Now that you've done the 
 * 2D array combinations problem, try solving this the same way.
 * 
 * @date	2018-3-11
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.*;
import java.util.Vector;

public class n_queens {
	class IntPair {
		// simple row,col Obj
		public int row;
		public int col;
		IntPair(int r, int c) { row=r; col=c; }
	}

	public static int n;
	// experimented with LinkedList and primitive arrays and Vector was fastest
	public Vector<IntPair> queens;

	n_queens() { queens = new Vector<IntPair>(); }

	boolean solveNQueen(int col) {
		// if we got this far, we're done!
		if (col >= n) {
			printBoard();
			System.out.println("queens.size(): "+queens.size());
			return true;
		}
		// reset row on every recursive run
		int row = 0;
		while (row < n) {
			// storing in IntPair Obj
			IntPair curr = new IntPair(row, col);
			// System.out.println("row: "+col+" col: "+row);
			// pushing to Vector
			queens.add(curr);
			// recursively calling main function solveNQueen(col+1)
			// this is where the grunt work happens on the stack
			if (queenIsSafe(curr) && solveNQueen(col+1)) {
				return true;
			}
			// System.out.println("backtracking...");
			// removing last attempted pos
			queens.removeElementAt(queens.size()-1);
			row++;
		}
		return false;
	}

	boolean queenIsSafe(IntPair curr) {
		IntPair prev;
		for (int i=0; i<queens.size()-1; i++) {
			prev = queens.get(i);
			// checking horizontal and diagonal threats
			if (prev.row == curr.row
				|| Math.abs(prev.row-curr.row) == Math.abs(prev.col-curr.col)) {
			
				return false;
			}
		}
		// we're good, no threats to queen
		return true;
	}

	void printBoard() {
		// print board with queens
		System.out.print(" ");
		for (int i=0; i<n; ++i) {
			System.out.print("_ ");
		}
		System.out.println();
		for (int i=0; i<n; ++i) {
			IntPair curr = queens.get(i);
			for (int j=0; j<n; ++j) {
				if (curr.row == j) {
					System.out.print("|Q");
				} else {
					System.out.print("|_");
				}
			}
			System.out.println("|");
		}

		// print queens' coordinates
		IntPair temp;
		for (int i=0; i<queens.size(); i++) {
			temp = queens.get(i);
			// swapping row, col to match for loop print
			System.out.println("row: "+temp.col+" col: "+temp.row);
		}
	}

	public static void main(String[] args) {
		long before = System.currentTimeMillis();

		System.out.print("enter n-queens board size:");
		Scanner sc = new Scanner(System.in);
		// n is static
		n = sc.nextInt();

		// testing base cases, from trial and error I quickly realized that 2 and 3 are not possible
		if (n<=0 || n==2 || n==3) {
			System.out.println("no possible solutions");
			return;
		}
		
		n_queens q = new n_queens();
		q.solveNQueen(0);

		System.out.printf("\nruntime:\t%dms\n", (System.currentTimeMillis()-before));
	}
}
