package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class Combination_Sum_I {

    // Method 1 : Optimal Solution (Using Recursion and Backtracking)
    // Time Complexity: O(2^t * k) where t is the target, k is the average length
    // Reason: Assume if you were not allowed to pick a single element multiple times, every
    // element will have a couple of options: pick or not pick which is 2^n different recursion calls,
    // also assuming that the average length of every combination generated is k.
    // (to put length k data structure into another data structure)
    // Why not (2^n) but (2^t) (where n is the size of an array)?
    // Assume that there is 1 and the target you want to reach is 10 so 10 times you can
    // “pick or not pick” an element.
    // Space Complexity: O(k*x), k is the average length and x is the no. of combinations
    // Main function to find all unique combinations that sum up to the target
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>(); // List to store the final result
        findCombinations(candidates, 0, target, ans, new ArrayList<>()); // Start the recursive function
        return ans; // Return the final list of combinations
    }

    // Recursive function to find all valid combinations
    private static void findCombinations(int[] arr, int index, int target, List<List<Integer>> ans, List<Integer> current) {
        // Base Case: If we have considered all elements
        if (index == arr.length) {
            // If target sum becomes zero, we found a valid combination
            if (target == 0) {
                ans.add(new ArrayList<>(current)); // Add the current combination to the result
            }
            return; // Stop recursion
        }

        // If the current element can be included (i.e., does not exceed the remaining target)
        if (arr[index] <= target) {
            current.add(arr[index]); // Pick the element
            findCombinations(arr, index, target - arr[index], ans, current); // Recurse with reduced target
            current.remove(current.size() - 1); // Backtrack: Remove the element after recursion
        }

        // Move to the next element without picking the current one
        findCombinations(arr, index + 1, target, ans, current);
    }

    // Main Function
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target)); // Print result
    }
}

// Output :
// [[2, 2, 3], [7]]

// Intuition : Optimal Solution
/*
Intuition:
For questions like printing combinations or subsequences, the first thing that should
strike your mind is recursion.

How to think recursively?
Whenever the problem is related to picking up elements from an array to form a combination,
start thinking about the “pick and non-pick” approach.

Approach:
Initially, the index will be 0, target as given and the data structure(vector or list) will be empty

Now there are 2 options, to pick or not pick the current index element.

If you pick the element, again come back at the same index as multiple occurrences of the
same element is possible so the target reduces to target - arr[index] (where target - arr[index] >= 0)
and also insert the current element into the data structure.

If you decide not to pick the current element, move on to the next index and the target value
stays as it is. Also, the current element is not inserted into the data structure.

While backtracking makes sure to pop the last element as shown in the recursion tree below.

Keep on repeating this process while index < size of the array for a particular recursion call.

You can also stop the recursion when the target value is 0, but here a generalized version without
adding too many conditions is considered.

Using this approach, we can get all the combinations.

Base condition :
If (index == size) of array and  target == 0 include the combination in our answer

Algorithm :
1. Initialize an empty list to store all combinations.
2. Initialize an empty list to store the current combination.
3. Start a recursive function with index as 0, target as given and current combination as an empty list.
4. Inside the recursive function, check if index == size of array and target == 0, if true, add current
combination to our answer list.
5. If target < 0, return (we have reached a dead end, we backtrack).
6. For each element from index to size of array, check if target - arr[i] >= 0.
7. If true, add arr[i] to current combination, call the recursive function with index as i and target
as target - arr[i].
8. Remove the last element from current combination (backtracking).
9. Call the recursive function with index as i + 1 and target as given.
 */

// Striver's (Video Explanation) : https://youtu.be/OyZFFqQtu98?si=JSPjIhnzTPtFfKNx