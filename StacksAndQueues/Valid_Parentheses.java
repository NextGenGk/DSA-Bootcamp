package StacksAndQueues;

import java.util.Stack;

public class Valid_Parentheses {

    // Method 1 : Optimal Solution
    // Time Complexity : O(N) for single traversal
    // Space Complexity : O(N) for using external stack data structure
    static boolean isValid(String str) {
        // Create a stack to keep track of opening brackets
        Stack<Character> stack = new Stack<Character>();

        // Iterate through each character in the input string
        for (char it : str.toCharArray()) {

            // If the character is an opening bracket, push it onto the stack
            if (it == '(' || it == '[' || it == '{') {
                stack.push(it);
            }
            // If the character is a closing bracket, check for matching opening bracket
            else {
                // If the stack is empty, it means there's no matching opening bracket
                if (stack.isEmpty()) return false;

                // Pop the top element from the stack
                char c = stack.pop();

                // Check if the popped bracket matches the current closing bracket
                if (it == ')' && c == '(' || it == ']' && c == '[' || it == '}' && c == '{') {
                    continue; // Continue with the next character if they match
                }
                else return false; // If they don't match, the string is invalid
            }
        }
        // If the stack is empty after processing the entire string, the string is valid
        return stack.isEmpty();
    }

    // Main Function
    public static void main (String[] args) {
        // Test the isValid function with a sample input string
        String s = "()[{}()]";

        // Print "True" if the string is valid, otherwise print "False"
        if(isValid(s))
            System.out.println("True");
        else
            System.out.println("False");
    }
}

// Output : True

// Intuition :
/*
We have to keep track of previous as well as most recent opening brackets and also keep in
mind the sequence, as after opening of the bracket there should be opposite pairs of brackets. Also
handle the corner cases like [ ) ( ] where closing bracket occurs first and opening bracket after it,
which is an invalid sequence, as well as [ ( ] ) where the most recent opening didn't get its opposite
pair hence it will also not be valid.

So we have to use a data structure that will keep track of first in and last out, hence we will use the stack.

Approach:

1. Whenever we get the opening bracket we will push it into the stack. I.e ‘{‘, ’[’, ’(‘.
2. Whenever we get the closing bracket we will check if the stack is non-empty or not.
3. If the stack is empty we will return false, else if it is nonempty then we will check if the
topmost element of the stack is the opposite pair of the closing bracket or not.
4. If it is not the opposite pair of the closing bracket then return false, else move ahead.
5. After we move out of the string the stack has to be empty if it is non-empty then return it as
invalid else it is a valid string.
 */
