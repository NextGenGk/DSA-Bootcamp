package StacksAndQueues;

import java.util.Stack;

public class Postfix_to_Prefix_Conversion {

    // Time Complexity : O(N) + (N + M)
    // Reason : In languages where strings are immutable (like Python, Java, etc.), concatenation often involves
    // creating a new string that combines the original strings. For example, in Java, using str1 + str2
    // creates a new string and involves copying the characters from both strings, resulting in O(n + m)
    // time complexity.
    // Space Complexity : O(N), for using stack

    // Function to convert a postfix expression to an prefix expression.
    static String postfixToPrefix(String exp) {
        // Create a stack to hold the operands
        Stack<String> stack = new Stack<>();

        // Iterate over each character in the postfix expression
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If the character is an operand (letter or digit), push it to the stack
            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            }
            // If the character is an operator
            else {
                // Pop the top two elements from the stack
                String t1 = stack.pop();
                String t2 = stack.pop();

                // Form a new string by combining the operator and the two operands in prefix order
                String result = c + t1 + t2;

                // Push the resulting string back onto the stack
                stack.push(result);
            }
        }

        // The final result (prefix expression) will be at the top of the stack
        return stack.peek();
    }

    // Main Function
    public static void main(String[] args) {
        // Example postfix expression
        String postfixExp = "AB+C*";

        // Convert the postfix expression to a prefix expression
        String prefixExp = postfixToPrefix(postfixExp);

        // Output the prefix expression
        System.out.println("Postfix Expression: " + postfixExp);
        System.out.println("Prefix Expression: " + prefixExp);
    }
}

// Output :
/*
Postfix Expression: AB+C*
Prefix Expression: *C+BA
 */

// Algorithm :
/*
1. Iterate an expression from start to end.
    i. if, scanned element is an operand, push the operand into the stack;
   ii. else, pop the two elements from the stack & whatever is the operator put it both the operands after the operator.
       eg: result = c + t2 + t1;
2. After that, return the stack's top
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=4pIc9UBHJtk
