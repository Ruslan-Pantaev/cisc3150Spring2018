/**
 * Question 2: Do question # 1 found on:
 * 
 * http://www.usna.edu/Users/cs/wcbrown/courses/S11IC211/classes/L12/HW/Homework.html
 * 
 * Instead of picking the letters A, B, C... tell me what is printed on the
 * screen, or which method is being called etc. Make sure your answers are
 * correct. If you're confused, write the program & test it out.
 * 
 * Format your homework properly so it's easy to read/find your answers for
 * each part.
 * 
 * @date	2018-3-3
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.*;

public class Question2 {

	public static void main(String[] args) {

		// a)
		Dog d = new Dog();
		System.out.println(d.call(3));
		// Woof, woof, woof!
		// Method Called:	E
		// Example of:		overloading
		
		// b)
		Object c = new Cat();
		System.out.println(c);
		// I'm a cat, go away!
		// Method Called:	G
		// Example of:		overriding/polymorphism

		// c)
		Object v = new Pet();
		// System.out.println(v.call());
		// error: cannot find symbol System.out.println(v.call());
		// Method Called:	Error
		// Example of:		error
		// 		compiler sees declared type as object, no cal() in object
		// 		casting would fix it

		// d)
		Pet p = new Pet();
		System.out.println(p);
		// Pet@7852e922
		// Method Called:	Method from class object
		// Example of:		overriding/polymorphism
		// 		pet inherits from object, so toString is called

		// e)
		Pet q = new Dog();
		System.out.println(q.call());
		// woof!
		// Method Called:	C
		// Example of:		overloading

		// f)
		Cat t = new Cat();
		System.out.println(t.toString("Come here, kitty"));
		// Come here, kitty? ... Don't waste my time.
		// Method Called:	H
		// Example of:		overloading

		// g)
		Cat u = new Cat();
		// System.out.println(u.call(3));
		// error: no suitable method found for call(int)
		// System.out.println(u.call(3));
		// Method Called:	Error
		// Example of:		error, attempted overloading

		// h)
		Dog e = new Dog();
		System.out.println(e.feet());
		// four
		// Method Called:	B
		// Example of:		overriding/polymorphism

		// i)
		Pet r = new Dog();
		// System.out.println(r.call(3));
		// error: method call in class Pet cannot be applied to given types;
		// Method Called:	Error
		// Example of:		error
		// 		this is a case of declared type vs actual type conflict
	}
}

class Pet
{
  public String call()
  { //A
    return "!!!";
  }
  public String feet()
  { //B
    return "four";
  }
}

class Dog extends Pet
{
  public String call()
  { //C
    return "woof!";
  }
  public String toString()
  { //D
    return "I'm a dog, pet me!";
  }
  public String call(int k)
  { //E
    String s = "Woof";
    for(int i = 1; i < k; ++i)
      s += ", woof";
    return s + "!";
  }
}

class Cat extends Pet
{
  public String call()
  { //F
    return "meow!";
  }
  public String toString()
  { //G
    return "I'm a cat, go away!";
  }
  public String toString(String b)
  { //H
    return b + "? ... " +
    "Don't waste my time.";
  }
}
