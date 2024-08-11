package Stack_And_Queue;

import java.util.Stack;

public class Infix_to_Postfix_Conversion {

    // A utility function to return
    // precedence of a given operator
    // Higher returned value means
    // higher precedence
    static int Prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // The main method that converts
    // given infix expression
    // to postfix expression.
    static String infixToPostfix(String exp) {
        // initializing empty String for result
        String result = new String("");

        // initializing empty stack
        Stack < Character > stack = new Stack < > ();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;

                // If the scanned character is an '(',
                // push it to the stack.
            else if (c == '(')
                stack.push(c);

                // If the scanned character is an ')',
                // pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')') {
                while (!stack.isEmpty() &&
                        stack.peek() != '(')
                    result += stack.pop();

                stack.pop();
            } else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c) <=
                        Prec(stack.peek())) {

                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result += stack.pop();
        }
        return result;
    }

    // Main Function
    public static void main(String[] args) {
        String exp = "(p+q)*(m-n)";
        System.out.println("Infix expression: " + exp);
        System.out.println("Postfix expression: " + infixToPostfix(exp));
    }
}

// Time Complexity : O(N)
// Space Complexity : O(N), for using stack

// Output :
/*
Infix expression: (p+q)*(m-n)
Prefix expression: pq+mn-*
 */

// Prefix, Infix, and Postfix Definitions
/*
What is infix expression?
1. The traditional method of writing mathematical expressions is called infix expressions.
2. It is of the form <operand><operator><operand>.
3. As the name suggests, here the operator is fixed inside between the operands. e.g. A+B here the plus operator is
placed inside between the two operators, (A*B)/Q.
4. Such expressions are easy to understand and evaluate for human beings. However, the computer finds it difficult
to parse - Information is needed about operator precedence and associativity rules, and brackets that override these rules.
5. Hence we have postfix and prefix notations which make the computer take less effort to solve the problem.

What is postfix expression?
1. The postfix expression as the name suggests has the operator placed right after the two operands.
2. It is of the form <operand><operand><operator>
3. In the infix expressions, it is difficult to keep track of the operator precedence whereas here
the postfix expression itself determines the precedence of operators (which is done by the placement
of operators)i.e the operator which occurs first operates on the operand.
4. E.g. PQ-C/, here – operation is done on P and Q and then / is applied on C and the previous result.
5. A postfix expression is a parenthesis-free expression. For evaluation, we evaluate it from left to right.

What is prefix expression?
1. The prefix expression as the name suggests has the operator placed before the operand is specified.
2. It is of the form <operator><operand><operand>.
    Eg: +a*bd
3. It works entirely in the same manner as the postfix expression.
4. While evaluating a prefix expression, the operators are applied to the operands immediately on the
right of the operator.
5. For evaluation, we evaluate it from left to right. Prefix expressions are also called polish notation.
 */

// Algorithm :
/*
Step 1: Define Operator Precedence
1. Define a function Prec(char ch) to return the precedence of the given operator:
    i. + and - have a precedence value of 1.
   ii. * and / have a precedence value of 2.
  iii. ^ (exponentiation) has a precedence value of 3.
Return -1 for non-operators.

Step 2: Initialize Variables
1. Initialize an empty string result to store the postfix expression.
2. Initialize an empty stack stack to hold operators and parentheses during conversion.

Step 3: Traverse the Infix Expression
1. Loop through each character c in the infix expression exp:
    i. If c is an operand (letter or digit):
        i. Append c to result.
   ii. If c is an opening parenthesis (:
        i. Push c onto the stack.
  iii. If c is a closing parenthesis ):
        i. Pop characters from the stack and append them to result until an opening parenthesis ( is encountered.
       ii. Discard the opening parenthesis ( by popping it from the stack.
   iv. If c is an operator:
        i. While the stack is not empty and the precedence of c is less than or equal to the precedence
        of the operator on top of the stack:
            Pop the top operator from the stack and append it to result.
       ii. Push the current operator c onto the stack.

Step 4: Pop Remaining Operators from the Stack
1. After the traversal, pop all remaining operators from the stack and append them to result.
2. If any opening parentheses ( are encountered, return "Invalid Expression" (indicating a mismatch in
parentheses).

Step 5: Return the Postfix Expression
i. The string result now contains the postfix expression. Return this as the final output.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=4pIc9UBHJtk