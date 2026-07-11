package SlidingWindowTwoPointers;

import java.util.Arrays;

public class Binary_Subarray_with_Sum {

    // ============================================================
    // Method 1 : Brute Force Solution
    // Time Complexity  : O(N^3) -> N^2 pairs (i, j), and O(N) to
    //                    recompute the sum of nums[i..j] every time.
    // Space Complexity : O(1), no extra space is needed.
    // ============================================================
    /*
    Idea:
    Generate every possible subarray using two pointers 'i' (start) and 'j' (end).
    For each subarray, compute its sum from scratch by looping from i to j.
    If the sum equals 'goal', increment the count.

    This is the most naive way to solve the problem - we don't use any
    previously computed information, we just recompute everything.
    */
    public static int numSubarraysWithSum_Brute(int[] nums, int goal) {
        int n = nums.length;
        int count = 0;

        // Pick every possible starting index 'i'
        for (int i = 0; i < n; i++) {
            // Pick every possible ending index 'j' (j >= i)
            for (int j = i; j < n; j++) {
                int sum = 0;

                // Recompute the sum of the subarray nums[i..j] from scratch
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }

                // If this subarray's sum matches the goal, count it
                if (sum == goal) {
                    count++;
                }
            }
        }

        return count;
    }


    // ============================================================
    // Method 2 : Better Solution (Running Sum)
    // Time Complexity  : O(N^2) -> N^2 pairs (i, j), but the sum is
    //                    maintained incrementally instead of recomputed.
    // Space Complexity : O(1), no extra space is needed.
    // ============================================================
    /*
    Idea:
    Same as brute force - we still check every subarray using two loops.
    BUT instead of recalculating the sum of nums[i..j] every single time
    (which wastes work), we keep a running 'sum' variable that we simply
    update by adding nums[j] as we expand the window to the right.

    This removes the innermost O(N) loop from the brute force approach,
    bringing the time complexity down from O(N^3) to O(N^2).
    */
    public static int numSubarraysWithSum_Better(int[] nums, int goal) {
        int n = nums.length;
        int count = 0;

        // Pick every possible starting index 'i'
        for (int i = 0; i < n; i++) {
            int sum = 0; // running sum for subarrays starting at 'i'

            // Expand the window to the right, index 'j' is the end point
            for (int j = i; j < n; j++) {
                sum += nums[j]; // incrementally add the new element

                // Check if this running sum matches the goal
                if (sum == goal) {
                    count++;
                }

                // Small optimization: since nums contains only 0s and 1s,
                // if sum already exceeds goal, it will never come back down
                // (adding 0/1 only keeps it the same or increases it),
                // so we can break early.
                if (sum > goal) {
                    break;
                }
            }
        }

        return count;
    }


    // ============================================================
    // Method 3 : Optimal Solution (Sliding Window / Two Pointers)
    // Time Complexity  : O(2 * 2N) ~ O(N), because we call the helper
    //                    function two times, each doing an O(N) sweep.
    // Space Complexity : O(1), no extra space is needed.
    // ============================================================
    /*
    Note : Because sliding window cannot directly count subarrays with EXACT sum when the array contains 0s.

    Instead, we convert the problem:
    1. count subarrays with sum <= goal
    2. count subarrays with sum <= goal-1

    Then subtract them.
    So:
    exact(goal) = atMost(goal) − atMost(goal-1)
    */
    public static int numSubarraysWithSum_Optimal(int[] nums, int goal) {
        // Calculate the number of subarrays that sum up to 'goal'
        // by using the difference between two helper function calls.
        return helper(nums, goal) - helper(nums, goal - 1);
    }

    // Helper function to count the (subarray <= goal)
    private static int helper(int[] nums, int goal) {
        // Base case: If the goal is negative, there can be no valid subarray.
        if (goal < 0) return 0;

        int n = nums.length;  // Length of the input array
        int left = 0;         // Left pointer for the sliding window
        int right = 0;        // Right pointer for the sliding window
        int sum = 0;          // Current sum of the elements in the window
        int cnt = 0;          // Counter to keep track of valid subarrays

        // Iterate through the array with the right pointer
        while (right < n) {
            sum += nums[right];  // Add the current element to the sum

            // If the current sum exceeds the goal, shrink the window from the left
            while (sum > goal) {
                sum -= nums[left];  // Subtract the leftmost element from the sum
                left++;             // Move the left pointer to the right
            }

            // Count the number of valid subarrays ending at 'right'
            // (all subarrays starting between 'left' and 'right')
            cnt += (right - left) + 1;
            right++;  // Move the right pointer to the next element
        }

        return cnt;  // Return the count of valid subarrays
    }


    // ============================================================
    // Main Function - Testing all three approaches
    // ============================================================
    public static void main(String[] args) {
        // Example test cases
        int[] nums1 = {1, 0, 1, 0, 1};
        int goal1 = 2;

        int[] nums2 = {0, 0, 0, 0, 0};
        int goal2 = 0;

        // ---- Test case 1 ----
        System.out.println("Test Case 1 : nums = " + Arrays.toString(nums1) + ", goal = " + goal1);
        System.out.println("Brute   -> " + numSubarraysWithSum_Brute(nums1, goal1));
        System.out.println("Better  -> " + numSubarraysWithSum_Better(nums1, goal1));
        System.out.println("Optimal -> " + numSubarraysWithSum_Optimal(nums1, goal1));

        System.out.println();

        // ---- Test case 2 ----
        System.out.println("Test Case 2 : nums = " + Arrays.toString(nums2) + ", goal = " + goal2);
        System.out.println("Brute   -> " + numSubarraysWithSum_Brute(nums2, goal2));
        System.out.println("Better  -> " + numSubarraysWithSum_Better(nums2, goal2));
        System.out.println("Optimal -> " + numSubarraysWithSum_Optimal(nums2, goal2));
    }
}

