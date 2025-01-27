package BitManipulation;

public class Toggle_ith_Bit {

    // Method 1 : Optimal Solution
    // Time Complexity: O(1) as the time complexity remains constant and independent
    // of the input size. The major operation is the bitwise left shift 1 << K, negating
    // the mask and the bitwise AND operation N & K also has a time complexity of O(1).
    // Space Complexity: O(1) as the space complexity remains constant and independent
    // of the input size. Only a fixed amount of money is required to store the integer variables.
    // Function to toggle the ith bit of n
    static int toggleBit(int n, int i) {
        // Creating the mask by shifting
        // 1 to the left by i positions
        // This creates a mask where only the ith
        // bit is set to 1.
        int mask = 1 << i;

        // Toggling the ith bit of n using
        // bitwise XOR with the mask
        // This toggles the ith bit of n by
        // performing a bitwise XOR
        // operation with the mask.
        return n ^ mask;
    }

    // Main Function
    public static void main(String[] args) {
        int n = 13;
        int i = 2;

        int result = toggleBit(n, i);

        System.out.println("Original number: " + n);
        System.out.println("After toggling the " + i + "th bit: " + result);
    }
}

// Output
/*
Original number: 13
After toggling the 2th bit: 9
 */

// Eg;
/*
Example : 1
Input: N = 12
Output: 8
Explanation:  The rightmost set bit in the binary representation of 12 (1100) is at
index 2 (from the right). After removing this bit, the updated number is 8 (binary: 1000).

Example : 2
Input: N = 25
Output: 24
Explanation:  The binary representation of 25 is 11001. The rightmost set bit is at
index 0 (from the right). After removing this bit, the updated number is 24, which
in binary is 11000.
 */

// Formula :
// n = n ^ ( 1 << i )

// Intuition : Optimal Solution
/*
The function toggleBit is designed to flip (toggle) the bit at position i of a given number n.
Flipping means changing a 0 bit to 1 or a 1 bit to 0.

The bitwise XOR operation (^) is the key here, and it behaves as follows:

1. If the bits are the same (either 0 ^ 0 or 1 ^ 1), the result is 0.
2. If the bits are different (0 ^ 1 or 1 ^ 0), the result is 1.
3. So, by using XOR with a mask, we can toggle the specific bit.

Example :
Let’s say n = 6 (which is 0110 in binary), and we want to toggle the bit at position i = 1.
1. Create the Mask:
    i. mask = 1 << 1 → This shifts 1 to the left by 1 position, so the mask is 0010 (which is 2 in decimal).
2. Perform the XOR:
    i. n = 6 (binary 0110)
   ii. mask = 2 (binary 0010)
  iii. n ^ mask = 0110 ^ 0010 = 0100 (which is 4 in decimal).
So, after toggling the bit at position 1, n changes from 6 (binary 0110) to 4 (binary 0100).
 */