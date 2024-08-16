package StacksAndQueues;

import java.util.Stack;

public class Next_Smaller_Element {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2) because we are using 2 loops
    // Space Complexity : O(N) because of returning result
    static int[] justNextSmallerElement(int[] arr) {
        int n = arr.length; // Get the length of the input array
        int[] result = new int[n]; // Create an array to store the result

        // Loop through each element of the array
        for (int i = 0; i < n; i++) {
            // Initialize min to -1 to store the next smaller element,
            // defaulting to -1 if none is found
            int min = -1;

            // Loop through the elements to the right of the current element
            for (int j = i + 1; j < n; j++) {
                // If a smaller element is found, update min
                if (arr[i] > arr[j]) {
                    min = arr[j]; // Set min to the smaller element
                    break; // Exit the loop since we only need the next smaller element
                }
            }
            result[i] = min; // Store the found smaller element (or -1 if none) in the result array
        }
        return result; // Return the result array
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(2N), O(N) for traversing and another O(N) for pushing and popping
    // elements onto the stack.
    // Space Complexity : O(N) + O(N), O(N) is for using stack and another O(N) for returning the result.
    static int[] justNextSmallerElement1(int[] arr) {
        int n = arr.length; // Get the length of the input array
        int[] result = new int[n]; // Create an array to store the result
        Stack<Integer> stack = new Stack<Integer>(); // Initialize a stack to track elements

        // Loop through the array starting from the last element
        for (int i = n - 1; i >= 0; i--) {
            // While the stack is not empty and the top element of the stack is greater
            // than or equal to the current element
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop(); // Pop the top element from the stack
            }
            // If the stack is empty, there is no smaller element to the right, otherwise,
            // take the top element from the stack
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push the current element onto the stack
            stack.push(arr[i]);
        }
        return result; // Return the result array
    }

    // Main Function
    public static void main(String[] args) {
        // Test array
        int[] arr = {13, 7, 6, 12};

        // Call the justNextSmallerElement function and store the result
        int[] result = justNextSmallerElement(arr);

        // Print the result array
        System.out.println("Next Smaller Elements:");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}

// Output :
/*
Next Smaller Elements:
7 6 -1 -1
 */

// Approach : Brute Force
/*
Next Smaller Element using two Nested loops
The outer loop picks all the elements one by one. The inner loop looks for the first smaller element
for the element picked by outer loop. If a smaller element is found then that element is printed as next,
otherwise, -1 is printed.
 */

// Approach : Optimal Solution
/*
This problem is similar to next greater element. Here we maintain items in increasing order in the stack
(instead of decreasing in next greater element problem). The idea is to store the indices of elements
for which we have to find the next smaller element in a stack and while traversing the array, if we
find a greater element, we will pair it with the elements from the stack till the top element of the
stack is less than the current element.

Algorithm :
1. Pick the elements one by one and follow following steps in loop.
    i. Mark the current element as next.
   ii. If stack is not empty, then compare next with stack top.
  iii. If next is smaller than top then next is the NSE for the top. Keep popping from the stack while top
       is greater than next. This next will be the NSE for all such popped elements
   iv. Push next into the stack
2. After all the iterations, pop all the elements from stack and print -1 as next element for them.

Note: To achieve the same order, we store indices in the stack instead of values.
 */

// Article : https://www.geeksforgeeks.org/next-smaller-element/