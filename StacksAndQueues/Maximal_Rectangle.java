package StacksAndQueues;

import java.util.Stack;

public class Maximal_Rectangle {

    // Method 1 : Optimal Solution (Using 1D Histogram)
    // Time - O(N x M) + O(N x M), Where N is no. of rows and M is no. of columns.
    // Reason : O(N x M) for updating the histogram (prefixSum array) for every row,
    //          and O(N x M) for calling largestRectangleArea() on each of the N rows.
    // Overall Time Complexity : O(N x M)
    //
    // Space - O(M) + O(M)
    // Reason : O(M) for the histogram (prefixSum) array and O(M) for the stack
    //          used in largestRectangleArea().
    // Overall Space Complexity : O(M)
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int n = matrix.length;
        int m = matrix[0].length;
        int maxArea = 0;

        int[] prefixSum = new int[m];  // Array to store heights for each column

        for (int i = 0; i < n; i++) {
            // Update the prefix sum (histogram) for the current row
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    // prefixSum[j] = prefixSum[j] + 1;
                    prefixSum[j]++;  // Increment the height if it's '1'
                } else {
                    prefixSum[j] = 0;  // Reset the height if it's '0'
                }
            }
            // Calculate the largest rectangle area for the current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(prefixSum));
        }

        return maxArea;
    }

    // Method 2 : Optimal Solution (Using 2D Prefix Sum Matrix)
    // Time - O(N x M) + O(N x M), Where N is no. of rows and M is no. of columns.
    // Reason : O(N x M) for building the 2D prefixSum (histogram heights),
    //          and O(N x M) for calling largestRectangleArea() on each row.
    // Overall Time Complexity : O(N x M)
    //
    // Space - O(N x M) + O(M)
    // Reason : O(N x M) for storing the 2D prefixSum matrix and O(M) for the stack
    //          used in largestRectangleArea().
    // Overall Space Complexity : O(N x M)
    public int maximalRectangle(char[][] matrix) {

        // Edge case: empty matrix
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        // prefixSum[r][c] stores the height of consecutive 1's
        // ending at row r in column c.
        int[][] prefixSum = new int[n][m];

        // Build the histogram heights for each column.
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[j][i] == '1') {
                    sum++;
                } else {
                    // Reset height when a 0 is encountered.
                    sum = 0;
                }
                prefixSum[j][i] = sum;
            }
        }

        int maxArea = 0;

        // Treat each row of prefixSum as a histogram and
        // compute the largest rectangle in that histogram.
        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, largestRectangleArea(prefixSum[i]));
        }

        return maxArea;
    }

    // Time Complexity : O(N) + O(N) ~ O(N)
    // O(N) is for traversing and another O(N) is for pushing and popping at max N elements
    // Space Complexity : O(N) for using Stack Data Structure
    // Function to calculate the largest rectangle area in a histogram
    static int largestRectangleArea(int[] histogram) {
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
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println("Maximal Rectangle Area: " + maximalRectangle(matrix)); // Output: 6
    }
}

// Output : Maximal Rectangle Area: 6

// Approach : Optimal Solution
/*
Intuition : In this question we will use the same approach in which we find the largest rectangle in Histogram.
In this question we will use the same concept which is used in Largest Rectangle in Histogram.

We assume the matrix[0] is an single array and same as for all the matrix[1] and so...on.

// Algorithm -
/*
Use Largest Rectangle in Histogram
1. We first initialize the heights array with all zeros.
2. We then iterate through the matrix and for each cell with value=1, we look upward (north), the number of continuous '1' is the height of cell.
3. We then calculate the area of the rectangle with the largest area.
*/

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=ttVu6G7Ayik
