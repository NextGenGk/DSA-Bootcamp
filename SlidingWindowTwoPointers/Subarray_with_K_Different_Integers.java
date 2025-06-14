package SlidingWindowTwoPointers;

import java.util.HashMap;

public class Subarray_with_K_Different_Integers {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(N)
    public static int subarraysWithKDistinct(int[] nums, int k) {
        // Get the length of the input array
        int n = nums.length;

        // Initialize a counter to store the number of valid subarrays
        int cnt = 0;

        // Iterate over each starting index of the subarray
        for (int i = 0; i < n; i++) {
            // Create a new HashMap to track the frequency of elements in the current subarray
            HashMap<Integer, Integer> map = new HashMap<>();

            // Iterate over each ending index of the subarray starting from index 'i'
            for (int j = i; j < n; j++) {
                // Add the current element to the map and update its frequency
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

                // Check if the current subarray has exactly 'k' distinct integers
                if (map.size() == k) {
                    // Increment the count as we've found a valid subarray
                    cnt++;
                }
                // If the number of distinct integers exceeds 'k', no further valid subarrays
                // can be formed starting from 'i', so break out of the loop
                else if (map.size() > k) {
                    break;
                }
            }
        }

        // Return the total count of subarrays with exactly 'k' distinct integers
        return cnt;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(2N) * 2
    // Space Complexity : O(N) * 2
    public static int subarraysWithKDistinct1(int[] nums, int k) {
        // The number of subarrays with exactly k distinct elements
        // is the difference between subarrays with at most k distinct elements
        // and subarrays with at most (k-1) distinct elements.
        return helper(nums, k) - helper(nums, k - 1);
    }

    // Helper function to count the subarray <= k
    private static int helper(int[] nums, int k) {
        if (k < 0) return 0; // If k is negative, no valid subarray can exist.

        int n = nums.length;
        int cnt = 0;         // This will hold the count of subarrays with at most 'k' distinct elements.
        int left = 0;        // Left pointer of the sliding window.
        int right = 0;       // Right pointer of the sliding window.
        HashMap<Integer, Integer> map = new HashMap<>(); // To track the frequency of elements in the current window.

        // Sliding window approach: expand the window by moving the 'right' pointer.
        while (right < n) {
            // Add the current element to the map and update its frequency.
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            // If the window has more than 'k' distinct elements, shrink the window by moving the 'left' pointer.
            while (map.size() > k) {
                // Decrease the frequency of the element at the 'left' pointer.
                map.put(nums[left], map.get(nums[left]) - 1);

                // If the frequency becomes zero, remove the element from the map.
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++; // Move the 'left' pointer to shrink the window.
            }

            // All subarrays ending at 'right' and starting from any index between 'left' and 'right'
            // (inclusive) will have at most 'k' distinct elements.
            cnt += right - left + 1;

            // Move the 'right' pointer to expand the window.
            right++;
        }

        // Return the count of subarrays with at most 'k' distinct elements.
        return cnt;
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;

        // Call the method and print the result
        int result = subarraysWithKDistinct(nums, k);
        System.out.println("Number of subarrays with exactly " + k + " distinct integers: " + result);
    }
}

// Output : Number of subarrays with exactly 2 distinct integers: 7

// Approach : Brute Force
/*
The code systematically explores every possible subarray in the given array by starting at each index
and extending to all subsequent elements, using a `HashMap` to track the frequency of distinct integers
in the current subarray. The intuition is to count subarrays that have exactly `k` distinct integers by
checking the size of the `HashMap`—if it matches `k`, the subarray is valid, and the count is incremented.
If the distinct count exceeds `k`, the loop breaks early since extending the subarray further would no
longer meet the criteria.
 */

// Approach : Optimal Solution
/*
The code efficiently counts subarrays with exactly `k` distinct integers by first counting subarrays with
at most `k` distinct integers using a sliding window, which dynamically adjusts to maintain the required
number of distinct elements. It then subtracts the count of subarrays with at most `k-1` distinct integers
from this total. This approach simplifies the problem by breaking it down into two easier subproblems,
allowing for an O(n) time complexity solution.

This idea is based on the idea that by subtracting atMost(nums, k - 1) from
atMost(nums, k), we are effectively removing subarrays whose sum is strictly less than goal.

Why this code works : High-Level Intuition
To count the number of subarrays with exactly k distinct elements, 

we use the formula:
Exactly k = At most k - At most (k - 1)

Why? Because:
1. "At most k distinct" includes subarrays with 1 to k distinct elements.
2. "At most (k-1)" includes subarrays with 1 to k-1 distinct elements.
3. So subtracting gives subarrays with exactly k distinct elements.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=7wYGbV_LsX4
