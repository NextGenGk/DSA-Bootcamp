package StacksAndQueues;

import java.util.Arrays;
import java.util.Stack;

public class Next_Greater_Element_II {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(N)
    static int[] nextGreaterElements(int[] arr) {
        // Get the length of the input array
        int n = arr.length;

        // Create an array to store the result, initialized with -1 (or any other sentinel value)
        int[] result = new int[n];
        Arrays.fill(result, -1);  // Fill the result array with -1

        // Iterate through each element in the array
        for (int i = 0; i < n; i++) {
            // Iterate through the rest of the array, starting from the next element
            // Use modulo operation to wrap around the array in case the index goes beyond the last element
            /*
            The correct condition should allow the loop to wrap around and check all n-1 elements after i:
            */
            for (int j = i + 1; j < i + n; j++) {
                int index = j % n;

                // If a greater element is found, store it in the result array and break out of the loop
                if (arr[index] > arr[i]) {
                    result[i] = arr[index];
                    break;
                }
            }
        }

        // Return the array containing the next greater elements
        return result;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(4N), 0(2N) for traversing the array (hypothetically) and another O(2N)
    // for pushing and popping elements onto the stack.
    // Space Complexity : O(2N) + O(N), O(2N) for using stack twice and O(N) for returning the result.
    static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<Integer>();
        int[] result = new int[n];

        // Traverse the array twice (circularly) to account for circular nature
        for (int i = 2 * n - 1; i >= 0; i--) {
            // Use modulo to wrap around the array
            int currentIndex = i % n;

            // Pop elements from the stack that are less than or equal to the current element
            while (!stack.isEmpty() && stack.peek() <= arr[currentIndex]) {
                stack.pop();
            }

            // For the first pass (i < n), populate the result array
            if (i < n) {
                // If the stack is empty, no greater element exists; otherwise, the top of the stack is the next greater element
                result[currentIndex] = stack.isEmpty() ? -1 : stack.peek();
            }

            // Push the current element onto the stack
            stack.push(arr[currentIndex]);
        }

        // Return the array containing the next greater elements
        return result;
    }

    // Main Function
    public static void main(String[] args) {
        // Example array to find the next greater elements
        int[] arr = {5, 7, 1, 2, 6, 0};

        // Call the nextGreaterElement method
        int[] result = nextGreaterElement(arr);

        // Print the input array
        System.out.print("Input Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Print the result array
        System.out.print("Next Greater Elements: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}

// Output :
/*
Input Array: 5 7 1 2 6 0
Next Greater Elements: 7 -1 2 6 -1 5
 */

// Algorithm : Brute Force
/*
Use Circular Array : In this approach we do double the array not bby doubling it in a real coding stuff
but we just think it virtually (hypothetically) in our brain

Find hypothetical index is
(index % n)

eg: N=5 elements
5 % 5 = 0th index;
6 % 5 = 1st index
7 % 5 = 2nd index
and so on for hypothetical index
 */

// Algorithm : Optimal Solution (Using Same Concept of Next Greater Element I)
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

// Striver (Video Explanation) : https://www.youtube.com/watch?v=7PrncD7v9YQ
