package Stack_And_Queue;

import java.util.Stack;

public class Postfix_to_Infix_Conversion {

    // Function to convert postfix expression to infix expression
    static String postfixToInfix(String exp) {
        Stack<String> stack = new Stack<>();  // Stack to hold intermediate infix expressions

        // Traverse each character in the postfix expression
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

                // Form the new infix expression by placing the operator between the two operands
                String result = '(' + t2 + c + t1 + ')';

                // Push the new infix expression back onto the stack
                stack.push(result);
            }
        }

        // The final element in the stack is the complete infix expression
        return stack.pop();
    }

    // Main Function
    public static void main(String[] args) {
        String postfix = "ab+c*";
        System.out.println("Postfix Expression: " + postfix);

        // Convert the postfix expression to infix
        String infix = postfixToInfix(postfix);
        System.out.println("Infix Expression: " + infix);
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
       eg: result : '(' + t2 + c + t1 + ')' )
2. After that, return the stack's top
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=4pIc9UBHJtk