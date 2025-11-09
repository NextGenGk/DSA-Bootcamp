package Strings;

public class Implement_Atoi {

    // Method 1: Optimal Approach
    // Time Complexity: O(N) where N is the length of the input string. We traverse the string once.
    // Space Complexity: O(1) as we use a constant amount of extra space.
    public int myAtoi(String s) {
        int i = 0;          // Pointer/index to iterate through the string
        int sign = 1;       // Default sign is +1 (positive number)
        long res = 0;       // Result stored as long to handle overflow during computation

        // Step 1: Skip all leading whitespaces (as per atoi rules)
        while (i < s.length() && s.charAt(i) == ' ') i++;

        // Edge Case: string may have only spaces, so return 0
        if (i == s.length()) return 0;

        // Step 2: Check for sign ('+' or '-')
        if (s.charAt(i) == '-') {
            sign = -1;
            i++; // Move pointer after sign
        } else if (s.charAt(i) == '+') {
            i++; // Only move pointer, sign remains +1
        }

        // Step 3: Process digits and build the result
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            // Extract digit from char by subtracting ASCII value of '0'
            res = res * 10 + (s.charAt(i) - '0');

            // Step 4: Overflow handling (before casting to int)
            // If res * sign exceeds int range, clamp accordingly
            if (sign * res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign * res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        // Step 5: Return final result, applying sign
        return (int) (sign * res);
    }

    // Main Function
    public static void main(String[] args) {
        Implement_Atoi atoi = new Implement_Atoi();

        String input1 = "   -42";
        System.out.println("Input: \"" + input1 + "\" => Output: " + atoi.myAtoi(input1)); // Output: -42

        String input2 = "4193 with words";
        System.out.println("Input: \"" + input2 + "\" => Output: " + atoi.myAtoi(input2)); // Output: 4193

        String input3 = "words and 987";
        System.out.println("Input: \"" + input3 + "\" => Output: " + atoi.myAtoi(input3)); // Output: 0

        String input4 = "-91283472332";
        System.out.println("Input: \"" + input4 + "\" => Output: " + atoi.myAtoi(input4)); // Output: -2147483648 (clamped)
    }
}

// Output :
// Input: "   -42" => Output: -42
// Input: "4193 with words" => Output: 4193
// Input: "words and 987" => Output: 0
// Input: "-91283472332" => Output: -2147483648 (clamped)

// Approach :
/*
1. Initialize index, sign, and result variables.
2. Skip leading whitespaces.
3. Check for optional sign and update sign variable.
4. Loop through characters while they are digits:
    - Build the number by multiplying current result by 10 and adding the new digit.
    - Check for overflow/underflow and clamp if necessary.
5. Return the final result with the correct sign.
 */

// Intuition :
/*
The goal is to convert a string s into an integer, similar to the C atoi() function:

1. Ignore leading spaces → because input may have " 42"
2. Handle optional sign → '+' or '-'
3. Build number digit by digit while characters are numeric
4. Stop conversion on invalid characters → "4193 with words" ➝ return 4193
5. Handle overflow using long because int may overflow while building the number
6. Clamp output to integer bounds:
    1. If overflow → return Integer.MAX_VALUE
    2. If underflow → return Integer.MIN_VALUE
 */

// Striver's (Article Explanation) : https://takeuforward.org/data-structure/implement-atoi
// Ayushi Sharma (Video Explanation) : https://youtu.be/qZoFJKyHQ98?si=CAwnOACOQnesuP4M
