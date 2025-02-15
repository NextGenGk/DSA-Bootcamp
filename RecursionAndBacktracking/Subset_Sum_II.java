package RecursionAndBacktracking;

import java.util.*;

public class Subset_Sum_II {

    // Method 1 : Brute Force
    // Time Complexity: O( 2^n *(k log (x) )). 2^n  for generating every subset and k* log( x)
    // to insert every combination of average length k in a set of size x. After this, we have to
    // convert the set of combinations back into a list of list /vector of vectors which takes more time.
    // Space Complexity:  O(2^n * k) to store every subset of average length k. Since we are initially
    // using a set to store the answer another O(2^n *k) is also used.
    // Method to generate all unique subsequences of a given array using brute force
    public static ArrayList<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> uniqueSubsets = new HashSet<>();
        List<Integer> subset = new ArrayList<>();
        generateSubsequences(nums, 0, subset, uniqueSubsets);
        return new ArrayList<>(uniqueSubsets);
    }

    // Recursive method to generate subsets and store unique subsets in a HashSet
    public static void generateSubsequences(int[] arr, int index,
                                            List<Integer> subsequence, Set<List<Integer>> res) {
        // Base Case: If index is out of bounds, store the sorted subsequence and return
        if (index == arr.length) {
            List<Integer> sortedSubset = new ArrayList<>(subsequence);
            Collections.sort(sortedSubset);
            res.add(sortedSubset);
            return;
        }

        // Pick the element
        subsequence.add(arr[index]);
        generateSubsequences(arr, index + 1, subsequence, res);

        // Don't pick the element (backtrack)
        subsequence.remove(subsequence.size() - 1);
        generateSubsequences(arr, index + 1, subsequence, res);
    }

    // Method 2 : Optimal Solution
    // Time Complexity: O(2^n * k), O(2^n) for generating every subset and O(k)  to insert every subset
    // in another data structure if the average length of every subset is k.
    // Space Complexity: O(2^n * k) to store every subset of average length k.
    // Auxiliary space is O(n)  if n is the depth of the recursion tree.
    // Method to generate all unique subsets of a given array
    public static ArrayList<ArrayList<Integer>> subsetsWithDups(int[] nums) {
        Arrays.sort(nums); // Sorting to handle duplicates
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        findCombinations(nums, 0, 0, ans, new ArrayList<>()); // Start recursion with index 0 and sum 0
        return ans;
    }

    // Recursive method to generate unique subsets
    private static void findCombinations(int[] arr, int index, int sum,
                                         ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> current) {
        // Add current subset to the answer list
        ans.add(new ArrayList<>(current));

        // Iterate through the array from the current index
        for (int i = index; i < arr.length; i++) {
            // Skip duplicates to maintain uniqueness
            if (i > index && arr[i] == arr[i - 1]) continue;

            // Include the current element
            current.add(arr[i]);
            // Move to the next index with updated sum
            findCombinations(arr, i + 1, sum + arr[i], ans, current);

            // Backtrack: remove the last added element before returning
            current.remove(current.size() - 1);
        }
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        ArrayList<ArrayList<Integer>> subsets = subsetsWithDups(nums);
        System.out.println("Subsets with duplicates: " + subsets);
    }
}

// Output :
//

// Intuition : Brute Force
/*
Intuition:
This problem is solved using recursion by exploring all possible subsets (subsequences)
of the given array. Since duplicates exist, we store subsets in a HashSet to ensure uniqueness.
The approach follows the power set concept, where each element has two choices:
either be included in the subset or not.

Algorithm:
1. Use a recursive function `generateSubsequences` to explore all subsets.
2. At each step, the function either includes or excludes the current element.
3. If we reach the end of the array (base case), we sort the subset and add it to a HashSet.
4. Sorting ensures that duplicate subsets are stored in the same format, avoiding redundant entries.
5. Finally, convert the HashSet into a List and return all unique subsets.
 */

// Intuition : Optimal Solution (Using Recursion and Backtracking)
/*
Approach:
In the previous method, we were taking extra time to store the unique combination with
the help of a set. To make the solution efficient we will have to decide on a method that
will consider only the unique combinations without the help of additional data structure.

Lets  understand  with an example where arr = [1,2,2 ].

Initially start with an empty data structure. In the first recursion, call make a subset of size
one, in the next recursion call a subset of size 2, and so on. But first, in order to make a subset
of size one what options do we have?

We can pick up elements from either the first index or the second index or the third index.
However, if we have already picked up two from the second index, picking up two from the third
index will make another duplicate subset of size one. Since we are trying to avoid duplicate
subsets we can avoid picking up from the third index. This should give us an intuition that
whenever there are duplicate elements in the array we pick up only the first occurrence.

The next recursion calls will continue from the point the previous one ended.

Let's summarize:-
1. Sort the input array.Make a recursive function that takes the input array ,the current
subset,the current index and  a list of list/ vector of vectors to contain the answer.
2. Try to make a subset of size n during the nth recursion call and consider elements from every
index while generating the combinations. Only pick up elements that are appearing for the first
time during a recursion call to avoid duplicates.
3. Once an element is picked up, move to the next index.The recursion will terminate when the
end of array is reached.While returning backtrack by removing the last element that was inserted.

Intuition:
This problem is solved using recursion by exploring all possible subsets (subsequences)
of the given array. Since duplicates exist, we store subsets in a HashSet to ensure uniqueness.
Sorting the array helps in skipping duplicate elements during recursion.
The approach follows the power set concept, where each element has two choices:
either be included in the subset or not.

Algorithm:
1. Sort the input array to handle duplicates effectively.
2. Use a recursive function `findCombinations` to explore all subsets.
3. At each step:
   a. Add the current subset to the result list.
   b. Iterate through the array from the current index.
   c. If a duplicate element is encountered (same as previous), skip it to avoid redundant subsets.
   d. Include the current element and recurse with the next index.
   e. Backtrack by removing the last added element.
4. Finally, return the list of unique subsets.
 */

// Striver's (Video Explanation) : https://youtu.be/RIn3gOkbhQE?si=amFX9UTvneIQbHImccc