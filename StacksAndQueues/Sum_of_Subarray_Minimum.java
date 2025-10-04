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
    }

    // Main Function
    public static void main(String[] args) {
        // Test array
        int[] arr = {1, 4, 6, 7, 3, 7, 8, 1};

        // Call the sumSubarrayMins function and print the result
        int result = new sumSubarrayMin().sumSubarrayMins(arr);
        System.out.println("Sum of Subarray Minimums: " + result);
    }
}

// Output : 
// Sum of Subarray Minimums: 17

// Approach : Brute Force
/*
Generate all the subarray and find the minimum, and then calculate the sum of the total minimum and
then return the total sum.
 */

// Approach : Optimal Solution v2
/*
High-Level Idea:
Instead of generating all subarrays (which is O(n^2)) and finding their minimum (which is inefficient),
this code smartly computes:

For every element arr[i], how many subarrays have it as the minimum?

Then multiplies its value by that count and adds to the total.
*/

/*
Core Insight (Main Intuition):
For any element arr[i], the total number of subarrays in which it is the minimum is:
left * right

Where:

 i. left = number of elements to the left (including itself) for which arr[i] is the first smaller
ii. right = number of elements to the right (including itself) for which arr[i] is the first smaller

So the total contribution of arr[i] is:
arr[i] * left * right

You repeat this for every element and sum the contributions.
*/

/*
⚙️ Working of Each Function:
1. justPreviousSmallerElement(int[] arr) — 🔙
    i. It finds for every index i, the index of the previous element smaller than arr[i].
   ii. If none found → return -1.
This helps us determine how far to the left the current element can "spread" as minimum.

2. justNextSmallerElement(int[] arr) — 🔜
    i. It finds for every index i, the index of the next element smaller than arr[i].
   ii. If none found → return n (out of bounds).
This helps us determine how far to the right the current element can "spread" as minimum.
 */

/*
Input Example:
int[] arr = {3, 1, 2, 4};

Output Example:
Sum of subarray minimums = 17

Subarray and their minimums:
| Subarray     | Minimum |
| ------------ | ------- |
| [3]          | 3       |
| [3, 1]       | 1       |
| [3, 1, 2]    | 1       |
| [3, 1, 2, 4] | 1       |
| [1]          | 1       |
| [1, 2]       | 1       |
| [1, 2, 4]    | 1       |
| [2]          | 2       |
| [2, 4]       | 2       |
| [4]          | 4       |

Sum = 3 + 1 + 1 + 1 + 1 + 1 + 1 + 2 + 2 + 4 = 17
 */

/*
Step-by-Step Execution:
int[] arr = {3, 1, 2, 4}; // n = 4

Step 1: Compute PSE (Previous Smaller Element)
justPreviousSmallerElement(arr) → returns [-1, -1, 1, 2]

Explanation:
3 → no previous smaller → -1
1 → no previous smaller → -1
2 → previous smaller is 1 at index 1 → 1
4 → previous smaller is 2 at index 2 → 2

Step 2: Compute NSE (Next Smaller Element)
justNextSmallerElement(arr) → returns [1, 4, 4, 4]

Explanation:
3 → next smaller is 1 at index 1 → 1
1 → no next smaller → 4 (out of bounds)
2 → no next smaller → 4
4 → no next smaller → 4

Step 3: Calculate Contribution of Each Element
| i | arr\[i] | PSE | NSE | left = i - PSE\[i] | right = NSE\[i] - i | Contribution = arr\[i] \* left \* right |
| - | ------- | --- | --- | ------------------ | ------------------- | --------------------------------------- |
| 0 | 3       | -1  | 1   | 1 - (-1) = 1       | 1 - 0 = 1           | 3 \* 1 \* 1 = 3                         |
| 1 | 1       | -1  | 4   | 2                  | 3                   | 1 \* 2 \* 3 = 6                         |
| 2 | 2       | 1   | 4   | 1                  | 2                   | 2 \* 1 \* 2 = 4                         |
| 3 | 4       | 2   | 4   | 1                  | 1                   | 4 \* 1 \* 1 = 4                         |

// Total Contribution = 3 + 6 + 4 + 4 = 17
 */








// Approach : Optimal Solution
/*
The sumSubarrayMins function calculates the sum of the minimums of all subarrays using the concept of next and
previous smaller elements. The helper functions justPreviousSmallerElement and justNextSmallerElement
use stacks to efficiently find the previous and next smaller elements for each array element. The main
function uses these indices to calculate the contribution of each element to the total sum of minimums.
 */

// Note : Explanation of Subarray
/*
Consider the array [1,4,6,7,3,7,8,1], where we want to focus on the contribution of 3 and determine
in how many subarrays it will be the minimum.

We eliminate 1 from both ends since it is smaller than 3, leaving us with [4,6,7,3,7,8].

Now, for each subarray in [4,6,7,3,7,8] that starts at any of [4,6,7,3], the possible ending values
are [3,7,8] (including 3 - since we focusing on 3 as minimum).

There are 4 possible starting points, and for each starting point, there are 3 possible ending points.
Thus, the total number of subarrays is 4 × 3 = 12.

Example breakdown:
Subarray starting at 4 (includes 3 ends at 3, 7, or 8):
[4,6,7,3]
[4,6,7,3,7]
[4,6,7,3,7,8]

Subarray starting at 6 (includes 3 and ends at 3, 7, or 8):
[6,7,3]
[6,7,3,7]
[6,7,3,7,8]

Subarray starting at 7 (includes 3 and ends at 3, 7, or 8):
[7,3]
[7,3,7]
[7,3,7,8]

Subarray starting at 3 (includes 3 and ends at 3, 7, or 8):
[3]
[3,7]
[3,7,8]

Thus, the total number of valid subarrays is 12.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=v0e8p9JCgRc
