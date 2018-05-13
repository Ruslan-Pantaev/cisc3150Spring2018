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

import java.util.*;

public class hw9 {

    public static void main(String[] args) {
        
    }
}
