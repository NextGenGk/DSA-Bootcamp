package Stack_And_Queue;

import java.util.Stack;

public class Prefix_to_Infix_Conversion {

    /**
     * Converts a prefix expression to an infix expression.
     *
     * @param exp The prefix expression to be converted.
     * @return The corresponding infix expression.
     */
    public static String prefixToInfix(String exp) {
        // Create a stack to hold intermediate infix expressions
        Stack<String> stack = new Stack<String>();

        // Traverse the prefix expression from right to left
        for (int i = exp.length() - 1; i >= 0; i--) {
            // Get the current character
            char c = exp.charAt(i);

            // Check if the character is an operand (letter or digit)
            if (Character.isLetterOrDigit(c)) {
                // Push the operand onto the stack
                stack.push(String.valueOf(c));
            } else {
                // The character is an operator; pop two operands from the stack
                String t1 = stack.pop();
                String t2 = stack.pop();

                // Form an infix expression with the operator and the two operands
                String result = '(' + t1 + c + t2 + ')';

                // Push the resulting infix expression back onto the stack
                stack.push(result);
            }
        }

        // The final element on the stack is the infix expression
        return stack.peek();
    }

    // Main Function
    public static void main(String[] args) {
        // Test cases
        System.out.println(prefixToInfix("*+AB-CD")); // Expected output: ((A+B)*(C-D))
        System.out.println(prefixToInfix("-+A*BC/DE")); // Expected output: ((A+(B*C))-(D/E))
    }
}

// Time Complexity : O(N) + (N + M)
// Reason : In languages where strings are immutable (like Python, Java, etc.), concatenation often involves
// creating a new string that combines the original strings. For example, in Java, using str1 + str2
// creates a new string and involves copying the characters from both strings, resulting in O(n + m)
// time complexity.
// Space Complexity : O(N), for using stack

// Output :
/*
Postfix Expression: ab+c*
Infix Expression: ((a+b)*c)
 */

// Algorithm :
/*
1. Iterate an expression from start to end.
    i. if, scanned element is an operand, push the operand into the stack;
   ii. else, pop the two elements from the stack & whatever is the operator put it, in between them
       eg: result = '(' + t1 + c + t2 + ')';
2. After that, return the stack's top
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=4pIc9UBHJtk