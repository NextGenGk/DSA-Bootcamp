package GreedyAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

public class Non_Overlapping_Intervals {

    // Method 1 : Optimal Solution
    // Time complexity: O(N) + O(N log N) where O(N) is for traversing and
    // O (N log N) for sorting the intervals.
    // Space complexity: O(1) no extra space is needed.
    public static int eraseOverlapIntervals(int[][] intervals) {
        // Base Case
        if (intervals.length == 0) return 0;

        // Sort intervals by their end time
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]); // compare by end times
            }
        });

        int n = intervals.length;
        int count = 1; // To count non-overlapping intervals
        int lastEndTime = intervals[0][1]; // Store the end time of the last added interval

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= lastEndTime) { // No overlap
                count++;
                lastEndTime = intervals[i][1]; // Update lastEndTime
            }
        }

        // Return the number of intervals to remove (total - non-overlapping count)
        return n - count;
    }

    // Main Function
    public static void main(String[] args) {
        int[][] intervals = {
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 3}
        };

        int result = eraseOverlapIntervals(intervals);
        System.out.println("Number of intervals to remove: " + result);
    }
}

// Output : Number of intervals to remove: 1

// Approach / Intuition : Optimal Solution
/*
The intuition behind this code is to minimize the number of overlapping intervals by always selecting
intervals that end the earliest, following a greedy approach. By sorting intervals based on their end
times, we ensure that each selected interval leaves the maximum possible space for the following
intervals, reducing the chance of overlap. We then iterate through the sorted intervals, picking
only those that start after the previous one ends, thus counting the non-overlapping intervals.
The number of intervals to remove is simply the total number of intervals minus the number of
non-overlapping ones, ensuring the minimal removal of overlapping intervals.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=HDHQ8lAWakY
