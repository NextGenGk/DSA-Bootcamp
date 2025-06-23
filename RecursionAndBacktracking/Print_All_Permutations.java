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
    public static List<List<Integer>> permute1(int[] nums) {
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

    // Method 2 : Optimal Solution (Using No Extra Space)
    // Time Complexity: O(N! * N), n! is the number of permutations
    // another O(N) (N is the number of elements)
    // Space Complexity: O(N) for storing the answer.
    // Function to generate all unique permutations
    // Helper function to generate permutations using recursion
    private static void recurPermute(int index, int[] nums, List<List<Integer>> ans) {
        // Base case: if index reaches the end, add the current permutation to ans
        if (index == nums.length) {
            List<Integer> ds = new ArrayList<>();
            // Copy current permutation into the list
            for (int i = 0; i < nums.length; i++) {
                ds.add(nums[i]);
            }
            ans.add(new ArrayList<>(ds)); // Store a copy of the current permutation
            return;
        }

        // Swap each element with the current index and recurse
        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);  // Swap to place a new element at 'index'
            recurPermute(index + 1, nums, ans); // Recur for the next index
            swap(i, index, nums);  // Backtrack to restore the original array
        }
    }

    // Utility function to swap two elements in the array
    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Main function to return all permutations of the given array
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        recurPermute(0, nums, ans); // Start recursive permutation generation
        return ans;
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute2(nums);

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

// Intuition : Optimal Solution (Using No Extra Space)
/*
The problem requires generating all possible permutations of a given array of distinct integers.

Every number should be a particular index in the array, if it is only possible when we swap with
the right possible elements, and you try to bring all those right elements to the current index and
that's why we are swapping the elements.

Algorithm :
1. The idea is to swap each element with every other element in the array and recurse for the next index.
2. The base case is when the index reaches the end of the array, we add the current permutation to the answer.
3. We swap the current element with every other element in the array and recurse for the next index.
4. After the recursive call, we backtrack by swapping the elements back to their original positions.
5. The time complexity of this approach is O(N! * N) since there are N! permutations and each permutation
   requires O(N) time to copy the elements.
 */

// Striver's (Video Explanation) : https://youtu.be/YK78FU5Ffjw?si=4AYfRjhHp0mapUHI
