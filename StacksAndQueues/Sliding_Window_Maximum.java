package StacksAndQueues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Sliding_Window_Maximum {

    // Method 1 : Brute Force
    // Time Complexity : O(N-K) * K
    // Space Complexity : O(N-k) for storing the result and return.
    static int[] slidingWindowMax(int[] arr, int k) {
        int n = arr.length;  // Get the length of the input array

        // If the size of the array is the same as the window size, return the array itself
        if (n == k) return arr;

        // Create a result array to store the maximum values for each sliding window
        int[] result = new int[n - k + 1];

        // Loop through the array, from the start to where the last window can begin
        for (int i = 0; i < n - k + 1; i++) {
            int max = arr[i];  // Initialize max as the first element of the current window

            // Loop through the elements in the current window
            for (int j = i; j < i + k; j++) {
                // Update max if the current element is larger
                max = Math.max(max, arr[j]);
            }

            // Store the maximum value of the current window in the result array
            result[i] = max;
        }

        // Return the result array containing the maximum values for each window
        return result;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(2N), O(N) for traversing and O(N) for pushing and popping elements in dequeue.
    // Space Complexity : O(K) + O(N-K) for storing at max k elements in deque and O(N-K) for storing and
    // returning the result.
    public static int[] slidingWindowMax1(int[] arr, int k) {
        int n = arr.length;                   // Length of the array
        int[] result = new int[n - k + 1];       // Result array to store max values
        int index = 0;                         // Index to insert max values in result
        Deque<Integer> q = new ArrayDeque<>();  // Deque to store indices

        for (int i = 0; i < arr.length; i++) {
            // Remove elements that are out of the current window (i - k)
            if (!q.isEmpty() && q.peek() == i - k) {
                q.poll();  // Remove the oldest element in the deque
            }

            // Remove smaller elements in the current window (they are useless)
            while (!q.isEmpty() && arr[q.peekLast()] < arr[i]) {
                q.pollLast();  // Remove smaller elements
            }

            // Add the current element index to the deque
            q.offer(i);

            // Once we have processed at least 'k' elements, record the max in result array
            if (i >= k - 1) {
                result[index++] = arr[q.peek()];  // The front of the deque is the max in the window            }
            }
        }
        return result;  // Return the result array
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] result = slidingWindowMax1(arr, k);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}

// Output : 3 3 5 5 6 7

// Approach : Brute Force
/*
Intuition: We want to look for a window of size k at a time and then shift to the next window. So why
not do exactly what we are asked to! We fix our window of size k at first and then calculate the maximum
element in it. We then shift our window to the next position and do the same process until we exhaust
all possibilities i.e we reach the end of the array.

Approach: We initially keep a left and right pointer to fix our window to a size of k. We compute the
maximum element present in this window using the GetMax function. Further, update the left and right
pointer by left++ and right++ every time to get to a new window of size k using a while loop.
For every new window we encounter, we add the maximum element using the GetMax function to our
data structure.
 */

// Approach : Optimal Solution
/*Intuition : Can we do something better?
To understand this, we would first need to check whether we are doing any repetitions. To understand this,
consider the following scenario:

Window : [1,2,3]  and the next incoming value is 2

For this state, we get a maximum of 3. However, when our state changes to, [2,3,2] we again check what
is the largest element even though we know that the outgoing element is not the largest one. Hence, the
point of concern lies only when the outgoing element was the largest.

Approach
We address this problem with the help of a data structure that keeps checking whether the incoming element
is larger than the already present elements. This could be implemented with the help of a de-queue.
When shifting our window, we push the new element in from the rear of our de-queue.

Every time before entering a new element, we first need to check whether the element present at the front
is out of bounds of our present window size. If so, we need to pop that out. Also, we need to check
from the rear that the element present is smaller than the incoming element. If yes, there’s no point
storing them and hence we pop them out. Finally, the element present at the front would be our largest
element.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=NwBvene4Imo