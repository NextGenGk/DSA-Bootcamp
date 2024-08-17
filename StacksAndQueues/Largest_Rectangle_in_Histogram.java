package StacksAndQueues;

import java.util.Stack;

public class Largest_Rectangle_in_Histogram {

    // Method 1 : Brute Force
    // Time Complexity : O(5N)
    // Space Complexity : O(4N)
    // Function is to find the Largest Rectangle in Histogram
    static int largestRectangleArea(int[] histogram) {
        int n = histogram.length;
        int maxi = 0;

        int[] pse = justPreviousSmallerElement(histogram); // TC: O(2N) SC: O(2N)
        int[] nse = justNextSmallerElement1(histogram); // TC: O(2N) SC: O(2N)

        for (int i = 0; i < n; i++) { // TC: O(N)
            maxi = Math.max(maxi, histogram[i] * (nse[i] - pse[i] - 1));
        }
        return maxi;
    }

    // Instead of storing elements in the stack, we are storing the index of that particular element
    static int[] justNextSmallerElement1(int[] arr) {
        int n = arr.length; // Get the length of the input array
        int[] result = new int[n]; // Create an array to store the result
        Stack<Integer> stack = new Stack<Integer>(); // Initialize a stack to track elements

        // Loop through the array starting from the last element
        for (int i = n - 1; i >= 0; i--) {
            // While the stack is not empty and the top element of the stack is greater
            // than or equal to the current element
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop(); // Pop the top element from the stack
            }
            // If the stack is empty, there is no smaller element to the right, otherwise,
            // take the top element from the stack
            result[i] = stack.isEmpty() ? n : stack.peek();

            // Push the current element onto the stack
            stack.push(i);
        }
        return result; // Return the result array
    }

    // Instead of storing elements in the stack, we are storing the index of that particular element
    static int[] justPreviousSmallerElement(int[] arr) {
        int n = arr.length;  // Get the length of the input array
        Stack<Integer> stack = new Stack<>();  // Stack to store elements for tracking nearest smaller elements
        int[] result = new int[n];  // Array to store the result

        // Iterate over each element in the array
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack until we find a smaller element or the stack is empty
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // If the stack is empty, it means no smaller element exists on the left, so store -1
            // Otherwise, store the top element of the stack (which is the nearest smaller element)
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push the current element onto the stack for future comparisons
            stack.push(i);
        }

        // Return the result array where each element is replaced by the nearest smaller element to its left
        return result;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N) + O(N) ~ O(N)
    // O(N) is for traversing and another O(N) is for pushing and popping at max N elements
    // Space Complexity : O(N) for using Stack Data Structure
    // Function to calculate the largest rectangle area in a histogram
    static int largestRectangleArea1(int[] histogram) {
        int n = histogram.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // Traverse through the histogram
        for (int i = 0; i < n; i++) {
            // While the current element is smaller than the element corresponding to the index at the top of the stack
            while (!stack.isEmpty() && histogram[stack.peek()] > histogram[i]) {
                int height = histogram[stack.pop()]; // Get the height of the bar at the top of the stack
                int nse = i; // The next smaller element index is 'i'
                int pse = stack.isEmpty() ? -1 : stack.peek(); // The previous smaller element index
                int width = nse - pse - 1; // Width of the rectangle
                maxArea = Math.max(maxArea, height * width); // Calculate the area and update maxArea
            }
            // Push the current index onto the stack
            stack.push(i);
        }

        // Process the remaining bars in the stack
        while (!stack.isEmpty()) {
            int height = histogram[stack.pop()]; // Get the height of the bar at the top of the stack
            int nse = n; // The next smaller element index is beyond the last element
            int pse = stack.isEmpty() ? -1 : stack.peek(); // The previous smaller element index
            int width = nse - pse - 1; // Width of the rectangle
            maxArea = Math.max(maxArea, height * width); // Calculate the area and update maxArea
        }

        return maxArea; // Return the maximum rectangle area
    }
    // Main Function
    public static void main(String args[]) {
        int histo[] = {3, 1, 5, 6, 2, 3};
        System.out.println("The largest area in the histogram is " + largestRectangleArea1(histo));
    }
}

// Output : The largest area in the histogram is 10

// Approach : Brute Force
/*
The intuition is to find the NSE and PSE and then we calculate the maxArea for each and every element (index).
The formula is to find the maxArea is
Histogram[i] = (NSE - PSE - 1)
 */

// Approach : Optimal Solution
/*
Intuition Behind the Algorithm

The problem asks for the largest rectangle area that can be formed in a histogram. The height of the
rectangle is defined by the shortest bar, and the width is defined by the number of consecutive bars
that are greater than or equal to this height. The key challenge is efficiently finding the width of
the largest rectangle for each bar.

Key Observations:
1. Width Calculation Depends on Smaller Elements:
The width of a rectangle that includes a given bar is limited by the nearest smaller bar on either side.
To find the largest rectangle for each bar, we need to know the index of the nearest smaller element on
both the left (Previous Smaller Element - PSE) and the right (Next Smaller Element - NSE).

2. Stack Helps Track the Previous Smaller Element:
By maintaining a monotonic stack (increasing order of heights), we can efficiently find the nearest
smaller element on the left for each bar. When a bar is found that is smaller than the bar at the top
of the stack, this smaller bar serves as the right boundary (NSE) for the top of the stack, and we can
calculate the rectangle area for the popped bar.

3. Processing Remaining Bars:
After traversing the histogram, any remaining bars in the stack do not have a smaller element on their
right. We assume the right boundary (NSE) to be the end of the histogram and calculate the rectangle
area using the remaining stack elements.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=Bzat9vgD0fs
// Article : https://takeuforward.org/data-structure/area-of-largest-rectangle-in-histogram/