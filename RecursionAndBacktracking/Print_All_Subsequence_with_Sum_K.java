package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class Print_All_Subsequence_with_Sum_K {

    // Method 1 : Optimal Solution
    // Time Complexity: O(2^N) - As we have 2 choices for each element, either include or exclude
    // Space Complexity: O(N) - As we are using recursion stack
    // Recursive function to find subsequences with sum k
    public static void subsequencesWithSumK(int[] arr, int index,
                                            List<Integer> subsequence, int sum, int k) {
        // Base case: If index reaches the end
        if (index == arr.length) {
            if (sum == k) { // If sum matches k, print the subsequence
                System.out.println(subsequence);
            }
            return;
        }

        // Pick the current element
        subsequence.add(arr[index]);
        sum += arr[index];
        subsequencesWithSumK(arr, index + 1, subsequence, sum, k);

        // Don't pick the current element (Backtrack)
        subsequence.remove(subsequence.size() - 1);
        sum -= arr[index];
        subsequencesWithSumK(arr, index + 1, subsequence, sum, k);
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        subsequencesWithSumK(arr, 0, new ArrayList<>(), 0, 3);
    }
}

// Output :
// [1, 2]
// [2, 1]

// Intuition : Optimal Solution
/*
Intuition :
To generate subsequences with a sum of k using recursion :
1. Pick the current element and include it in the subsequence.
2. Don’t pick the current element and move to the next index.
3. Repeat this for all elements until you reach the base case (i.e., index out of bounds).
4. If the sum of the subsequence matches k, print the subsequence.
5. This approach ensures that all possible subsequences with a sum of k are generated.

Algorithm :
1. Create a recursive function subsequencesWithSumK(arr, index, currentSubsequence, sum, k).
2. If the index reaches the end, check if the sum of the subsequence matches k.
3. If the sum matches k, print the subsequence.
4. Pick the current element and include it in the subsequence.
5. Recur for the next index with the updated sum.
6. Don’t pick the current element (Backtrack).
7. Recur for the next index with the updated sum.
8. This approach ensures that all possible subsequences with a sum of k are generated.
 */

// Recursion Tree
/*
                                            (0, [], 0)
                                    /                          \
                            (1, [1], 1)                         (1, [], 0)
                           /            \                     /             \
                    (2, [1,2], 3)   (2, [1], 1)        (2, [2], 2)       (2, [], 0)
                    /        \         /       \        /       \        /      \
            (3, [1,2,1], 4) (3, [1,2], 3)  (3, [1,1], 2) (3, [1], 1) (3, [2,1], 3) (3, [2], 2) (3, [1], 1) (3, [], 0)
                            ✅ PRINT [1,2]                            ✅ PRINT [2,1]

 */

// Striver's (Video Explanation) : https://youtu.be/eQCS_v3bw0Q?si=HYPSOFBWpCDj7121
