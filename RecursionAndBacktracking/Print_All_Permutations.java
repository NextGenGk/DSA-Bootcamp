package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class Print_All_Permutations {

    // Method 1 : Optimal Solution (Using Extra Space)
    // Time Complexity: O(N! * N), n! is the number of permutations
    // another O(N) (N is the number of elements)
    // Space Complexity: O(n) + O(n) ~ O(2N), O(N) is for storing the answer and
    // another O(N) is for frequency array.
    // Function to generate all unique permutations
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>(); // Stores all permutations
        boolean[] frequency = new boolean[nums.length]; // Tracks used elements
        recurPermute(nums, ans, new ArrayList<>(), frequency);
        return ans; // Return the list of all permutations
    }

    // Recursive function to generate permutations
    private static void recurPermute(int[] nums, List<List<Integer>> ans, List<Integer> ds, boolean[] frequency) {
        // Base Case: If ds contains all elements, add it to the answer list
        if (ds.size() == nums.length) {
            ans.add(new ArrayList<>(ds)); // Add a copy of ds to ans
            return;
        }

        // Loop through the array to try each element
        for (int i = 0; i < nums.length; i++) {
            if (!frequency[i]) { // If element is not already used in ds
                // Pick the element
                frequency[i] = true; // Mark as used
                ds.add(nums[i]); // Add element to current permutation

                // Recursive call to place the next element
                recurPermute(nums, ans, ds, frequency);

                // Backtrack: Remove the last added element and mark it unused
                ds.remove(ds.size() - 1);
                frequency[i] = false;
            }
        }
    }

    // Main function to test the permutation generator
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);

        // Print all the permutations
        System.out.println(result);
    }
}

// Output :
// [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

// Intuition : Optimal Solution (Using Extra Space)
/*
The problem requires generating all possible permutations of a given array of distinct integers.
Since order matters in permutations, we use backtracking to explore different orderings systematically.

Key Observations :
1. Each element must appear exactly once in every permutation
    i. We use a boolean frequency[] array to track whether an element has already been used in
       the current permutation.
2. Recursive approach to build permutations step by step
    i. We maintain a temporary list (ds) to store the ongoing permutation.
   ii. If ds.size() == nums.length, we have found a valid permutation.
3. Backtracking to explore all possibilities
    i. After choosing an element, we mark it as used, make the recursive call, and then
    backtrack (undo choices).
 */

// Striver's (Video Explanation) : https://youtu.be/YK78FU5Ffjw?si=4AYfRjhHp0mapUHI