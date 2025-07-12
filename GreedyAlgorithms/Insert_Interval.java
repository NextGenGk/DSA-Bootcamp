package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class Insert_Interval {

    // Method 1 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(N)
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> result = new ArrayList<>();
        int i = 0;

        // Add all intervals that end before the newInterval starts (no overlap).
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge all intervals that overlap with the newInterval.
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // Adjust the start and end of the newInterval to cover all overlaps.
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        // Add the merged interval (newInterval).
        result.add(newInterval);

        // Add the remaining intervals that start after the newInterval ends.
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // Convert result list back to a 2D array and return.
        return result.toArray(new int[result.size()][]);
    }

    // Helper function to print 2D intervals
    public static void printIntervals(int[][] intervals) {
        for (int[] interval : intervals) {
            System.out.print("[" + interval[0] + ", " + interval[1] + "] ");
        }
        System.out.println();
    }

    // Main Function
    public static void main(String[] args) {
        // Example 1
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};

        // Results
        int[][] result = insert(intervals, newInterval);
        System.out.println("Result after inserting [4, 8]:");
        printIntervals(result);
    }
}

// Output :
// Result after inserting [4, 8]:
// [1, 2] [3, 10] [12, 16]

// Approach / Intuition : Optimal Solution
/*
The code aims to insert a new interval into a list of sorted, non-overlapping intervals while maintaining
the non-overlapping property. It does this by first adding all intervals that end before the new interval
(non-overlapping) to the result. Then, it merges any intervals that overlap with the new interval by
adjusting the start and end of the new interval to cover all overlaps. After merging, the new interval
is added to the result. Finally, any remaining intervals that start after the new interval are added.
The result is then returned as a 2D array.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=xxRE-46OCC8
