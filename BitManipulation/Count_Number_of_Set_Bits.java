package BitManipulation;

public class Count_Number_of_Set_Bits {

    // Method 1 : Optimal Solution
    // Time Complexity: O(total no. of set bits)
    // Space Complexity: O(1)
    static int getBits(int n) {
        // Initialize a counter to keep track of the number of
        // set bits (1s) in the binary representation of n
        int count = 0;

        // Continue looping while n is greater than 0
        while (n > 0) {
            // Check if the least significant bit (LSB) of n is set (1)
            // This is done using the bitwise AND operation: n & 1
            if ((n & 1) == 1) {
                count++; // Increment the counter if the LSB is set
            }

            // Right shift n by 1 to process the next bit
            // Example: If n = 1010 (binary), after n >> 1, n = 0101 (binary)
            n = n >> 1;
        }

        // Once the loop finishes, count contains the total number of set bits in n
        return count;
    }

    // Main Function
    public static void main(String[] args) {
        int n = 13; // 1101 (binary) -> 3 set bits
        System.out.println(getBits(n));
    }
}

// Output: 3

// Intuition : Optimal Solution
/*
Use brute force to count the number of set bits (1s) in the binary representation of a number.
 */

// Algorithm : Optimal Solution
/*
1. Start with count = 0.
2. Use n & 1 to check if the last bit of n is 1. Add it to count.
3. Right shift n by 1 (n = n >> 1) to process the next bit.
4. Repeat until n becomes 0.
5. Return count as the total number of 1s in n.
 */

// Striver (Video Explanation): https://www.youtube.com/watch?v=nttpF8kwgd4