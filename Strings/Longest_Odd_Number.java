package Strings;

public class Longest_Odd_Number {

    // Main Function
    public static void main(String[] args) {
        Longest_Odd_Number solution = new Longest_Odd_Number();
        String num = "0214638";
        String result = solution.largestOddNumber(num);
        System.out.println("The largest odd number in the string is: " + result);
    }

    // Method 1 : Optimal Solution
    // Time Complexity: O(n) where n is the length of the input string num.
    // Space Complexity: O(1) as we are using only a constant amount of extra space.
    public String largestOddNumber(String num) {
        // Variable to store the index of the last (rightmost) odd digit
        int idx = -1;

        // Step 1: Traverse from the end to find the rightmost odd digit
        for (int i = num.length() - 1; i >= 0; i--) {
            // Convert character to integer and check if it's odd
            if ((num.charAt(i) - '0') % 2 == 1) {
                idx = i; // store the index of the last odd digit
                break;   // no need to check further
            }
        }

        // Step 2: If no odd digit is found, return an empty string
        if (idx == -1) {
            return "";
        }

        // Step 3: Skip any leading zeros before the first significant digit
        int i = 0;
        while (i <= idx && num.charAt(i) == '0') {
            i++;
        }

        // Step 4: Return the substring from the first non-zero digit
        // up to and including the last odd digit
        return num.substring(i, idx + 1);
    }
}

// Output:
// The largest odd number in the string is: "21463"

// Algorithm :
/*
1. Initialize an index variable `idx` to -1 to store the position of the last odd digit.
2. Traverse the string `num` from the end to the beginning:
   a. For each character, check if it represents an odd digit.
   b. If an odd digit is found, update `idx` with its position and break the loop.
3. If `idx` remains -1 after the loop, return an empty string as there are no odd digits.
4. Initialize another index variable `i` to 0 to find the first non-zero digit.
5. Traverse the string from the beginning until `i` exceeds `idx` or a non-zero digit is found:
   a. Increment `i` while the character at position `i` is '0'.
6. Return the substring of `num` starting from index `i` to `idx + 1`, which represents the
   largest odd number without leading zeros.
 */

// Striver's (Article Explanation) : https://takeuforward.org/data-structure/largest-odd-number-in-a-string