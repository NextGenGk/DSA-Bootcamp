package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.Collections;

public class Subset_Sum_I {

    // Method 1 : Optimal Solution
    // Time Complexity: O(2^n)+O(2^n log(2^n)). Each index has two ways. You can either pick
    // it up or not pick it. So for n index time complexity for O(2^n) and for sorting it will
    // take (2^n log(2^n)).
    // Space Complexity: O(2^n) for storing subset sums, since 2^n subsets can be generated for
    // an array of size n.
    // Method to calculate all subset sums
    public static ArrayList<Integer> subsetSums(int[] arr) {
        // Create a list to store the results
        ArrayList<Integer> ans = new ArrayList<>();
        // Call the generateSubsequences() method to generate the subset sums
        generateSubsequences(arr, 0, 0, ans);
        // Sort the results
        Collections.sort(ans);
        // Return the results
        return ans;
    }

    // Function to generate all the subsets and their sums
    private static void generateSubsequences(int[] arr, int index, int sum, ArrayList<Integer> ans) {
        // Base Case: If index is out of bounds, add the sum to the list and return
        if (index == arr.length) {
            ans.add(sum);
            return;
        }

        // Pick the element
        generateSubsequences(arr, index + 1, sum + arr[index], ans);

        // Don't pick the element (backtrack)
        generateSubsequences(arr, index + 1, sum, ans);
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {3, 1, 2};
        ArrayList<Integer> result = subsetSums(arr);
        System.out.println(result); // Output: [0, 1, 2, 3, 3, 4, 5, 6]
    }
}

// Output: [0, 1, 2, 3, 3, 4, 5, 6]

// Intuition : Optimal Solution (Using Recursion and Backtracking)
/*
Intuition :
1. For problems involving generating combinations or subsequences, recursion is a natural approach.
2. The "pick and non-pick" strategy is useful for generating all possible combinations.
3. The goal is to explore all subsets of the array and calculate their sums.
4. By recursively including or excluding each element, we can generate all possible subsets.

Algorithm :
1. Initialize an empty list to store all subset sums.
2. Define a recursive function generateSubsequences that takes the array, current index, current sum,
 and the result list as parameters.
3. In the recursive function:
    1. If the current index is out of bounds (i.e., equals the length of the array), add the current
     sum to the result list and return.
    2. Otherwise, recursively call the function twice:
    3. Once including the current element in the sum.
    4. Once excluding the current element.
4. In the main function, call the recursive function starting with index 0 and sum 0.
5. Sort the result list to get the subset sums in ascending order.
6. Return the result list.
 */

// Recursion Tree :
/*                                            (0, 0, [])
                                        /                          \
                            (1, 3, [3])                         (1, 0, [])
                           /            \                     /             \
                    (2, 4, [3, 1])   (2, 3, [3])        (2, 1, [1])       (2, 0, [])
                     /        \         /       \        /       \        /      \
          (3, 6, [3, 1, 2]) (3, 4, [3, 1])  (3, 5, [3, 2]) (3, 3, [3]) (3, 3, [1, 2]) (3, 1, [1]) (3, 2, [2]) (3, 0, [])
*/

// Striver's (Video Explanation) : https://youtu.be/rYkfBRtMJr8?si=W5z7FQVSbMfvWMuc