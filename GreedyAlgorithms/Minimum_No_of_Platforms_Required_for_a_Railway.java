package GreedyAlgorithms;

import java.util.Arrays;

public class Minimum_No_of_Platforms_Required_for_a_Railway {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(1)
    static int countPlatforms(int n, int[] arr, int[] dep) {
        int ans = 1; //final value
        for (int i = 0; i <= n - 1; i++) {
            int count = 1; // count of overlapping interval of only this iteration
            for (int j = i + 1; j <= n - 1; j++) {
                if ((arr[i] >= arr[j] && arr[i] <= dep[j]) ||
                        (arr[j] >= arr[i] && arr[j] <= dep[i])) {
                    count++;
                }
            }
            ans = Math.max(ans, count); // updating the value
        }
        return ans;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    static int countPlatforms1(int n, int[] arr, int[] dep) {
        // Sort both the arrival and departure arrays
        Arrays.sort(arr);
        Arrays.sort(dep);

        // Initialize platformNeeded to track the current number of platforms needed
        int platformNeeded = 1;

        // Result will store the maximum number of platforms needed at any time
        int result = 1;

        // Initialize two pointers, i for arrival array and j for departure array
        int i = 1;  // Start from the second train in the arrival array
        int j = 0;  // Start from the first train in the departure array

        // Traverse through all arrival and departure times
        while (i < n && j < n) {
            // If the next train arrives before or when the current train departs
            if (arr[i] <= dep[j]) {
                platformNeeded++;  // Increase platform count since a new train arrives
                i++;  // Move to the next arrival
            }
            // If the next train arrives after the current train departs
            else if (arr[i] > dep[j]) {
                platformNeeded--;  // Free up a platform as one train departs
                j++;  // Move to the next departure
            }

            // Update the result if the current number of platforms exceeds the previous maximum
            if (platformNeeded > result) {
                result = platformNeeded;
            }
        }

        // Return the maximum number of platforms needed
        return result;
    }

    // Main Function
    public static void main(String[] args) {

        int[] arr = {900, 945, 955, 1100, 1500, 1800};
        int[] dep = {920, 1200, 1130, 1150, 1900, 2000};
        int n = arr.length;
        int totalCount = countPlatforms(n, arr, dep);
        System.out.println("Minimum number of Platforms required " + totalCount);
    }
}

// Output :
/*
Minimum number of Platforms required 3
 */

// Approach / Intuition : Brute Force
/*
Intuition: Take each interval of arrival and departure one by one and count the number of overlapping
time intervals. This can easily be done using nested for-loops. Maintain the maximum value of the
count during the process and return the maximum value at the end.
 */

// Approach / Intuition : Optimal Solution
/*
The intuition behind this code is to simulate train arrivals and departures in chronological order by
sorting both the arrival and departure times. By using two pointers — one for arrivals and one for
departures  we can track when a train arrives and when the next train departs. If a train arrives
before the previous one departs, an additional platform is needed, and if a train departs before
the next one arrives, a platform is freed. The goal is to find the maximum number of platforms needed
at any time by tracking overlaps between arrivals and departures efficiently.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=AsGzwR_FWok