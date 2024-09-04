package SlidingWindowTwoPointers;

public class Max_Consecutive_Ones_III {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(1)
    public static int longestOnes(int[] nums, int k) {
        int n = nums.length; // Length of the input array
        int maxLength = 0; // Variable to store the maximum length of subarray found

        // Outer loop iterates through each element in the array, setting the starting point of the subarray
        for (int i = 0; i < n; i++) {
            int zeroes = 0; // Variable to count the number of zeros in the current subarray

            // Inner loop expands the subarray from the starting point i to the end of the array
            for (int j = i; j < n; j++) {
                // If the current element is 0, increment the zero count
                if (nums[j] == 0) {
                    zeroes++;
                }

                // If the number of zeros in the subarray is within the allowed limit k, update maxLength
                if (zeroes <= k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
                // If the number of zeros exceeds k, break out of the inner loop to start a new subarray
                else {
                    break;
                }
            }
        }

        // Return the maximum length of the subarray with at most k zeros
        return maxLength;
    }

    // Method 2 : Better Solution
    // Time Complexity : O(2N)
    // Space Complexity : O(1)
    public static int longestOnes1(int[] nums, int k) {
        int n = nums.length; // Length of the input array

        int left = 0; // Left pointer for the sliding window
        int right = 0; // Right pointer for the sliding window
        int maxLength = 0; // Variable to store the maximum length of subarray found
        int zeroes = 0; // Variable to count the number of zeros in the current window

        // Iterate through the array with the right pointer
        while (right < n) {
            // If the current element is 0, increment the zero count
            if (nums[right] == 0) {
                zeroes++;
            }

            // If the number of zeros exceeds k, move the left pointer to shrink the window
            while (zeroes > k) {
                // If the leftmost element in the window is 0, decrement the zero count
                if (nums[left] == 0) {
                    zeroes--;
                }
                // Move the left pointer to the right to reduce the window size
                left++;
            }

            // Update the maximum length of the subarray with at most k zeros
            if (zeroes <= k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            // Move the right pointer to the right to expand the window
            right++;
        }

        // Return the maximum length of the subarray with at most k zeros
        return maxLength;
    }


    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    public static int longestOnes2(int[] nums, int k) {
        int n = nums.length; // Length of the input array

        int left = 0; // Left pointer of the sliding window
        int right = 0; // Right pointer of the sliding window
        int maxLength = 0; // Variable to store the maximum length of subarray
        int zeroes = 0; // Variable to count the number of zeros in the current window

        // Iterate through the array with the right pointer
        while (right < n) {
            // If the current element is 0, increment the zero count
            if (nums[right] == 0) {
                zeroes++;
            }

            // If the number of zeros exceeds k, move the left pointer to shrink the window
            if (zeroes > k) {
                // If the leftmost element in the window is 0, decrement the zero count
                if (nums[left] == 0) {
                    zeroes--;
                }
                // Move the left pointer to the right
                left++;
            }

            // Update the maximum length of the subarray
            if (zeroes <= k) {
               maxLength = Math.max(maxLength, right - left + 1);
            }

            // Move the right pointer to the right
            right++;
        }

        // Return the maximum length of the subarray with at most k zeros
        return maxLength;
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        int result = longestOnes2(nums, k);
        System.out.println(result);
    }
}

// Output : 10

// Approach : Brute Force
/*
The code uses a brute-force approach to find the longest subarray with at most `k` zeroes. It iterates through
each possible starting point in the array, then expands a subarray from that point by checking every
subsequent element. As it expands the subarray, it counts the number of zeroes. If the count exceeds
`k`, it stops further expansion and moves to the next starting point. The maximum length of all valid
subarrays is tracked and returned. While this method guarantees finding the correct result, it's less
efficient due to the need to examine all possible subarrays, leading to a time complexity of O(N²).
 */

// Approach : Better Solution
/*
The code employs a sliding window technique to find the longest subarray with at most k zeroes.
As the right pointer expands the window by moving through the array, the code counts zeroes within
the window. If the count exceeds k, the left pointer shifts right to shrink the window until the
 number of zeroes is within the allowed limit. Throughout this process, the code continuously
 updates the maximum length of such a valid subarray, ensuring an optimal solution in linear time.
 */

// Approach : Optimal Solution
/*
The code uses a sliding window approach to find the longest subarray containing at most k zeroes.
By expanding the window with the right pointer and tracking the number of zeroes, it ensures the
window remains valid. If the count of zeroes exceeds k, the left pointer moves right to shrink
the window until the zero count is within the allowed limit. Throughout this process, the code
keeps track of the maximum length of such a subarray, ensuring an efficient solution in linear time.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=3E4JBHSLpYk