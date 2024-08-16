package StacksAndQueues;

import java.util.Stack;

public class Sum_of_Subarray_Minimum {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(N)
    static int sumSubarrayMin(int[] arr) {
        int n = arr.length; // Get the length of the input array
        int sum = 0; // Variable to store the total sum of subarray minimums
        int mod = (int) (1e9 + 7); // Modulo value to prevent overflow

        // Loop through each element as the starting point of a subarray
        for (int i = 0; i < n; i++) {
            int min = arr[i]; // Initialize the minimum value as the starting element

            // Loop through the subarray starting at index i and ending at index j
            for (int j = i; j < n; j++) {
                // Update the minimum value for the current subarray
                min = Math.min(min, arr[j]);

                // Add the minimum value of the current subarray to the total sum, applying modulo
                sum = (sum + min) % mod;
            }
        }

        // Return the final sum of minimums for all subarrays
        return sum;
    }


    // Method 2 : Optimal Solution
    // Time Complexity : O(5N), O(2N) for finding nse and pse, and O(N) for traversing.
    // Space Complexity : O(4N), O(2N) for using stack and O(2N) for returning the answer.
    // Function to calculate the sum of minimums of all subarrays
    static class sumSubarrayMin {
        public int sumSubarrayMins(int[] arr) {
            int n = arr.length; // Get the length of the input array
            long total = 0; // Variable to store the total sum of minimums
            int mod = (int) (1e9 + 7); // Modulo value to prevent overflow

            // Get the Next Smaller Element (NSE) and Previous Smaller Element (PSE) arrays
            int[] nse = justNextSmallerElement(arr);
            int[] pse = justPreviousSmallerElement(arr);

            // Loop through the array to calculate contributions of subarrays
            for (int i = 0; i < n; i++) {
                int left = i - pse[i]; // Number of elements on the left (including current element)
                int right = nse[i] - i; // Number of elements on the right (including current element)

                // Calculate the contribution of arr[i] to the total sum and apply modulo
                total = (total + (long) left * right * arr[i]) % mod;
            }

            // Return the result as an integer
            return (int) total;
        }

        // Function to find the Previous Smaller Element (PSE) for each element in the array
        static int[] justPreviousSmallerElement(int[] arr) {
            int n = arr.length;  // Get the length of the input array
            Stack<Integer> stack = new Stack<>();  // Stack to store indices for tracking nearest smaller elements
            int[] result = new int[n];  // Array to store the result

            // Iterate over each element in the array
            for (int i = 0; i < n; i++) {
                // Pop elements from the stack until we find a smaller element or the stack is empty
                while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                    stack.pop();
                }

                // If the stack is empty, it means no smaller element exists on the left, so store -1
                // Otherwise, store the index of the nearest smaller element
                result[i] = stack.isEmpty() ? -1 : stack.peek();

                // Push the current index onto the stack for future comparisons
                stack.push(arr[i]);
            }

            // Return the result array containing indices of nearest smaller elements to the left
            return result;
        }

        // Function to find the Next Smaller Element (NSE) for each element in the array
        static int[] justNextSmallerElement(int[] arr) {
            int n = arr.length; // Get the length of the input array
            int[] result = new int[n]; // Create an array to store the result
            Stack<Integer> stack = new Stack<>(); // Stack to store indices for tracking nearest smaller elements

            // Loop through the array starting from the last element
            for (int i = n - 1; i >= 0; i--) {
                // Pop elements from the stack while the stack is not empty and the top element is greater or equal to arr[i]
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }

                // If the stack is empty, no smaller element exists to the right, so store n (indicating end of array)
                // Otherwise, store the index of the nearest smaller element
                result[i] = stack.isEmpty() ? n : stack.peek();

                // Push the current index onto the stack for future comparisons
                stack.push(i);
            }

            // Return the result array containing indices of nearest smaller elements to the right
            return result;
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Test array
        int[] arr = {3, 1, 2, 4};

        // Call the sumSubarrayMins function and print the result
        int result = new sumSubarrayMin().sumSubarrayMins(arr);
        System.out.println("Sum of Subarray Minimums: " + result);
    }
}

// Output : Sum of Subarray Minimums: 17

// Approach : Brute Force
/*
Generate all the subarray and find the minimum, and then calculate the sum of the total minimum and
then return the total sum.
 */

// Approach : Optimal Solution
/*
The sumSubarrayMins function calculates the sum of the minimums of all subarrays using the concept of next and
previous smaller elements. The helper functions justPreviousSmallerElement and justNextSmallerElement
use stacks to efficiently find the previous and next smaller elements for each array element. The main
function uses these indices to calculate the contribution of each element to the total sum of minimums.
 */