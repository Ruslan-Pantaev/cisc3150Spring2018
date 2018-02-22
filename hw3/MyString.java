/**
 * hw3
 * 
 * Create your own version of the String class. Call it MyString. This class 
 * should be immutable. Make sure to encapsulate your data properly. Provide 
 * an implementation for the following methods:
 * 
 * public MyString(char[] chars);
 * public char charAt(int index);
 * public int length();
 * public MyString substring(int begin, int end);
 * public MyString toLowerCase();
 * public MyString toUpperCase();
 * public boolean equals(MyString s);
 * public MyString getMyString();
 * public String toString();	
 * public static MyString valueOf(int i);
 * 
 * Do not use any function from the String class. The only time you're 
 * allowed to use the String class is in the toString() method. Even then, 
 * you may only use the String's constructor, but not the methods.
 * 
 * Make sure to provide a driver class, too, and show the output.
 * 
 * @date	2018-1-31
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.*;
// import java.lang.reflect.Field;
// 
import java.io.BufferedReader;
// import java.io.FileInputStream;
// import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Scanner;

public final class MyString {

	private final char[] s;
	private final int length;

	public MyString(char[] chars) {
		this.s = chars;
		// accessing java's array.length, not from the String class
		this.length = this.s.length;
	}

	public char charAt(int index) {
		if (index < this.length || index > 0)
			return this.s[index];
		throw new IndexOutOfBoundsException();
	}

	public int length() {
		return this.length;
	}

	public MyString substring(int begin, int end) {
		if (end > this.length - 1) {
			throw new IndexOutOfBoundsException();		
		}
		int newLength = (end - begin) + 1;
		char[] temp = new char[newLength];
		for (int i=begin, j=0; i<end; i++, j++) {
			temp[j] = this.s[i];
		}
		MyString substring = new MyString(temp);
		return substring;
	}

	public MyString toLowerCase() {
		char[] temp = new char[this.length];
		for (int i=0; i<this.length; i++) {
			temp[i] = this.s[i];
			if (this.s[i] <= 90 /*ascii val for Z*/) {
				temp[i] += 32; // shift it
			}
		}
		MyString lowercase = new MyString(temp);
		return lowercase;
	}

	public MyString toUpperCase() {
		char[] temp = new char[this.length];
		for (int i=0; i<this.length; i++) {
			temp[i] = this.s[i];
			if (this.s[i] >= 97 /*ascii val for a*/) {
				temp[i] -= 32; // shift it
			}
		}
		MyString uppercase = new MyString(temp);
		return  uppercase;	
	}
	
	public int compareTo(MyString s) {
		// I realize the official String.compareTo() method
		// lexographically compares strings, but that was
		// not specified here
		if (this.length == s.length) {
			for (int i=0; i<this.length; i++) {
				if (this.s[i] != s.s[i])
					return -1;
			}
			return 0;
		}
		return -1;
	}
	
	public MyString getMyString() {
		// returning a copy version
		// if return type was char[], i'd return this.s
		MyString temp = this;
		return temp;
	}
	
	public String toString() {
		String temp = "";
		for (int i=0; i<this.length; i++) {
			temp += this.s[i];
		}
		return temp;
	}

	public static MyString valueOf(int i) {
		// returning the char equivalent value of i
		char[] charHolder = { (char)i };
		MyString temp = new MyString(charHolder);
		return temp;
	}

	public static void main(String[] args) {
		/* 	after much time spent learning about the BufferedReader
		 	and InputStreamReader classes, I decided against
		 	getting the user input to avoid using Strings as much as possible
		*/

		char[] test = {'a','B','c','D','e','F','g','H','i'};
		System.out.print("original word:		");
		for (int i=0; i<test.length; i++) {
			System.out.print(test[i]);
		}
		System.out.println();

		MyString thisIsMyString = new MyString(test);

		char a = thisIsMyString.charAt(2);
		System.out.print("a.charAt(2):		");
		System.out.println(a);

		int b = thisIsMyString.length();
		System.out.print("b.length():		");
		System.out.println(b);

		MyString c = thisIsMyString.substring(2, 8);
		System.out.print("c.substring(2, 8):	");
		for (int i=0; i<c.length; i++) {
			System.out.print(c.s[i]);
		}
		System.out.println();

		MyString d = thisIsMyString.toLowerCase();
		System.out.print("d.toLowerCase():	");
		for (int i=0; i<d.length; i++) {
			System.out.print(d.s[i]);
		}
		System.out.println();

		MyString e = thisIsMyString.toUpperCase();
		System.out.print("e.toUpperCase():	");
		for (int i=0; i<e.length; i++) {
			System.out.print(e.s[i]);
		}
		System.out.println();

		int f = e.compareTo(d);
		System.out.println("e.compareTo(d):		" + f);

		MyString g = thisIsMyString.getMyString();
		System.out.print("g.getMyString():	");
		for (int i=0; i<g.length; i++) {
			System.out.print(g.s[i]);
		}
		System.out.println();

		String h = thisIsMyString.toString();
		System.out.println("h.toString():		" + h);

		MyString j = thisIsMyString.valueOf(76);
		System.out.print("j.valueOf(76):		");
		for (int i=0; i<j.length; i++) {
			System.out.print(j.s[i]);
		}
		System.out.println();
	}
}
