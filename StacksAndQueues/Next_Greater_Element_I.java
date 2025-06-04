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
            
            // // If the stack is empty, it means there is no greater element to the right
            // if (stack.isEmpty()) {
            //     result[i] = -1; // Set result[i] to -1 indicating no greater element
            // }
            // // If the stack is not empty, the top element is the next greater element
            // else {
            //     result[i] = stack.peek(); // Set result[i] to the top element of the stack
            // }

            // If the stack is empty, there is no smaller element to the right, otherwise,
            // take the top element from the stack
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            
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
To make it a bit easier let’s first try to solve without considering the array as circular.
To find the next greater element we start traversing the given array from the right. As for the
rightmost element, there is no other element at its right. Hence, we assign -1 at its index in the
resultant array. Since this can be the next greater element (NGE) for some other element, we push
it in the stack S. We keep checking for other elements. Let’s say we are checking for an element
at index i. We keep popping from the stack until the element at the top of the stack is smaller
than A[i]. The main intuition behind popping them is that these elements can never be the NGE for
any element present at the left of A[i] because A[i] is greater than these elements. Now, if the
top element of S is greater than A[i] then this is NGE of A[i] and we will assign it to res[i],
where res is the resultant array. If the stack becomes empty then it implies that no element at
the right of A[i] is greater than it and we assign -1. At last, we push A[i] in S.

Dry run: Let’s apply this algorithm for A[] = {5,7,1,2,6,0}:

So, the resultant array is {7,-1,2,6,-1,-1}. Remember that we have considered the array to be
non-circular. For a circular array, the resultant array should be {7,-1,2,6,7,5}.

Now we need to make this algorithm work for a circular array. The only difference between
a circular and non-circular array is that while searching for the next greater element in a
non-circular array we don’t consider the elements left to the concerned element. This can be
easily done by inserting the elements of the array A at the end of A, thus making its size double.
But we actually don’t require any extra space. We can just traverse the array twice. We actually run
a loop 2*N times, where N is the size of the given array.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=e7XQLtOQM3I
