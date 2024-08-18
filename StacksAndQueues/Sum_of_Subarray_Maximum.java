package StacksAndQueues;

import java.util.Stack;

public class Sum_of_Subarray_Maximum {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(N)
    static int sumSubarrayMax(int[] arr) {
        int n = arr.length; // Get the length of the input array
        int sum = 0; // Variable to store the total sum of subarray maximums
        int mod = (int) (1e9 + 7); // Modulo value to prevent overflow

        // Loop through each element as the starting point of a subarray
        for (int i = 0; i < n; i++) {
            int max = arr[i]; // Initialize the maximum value as the starting element

            // Loop through the subarray starting at index i and ending at index j
            for (int j = i; j < n; j++) {
                // Update the maximum value for the current subarray
                max = Math.max(max, arr[j]);

                // Add the maximum value of the current subarray to the total sum, applying modulo
                sum = (sum + max) % mod;
            }
        }

        // Return the final sum of maximums for all subarrays
        return sum;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(5N), O(2N) for finding nse and pse, and O(N) for traversing.
    // Space Complexity : O(4N), O(2N) for using stack and O(2N) for returning the answer.
    // Function to calculate the sum of maximums of all subarrays
    // Function to calculate the sum of subarray maximums
    public static int sumSubarrayMax1(int[] arr) {
        int n = arr.length; // Get the length of the input array
        long total = 0; // Variable to store the total sum of maximums
        int mod = (int) (1e9 + 7); // Modulo value to prevent overflow

        // Get the Next Greater Element (NGE) and Previous Greater Element (PGE) arrays
        int[] nge = justNextGreaterElement(arr);
        int[] pge = justPreviousGreaterElement(arr);

        // Loop through the array to calculate contributions of subarrays
        for (int i = 0; i < n; i++) {
            int left = i - pge[i]; // Number of elements on the left (including current element)
            int right = nge[i] - i; // Number of elements on the right (including current element)

            // Calculate the contribution of arr[i] to the total sum and apply modulo
            total = (total + (long) left * right * arr[i]) % mod;
        }

        // Return the result as an integer
        return (int) total;
    }

    // Function to find the Previous Greater Element (PGE) for each element in the array
    static int[] justPreviousGreaterElement(int[] arr) {
        int n = arr.length; // Get the length of the input array
        Stack<Integer> stack = new Stack<>(); // Stack to store indices for tracking nearest greater elements
        int[] result = new int[n]; // Array to store the result

        // Iterate over each element in the array
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack until we find a greater element or the stack is empty
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            // If the stack is empty, it means no greater element exists on the left, so store -1
            // Otherwise, store the index of the nearest greater element
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push the current index onto the stack for future comparisons
            stack.push(i);
        }

        // Return the result array containing indices of nearest greater elements to the left
        return result;
    }

    // Function to find the Next Greater Element (NGE) for each element in the array
    static int[] justNextGreaterElement(int[] arr) {
        int n = arr.length; // Get the length of the input array
        int[] result = new int[n]; // Create an array to store the result
        Stack<Integer> stack = new Stack<>(); // Stack to store indices for tracking nearest greater elements

        // Loop through the array starting from the last element
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from the stack while the stack is not empty and the top element
            // is less than or equal to arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            // If the stack is empty, no greater element exists to the right, so store n
            // (indicating end of array)
            // Otherwise, store the index of the nearest greater element
            result[i] = stack.isEmpty() ? n : stack.peek();

            // Push the current index onto the stack for future comparisons
            stack.push(i);
        }

        // Return the result array containing indices of nearest greater elements to the right
        return result;
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4}; // Example array
        int result = sumSubarrayMax1(arr); // Call the method
        System.out.println("Sum of Subarray Maximums: " + result); // Output the result
    }
}

// Output : Sum of Subarray Maximums: 30

// Approach : Brute Force
/*
Generate all the subarrays and find the maximum, then calculate the sum of the total maximums
and return the total sum.
 */

// Approach : Optimal Solution
/*
The sumSubarrayMax function calculates the sum of the maximums of all subarrays using the concept of next and
previous greater elements. The helper functions justPreviousGreaterElement and justNextGreaterElement
use stacks to efficiently find the previous and next greater elements for each array element. The main
function uses these indices to calculate the contribution of each element to the total sum of maximums.
 */

// Logic : Sum of Subarray Minimum.