// Output :
/*
Test Case 1 : nums = [1, 0, 1, 0, 1], goal = 2
Brute   -> 4
Better  -> 4
Optimal -> 4

Test Case 2 : nums = [0, 0, 0, 0, 0], goal = 0
Brute   -> 15
Better  -> 15
Optimal -> 15
*/

// ============================================================
// Approach Summary
// ============================================================
/*
1. BRUTE FORCE (O(N^3)):
   - Check every subarray (i, j) and recompute its sum from scratch every time.
   - Simplest to think about, but wasteful because we redo work we've
     already done for overlapping subarrays.

2. BETTER (O(N^2)):
   - Same nested loop structure as brute force, but instead of
     recomputing the sum from i to j every time, we maintain a running
     sum and just add nums[j] as the window expands.
   - This removes the third inner loop, cutting time complexity to O(N^2).
   - Since the array is binary (only 0s and 1s), we can also break early
     once the running sum exceeds the goal (sum is non-decreasing).

3. OPTIMAL (O(N)):
   - Direct sliding window cannot count EXACT sum subarrays cleanly when
     zeros are present (window can't shrink/grow in one unambiguous way
     for an exact match).
   - Trick: exact(goal) = atMost(goal) - atMost(goal - 1)
   - atMost(x) counts subarrays with sum <= x using a classic sliding
     window (two pointers), which works correctly because all elements
     are non-negative (0 or 1), so the window sum only increases as we
     expand right and only decreases as we shrink left.
   - This gives us the O(N) approach, calling the helper twice: once for
     goal, once for goal - 1, then subtracting.

Why brute/better work but are slower:
   - They exhaustively check every subarray without exploiting the
     special "0/1 only" structure of the array, so they don't benefit
     from the monotonic sliding window trick that the optimal solution uses.

Striver's (Video Explanation) : https://www.youtube.com/watch?v=XnMdNUkX6VM
*/
