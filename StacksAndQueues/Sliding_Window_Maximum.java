package StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Deque;

public class Sliding_Window_Maximum {

    // Method 1 : Brute Force
    // Time Complexity : O(N-k) * k
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

    // Method 2: Optimal Solution (Deque / Monotonic Queue)
    // Time Complexity: O(N)
    // Each element is inserted into the deque once and removed at most once.
    // Therefore, the total number of deque operations is O(N).
    //
    // Space Complexity: O(K) + O(N - K + 1)
    // O(K)           -> Deque stores at most K indices.
    // O(N - K + 1)   -> Result array stores the maximum for each sliding window.
    
    public static int[] slidingWindowMaximum(int[] arr, int k) {
    
        int n = arr.length;                       // Total number of elements
        int[] result = new int[n - k + 1];        // Stores the maximum of each window
        int resultIndex = 0;                      // Current index in the result array
    
        // Deque stores indices of array elements.
        // Elements are maintained in decreasing order of their values.
        Deque<Integer> deque = new ArrayDeque<>();
            // Base case
            if (n == 0) {
                return arr;
            }
        
            // Traverse the array
            for (int i = 0; i < n; i++) {
        
                // Step 1: Remove indices that are outside the current window.
                // Current window = [i - k + 1, i]
                if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                    deque.pollFirst();
                }
        
                // Step 2: Remove all smaller elements from the back.
                // They can never become the maximum while the current element exists.
                while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                    deque.pollLast();
                }
        
                // Step 3: Add the current index to the deque.
                deque.offerLast(i);
        
                // Step 4: Once the first window is formed,
                // the front of the deque always contains the index
                // of the maximum element for the current window.
                if (i >= k - 1) {
                    result[resultIndex++] = arr[deque.peekFirst()];
                }
            }
    
        // Return the processed result
        return result;
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

// Output : 
// 3 3 5 5 6 7

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

Example input :
arr = [1, 3, -1, -3, 5, 3, 7, 1, 6]
k = 3
We want to slide a window of size 3 and get the maximum in each window.

We’ll keep a deque () to store indices (not values). The front of the deque always has the index
of the largest element in the current window.

Step-by-step with push and pop:

| i | arr\[i] | Action                              | Deque (indexes) | Deque (values) | Result (if i ≥ 2) |
| - | ------- | ----------------------------------- | --------------- | -------------- | ----------------- |
| 0 | 1       | `push(0)`                           | \[0]            | \[1]           | -                 |
| 1 | 3       | `pop(0)` (1 < 3), `push(1)`         | \[1]            | \[3]           | -                 |
| 2 | -1      | `push(2)`                           | \[1, 2]         | \[3, -1]       | 3                 |
| 3 | -3      | `push(3)`                           | \[1, 2, 3]      | \[3, -1, -3]   | 3                 |
|   |         | `pop(1)` (index 1 is out of window) | \[2, 3]         | \[-1, -3]      |                   |
| 4 | 5       | `pop(3)`, `pop(2)`, `push(4)`       | \[4]            | \[5]           | 5                 |
| 5 | 3       | `push(5)`                           | \[4, 5]         | \[5, 3]        | 5                 |
| 6 | 7       | `pop(5)`, `pop(4)`, `push(6)`       | \[6]            | \[7]           | 7                 |
| 7 | 1       | `push(7)`                           | \[6, 7]         | \[7, 1]        | 7                 |
| 8 | 6       | `pop(7)` (1 < 6), `push(8)`         | \[6, 8]         | \[7, 6]        | 7                 |

Final Output : [3, 3, 5, 5, 7, 7, 7]
 */

// Visualization
/*
═══════════════════════ HORIZONTAL VIEW ═══════════════════════

                    REAR (Last)
                         ▲
        offerLast()   peekLast()   pollLast()
                         │
                         │
FRONT (First) ◀────────────────────────────────────────▶ REAR
        
        +-------+-------+-------+-------+-------+
        |  10   |  20   |  30   |  40   |  50   |
        +-------+-------+-------+-------+-------+

        offerFirst()  peekFirst()  pollFirst()
                         │
                         ▼
                    FRONT (First)



════════════════════════ VERTICAL VIEW ════════════════════════

                    REAR (Last)
                         ▲
                     peekLast()
                     pollLast()
                    offerLast()
                         │
                  +-------------+
                  |     50      |
                  +-------------+
                  |     40      |
                  +-------------+
                  |     30      |
                  +-------------+
                  |     20      |
                  +-------------+
                  |     10      |
                  +-------------+
                         │
                    offerFirst()
                    pollFirst()
                    peekFirst()
                         ▼
                   FRONT (First)
*/

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=NwBvene4Imo
