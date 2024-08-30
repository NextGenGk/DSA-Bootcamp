package Sliding_Window_and_Two_Pointers;

public class Binary_Subarray_with_Sum {

    // Method 1 : Optimal Solution
    // Time Complexity : O(2 * 2N), because we call helper functions two times.
    // Space Complexity : O(1), no extra space is needed.
    public static int numSubarraysWithSum(int[] nums, int goal) {
        // Calculate the number of subarrays that sum up to 'goal'
        // by using the difference between two helper function calls.
        return helper(nums, goal) - helper(nums, goal - 1);
    }

    // Helper function to count the subarray <= goal
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

    // Main Function
    public static void main(String[] args) {
        // Example test cases
        int[] nums1 = {1, 0, 1, 0, 1};
        int goal1 = 2;

        int[] nums2 = {0, 0, 0, 0, 0};
        int goal2 = 0;

        // Test case 1
        int result1 = numSubarraysWithSum(nums1, goal1);
        System.out.println("Number of subarrays with sum " + goal1 + " in array "
                + java.util.Arrays.toString(nums1) + " is: " + result1);

        // Test case 2
        int result2 = numSubarraysWithSum(nums2, goal2);
        System.out.println("Number of subarrays with sum " + goal2 + " in array "
                + java.util.Arrays.toString(nums2) + " is: " + result2);
    }
}

// Output :
/*
Number of subarrays with sum 2 in array [1, 0, 1, 0, 1] is: 4
Number of subarrays with sum 0 in array [0, 0, 0, 0, 0] is: 15
 */

// Approach : Optimal Solution
/*
The code efficiently counts the number of subarrays with a specific sum using a sliding window technique.
By calculating the number of subarrays with sums less than or equal to the target (`goal`) and subtracting
the number of subarrays with sums less than or equal to `goal - 1`, it isolates the count of subarrays that
sum exactly to the target. The sliding window dynamically adjusts as it iterates through the array,
expanding to include new elements and contracting when the sum exceeds the target, allowing for an
O(N) solution to what would otherwise be a more complex problem.

This idea is based based on the idea that by subtracting atMost(nums, goal - 1) from
atMost(nums, goal), we are effectively removing subarrays whose sum is strictly less than goal.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=XnMdNUkX6VM