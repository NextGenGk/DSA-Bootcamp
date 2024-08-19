package StacksAndQueues;

import java.util.Stack;

public class Remove_K_Digits {

    // Method 1 : Optimal Solution
    // Time Complexity : O(3N) + O(K) ~ O(N) + O(K)
    // Reason : O(N) is for traversing, and another O(N) is for returning the result string
    // and another O(N) is for removing leading zeroes & O(K) is for removing extra digits (i.e. k).
    // Space Complexity : O(N) + O(N) ~ O(2N) ~ O(N)
    // Reason : O(N) for using Stack DS and another O(N) for returning the result string.
    static String removeKDigits(String str, int k) {
        int n = str.length(); // Get the length of the input string
        Stack<Character> stack = new Stack<Character>(); // Initialize a stack to store the digits

        // If k equals the length of the string, remove all digits, return "0"
        if (k == n) return "0";

        int i = 0; // Initialize index to traverse the input string

        // Iterate through the string characters
        while (i < n) {
            // If the stack is not empty, and the current digit is smaller than the stack's top,
            // and k is greater than 0 (i.e., we still need to remove digits), pop the stack
            while (!stack.isEmpty() && stack.peek() > str.charAt(i) && k > 0) {
                stack.pop(); // Remove the top element from the stack
                k--; // Decrease k, as we have removed one digit
            }
            // Push the current character onto the stack
            stack.push(str.charAt(i));
            i++; // Move to the next character
        }

        // If k is still greater than 0 after processing all digits, remove more digits from the stack
        while (k > 0) {
            stack.pop(); // Remove extra digits from the top of the stack
            k--;
        }

        // Build the result string from the characters in the stack
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop()); // Pop characters from the stack and append them to the result
        }

        // Since the characters were added in reverse order (LIFO behavior of the stack), reverse the result string
        result.reverse();

        // Remove leading zeros from the result, if any, but ensure the result has at least one digit
        while (result.charAt(0) == '0' && result.length() > 1) {
            result.deleteCharAt(0); // Remove the first character if it's '0'
        }

        // Return the final result as a string
        return result.toString();
    }

    // Main Function
    public static void main(String[] args) {
        // Test cases
        String num1 = "1432219";
        int k1 = 3;
        System.out.println("Result for num = " + num1 + ", k = " + k1 + " : " + removeKDigits(num1, k1)); // Expected output: "1219"

        String num2 = "10200";
        int k2 = 1;
        System.out.println("Result for num = " + num2 + ", k = " + k2 + " : " + removeKDigits(num2, k2)); // Expected output: "200"

        String num3 = "10";
        int k3 = 2;
        System.out.println("Result for num = " + num3 + ", k = " + k3 + " : " + removeKDigits(num3, k3)); // Expected output: "0"

        String num4 = "1234567890";
        int k4 = 9;
        System.out.println("Result for num = " + num4 + ", k = " + k4 + " : " + removeKDigits(num4, k4)); // Expected output: "0"
    }
}

// Output :
/*
Result for num = 1432219, k = 3 : 1219
Result for num = 10200, k = 1 : 200
Result for num = 10, k = 2 : 0
Result for num = 1234567890, k = 9 : 0
 */

// Approach : Optimal Solution
/*
// Intuition : The intuition is very simple is to keep the smaller digits at the start and get rid of the
   larger ones, because we need to find the smallest integer after removing the K largest digit.

// Algorithm -
1. We first initialize a stack to store the digits.
2. We then iterate through the digits of the number from left to right.
3. We keep on popping the stack if the stack is not empty and the top of the stack is greater than the current digit.
4. We push the current digit onto the stack.
5. We keep on doing this until k becomes 0.
6. Once k becomes 0, we start popping the stack and adding the popped digits to the string builder.
7. We keep on doing this until the stack becomes empty.
8. We reverse the string builder and return the result.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=jmbuRzYPGrg