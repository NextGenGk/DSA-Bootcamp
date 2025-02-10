package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class Print_All_Subsequences {

    // Method 1 : Optimal Solution
    // Time Complexity: O(2^N) - As we have 2 choices for each element, either include or exclude
    // Space Complexity: O(N) - As we are using recursion stack
    // Recursive function to generate all subsequences
    public static void generateSubsequences(int[] arr, int index, List<Integer> subsequence) {
        // Base Case : If index are out of bounds, print the subsequence and return
        if (index == arr.length) {
            System.out.println(subsequence); // Print the subsequence
            return;
        }

        // Pick the element
        subsequence.add(arr[index]);
        generateSubsequences(arr, index + 1, subsequence);

        // Don't pick the element (backtrack)
        subsequence.remove(subsequence.size() - 1);
        generateSubsequences(arr, index + 1, subsequence);
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        generateSubsequences(arr, 0, new ArrayList<>());
    }
}

// Output
/*
[1, 2, 3]
[1, 2]
[1, 3]
[1]
[2, 3]
[2]
[3]
[]
 */

// Intuition : Optimal Solution
/*
Intuition
A subsequence is a sequence that can be derived from an array by deleting some or no elements
without changing the order of the remaining elements.

To generate subsequences using recursion :
1. Pick the current element and include it in the subsequence.
2. Don’t pick the current element and move to the next index.
3. Repeat this for all elements until you reach the base case (i.e., index out of bounds).
4. This approach ensures that all possible subsequences are generated.

Algorithm :
1. Create a recursive function generateSubsequences(arr, index, currentSubsequence).
2. Base Case: If index == arr.length, print the current subsequence and return.
3. Pick the element at index and recurse with index + 1.
4. Don't pick the element at index and recurse with index + 1.
 */

// Recursion Tree
/*

                         []
                      /     \
                  [1]        []
                /     \     /     \
           [1,2]     [1]   [2]     []
          /     \   /   \  /   \   /   \
     [1,2,3]  [1,2] [1,3] [1] [2,3] [2] [3] []

 */

// Striver's (Video Explanation): https://youtu.be/AxNNVECce8c?si=tDj0zsVdlhnRBrDP