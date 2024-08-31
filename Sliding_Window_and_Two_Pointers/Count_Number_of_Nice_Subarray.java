package Sliding_Window_and_Two_Pointers;

public class Count_Number_of_Nice_Subarray {

    // This function returns the number of subarrays with exactly 'k' odd numbers.
    public static int numberOfSubarrays(int[] nums, int k) {
        // To find the number of subarrays with exactly 'k' odd numbers,
        // we subtract the number of subarrays with at most 'k-1' odd numbers
        // from the number of subarrays with at most 'k' odd numbers.
        return helper(nums, k) - helper(nums, k - 1);
    }

    // This helper function returns the number of subarrays with at most 'k' odd numbers.
    public static int helper(int[] nums, int k) {
        // Base case: If the goal is negative, there can be no valid subarray.
        if (k < 0) return 0;

        int n = nums.length; // Length of the array

        int left = 0;        // Left pointer for the sliding window
        int right = 0;       // Right pointer for the sliding window
        int cnt = 0;         // Count of valid subarrays
        int sum = 0;         // Sum representing the count of odd numbers in the current window

        // Traverse the array using the right pointer
        while (right < n) {
            // Increase the sum if the current number is odd
            sum += nums[right] % 2;

            // If the sum exceeds 'k', move the left pointer to reduce the sum
            while (sum > k) {
                sum -= nums[left] % 2;
                left++;
            }

            // If the sum is within the limit, add all possible subarrays ending at 'right' to the count
            if (sum <= k) {
                cnt += (right - left + 1);
            }
            right++;
        }
        return cnt;
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1}; // Example input array
        int k = 3; // Number of odd numbers in the subarray

        // Call the function and print the result
        int result = numberOfSubarrays(nums, k);
        System.out.println("Number of subarrays with exactly " + k + " odd numbers: " + result);
    }
}

// Output : Number of subarrays with exactly 3 odd numbers: 2

// Approach : Optimal Solution
/*
We convert this problem into a "Binary Subarray with Sum" problem. In binary subarray problem contains element
only 0 & 1. So, In this problem we use the logic where we assume 0 as even and 1 as Odd to solve this problem.

nums = [1, 5, 2, 1, 1]
After Converting = [1, 1, 0, 1, 1]
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=j_QOv9OT9Og