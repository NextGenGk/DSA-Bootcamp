package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination_Sum_II {

    // Method 1 : Optimal Solution (Using Recursion and Backtracking)
    // Time Complexity:O(2^n*k)
    // Reason: Assume if all the elements in the array are unique then the no. of subsequence
    // you will get will be O(2^n). we also add the ds to our ans when we reach the base case
    // that will take “k”//average space for the ds.
    // Space Complexity:O(k*x)
    // Reason: if we have x combinations then space will be x*k where k is the average length of the combination.
    // Function to find unique combinations that sum up to the target (Each number can be used once)
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates); // Sort to handle duplicates easily
        findCombinations(candidates, 0, target, ans, new ArrayList<>());
        return ans; // Return the final list of combinations
    }

    // Recursive function to explore all possible unique combinations
    private static void findCombinations(int[] arr, int index, int target, List<List<Integer>> ans, List<Integer> current) {
        // Base Case: If target becomes 0, we found a valid combination
        if (target == 0) {
            ans.add(new ArrayList<>(current)); // Store the valid combination
            return;
        }

        // Iterate through the array from the current index
        for (int i = index; i < arr.length; i++) {
            // Skip duplicate elements to avoid duplicate combinations
            // Edge case : 1st element is always pick using this condition (i > index)
            if (i > index && arr[i] == arr[i - 1]) continue;

            // If the element is greater than the remaining target, break (since array is sorted)
            if (arr[i] > target) break;

            // Pick the element
            current.add(arr[i]);
            // Move to the next index (as elements can be used only once)
            findCombinations(arr, i + 1, target - arr[i], ans, current);
            // Backtrack: Remove the element to try other possibilities
            current.remove(current.size() - 1);
        }
    }

    // Main Function
    public static void main(String[] args) {
        int[] candidates = {1, 1, 1, 2, 2};
        int target = 4;
        List<List<Integer>> result = combinationSum2(candidates, target);
        System.out.println("Unique Combinations that sum up to " + target + ": " + result);
    }
}

// Output :
// Unique Combinations that sum up to 4: [[1, 1, 2], [2, 2]]

// Intuition : Optimal Solution
/*
Intuition :
Before starting the recursive call make sure to sort the elements because the ans should contain
the combinations in sorted order and should not be repeated.

Initially, We start with the index 0, At index 0 we have n - 1 way to pick the first element of our subsequence.

Check if the current index value can be added to our ds. If yes add it to the ds and move the index
by 1. while moving the index skip the consecutive repeated elements because they will form duplicate sequences.

Reduce the target by arr[i],call the recursive call for f(idx + 1,target - 1,ds,ans) after the call
make sure to pop the element from the ds.(By seeing the example recursive You will understand).

if(arr[i] > target) then terminate the recursive call because there is no use to check as the array is
sorted in the next recursive call the index will be moving by 1 all the elements to its right will
be in increasing order.

Base Condition:
Whenever the target value is zero add the ds to the ans return.

Algorithm :
1. We sort the array to handle duplicates easily.
2. We use a recursive function to explore all possible unique combinations.
3. We iterate through the array from the current index.
4. We skip duplicate elements to avoid duplicate combinations.
5. If the element is greater than the remaining target, we break (since the array is sorted).
6. We pick the element and move to the next index (as elements can be used only once).
7. We backtrack by removing the element to try other possibilities.
8. We store the valid combination when the target becomes 0.
9. Finally, we return the list of unique combinations.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=G1fRTGRxXU8
