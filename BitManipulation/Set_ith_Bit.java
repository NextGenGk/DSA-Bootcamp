package BitManipulation;

public class Set_ith_Bit {

    // Method 1 : Optimal Solution
    // Time Complexity: O(1) as the time complexity remains constant and independent
    // of the input size. The major operation is the bitwise left shift 1 << K and the
    // bitwise OR operation N | K also has a time complexity of O(1).
    // Space Complexity: O(1) as the space complexity remains constant and independent
    // of the input size. Only a fixed amount of money is required to store the integer variables.
    public static int setKthBit(int N, int K) {
        // Create a mask with a single bit
        // set at the Kth position.
        // Left shift the binary digit 1 by
        // K positions to create the mask.
        int mask = 1 << K;

        // Perform a bitwise OR operation between
        // the original number (N) and the mask.
        // This sets the bit at the Kth position
        // to 1 without altering other bits.
        return N | mask;
    }

    // Main Function
    public static void main(String[] args) {
        int n = 10;
        int k = 2;

        int result = setKthBit(n, k);

        System.out.println("Original number: " + n);
        System.out.println("Setting bit at index " + k + " to 1: " + result);
    }
}

// Output :
/*
Original number: 10
Setting bit at index 2 to 1: 14
 */

// Formula :
// n = n | (1 << i)

// Intuition : Optimal Solution
/*
To set a specific bit in a decimal number to 1 at a given index, we generate a
mask with a 1 at the index position and 0s elsewhere. We then perform a bitwise
OR operation between the original number and the mask to set the desired bit.

For all positions except the ith index, the corresponding bits in the number remain
unchanged because ORing with 0 does not change the value. At the ith index, the
bit in the number will be set to 1 if it was previously 0, and it will remain 1 if
it was already 1.

Example:
Let’s take n = 5 (binary: 0101) and set the bit at position i = 3.

1. Initial value of n:
    i. n = 5 → Binary: 0101
2. Creating the Mask:
    i. 1 << 3 = 8 → Binary: 1000
3. Performing the OR operation:
    i. n | (1 << 3) = 0101 | 1000 = 1101 → Binary: 1101 (Decimal: 13)
4. Result:
    i. The bit at position 3 is set to 1, and the updated number is 13 (binary: 1101).
 */

// Striver (Video Explanation): https://www.youtube.com/watch?v=nttpF8kwgd4