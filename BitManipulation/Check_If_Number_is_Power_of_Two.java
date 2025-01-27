package BitManipulation;

public class Check_If_Number_is_Power_of_Two {

    // Method 1 : Optimal Solution
    // Time Complexity: O(1) as the time complexity remains constant and independent
    // of the input size. The major operation involved is the bitwise AND operation
    // between N and (N - 1), which has a time complexity of O(1). Other operations
    // such as comparison and subtraction are also constant-time operations.
    // Space Complexity: O(1) as the space complexity remains constant and
    // independent of the input size. Only a fixed amount of money is required to
    // store the integer variables.
    // Function to check if a number is a power of 2
    public static boolean isPowerOfTwo(int n) {
        // Check if N is greater than 0
        if (n <= 0) {
            return false;
        }

        // Performing bitwise AND operation between N and (N - 1)
        // to remove the last (rightmost) set bit
        int result = n & (n - 1);

        // If the result is 0, N is a power of 2
        if (result == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Main Function
    public static void main(String[] args) {
        int n = 16; // Example number
        boolean isPower = isPowerOfTwo(n);

        System.out.println("Number: " + n);
        if (isPower) {
            System.out.println("It is a power of 2.");
        } else {
            System.out.println("It is not a power of 2.");
        }
    }
}

// Output :
/*
Number: 16
It is a power of 2.
 */

// Formula :
// n = n & (n - 1)

// Intuition : Optimal Solution
/*
A number is a power of 2 if it has only one set bit in its binary representation.
For example, 8 is a power of 2 because its binary representation is 1000, which
What does n & (n - 1) do?
1. Subtracting 1 from n flips all the bits after the rightmost 1 in n.
2. Performing the bitwise AND operation between n and (n - 1) removes the rightmost bit.
3. If n is a power of 2, it has only one 1 bit, so n & (n - 1) results in 0.
4. If n is a power of 2, the result will not be 0.

Eg:
n = 8 (1000)
n - 1 = 7 (0111)
n & (n - 1) = 0 (means it is a power of 2)
 */

// Striver (Video Explanation): https://www.youtube.com/watch?v=nttpF8kwgd4