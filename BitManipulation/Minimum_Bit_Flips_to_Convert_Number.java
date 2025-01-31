package BitManipulation;

public class Minimum_Bit_Flips_to_Convert_Number {

    // Method 1 : Optimal Solution
    // Time Complexity: O(31) as the time complexity remains constant and because the
    // loop iterates over a fixed number of bit positions which is constant and independent
    // of the magnitude of the input numbers.
    // Space Complexity: O(1) as the algorithm uses only a constant amount of extra space.
    // The space required does not increase with the size of input numbers.
    // Function to calculate the number of
    // flips needed to convert the start
    // number to the goal number
    static int numberOfFlips(int start, int goal) {
        // XOR of start and goal numbers
        int xorResult = start ^ goal;
        // Counter to track the
        // number of set bits
        int cnt = 0;

        // Iterate over each bit position
        for (int i = 0; i < 32; i++) {
            // Check if the i-th bit
            // of xorResult is set
            if ((xorResult & (1 << i)) != 0) {
                // Increment the count
                // if the bit is set
                cnt++;
            }
        }
        // Return the count of
        // set bits (number of flips)
        return cnt;
    }

    // Mai Function
    public static void main(String[] args) {
        int start = 10;
        int goal = 20;
        System.out.println("Number of flips needed to" +
                " convert 10 to 20: " + numberOfFlips(start, goal));
    }
}

// Output : Number of flips needed to convert 10 to 20: 4

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
To convert a given number to a target number, we need to determine the number of differing
bits between the two. An operator that efficiently identifies differing bits is the XOR operation.

When we perform an XOR operation between the start number and the target number, the
resulting value will have set bits at positions where the corresponding bits in the two
numbers are different.

By counting the number of set bits in this XOR result, we can find the number of bit flips
required to transform the start number into the target number.

Algorithm
Step 1:Calculate the XOR of start and goal denoted by xorResult. XOR operations return a
binary 1 if the corresponding bits are different and 0 if they are the same.
Step 2: Initialise a counter variable to keep track of the number of set bits in the xorResult.
Step 3: Iterate through each bit of the xorResult from 0 to 31 (as integer is stored as 32 bits).
Inside the loop, for each position ‘i’:
    i. Check if the ith bit of the xorResult is set (equal to 1) using a bitwise AND operation with a
       mask where 1 is left-shifted by ‘i’ positions.
   ii. If the ith bit is set, increment the counter variable indicating that a bit flip is required
       at that position to convert start to goal.
Step 4: Return the final count of set bits which represents the minimum number of bit flips needed
to convert start to goal.
 */

// Striver (Video Explanation : https://www.youtube.com/watch?v=OOdrmcfZXd8