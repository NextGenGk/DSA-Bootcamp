package StacksAndQueues;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class Next_Greater_Element_I {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(N)
    static int[] nextGreaterElements(int[] arr) {
        // Initialize the result array with -1, assuming no greater element exists.
        int[] result = new int[arr.length];
        Arrays.fill(result, -1); // Fills the array with -1 by default.

        // Outer loop: iterate through each element in the array.
        for (int i = 0; i < arr.length; i++) {
            // Inner loop: iterate through the elements to the right of the current element.
            for (int j = i + 1; j < arr.length; j++) {
                // If we find a greater element, store it in the result array.
                if (arr[j] > arr[i]) {
                    result[i] = arr[j];
                    // Break the inner loop as we found the next greater element.
                    break;
                }
            }
        }
        // Return the result array containing the next greater elements for each index.
        return result;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(2N)
    // Space Complexity : O(N) for using stack
    static int[] nextGreaterElement(int[] arr) {
        int n = arr.length; // Get the length of the input array
        Stack<Integer> stack = new Stack<Integer>(); // Initialize a stack to keep track of elements
        int[] result = new int[n]; // Initialize the result array to store the next greater elements

        // Iterate over the array from the last element to the first
        for (int i = n - 1; i >= 0; i--) {
            // While stack is not empty and the top element of the stack is less than or equal to the current element
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop(); // Pop elements from the stack that are not greater than the current element
            }
            // If the stack is empty, it means there is no greater element to the right
            if (stack.isEmpty()) {
                result[i] = -1; // Set result[i] to -1 indicating no greater element
            }
            // If the stack is not empty, the top element is the next greater element
            else {
                result[i] = stack.peek(); // Set result[i] to the top element of the stack
            }
            // Push the current element onto the stack
            stack.push(arr[i]);
        }
        // Return the result array containing the next greater elements for each index
        return result;
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 2, 6, 0};

        int[] arr2 = nextGreaterElement(arr);
        System.out.println("The next greater elements are ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
    }
}

// Output :
/*
The next greater elements are
7 -1 2 6 -1 -1
 */

// Approach : Brute Force (Using 2 Loops)

// Approach : Optimal Solution
/*

 */

