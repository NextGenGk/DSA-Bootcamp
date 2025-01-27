package BitManipulation;

public class Remove_Rightmost_Set_Bit {

    // Method 1 : Optimal Solution
    // Time Complexity: O(1) as the time complexity remains constant and independent
    // of the input size. The major operation is the bitwise left shift 1 << K, negating
    // the mask and the bitwise AND operation N & K also has a time complexity of O(1).
    // Space Complexity: O(1) as the space complexity remains constant and independent
    // of the input size. Only a fixed amount of money is required to store the integer variables.
    // Function to toggle the ith bit of n
    public static int removeRightmostSetBit(int n) {
        // Perform the operation n & (n - 1) to remove the rightmost set bit
        return n & (n - 1);
    }


    // Main Function
    public static void main(String[] args) {
        int n = 12;  // Binary: 1100
        System.out.println(removeRightmostSetBit(n));  // Output: 8 (Binary: 1000)

        n = 6;  // Binary: 0110
        System.out.println(removeRightmostSetBit(n));  // Output: 4 (Binary: 0100)
    }
}

// Output: 8
// Output: 4

// Formula :
// n = n & (n - 1)

// Intuition : Optimal Solution
/*
What does n & (n - 1) do?
1. Subtracting 1 from n flips all the bits after the rightmost set bit
(including the rightmost 1 itself).
2. By applying n & (n - 1), we clear (set to 0) the rightmost set bit in n,
leaving all other bits unchanged.

Example :
Let's take n = 12 (binary: 1100).

1. Initial value of n:
    i. n = 12 → Binary: 1100
2. Subtract 1 from n:
    i. n - 1 = 11 → Binary: 1011
3. Perform the operation n & (n - 1):
    i. n & (n - 1) = 1100 & 1011 = 1000 → Binary: 1000 (Decimal: 8)
4. Result:
    i. The rightmost set bit (at position 2) is removed, and the updated
    number is 8 (binary: 1000).
 */

// Striver (Video Explanation): https://www.youtube.com/watch?v=nttpF8kwgd4