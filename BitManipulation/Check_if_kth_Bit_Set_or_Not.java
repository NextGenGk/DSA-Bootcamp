package BitManipulation;

public class Check_if_kth_Bit_Set_or_Not {

    // Method 1 : Optimal Solution
    // Time Complexity: O(1) as the time complexity remains constant and independent
    // of the input size. The major operation is the shift left and bitwise AND which
    // execute in constant time regardless of the size of the input number or the bit position.
    // Space Complexity : O(1) as the space complexity remains constant and independent of
    // the input size. Only a fixed amount of memory is required to store the integer variables.
    // Function to check if the kth bit is
    // set in the binary representation of n
    static boolean isKthBitSet(int n, int k) {
        // Create a mask with only the kth bit
        // set to 1 and all other bits set to 0
        int mask = 1 << (k - 1);
        // Perform bitwise AND operation
        // between n and the mask, which results
        // in a value where only the kth bit
        // is preserved If the result is not zero,
        // then the kth bit is set in n
        return (n & mask) != 0;
    }

    public static void main(String[] args) {
        int n = 342;
        int i = 4;
        System.out.println("n: " + n);

        if (isKthBitSet(n, i)) {
            System.out.println("Bit at position " + i + " is set (1)");
        } else {
            System.out.println("Bit at position " + i + " is not set (0)");
        }
    }
}

// Output
/*
n: 342
Bit at position 4 is not set (0)
 */

// Formula :
// (( n & ( 1 << i ) != 0)

// Intuition : Optimal Solution
/*
A more optimised approach using bitwise operators will be to use the left shift operator
to create a mask and then performing a bitwise AND operation with the number to check
if the ith bit is set or not.

Example :
Let’s take n = 12 (binary: 1100) and check if the bit at position i = 2 is set.

1. Initial value of n:
    i. n = 12 → Binary: 1100
2. Creating the Mask:
    i. 1 << 2 = 4 → Binary: 0100
3. Performing the AND operation:
    i. n & (1 << 2) = 1100 & 0100 = 0100 → Binary: 0100 (Decimal: 4)
4. Result:
    i.The result is 4, which is non-zero, meaning the bit at position 2 is set (1).
 */

// Striver (Video Explanation): https://www.youtube.com/watch?v=nttpF8kwgd4