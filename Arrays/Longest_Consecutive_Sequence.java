package Arrays;

import java.util.Arrays;
import java.util.HashSet;

public class Longest_Consecutive_Sequence {

    // Method 1 : Brute Force
    // Time - O(N^3), Space - O(1)
    // static int longestSequence(int[] arr, int n) {
    
    //     // Edge case: empty array
    //     if (n == 0) return 0;
    
    //     int longest = 0;
    
    //     // Loop through every element as a possible sequence start
    //     for (int i = 0; i < n; i++) {
    
    //         int curr = arr[i];   // Current number being checked
    //         int count = 1;       // Minimum sequence length is 1
    
    //         // Keep checking if the next consecutive number exists
    //         // Edge cases handled here:
    //         // - Works for negative numbers
    //         // - Works for unordered arrays
    //         // - Stops when a gap is found
    //         while (contains(arr, curr + 1)) {
    //             curr++;
    //             count++;
    //         }
    
    //         // Update the longest sequence found so far
    //         longest = Math.max(longest, count);
    //     }
    
    //     return longest;
    // }
    
    // Helper function to check if a value exists in the array
    // static boolean contains(int[] arr, int target) {
    
    //     // Linear search
    //     // Edge case:
    //     // - If array is empty, loop never runs and returns false
    //     for (int i = 0; i < arr.length; i++) {
    
    //         if (arr[i] == target) {
    //             return true;    // Target found
    //         }
    //     }
    
    //     return false;           // Target not found
    // }

    // Method 2 : Better Solution (Using Sorting)
    // Time : O(N * log(N)). We are first sorting the array which will take O(N * log(N)) time and then we are running a for loop
    // which will take O(N) time. Hence, the overall time complexity will be O(N * log(N)).
    // Space : O(1). No extra space is required.
    // static int longestSequence2(int[] arr, int n) {
    
    //     // Edge case: empty array
    //     if (n == 0) return 0;
    
    //     // Sort the array first
    //     // Example:
    //     // [100, 4, 200, 1, 3, 2] -> [1, 2, 3, 4, 100, 200]
    //     Arrays.sort(arr);
    
    //     int longest = 1;                 // Stores the longest sequence found
    //     int count = 1;                   // Length of current sequence
    //     int lastSmallest = arr[0];        // Tracks previous valid number
    
    //     for (int i = 1; i < n; i++) {
    
    //         // Case 1: Current number is consecutive
    //         // Example: [1, 2, 3]
    //         if (arr[i] == lastSmallest + 1) {
    //             count++;
    //             lastSmallest = arr[i];
    //         }
    
    //         // Case 2: Duplicate number
    //         // Example: [1, 1, 2, 2, 3]
    //         // We skip duplicates so they don't reset the sequence
    //         else if (arr[i] == lastSmallest) {
    //             continue;
    //         }
    
    //         // Case 3: Sequence breaks
    //         // Example: [1, 2, 4]
    //         else {
    //             count = 1;               // Reset count
    //             lastSmallest = arr[i];   // Start new sequence
    //         }
    
    //         // Update the longest sequence length
    //         longest = Math.max(longest, count);
    //     }
    
    //     return longest;
    // }

    // Method 3 : Optimal Solution (Using HashSet)
    // Time - O(N) The time complexity of the above approach is O(N) because we traverse each consecutive subsequence
    // only once. (assuming HashSet takes O(1) to search)
    // Space - O(N) The space complexity of the above approach is O(N) because we use a HashSet to store elements.
    static int longestConsecutive(int[] nums) {
    
        // Edge case: if the array is empty, the loop below never runs
        // and 'longest' will correctly remain 0.
        HashSet<Integer> set = new HashSet<>();
    
        // Add all numbers to the set
        // Edge case handled here:
        // - Duplicates are automatically removed (e.g., [1,2,2,3])
        for (int num : nums) {
            set.add(num);
        }
    
        int longest = 0;
    
        // Iterate through unique numbers only
        for (int num : set) {
    
            // Check if this number is the START of a sequence
            // Edge case:
            // - If (num - 1) exists, then 'num' is in the middle of a sequence
            // - We skip it to avoid recounting the same sequence
            if (!set.contains(num - 1)) {
    
                int currentNum = num;
    
                // At minimum, a sequence has length 1
                // Edge case:
                // - Handles isolated numbers like [100]
                int count = 1;
    
                // Expand the sequence forward
                // Edge cases handled here:
                // - Stops when there is a gap (e.g., 1,2,4)
                // - Works with negative numbers (e.g., -3,-2,-1)
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    count++;
                }
    
                // Update the longest sequence found so far
                // Edge case:
                // - Handles multiple sequences (e.g., [1,2,3,10,11])
                longest = Math.max(longest, count);
            }
        }

    // Edge case:
    // - Returns 0 for empty input
    // - Returns 1 for single-element input
    return longest;
}

    // Main Function
    public static void main(String[] args) {
        int[] arr = {100, 200, 1, 3, 2, 4};
        int n = arr.length;
        int ans = longestSequence3(arr, n);
        System.out.println(ans);
    }
}

// Output
// 4

// Algorithm: Brute Force (Using 3 loops)
/*
1. We will traverse the array and for each element, we will check if the next element is present in the array or not.
2. If the next element is present in the array, we will increment the count and move to the next element.
3. If the next element is not present in the array, we will update the longest variable if the current count is
greater than the longest variable.
4. We will return the longest variable.
 */

// Algorithm: Better Solution (Using Sorting)
/*
1. We will sort the array.
2. We will traverse the array and for each element, we will check if the current element is equal to the previous
element or not.
3. If the current element is equal to the previous element, we will increment the count.
4. If the current element is not equal to the previous element, we will update the longest variable if the current
count is greater than the longest variable.
5. We will return the longest variable.
 */

// Algorithm: Optimal Solution (Using HashSet)
/*
1. We will create a HashSet and store all the elements of the array in the HashSet.
2. We will traverse the array and for each element, we will check if the previous element is present in the HashSet or not.
3. If the previous element is present in the HashSet, we will not do anything.
4. If the previous element is not present in the HashSet, we will check if the next element is present in the HashSet or not.
5. If the next element is present in the HashSet, we will increment the count and move to the next element.
6. If the next element is not present in the HashSet, we will update the longest variable if the current count is greater
than the longest variable.
7. We will return the longest variable.
 */

// Striver's (Video Explanation) : https://youtu.be/oO5uLE7EUlM
