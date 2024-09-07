package GreedyAlgorithms;

public class Valid_Parenthesis_String {

    // Method 1 : Brute Force
    // Time Complexity : O(3^N)
    // Space Complexity : O(N) for recursive stack space
    public static boolean checkValidString(String s) {
        // Start the helper function with the initial index of 0 and count of 0 for '('.
        return helper(s, 0, 0);
    }

    // Helper function
    public static boolean helper(String s, int index, int cnt) {
        int n = s.length(); // Get the length of the string

        // If at any point count of '(' is negative, it's an invalid string.
        if (cnt < 0)
            return false;

        // If we have traversed the entire string, check if the count of '(' is balanced
        // (i.e., cnt == 0).
        if (index == n)
            return (cnt == 0);

        // Check the current character at index
        char currentChar = s.charAt(index);

        // If it's an opening bracket '(', increment count and move to the next
        // character.
        if (currentChar == '(') {
            return helper(s, index + 1, cnt + 1);
        }

        // If it's a closing bracket ')', decrement count and move to the next
        // character.
        if (currentChar == ')') {
            return helper(s, index + 1, cnt - 1);
        }

        // If it's a '*' (wildcard), it can behave as '(', ')', or an empty string.
        // Explore all three possibilities by recursively calling the helper function.
        if (currentChar == '*') {
            // Option 1: Treat '*' as an opening bracket '('
            // Option 2: Treat '*' as a closing bracket ')'
            // Option 3: Treat '*' as an empty string (ignore it)
            return helper(s, index + 1, cnt + 1) ||
                    helper(s, index + 1, cnt - 1) ||
                    helper(s, index + 1, cnt);
        }

        // Return false if no valid conditions are met.
        return false;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    public static boolean checkValidString1(String s) {
        // n stores the length of the string
        int n = s.length();

        // min represents the minimum possible number of open parentheses ('(').
        // max represents the maximum possible number of open parentheses ('(').
        int min = 0; // Minimum possible count of open brackets
        int max = 0; // Maximum possible count of open brackets

        // Loop through each character of the string
        for (int i = 0; i < n; i++) {
            // Get the current character at index i
            char currentChar = s.charAt(i);

            // If the current character is '(', it increases both min and max
            // because it's guaranteed that we need a ')' to balance it.
            if (currentChar == '(') {
                min++; // '(' increases the minimum possible open count
                max++; // '(' increases the maximum possible open count
            }
            // If the current character is ')', it decreases both min and max
            // because it could balance an open '(' or be invalid.
            else if (currentChar == ')') {
                min--; // ')' decreases the minimum possible open count
                max--; // ')' decreases the maximum possible open count
            }
            // If the current character is '*', we don't know whether it behaves as '(',
            // ')', or nothing, so we consider all possibilities:
            // - It could decrease min (as if it's ')'),
            // - Or it could increase max (as if it's '('),
            // - It might be ignored altogether.
            else { // currentChar == '*' (wildcard)
                min--; // '*' can act as ')' so we reduce min
                max++; // '*' can act as '(' so we increase max
            }

            // If min becomes negative, it means we have more closing brackets than opening ones,
            // which isn't valid, so we reset min to 0 because there can't be negative open brackets.
            if (min < 0)
                min = 0;

            // If max becomes negative, it means we have more closing brackets than opening brackets
            // across all possible scenarios, and thus the string is invalid.
            if (max < 0)
                return false;
        }

        // After processing the entire string, if the minimum possible count of open brackets is 0,
        // it means the string can be valid with balanced parentheses, so we return true.
        // If min is not zero, there are unbalanced open brackets, so it's invalid.
        return min == 0;
    }

    // Main Function
    public static void main(String[] args) {
        String testString1 = "()*)*()";
        String testString2 = "(*()";

        // Call the helper method with test strings and print results
        System.out.println("Is testString1 valid? " + checkValidString(testString1));
        System.out.println("Is testString2 valid? " + checkValidString1(testString2));
    }
}

// Output :
/*
Is testString1 valid? true
Is testString2 valid? true
 */

// Approach / Intuition : Brute Force
/*
This code recursively checks if a string with `(`, `)`, and `*` can be valid by treating `*` as
either an opening bracket `(`, a closing bracket `)`, or nothing. It starts at the first character
with an initial count (`cnt`) of 0 for open parentheses and checks each character: increasing `cnt`
for `(`, decreasing it for `)`, and exploring all possibilities for `*`. If at any point `cnt`
becomes negative, it means there are too many closing brackets, and the string is invalid. The
recursion continues until the string is fully traversed, and the string is valid if `cnt` is zero
at the end, meaning all parentheses are balanced.
 */

// Approach / Intuition : Optimal Solution
/*
The code checks if a string containing `(`, `)`, and `*` can be a valid sequence of balanced parentheses
by tracking the minimum (`min`) and maximum (`max`) possible number of open brackets. For each `(`,
both `min` and `max` increase, and for each `)`, both decrease. When encountering `*`, it could act
as `(`, `)`, or be ignored, so `min` decreases and `max` increases. If `min` goes below 0, it's reset
to 0 because we can't have negative open brackets, and if `max` becomes negative, it means there are
too many closing brackets, making the string invalid. Finally, if `min == 0` after processing, the
string is valid; otherwise, it isn't.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=cHT6sG_hUZI