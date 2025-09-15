package StacksAndQueues;

import java.util.Stack;

public class Sum_of_Subarray_Ranges {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(1)
    static int sumOfSubarrayRanges(int[] arr) {
        int n = arr.length; // Get the length of the input array
        int total = 0; // Variable to store the total sum of subarray ranges (max - min)

        // Loop through each element as the starting point of a subarray
        for (int i = 0; i < n; i++) {
            int min = arr[i]; // Initialize the minimum value as the starting element
            int max = arr[i]; // Initialize the maximum value as the starting element

            // Loop through the subarray starting at index i and ending at index j
            for (int j = i + 1; j < n; j++) {
                // Update the minimum and maximum values for the current subarray
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                // Calculate the difference between max and min (the range of the subarray)
                // and add it to the total sum
                total += max - min;
            }
        }

        // Return the final total sum of subarray ranges (max - min)
        return total;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(10N) ~ O(N)
    // Space Complexity : O(4N)) ~ O(N)
    static int sumOfSubarrayRanges1(int[] arr) {
        return sumSubarrayMax(arr) - sumSubarrayMin(arr);
    }

    // Time Complexity : O(5N), O(4N) for finding nse and pse, and O(N) for traversing.
    // Space Complexity : O(4N), O(2N) for using stack and O(2N) for returning the answer.
    // Function to calculate the sum of maximums of all subarrays
    // Function to calculate the sum of subarray maximums
    public static int sumSubarrayMax(int[] arr) {
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

    // Function to find the Subarray Minimum
    public static int sumSubarrayMin(int[] arr) {
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
        int n = arr.length; // Get the length of the input array
        Stack<Integer> stack = new Stack<>(); // Stack to store indices for tracking nearest smaller elements
        int[] result = new int[n]; // Array to store the result

        // Iterate over each element in the array
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack until we find a smaller element or the stack is
            // empty
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // If the stack is empty, it means no smaller element exists on the left, so
            // store -1
            // Otherwise, store the index of the nearest smaller element
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push the current index onto the stack for future comparisons
            stack.push(i);
        }

        // Return the result array containing indices of nearest smaller elements to the
        // left
        return result;
    }

    // Function to find the Next Smaller Element (NSE) for each element in the array
    static int[] justNextSmallerElement(int[] arr) {
        int n = arr.length; // Get the length of the input array
        int[] result = new int[n]; // Create an array to store the result
        Stack<Integer> stack = new Stack<>(); // Stack to store indices for tracking nearest smaller elements

        // Loop through the array starting from the last element
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from the stack while the stack is not empty and the top element
            // is greater or equal to arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            // If the stack is empty, no smaller element exists to the right, so store n
            // (indicating end of array)
            // Otherwise, store the index of the nearest smaller element
            result[i] = stack.isEmpty() ? n : stack.peek();

            // Push the current index onto the stack for future comparisons
            stack.push(i);
        }

        // Return the result array containing indices of nearest smaller elements to the
        // right
        return result;
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2}; // Example array
        int result = sumOfSubarrayRanges1(arr); // Call the method
        System.out.println("Sum of Subarray Ranges: " + result); // Output the result
    }
}

// Output : Sum of Subarray Ranges: 13

// Approach : Brute Force
/*
Generate all the subarray and find the largest and smallest element of every subarray, and then calculate the sum of the
total = largest - smallest and then return the total.
 */

// Approach : Optimal Solution
/*
The logic is to find the sumOfSubarrayMinimum (largest) and find the sumOfSubarrayMaximum (smallest)
and then return largest - smallest;
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=gIrMptNPf5M
