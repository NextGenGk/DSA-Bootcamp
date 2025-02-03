package BitManipulation;

public class XOR_of_Numbers_in_a_Range {

    // Method 1 : Brute Force
    // Time Complexity: O(n) as the loop iterates n times where n is the input number.
    // Space Complexity : O(1) as no extra space is used.
    // Function to find the XOR of all numbers
    public static int xorInRange(int L, int R) {
        // Initialize ans to store
        // the result of the XOR operation
        int ans = 0;

        // Iterate from L to R (inclusive)
        for (int i = L; i <= R; i++) {

            // Perform XOR operation of ans
            // with the current value of i
            // and store the result in ans
            ans = ans ^ i;
        }

        // Return the final result after
        // iterating through all
        // numbers from L to R
        return ans;
    }

    // Method 2 : Optimal Solution
    // Time Complexity: O(1) as the time complexity is constant and does not depend on the input size.
    // Space Complexity : O(1) as no extra space is used.
    // Function to compute XOR of all numbers
    // from 1 to n based on observed pattern
    public static int xorTill(int n) {
        // Check if n is
        // congruent to 1 modulo 4
        if (n % 4 == 1) {
            return 1;
        }
        // Check if n is congruent
        // to 2 modulo 4
        else if (n % 4 == 2) {
            return n + 1;
        }
        // Check if n is
        // congruent to 3 modulo 4
        else if (n % 4 == 3) {
            return 0;
        }
        // Return condition
        // when n is a multiple
        else {
            return n;
        }
    }

    // Function to compute XOR of numbers in the range [L, R]
    public static int xorInRange1(int L, int R) {
        // Compute XOR of numbers from 1 to L-1
        // and 1 to R using the xorTill function
        int xorTillL = xorTill(L - 1);
        int xorTillR = xorTill(R);
        // Compute XOR of the range from L to R
        return xorTillL ^ xorTillR;
    }

    // Main Function
    public static void main(String[] args) {
        int L = 3;
        int R = 19;
        int ans = xorInRange(L, R);
        System.out.println("XOR of of Numbers from " + L + " to " + R + ": " + ans);
    }
}

// Output :
// XOR of of Numbers from 3 to 19: 20

// Intuition : Brute Force
/*
Algorithm / Intuition
A brute force approach would be to calculate the XOR of all numbers from L to R iteratively using a for loop.

Algorithm:
Step 1:Initialise a variable ans to store the result of the XOR operations.
Step 2: Iterate from 1 to n ie. the input number. At each step: Perform XOR
operation of the current value of i with the current value of ans and store the result back in ans.
Step 3:After iterating through all numbers from L to R and return the final value of ans.
 */

// Intuition : Optimal Solution
/*
We can use a more optimised approach using bitwise where we find the XOR in range 1 to L-1
and XOR in range 1 to R. We XOR these to results add cancel out all the common XORs before L,
leaving us with the XOR of numbers within the range [L, R].

Algorithm :

Step 1: Define a function that takes an integer n as input and calculates the XOR of numbers
from 1 to n based on the pattern:

If N % 4 == 0, the XOR result is N itself.
If N % 4 == 1, the XOR result is 1.
If N % 4 == 2, the XOR result is N + 1.
If N % 4 == 3, the XOR result is 0.

Read more about this approach here: Xor of Numbers in given range

Step 2: For the given range [L, R], calculate the XOR of numbers from 1 to L-1 and 1 to R using
 the function defined in the previous step.
Step 3: Return the XOR of the range [L, R] by XORing the results of 1 to L-1 and 1 to R.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=WqGb7159h7Q