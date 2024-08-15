package StacksAndQueues;

public class Trapping_Rainwater_Problem {

    // Method 1 : Better Force
    // Time Complexity: O(3*N) as we are traversing through the array only once. And O(2*N) for computing
    // prefix and suffix array.
    // Space Complexity: O(N)+O(N) for prefix and suffix arrays.
    static int trapWater(int[] height) {
        // Get the size of the array
        int n = height.length;

        // Arrays to store the maximum height to the left and right of each index
        int[] left = new int[n];
        int[] right = new int[n];

        // Initialize the first element of the left array with the first height
        left[0] = height[0];

        // Fill the left array with the maximum heights from the left up to the current index
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        // Initialize the last element of the right array with the last height
        right[n - 1] = height[n - 1];

        // Fill the right array with the maximum heights from the right up to the current index
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        // Initialize the waterTrapped variable to store the total amount of trapped water
        int waterTrapped = 0;

        // Calculate the trapped water for each index
        for (int i = 0; i < n; i++) {
            // Only calculate water if the current height is lower than both left and right heights
            if (height[i] < left[i] && height[i] < right[i]) {
                // The amount of water trapped at this index is the difference between
                // the minimum of the left and right maximums and the current height
                waterTrapped += Math.min(left[i], right[i]) - height[i];
            }
        }

        // Return the total amount of trapped water
        return waterTrapped;
    }


    // Method 2 : Optimal Solution
    // Time Complexity: O(N) because we are using 2 pointer approach.
    // Space Complexity: O(1) because we are not using anything extra.
    static int trapWater1(int[] height) {
        // Get the size of the array
        int n = height.length;

        // Initialize two pointers: left at the start and right at the end of the array
        int left = 0, right = n - 1;

        // Initialize a variable to store the total amount of trapped water
        int res = 0;

        // Variables to store the maximum heights on the left and right sides
        int maxLeft = 0, maxRight = 0;

        // Iterate while the left pointer is less than or equal to the right pointer
        while (left <= right) {
            // If the height at the left pointer is less than or equal to the height at the right pointer
            if (height[left] <= height[right]) {
                // If the current height at left is greater than or equal to maxLeft, update maxLeft
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                }
                // Otherwise, water can be trapped at this index, add the difference to res
                else {
                    res += maxLeft - height[left];
                }
                // Move the left pointer to the right
                left++;
            }
            // If the height at the right pointer is smaller than the left pointer
            else {
                // If the current height at right is greater than or equal to maxRight, update maxRight
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                }
                // Otherwise, water can be trapped at this index, add the difference to res
                else {
                    res += maxRight - height[right];
                }
                // Move the right pointer to the left
                right--;
            }
        }

        // Return the total amount of trapped water
        return res;
    }


    // Main Function
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("The duplicate element is " + trapWater1(arr));
    }
}

// Output : The duplicate element is 6

// Approach : Better Solution
/*
Intuition: We are taking O(N) for computing leftMax and rightMax at each index. The complexity can be boiled down to
O(1) if we precompute the leftMax and rightMax at each index.

Approach: Take 2 array prefix and suffix array and precompute the leftMax and rightMax for each index beforehand.
Then use the formula min(prefix[I], suffix[i])-arr[i] to compute water trapped at each index.
 */

// Approach : Optimal Solution
/*
Approach: Take 2 pointers l(left pointer) and r(right pointer) pointing to 0th and (n-1)th index respectively.
Take two variables leftMax and rightMax and initialize them to 0. If height[l] is less than or equal
to height[r] then if leftMax is less than height[l] update leftMax to height[l] else add
leftMax-height[l] to your final answer and move the l pointer to the right i.e l++. If height[r]
is less than height[l], then now we are dealing with the right block. If height[r] is greater than
rightMax, then update rightMax to height[r] else add rightMax-height[r] to the final answer.
Now move r to the left. Repeat these steps till l and r crosses each other.

Intuition: We need a minimum of leftMax and rightMax.So if we take the case when height[l]<=height[r]
we increase l++, so we can surely say that there is a block with a height more than height[l] to the
right of l. And for the same reason when height[r]<=height[l] we can surely say that there is a block
to the left of r which is at least of height[r]. So by traversing these cases and using two pointers
approach the time complexity can be decreased without using extra space.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=1_5VuquLbXg