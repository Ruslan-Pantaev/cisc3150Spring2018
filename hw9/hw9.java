/**
 * hw9
 *
 * In our class we went over the code posted at: 
 * https://pastebin.com/dwZqBs4u
 * But we ran into a bunch of problems.
 * 
 * First, we were unable to instantiate the Object class. Explain that.
 * 
 * Second, we saw that with the notifyAll() uncommented, and the keyword 
 * synchonized removed from the method declarations, the program just died 
 * without throwing any exceptions. Given that we are working with an 
 * infinite loop here, that shouldn't have happened. Explain that.
 * 
 * At home, I was playing around with this code, and I noticed that if you 
 * wrap notifyAll() in a try/catch block, you'll notice that 
 * IllegalMonitorStateException is being thrown. The reason why you don't get 
 * to see it is because it is a child of RuntimeException. Figure out how 
 * you'd fix it. Research how to have two threads share an object's monitor and 
 * fix the code.
 * 
 * @date    2018-5-13
 * @version 1.0
 * @author  Ruslan Pantaev
 */

// 1) For the first part, Object class was probably extracted somewhere
// where it wasn't accessible. Not sure of the specifics, but definitely
// a sys-specific bug.
// 
// 2) It appears that the calls to e.producer() and e.consumer() are throwing exception(s)
// that are not being displayed to console.
// 
// 3) After wrapping notifyAll() in try/catch blocks, I saw that e.producer() actually ran
// the notifyAll() method, but that a thrown exception was caught in e.consumer().
// 
// After debugging w/ print statements, I simply wrote:
//  try {
//      notifyAll();
// } catch(IllegalMonitorStateException i){}
// 
// Now it appears to run.
// 
// 4) Found this re: Threads and Object's monitor
// 
// "Each object has an 'Object monitor'. Basically it is a 'semaphore',
// indicating if a critical section code is being executed by a thread or not.
// Before a critical section can be executed, the Thread must obtain an
// 'Object monitor'. Only one Thread at a time can own that object's monitor."
// -ref https://en.wikibooks.org/wiki/Java_Programming/API/java.lang.Object#Synchronizing_Threads_Methods
// 
// At any given time, one of the two threads attempted to call notifyAll() on an object monitor without permissions
// of said monitor. Basically, a critical section cannot be executed by thread until that thread owns the object's
// monitor.

import java.util.*;
import java.io.*;
import java.net.*;
 
public class hw9{
 
    public static void main(String[] args) throws Throwable{
        economy myeconomy = new economy();
 
        Thread t1 = new Thread(new producer(myeconomy));
        Thread t2 = new Thread(new consumer(myeconomy));
        
        t2.start();
        t1.start();
    }
}
 
class producer implements Runnable{
    economy e;
 
    producer(economy e){
        this.e = e;
    }
 
    public void run(){
        try{
            e.producer();
        }catch(Throwable t){}
    }
}
 
class consumer implements Runnable{
    economy e;
 
    consumer(economy e){
        this.e = e;
    }
 
    public void run(){
        try{
            e.consumer();
        }catch(Throwable t){}
    }
}
 
class economy{
    boolean product = false;
 
    public void producer() throws Throwable{
        System.out.println("Running producer");
        while(true){
            if(product == true){
                try{
                    System.out.println("Producer: Too much product exists. Gonna wait...");
                    wait();
                }catch(Throwable t){}
            }
            System.out.println("Producer: Making more product now.");
            product = true;
            try {
                notifyAll();
            // }catch(Throwable t){}
            } catch(IllegalMonitorStateException i) {
                // System.out.println("IllegalMonitorStateException in producer!");
            }
            // finally {
            //     System.out.println("Finally block in producer...");
            // }
            Thread.sleep(1000);
            System.out.println("Producer ending");
 
        }
    }
 
    public void consumer() throws Throwable{
        System.out.println("Running Consumer");
        while(true){
            if(product == false){
                try{
                    System.out.println("Consumer: No products available to consume. Gonna wait...");
                    wait();
                }catch(Throwable t){}
            }
 
            System.out.println("Consumer: Now consuming product");
            product = false;
            try {
                notifyAll();
            // }catch(Throwable t){}
            } catch(IllegalMonitorStateException i){
                // System.out.println("IllegalMonitorStateException in consumer!");
            }
            // finally {
            //     System.out.println("Finally block in consumer...");
            // }
            Thread.sleep(10);
            System.out.println("Consumer ending");
        }
    }
}