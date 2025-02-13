package RecursionAndBacktracking;

public class Count_Subsequence_with_Sum_K {

    // Method 1 : Optimal Solution
    // Time Complexity: O(2^N) - As we have 2 choices for each element, either include or exclude
    // Space Complexity: O(N) - As we are using recursion stack
    // Recursive function to count subsequences with sum k
    public static int countSubsequenceWithSumK(int[] arr, int index, int sum, int k) {
        // Base case: If index reaches the end
        if (index == arr.length) {
            // If sum matches k, (Condition satisfied)
            if (sum == k) {
                return 1;
            }
            // If sum doesn't match k, (Condition not satisfied)
            return 0;
        }

        // Pick the current element
        sum += arr[index];
        // Call the left function
        int l = countSubsequenceWithSumK(arr, index + 1, sum, k);

        // Don't pick the current element (Backtrack)
        sum -= arr[index];
        // Call the right function
        int r = countSubsequenceWithSumK(arr, index + 1, sum, k);

        // Return the total count
        return l + r;
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        System.out.println(countSubsequenceWithSumK(arr, 0, 0, 2));
    }
}

// Output :
// 2

// Intuition : Optimal Solution
/*
Intuition :
To generate subsequences with a sum of k using recursion :
1. Pick the current element and include it in the subsequence.
2. Don’t pick the current element and move to the next index.
3. Repeat this for all elements until you reach the base case (i.e., index out of bounds).
4. If the sum of the subsequence matches k, increment the count.
5. This approach ensures that all possible subsequences with a sum of k are generated.

Algorithm :
1. Create a recursive function countSubsequenceWithSumK(arr, index, sum, k).
2. If the index reaches the end, check if the sum of the subsequence matches k.
3. If the sum matches k, increment the count.
4. Pick the current element and include it in the subsequence.
5. Recur for the next index with the updated sum.
6. Don’t pick the current element (Backtrack).
7. Recur for the next index with the updated sum.
8. Return the total count.
 */

// Recursion Tree :
/*
                                   (0, 0)
                            /                     \
                     (1, 1)                         (1, 0)
                   /        \                     /        \
               (2, 3)      (2, 1)            (2, 2)       (2, 0)
             /      \      /      \          /      \      /      \
        (3, 4)  (3, 3) (3, 2)  (3, 1)  (3, 3)  (3, 2)  (3, 1)  (3, 0)

                             ✅ +1         ✅ +1

 */

// Striver's (Video Explanation) : https://youtu.be/eQCS_v3bw0Q?si=HYPSOFBWpCDj7121