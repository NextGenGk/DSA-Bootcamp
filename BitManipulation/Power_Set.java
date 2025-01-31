package BitManipulation;

import java.util.ArrayList;
import java.util.List;

public class Power_Set {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N x 2N) where N is the number of elements in the input array.
    // i. Iterating through all possible numbers from 0 to 2N-1 where N is the number of
    // elements in the input array requires O(2N) iterations.
    // ii. For each iteration, we perform O(N) operations to construct the corresponding
    // subset by interpreting the bits of the number.
    // Space Complexity: O(N x 2N) where N is the number of elements in the input array.
    // We store all subsets in a list. Since there are 2N subsets in the power set,
    // each subset can have at most N elements.
    // Function to generate all subsets of
    // the input array using bitwise operators
    public static List<List<Integer>> getPowerSet(int[] nums) {
        // Get the size of the input array
        int n = nums.length;
        // Calculate the total number of
        // subsets using bitwise left
        // shift operator
        int subsets = 1 << n;
        // Initialize a list
        // to store all subsets
        List<List<Integer>> ans = new ArrayList<>();

        // Iterate through all numbers
        // from 0 to subsets - 1
        for (int num = 0; num < subsets; num++) {
            // Initialize a list to
            // store the current subset
            List<Integer> subset = new ArrayList<>();

            // Iterate through each bit
            // position in the current number
            for (int i = 0; i < n; i++) {
                // Check if the ith bit of
                // the current number is set
                if ((num & (1 << i)) != 0) {
                    // If the bit is set, add the
                    // corresponding element from
                    // the input array to the subset
                    subset.add(nums[i]);
                }
            }

            // Add the current subset
            // to the list of subsets
            ans.add(subset);
        }

        // Return the list
        // of all subsets
        return ans;
    }

    // Main Function
    public static void main(String[] args) {
        // Input array
        int[] nums = {1, 2, 3};
        // Function call to generate
        // all subsets of the input array
        List<List<Integer>> powerSet = getPowerSet(nums);

        // Print all subsets
        for (List<Integer> subset : powerSet) {
            System.out.println(subset);
        }
    }
}

// Output :
// []
// [1]
// [2]
// [1, 2]
// [3]
// [1, 3]
// [2, 3]
// [1, 2, 3]

// Intuition : Optimal Solution
/*
Algorithm / Intuition
This problem has already been previously solved using Recursion here:Power Set: Recursion.

To solve it using bit wise operators, we observe a pattern that the number of subsets is
dependant on the size of the input array as:

N = 1, No. of subsets = 2
N = 2, No. of subsets = 4
N = 3, No. of subsets = 8 and so on…
No. of subsets of input array of size N = 2N = [1 << n]

Hence we can associate each subset with a number in the range 0 to 2N-1. Each of these
numbers represents a bitmask where each bit indicates whether the corresponding element
from the input array is included in the current subset or not.

Algorithm :
Step 1: Create an empty array to hold all the subsets of the input array.
Step 2: Determine the size of the power set, which is 2N, where N represents the
number of elements in the input array.
Step 3: Iterate through all possible numbers from 0 to 2N-1: Each number from 0
to 2N-1 represents a unique combination of elements in the input array.
Step 4: Iterate through all possible numbers from 0 to 2N-1: Each number from 0
to 2N-1 represents a unique combination of elements in the input array.
Step 5: After constructing each subset, add it to the array of subsets.
Step 6: Return the array containing all the subsets of the input array.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=LqKaUv1G3_I