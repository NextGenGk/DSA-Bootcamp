package BitManipulation;

public class Convert_Decimal_to_Binary {

    // Method 1 : Optimal Solution
    // Time Complexity: O(log2N)
    // Space Complexity: O(log2N)
    static String convert2Binary(int n) {
        // Initialize an empty StringBuilder to store the binary representation
        StringBuilder res = new StringBuilder();

        // Loop until n becomes 0
        while (n > 0) {
            // If n is odd, append "1" to the result; otherwise, append "0"
            if (n % 2 == 1) {
                res.append("1");
            } else {
                res.append("0");
            }

            // Divide n by 2 to process the next bit
            n /= 2;
        }

        // Reverse the string to get the correct binary representation
        return res.reverse().toString();
    }

    // Main Function
    public static void main(String[] args) {
        int n = 13;
        System.out.println(convert2Binary(n));
    }
}

// Output: 1101

// Algorithm : Optimal Solution
/*
Intuition: To convert a decimal number to binary, we can repeatedly divide the number by 2
and append the remainder to the result.

Approach:
1. Initialize an empty StringBuilder to store the binary representation.
2. Loop until n becomes 0:
    a. If n is odd, append "1" to the result; otherwise, append "0".
    b. Divide n by 2 to process the next bit.
3. Reverse the string to get the correct binary representation.
4. Return the binary representation as a string.
 */

// Striver (Video Explanation): https://www.youtube.com/watch?v=qQd-ViW7bfk&list=PLgUwDviBIf0rnqh8QsJaHyIX7KUiaPUv7