/**
 * hw10
 *
 * Write a program that has 4 threads and prints the letters of the alphabet 
 * in their correct order. So for eg, thread 1 wil print A, thread 2 will 
 * then print B, thread 3 then prints C, and finally thread 4 will print a D. 
 * At this point, thread 1 is activated and it prints an E etc. Keep going 
 * around in a loop like that until you run out of letters. Then exit 
 * gracefully.
 * 
 * @date    2018-5-13
 * @version 1.0
 * @author  Ruslan Pantaev
 */

import java.util.*;
import java.util.concurrent.*;

public class hw10 {

    public static void main(String[] args) throws Throwable {
        alphabet a1 = new alphabet();
        alphabet a2 = new alphabet();
        alphabet a3 = new alphabet();
        alphabet a4 = new alphabet();
         
        Thread thr1 = new Thread(a1);
        Thread thr2 = new Thread(a2);
        Thread thr3 = new Thread(a3);
        Thread thr4 = new Thread(a4);

        thr1.start();
        thr2.start();
        thr3.start();
        thr4.start();

        thr1.join();
        thr2.join();
        thr3.join();
        thr4.join();
    }
}

class alphabet implements Runnable {
    private String lock = "";
    private static int i;
    public alphabet() { this.i = 0; }
    private char[] abc = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private void printChar() {
        synchronized (this.lock) {
            if (i < 26) {
                System.out.println(abc[this.i]);
                this.i++;
            }
        }
    }
    public void run() {
        while (this.i < 26) {
            printChar();
        }
    }
}
