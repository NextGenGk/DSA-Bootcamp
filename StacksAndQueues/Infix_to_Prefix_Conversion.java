package StacksAndQueues;

import java.util.Stack;

public class Infix_to_Prefix_Conversion {

    // Time Complexity : O(N) + O(N) + O(N) ~ O(3N) ~ O(N)
    // Reason : O(N) for reverse a string, O(N) for stack operations, and another O(N) for reverse the
    // postfix expression.
    // Space Complexity : O(N) for using stack.

    // Function to determine the precedence of an operator
    static int prec(char ch) {
        return switch (ch) {
            case '+', '-' -> 1; // Lowest precedence for addition and subtraction
            case '/', '*' -> 2; // Medium precedence for multiplication and division
            case '^' -> 3; // Highest precedence for exponentiation
            default -> -1; // Return -1 for non-operators
        };
    }

    // Function to reverse a string and swap brackets
    static String reverseExpression(String expr) {
        // Use StringBuilder for efficient string manipulation
        StringBuilder result = new StringBuilder(expr.length());

        // Traverse the string from end to start
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);

            // Swap opening and closing brackets
            if (c == '(') {
                result.append(')');
            } else if (c == ')') {
                result.append('(');
            } else {
                result.append(c);  // Append the character as is
            }
        }
        return result.toString();  // Return the modified string
    }

    // Function to convert an infix expression to a postfix expression
    static String infixToPostfix(String expression) {
        Stack<Character> stack = new Stack<>();  // Stack to hold operators and parentheses
        StringBuilder result = new StringBuilder();  // StringBuilder to build the resulting postfix expression

        // Iterate over each character in the infix expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If the character is an operand (letter or digit), add it to the result
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // If the character is '(', push it onto the stack
            else if (c == '(') {
                stack.push(c);
            }
            // If the character is ')', pop from the stack to the result until '(' is found
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();  // Pop the '(' from the stack
            }
            // If the character is an operator
            else {
                // Special handling for the exponentiation operator '^'
                // (Basically we can't store two powers in stack together)
                if (c == '^') {
                    while (!stack.isEmpty() && prec(c) <= prec(stack.peek())) {
                        result.append(stack.pop());
                    }
                }
                // For other operators, pop from the stack to the result based on precedence
                else {
                    while (!stack.isEmpty() && prec(c) < prec(stack.peek())) {
                        result.append(stack.pop());
                    }
//                    while (!stack.isEmpty() && prec(c) <= prec(stack.peek())) {
//                        result.append(stack.pop());
//                    }
                    stack.push(c);  // Push the current operator onto the stack
                }
            }
        }

        // Pop all remaining operators from the stack to the result
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();  // Return the postfix expression
    }

    // Function to convert an infix expression to a prefix expression
    static String infixToPrefix(String infix) {
        // Step 1: Reverse the infix expression and swap brackets
        String reversedInfix = reverseExpression(infix);

        // Step 2: Convert the reversed infix expression to postfix notation
        String postfix = infixToPostfix(reversedInfix);

        // Step 3: Reverse the postfix expression to get the prefix expression
        return new StringBuilder(postfix).reverse().toString();
    }

    // Main Function
    public static void main(String[] args) {
        // Example infix expression
        String infix = "(p+q)*(c-d)";
        System.out.println("Infix Expression: " + infix);

        // Convert the infix expression to prefix
        String prefix = infixToPrefix(infix);
        System.out.println("Prefix Expression: " + prefix);
    }
}

// Output :
/*
Infix Expression: (p+q)*(c-d)
Prefix Expression: *+pq-cd
 */

// Approach :
/*
1. First, reverse the infix expression given in the problem.
2. Scan the expression from left to right.
3. Whenever the operands arrive, print them.
4. If the operator arrives and the stack is found to be empty, then simply push the operator into the stack.
5. If the incoming operator has higher precedence than the TOP of the stack, push the incoming operator into the stack.
6. If the incoming operator has the same precedence with a TOP of the stack, push the incoming operator into the stack.
7. If the incoming operator has lower precedence than the TOP of the stack, pop, and print the top of the stack.
Test the incoming operator against the top of the stack again and pop the operator from the stack till it finds
the operator of lower precedence or same precedence.
8. If the incoming operator has the same precedence with the top of the stack and the incoming operator is ^,
then pop the top of the stack till the condition is true. If the condition is not true, push the ^ operator.
9. When we reach the end of the expression, pop, and print all the operators from the top of the stack.
10. If the operator is ')', then push it into the stack.
11. If the operator is '(', then pop all the operators from the stack till it finds the ‘)’ bracket in the stack.
12. If the top of the stack is ')', push the operator on the stack.
13. In the end, reverse the output. And print it.
 */

// Algorithm :
/*
Step 1: Reverse the Infix Expression

1. Initialize an empty string reversedInfix.
2. Traverse the infix expression from right to left:
    i. If the current character is (, replace it with ), and vice versa.
   ii. Append each character to reversedInfix.

Step 2: Convert Reversed Infix Expression to Postfix

1. Initialize an empty stack stack and an empty string postfix.
2. Traverse the reversedInfix expression from left to right:
    i. If the character is an operand (letter or digit):
        i. Append it directly to postfix.
   ii. If the character is (:
        i. Push it onto the stack.
  iii. If the character is ):
        i. Pop characters from the stack and append them to postfix until ( is encountered.
       ii. Discard the (.
   iv. If the character is an operator:
        i. While the stack is not empty and the precedence of the current operator is less than or equal to the precedence of the operator at the top of the stack:
            i. Pop the operator from the stack and append it to postfix.
       ii. Push the current operator onto the stack.
3. After the traversal, pop all remaining operators from the stack and append them to postfix.

Step 3: Reverse the Postfix Expression
i. Reverse the postfix string to obtain the prefix expression.

Step 4: Return the Prefix Expression
i. The reversed postfix string is the final prefix expression.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=4pIc9UBHJtk
