package BitManipulation;

public class Convert_Binary_to_Decimal {

    // Method 1 : Optimal Solution
    // Time Complexity: O(len(binary))
    // Space Complexity: O(1) 0
    static int convert2Decimal(String binary) {
        // Initialize the result to store the decimal representation
        int res = 0;

        // Initialize a variable to represent the current power of 2 (starting from 2^0)
        int power2 = 1;

        // Loop through the binary string from the least significant bit (rightmost) to the most significant bit (leftmost)
        for (int i = binary.length() - 1; i >= 0; i--) {
            // If the current character in the binary string is '1', add the current power of 2 to the result
            if (binary.charAt(i) == '1') {
                res += power2;
            }
            // Update the power of 2 for the next iteration (move to the next bit position)
            power2 *= 2;
        }

        // Return the calculated decimal representation of the binary string
        return res;
    }

    // Main Function
    public static void main(String[] args) {
        String binary = "1101"; // 13
        System.out.println(convert2Decimal(binary));
    }
}

// Output: 1101

// Algorithm : Optimal Solution
/*
Intuition: To convert a binary number to decimal, we can multiply each bit by 2 raised
to the power of its position

Approach:
1. Initialize the result to store the decimal representation.
2. Initialize a variable power2 to store the value of 2 raised to the power of the bit position.
3. Loop through the binary string from right to left:
    a. If the current bit is '1', add 2^power2 to the result.
    b. Multiply power2 by 2 to process the next bit.
4. Return the decimal representation.
 */

// Striver (Video Explanation): https://www.youtube.com/watch?v=qQd-ViW7bfk&list=PLgUwDviBIf0rnqh8QsJaHyIX7KUiaPUv7