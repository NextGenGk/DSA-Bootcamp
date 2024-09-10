package GreedyAlgorithms;

public class Jump_Game_II {

    // Method 1 : Brute Force
    // Time Complexity : O(N^N)
    // Space Complexity : O(1)
    public static int jump(int[] nums) {
        return minJumps(nums, 0);
    }

    // Recursive function to find minimum jumps starting from index 'index'
    private static int minJumps(int[] nums, int index) {
        // If we have reached the last index
        if (index >= nums.length - 1) {
            return 0;
        }

        // If the current index has a 0 jump, we can't move forward
        if (nums[index] == 0) {
            return Integer.MAX_VALUE;
        }

        int minJumps = Integer.MAX_VALUE;
        // Try all jumps from 1 to nums[i]
        for (int jump = 1; jump <= nums[index]; jump++) {
            int jumpsFromNextPosition = minJumps(nums, index + jump);

            // If the result is not infinity, update the minimum jumps
            if (jumpsFromNextPosition != Integer.MAX_VALUE) {
                minJumps = Math.min(minJumps, 1 + jumpsFromNextPosition);
            }
        }

        return minJumps;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    public static int jump2(int[] nums) {
        int n = nums.length;  // Get the length of the array

        int l = 0;  // Left pointer (start of the current jump range)
        int r = 0;  // Right pointer (end of the current jump range)
        int jumps = 0;  // Count of jumps made

        // Loop until the right pointer reaches or exceeds the last index
        while (r < n - 1) {
            int farthest = 0;  // Track the farthest point we can reach in the current jump

            // Explore all indices within the current jump range (from l to r)
            for (int i = l; i <= r; i++) {
                // Calculate the farthest index we can reach from the current position
                farthest = Math.max(farthest, nums[i] + i);
            }

            // Move the left pointer to the position right after the current jump range
            l = r + 1;
            // Update the right pointer to the farthest point we can reach
            r = farthest;
            // Increment the number of jumps taken
            jumps++;
        }

        // Return the total number of jumps needed to reach the end
        return jumps;
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 4, 1, 1, 1, 2};
        System.out.println("Minimum jumps to reach the end: " + jump2(nums));
    }
}

// Output : 3

// Approach / Intuition : Brute Force
/*
The intuition behind this recursive solution is to explore every possible jump from each index to find
the minimum number of jumps required to reach the last index. Starting from the first index, the code
tries every jump from `1` to the value at that index (which represents the maximum allowable steps).
For each jump, it recursively calculates the minimum jumps needed to reach the end from the new position.
The goal is to find the minimum jumps by comparing all possible paths from the current index, with
the base case being when we reach or exceed the last index, where no more jumps are required.
 */

// Approach / Intuition : Optimal Solution
/*
The intuition behind the code is to minimize the number of jumps by always exploring the maximum possible
range of positions that can be reached within the current jump. Starting with an initial range, the code
checks every position in that range to calculate the farthest point reachable, then updates the range to
cover that farthest point. Each time the range is extended, the number of jumps is incremented. This
ensures that with each jump, we aim to get as far as possible, minimizing the total number of jumps
needed to reach the last index.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=7SBVnw7GSTk