/**
 * hw7
 *
 * Question 1
 *
 * Write a command line calculator. Implement all the basic functions 
 * (addition, subtraction...) as well as the correct behavior for 
 * parenthesis. Make sure your calculator works with both integers and 
 * doubles. Here's an example run:
 * 
 * javac Calculator 2 + 3
 * 5
 * 
 * Separate all the arguments with a space to make your life easy.
 * 
 * If the user enters anything other than a number where a number belongs, 
 * throw a AlgebraFailException derived from the IlleglArgumentException. If 
 * the user enters an operation which isn't supported, throw a 
 * QuitMashingOnYourKeyboardException derived from the 
 * IllegalArgumentException. If the user forgets to enter a number, throw a 
 * UserIsADumbassException also derived from IllegalArgumentException. Make 
 * sure you deal with these exceptions gracefully. Don't pass them on to the 
 * JVM. The user should never know what we think of them.
 * 
 * You should also catch and deal with ArithmeticException in case the 
 * user tries to divide by zero.
 * 
 * Make sure your calculator correctly deals with the order of operations. 
 * (Remember PEMDAS?). If you research how to turn the command line arguments 
 * into postfix notation, it'll help. You will probably run into another 
 * interesting problem, too.
 * 
 * @date	2018-4-22
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.util.*;

public class JavaCalculator {

	public static int precedence(char op) {
		int r = 0;
		switch(op) {
			// not needed b/c '( )' is handled in if statement
			// case '(' : // (
			// case ')' : // )
			// 	r = 4;
			// 	break;
			case '^' : // ^
				r = 3;
				break;
			case '%' : // %
			case '*' : // *
			case '/' : // /
				r = 2;
				break;
			case '+' : // +
			case '-' : // -
				r = 1;
				break;
			default:
				r = 0;
		}
		// System.out.println(r);
		return r;
	}

	public static double perform_op(double op1, double op2, char arg) {
		switch(arg) {
			case '^':
				return Math.pow(op1, op2);
			case '%':
				return op1 % op2;
			case '*':
				return op1 * op2;
			case '/':
				return op1 / op2;
			case '+':
				return op1 + op2;
			case '-':
				return op1 - op2;
		}
		return 0;
	}

	public static double eval_postfix(Queue<String> pf) {
		// Note to self: use escape '\' when using (,),* in terminal

		double result = 0.0;
		Stack<Double> digits_stack = new Stack();

		while (!pf.isEmpty()) {
			String temp = pf.remove();
			if (Character.isDigit(temp.charAt(0))) {
					// System.out.println("pushing digit");
					digits_stack.push(Double.parseDouble(temp));
			} else {
					// System.out.println("in Else");
					double op1 = 0.0;
					double op2 = 0.0;
					if (!digits_stack.isEmpty()) {
						// System.out.println("popped digits once");
						op2 = digits_stack.pop();
						if (!digits_stack.isEmpty()) {
							// System.out.println("popped digits twice");
							op1 = digits_stack.pop();
							result = perform_op(op1, op2, temp.charAt(0));
							System.out.println("Performing: " + op1 + temp + op2);
							System.out.println("Result: " + result);
							digits_stack.push(result);
						}
					}
			}
		}
		return result;
	}

	// TODO throw exceptions


	
	public static void main(String[] args) {
		Stack<Character> ops_stack = new Stack();
		String postfix_str = ""; // for testing, using https://www.mathblog.dk/tools/infix-postfix-converter/

		// TRY CATCH IN MAIN


		// Evaluating pgm args & populating data structures
		// 
		// PUT BELOW INTO FUNCTION
		// 
		for (int i = 0; i < args.length; i++) {
			char arg = args[i].charAt(0);
			// ref http://interactivepython.org/runestone/static/pythonds/BasicDS/InfixPrefixandPostfixExpressions.html
			// ref https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
			if (Character.isDigit(arg)) {
					// adding args[i] String since numbers can be more than one char,
					// but using first char for evaluation
					postfix_str += args[i] + " ";
					// EXCEPTION cannot parse double
			} else if (arg == '(') {
				// EXCEPTION
					ops_stack.push(arg);
			} else if (arg == ')') {
					while (!ops_stack.isEmpty() && ops_stack.peek() != '(') {
						postfix_str += ops_stack.pop() + " ";
					}
					ops_stack.pop();
			} else {
					while (!ops_stack.isEmpty() && precedence(arg) <= precedence(ops_stack.peek())) {
						postfix_str += ops_stack.pop() + " ";
					}
					ops_stack.push(arg);
			}
		}

		// completeing final postfix String
		while (!ops_stack.isEmpty()) {
			postfix_str += ops_stack.pop() + " ";
		}
		// converting postfix_str to stack for easier calculation
		Queue<String> postfix_que = new LinkedList();
		for (int i = 0; i < postfix_str.length(); i++) {
			String temp = "";
			while (postfix_str.charAt(i) != ' ') {
				temp += postfix_str.charAt(i);
				i++;
			}
			// System.out.println(temp);
			postfix_que.add(temp);
		}

		double result = eval_postfix(postfix_que);

		// ------------Printing results------------
		System.out.println("\nargs length:\t" + args.length);
		System.out.print("postfix: \t");
		System.out.println(postfix_str);
		System.out.printf("result:\t\t%f\n\n", result);
	}
}
