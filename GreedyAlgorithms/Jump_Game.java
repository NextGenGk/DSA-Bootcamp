package GreedyAlgorithms;

public class Jump_Game {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) where N is the length of the input array. We iterate through the input
    // array exactly once and at each element perform constant time operations.
    // Space Complexity: O(1) as the algorithm uses only a constant amount of extra space regardless
    // of the size of the input array. It does not require any additional data structures that scale
    // with the input size.
    
    // Function to determine if you can reach the last index
    public static boolean canJump(int[] nums) {
        // Initialize the maximum index that can be reached
        int maxIndex = 0;

        // Iterate through each index of the array
        for (int i = 0; i < nums.length; i++) {
            // If the current index is greater than the maximum reachable index
            // it means we cannot move forward and should return false
            // arr = [1, 2, 3, 1, 1, 0, 2, 5]
            // where 'maxIndex' = 5, so we can't move further
            if (i > maxIndex) {
                return false;
            }

            // Update the maximum index that can be reached by comparing
            // the current maxIndex with the sum of the current index and the maximum jump from that index
            maxIndex = Math.max(maxIndex, i + nums[i]);
        }

        // If we complete the loop,
        // it means we can reach the last index
        return true;
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {4, 3, 7, 1, 2};

        System.out.print("Array representing maximum jump from each index: ");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        boolean ans = canJump(nums);

        if (ans) {
            System.out.println("It is possible to reach the last index.");
        } else {
            System.out.println("It is not possible to reach the last index.");
        }
    }
}

// Output : true

// Approach / Intuition : Optimal Solution
/*
A greedy algorithm makes a series of choices, each of which looks the best at the moment, with the hope
that these local optimizations will lead to a globally optimal solution. The key idea is to make the
best possible choice at each step without considering the overall problem.

For this problem, we keep track of the farthest position we can reach at any point in time. We iterate
over the array and keep making the greedy choice of reaching the farthest by comparing if the current
index can be reached by the previous indices or not.

If we reach an index that is beyond the farthest position we can reach, we return false. Else, we keep
updating the farthest position with the maximum index we can reach from the current index.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=tZAa_jJ3SwQ
