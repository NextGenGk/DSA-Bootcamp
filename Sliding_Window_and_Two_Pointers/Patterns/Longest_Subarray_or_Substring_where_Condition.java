package Sliding_Window_and_Two_Pointers.Patterns;

public class Longest_Subarray_or_Substring_where_Condition {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(1)
    public static int longestSubarraySumLessThanK(int[] nums, int k) {
        int n = nums.length; // Length of the input array
        int maxLength = 0; // Variable to store the maximum length of subarray

        // Outer loop iterates through each element as the starting point of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0; // Variable to store the sum of the current subarray

            // Inner loop expands the subarray from index i to the end of the array
            for (int j = i; j < n; j++) {
                // Add the current element to the sum
                currentSum += nums[j];

                // If the sum is less than k, update maxLength
                if (currentSum < k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                } else {
                    // If the sum exceeds or equals k, break out of the loop
                    break;
                }
            }
        }

        // Return the maximum length of the subarray with sum less than k
        return maxLength;
    }

    // Method 2 : Better Solution
    // Time Complexity : O(2N)
    // Space Complexity : O(1)
    public static int longestSubarraySumLessThanK1(int[] nums, int k) {
        // Get the length of the input array
        int n = nums.length;

        // Initialize the left pointer of the sliding window
        int left = 0;

        // Initialize the right pointer of the sliding window
        int right = 0;

        // Variable to store the sum of the current window
        int currentSum = 0;

        // Variable to store the maximum length of the subarray with sum less than k
        int maxLength = 0;

        // Iterate through the array with the right pointer
        while (right < n) {
            // Add the current element to the sum of the current window
            currentSum += nums[right];

            // Shrink the window from the left while the current sum is greater than k
            while (currentSum > k) {
                // Subtract the element at the left pointer from the current sum
                currentSum -= nums[left];

                // Move the left pointer to the right to shrink the window
                left++;
            }

            // Update the maximum length if the current subarray sum is less than or equal to k
            if (currentSum <= k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            // Move the right pointer to expand the window
            right++;
        }

        // Return the maximum length of the subarray with sum less than or equal to k
        return maxLength;
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    public static int longestSubarraySumLessThanK2(int[] nums, int k) {
        // Get the length of the input array
        int n = nums.length;

        // Initialize the left pointer of the sliding window
        int left = 0;

        // Initialize the right pointer of the sliding window
        int right = 0;

        // Variable to store the sum of the current window
        int currentSum = 0;

        // Variable to store the maximum length of the subarray with sum less than k
        int maxLength = 0;

        // Iterate through the array with the right pointer
        while (right < n) {
            // Add the current element to the sum of the current window
            currentSum += nums[right];

            // Shrink the window from the left if the current sum is greater than k
            if (currentSum > k) {
                // Subtract the element at the left pointer from the current sum
                currentSum -= nums[left];

                // Move the left pointer to the right to reduce the window size
                left++;
            }

            // Update the maximum length if the current subarray sum is less than or equal to k
            if (currentSum <= k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            // Move the right pointer to expand the window
            right++;
        }

        // Return the maximum length of the subarray with sum less than or equal to k
        return maxLength;
    }

    // Main function to test the method
    public static void main(String[] args) {
        // Test case 1
        int[] nums = {1, 2, 3, 4, 5};
        int k = 8;
        System.out.println("Longest subarray length : " + longestSubarraySumLessThanK2(nums, k));
    }
}

// Output : Longest subarray length : 3

// Approach : Brute Force
/*
The code uses a brute-force approach to find the longest subarray whose sum is less than a given
value `k`. It systematically checks every possible subarray by starting at each index of the array
and expanding to subsequent indices. For each subarray, it calculates the sum and checks if it's less
than `k`. If the sum is valid, it updates the maximum length found. If the sum exceeds or equals `k`,
it stops further expansion from that starting index, as any larger subarray would only increase the sum.
This method guarantees the correct result by exhaustively exploring all possible subarrays, though
it does so at the cost of efficiency, making it feasible primarily for smaller arrays.
 */

// Approach : Better Solution
/*
The code uses the sliding window technique to efficiently find the longest subarray with a sum less than
or equal to (k). It does this by maintaining a window between two pointers, `left` and `right`,
which expands by moving `right` to include more elements. If the sum within the window exceeds (k),
the `left` pointer is incremented to shrink the window until the sum is within the desired limit.
The maximum length of any valid subarray found during this process is tracked and returned. This
approach ensures an optimal solution with a time complexity of O(2N).
 */

// Approach : Optimal Solution
/*
The code utilizes the sliding window technique to find the length of the longest subarray with a sum
less than or equal to (k). By maintaining two pointers, `left` and `right`, it expands the window
with the `right` pointer and includes new elements in the sum. If the sum exceeds (k), it shrinks
the window from the left by incrementing the `left` pointer until the sum is within the limit. During
each valid window (where the sum is less than or equal to \( k \)), it calculates and updates the
maximum length of such subarrays. This approach ensures an efficient solution with a time complexity
of O(N).
 */

// Striver's (Video Explanation) : https://youtu.be/9kdHxplyl5I?si=6jMhjwSmw8puyIJq