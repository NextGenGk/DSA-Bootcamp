package StacksAndQueues;

import java.util.Stack;

public class Previous_Smaller_Element {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2) because we are using 2 loops
    // Space Complexity : O(N) because of returning result
    static int[] justPreviousSmallerElements(int[] arr) {
        int n = arr.length;  // Get the length of the array
        int[] result = new int[n];  // Initialize the result array of the same length

        // Loop through each element of the array
        for (int i = 0; i < n; i++) {
            int min = -1;  // Initialize the minimum value as -1 (default if no smaller element is found)

            // Look for any smaller element to the left of the current element (including itself)
            for (int j = 0; j <= i; j++) {
                // If a smaller element is found, update the min
                if (arr[j] < arr[i]) {
                    min = arr[j];
                }
            }

            // Store the nearest smaller element in the result array
            result[i] = min;
        }
        return result;  // Return the result array
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(2N), O(N) for traversing and another O(N) for pushing and popping
    // elements onto the stack.
    // Space Complexity : O(N) + O(N), O(N) is for using stack and another O(N) for returning the result.
    static int[] justPreviousSmallerElement(int[] arr) {
        int n = arr.length;  // Get the length of the input array
        Stack<Integer> stack = new Stack<>();  // Stack to store elements for tracking nearest smaller elements
        int[] result = new int[n];  // Array to store the result

        // Iterate over each element in the array
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack until we find a smaller element or the stack is empty
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            // If the stack is empty, it means no smaller element exists on the left, so store -1
            // Otherwise, store the top element of the stack (which is the nearest smaller element)
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push the current element onto the stack for future comparisons
            stack.push(arr[i]);
        }

        // Return the result array where each element is replaced by the nearest smaller element to its left
        return result;
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};  // Example input array
        int[] result = justPreviousSmallerElement(arr);

        System.out.println("Input Array: ");
        for (int val : arr) {
            System.out.print(val + " ");
        }

        System.out.println("\nNearest Smaller Elements: ");
        for (int val : result) {
            System.out.print(val + " ");
        }
    }
}

// Output :
/*
Input Array:
4 5 2 10 8
Nearest Smaller Elements:
-1 4 -1 2 2
 */

// Approach : Brute Force (Using Simple 2 for loops)
/*
1. Create an array result[] of size n to store the nearest smaller elements.
2. For each element arr[i]:
    i. Initialize min to -1 (which is used when no smaller element is found).
   ii. Loop through all elements arr[j] where j ranges from 0 to i. For each element arr[j],
       if arr[j] < arr[i], update min to arr[j].
  iii. Store the value of min in result[i].
3. Return the result[] array, where each position contains the nearest smaller element, or -1 if no
smaller element exists.
 */

// Approach : Optimal Solution (with the help Monotonic Stack)
// Using the logic of Next Greater Element
/*
Steps:
1. Stack Initialization:
i. A stack is used to keep track of the elements as we traverse the array.
The stack will help maintain the elements in such a way that the top of the stack is always the
nearest smaller element.

2. Iterate through the array:
i. For each element arr[i], the goal is to find the nearest smaller element to the left.

3. Pop Elements:
i. While the stack is not empty and the top of the stack is greater than or equal to the current
element (arr[i]), pop the stack. This removes elements that are not useful for finding the nearest
smaller element.

4. Check Stack:
i. If the stack is empty after popping, it means there are no smaller elements to the left of arr[i],
so store -1 in the result array.
ii. If the stack is not empty, the top element of the stack is the nearest smaller element, so store it
in the result array.

5. Push Current Element:
i. After determining the nearest smaller element for arr[i], push the current element arr[i] onto the
stack for future comparisons.

6. Return Result:
After processing all elements, return the result array, which contains the nearest smaller element
for each element of arr[].
 */

// Striver (Video Explanation) : https://youtu.be/zMdbdGJNlh4?si=Yegxjmyssi0WvfOx