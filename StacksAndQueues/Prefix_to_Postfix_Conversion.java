package StacksAndQueues;

import java.util.Stack;

public class Prefix_to_Postfix_Conversion {

    // Function to convert a prefix expression to a postfix expression
    static String prefixToPostfix(String exp) {
        // Create a stack to hold the operands
        Stack<String> stack = new Stack<>();

        // Iterate over each character in the prefix expression from right to left
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            // If the character is an operand (letter or digit), push it to the stack
            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            }
            // If the character is an operator
            else {
                // Pop the top two elements from the stack (note the order)
                String t1 = stack.pop(); // First popped operand (left operand)
                String t2 = stack.pop(); // Second popped operand (right operand)

                // Form a new string by combining the two operands and the operator in postfix order
                String result = t1 + t2 + c;

                // Push the resulting string back onto the stack
                stack.push(result);
            }
        }

        // The final result (postfix expression) will be at the top of the stack
        return stack.peek();
    }

    // Main Function
    public static void main(String[] args) {
        // Example prefix expression
        String prefixExp = "*+ABC";

        // Convert the prefix expression to a postfix expression
        String postfixExp = prefixToPostfix(prefixExp);

        // Output the prefix and postfix expressions
        System.out.println("Prefix Expression: " + prefixExp);
        System.out.println("Postfix Expression: " + postfixExp);
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
Prefix Expression: *+ABC
Postfix Expression: AB+C*
 */

// Algorithm :
/*
1. Iterate an expression from start to end.
    i. if, scanned element is an operand, push the operand into the stack;
   ii. else, pop the two elements from the stack & whatever is the operator put it both the operands before the operator.
       eg: result = t1 + t2 + c;
2. After that, return the stack's top
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=4pIc9UBHJtk