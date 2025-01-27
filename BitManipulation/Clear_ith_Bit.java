package BitManipulation;

public class Clear_ith_Bit {


    // Method 1 : Optimal Solution
    // Time Complexity: O(1) as the time complexity remains constant and independent of
    // the input size. The major operation is the bitwise left shift 1 << K, negating the
    // mask and the bitwise AND operation N & K also has a time complexity of O(1).
    // Space Complexity: O(1) as the space complexity remains constant and independent
    // of the input size. Only a fixed amount of money is required to store the integer variables.
    // Function to clear the kth bit of n
    static int clearBit(int n, int k) {
        // Creating the mask by shifting
        // 1 to the left by k positions
        // This creates a mask where the kth
        // bit is 0 and all other bits are 1.
        int mask = ~(1 << k);

        // Clearing the kth bit of n using
        // bitwise AND with the mask
        // This clears the kth bit of n by
        // performing a bitwise AND
        // operation with the mask.
        return n & mask;
    }

    // Main Function
    public static void main(String[] args) {
        int n = 13;
        int k = 2;

        int result = clearBit(n, k);

        System.out.println("Original number: " + n);
        System.out.println("After clearing the " + k + "th bit to 1: " + result);
    }
}

// Output :
/*
Original number: 13
After clearing the 2th bit to 1: 9
 */

// Formula :
// n = n & ~(1 << i)

// Intuition : Optimal Solution
/*
To clear the bit at the ith index to 0, we need to first target the ith position.
This can be done by creating a mask using 1 << i, this shifts 1 to the left i times
setting the ith bit to 1 and all other bits to 0. We negate this mask to make all bits
1 except for the ith bits, which is now 0.

Now that we have the inverted mask, we perform a bitwise AND operation between n and the
inverted mask. This operation clears the ith bit of n to 0 while leaving all other bits
unchanged. Finally, we assign the result of the bitwise AND operation back to n,
effectively updating n with the ith bit cleared.

Example :
Let’s say n = 14 (binary: 1110) and we want to clear the bit at position i = 1.

1. Initial value of n:
    i. n = 14 → Binary: 1110
2. Creating the Mask:
    i. 1 << 1 = 2 → Binary: 0010
3. Negating the Mask:
    i. ~(1 << 1) = ~(0010) = 1101 → Binary: 1101
4. Performing the AND operation:
    i. n & ~(1 << 1) = 1110 & 1101 = 1100 → Binary: 1100 (Decimal: 12)
5. Result:
    i. The bit at position 1 (which was 1 in n = 14) is cleared, and the updated
    number is 12 (binary: 1100).
 */

// Striver (Video Explanation): https://www.youtube.com/watch?v=nttpF8kwgd4