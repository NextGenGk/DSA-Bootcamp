package Sliding_Window_and_Two_Pointers.Patterns;

import java.util.ArrayList;

public class Fixed_Window {

    // Method 1 : Optimal Solution
    public static int maxConsecutiveSum(ArrayList<Integer> arr, int K) {
        // Get the size of the input array
        int n = arr.size();

        // Check if the size of
        // the array is less than K
        if (n < K) {
            // Handle invalid input
            // by returning 0
            return 0;
        }

        // Initialize left and right
        // pointers of the sliding window

        // Left pointer
        int left = 0;
        // Right pointer,
        // initialized to K-1
        int right = K - 1;

        // Initialize variables to store
        // current sum and maximum sum

        // Current sum
        int sum = 0;

        // Maximum sum
        int maxSum = 0;

        // Calculate sum of first K elements and set it as maxSum
        for (int i = 0; i <= right; ++i) {
            // Add each element within the window to the sum
            sum += arr.get(i);
        }

        // Set maxSum to the sum of first K elements
        maxSum = sum;

        // Slide the window and calculate the maximum sum
        while (right < n - 1) {

            // Slide the window by subtracting the leftmost element and adding the next element to the right
            sum = sum - arr.get(left) + arr.get(right + 1);

            // Update maxSum with the maximum of current maxSum and sum within the window
            maxSum = Math.max(maxSum, sum);

            // Move the left pointer to the right by one position
            left++;
            // Move the right pointer to the right by one position
            right++;
        }

        // Return the maximum sum
        return maxSum;
    }

    // Main Function
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(-1);
        arr.add(2);
        arr.add(3);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(-1);

        int K = 4;
        System.out.print("Input Array: ");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println("\nK: " + K);
        System.out.println("Maximum sum: " + maxConsecutiveSum(arr, K));
    }
}

// Output : Maximum sum: 15

// Approach / Intuition :
/*
Step 1: Initialise a variable to keep track of the size of the array, the left pointer of the sliding window,
the right pointer of the sliding window, the current sum and the maximum sum.
Step 2: Calculate the sum of the first K elements of the array and set it as the initial value of maximum sum.
Step 3: Slide the Window and Update Maximum Sum: While the right pointer is less than the size of the array.
    i. Slide the window by removing the leftmost elements and adding the next element to the right.
   ii. Update the maximum sum with the maximum of the current maximum sum and the sum within the window.
  iii. Move the left and right pointer to the right by one position.
Step 4: After sliding the window through the entire array, return the maximum sum obtained.
 */

// Time & Space Complexity :
/*
Time Complexity: O(K + (N-K+1)) where K is the number of consecutive numbers and N is the size of
the input array,
    i. The loop to calculate the sum of the first K elements runs in O(K) time.
   ii. The main loop that slides the window runs N-K+1 times.
Space Complexity: O(1) as the algorithm uses only a constant amount of extra space regardless of the
size of the input array. It does not require any additional data structures that scale with the input size.
 */

// Striver's (Video Explanation) : https://youtu.be/9kdHxplyl5I?si=6jMhjwSmw8puyIJq