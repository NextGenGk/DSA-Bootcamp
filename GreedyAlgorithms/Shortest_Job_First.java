package GreedyAlgorithms;

import java.util.Arrays;

public class Shortest_Job_First {

    // Method 1 : Optimal Solution
    // Time Complexity : O(N) + O(N log N)
    // Space Complexity : O(1)
    // Function to calculate average waiting time using Shortest Job First algorithm
    static float shortestJobFirst(int[] jobs) {
        // Sort the jobs in ascending order
        Arrays.sort(jobs);

        // Initialize total waiting time
        float waitTime = 0;
        // Initialize total time taken
        int totalTime = 0;
        // Get the number of jobs
        int n = jobs.length;

        // Iterate through each job to calculate waiting time
        for (int i = 0; i < n; ++i) {

            // Add current total time to waiting time
            waitTime += totalTime;

            // Add current job's time to total time
            totalTime += jobs[i];
        }

        // Return the average waiting time
        return waitTime / n;
    }

    // Main Function
    public static void main(String[] args) {
        int[] jobs = {4, 3, 7, 1, 2};

        System.out.print("Array Representing Job Durations: ");
        for (int i = 0; i < jobs.length; i++) {
            System.out.print(jobs[i] + " ");
        }
        System.out.println();

        float ans = shortestJobFirst(jobs);
        System.out.println("Average waiting time: " + ans);
    }
}

// Output Array Representing Job Durations: 4 3 7 1 2
// Average waiting time: 4

// Approach / Intuition : Optimal Solution
/*
We implement the Shortest Job Duration First algorithm using the Greedy technique by selecting the
shortest job from the array. To always select the shortest job first we sort the job duration array
in ascending order based on their duration.

Once the jobs are sorted, we iterate through each job in the sorted list. For each iteration the
waiting time is the sum of the total time taken by all previous jobs. We calculate the waiting time
for each job and update the total time taken by adding the duration of the current job to the total
waiting time.

Algorithm :
Algorithm:
Step 1: Sort the jobs in ascending order based on their durations. The jobs array will now contain
the job durations arranged from shortest to longest.

Step 2: Initialise variables waitTime to 0 (waiting time for that particular job) and totalTime
(total waiting time for all jobs).

Step 3: Iterate through each job in the sorted array. For each job, its waiting time is the sum of
total time taken by all previous jobs. Update the total time taken by adding the duration of the
current job to the total waiting time.

Step 4: After iterating through each job in the array, the average waiting is total waiting time
divided by the number of jobs ie. length of the jobs array.After iterating through each job in the
array, the average waiting is total waiting time divided by the number of jobs ie. length of the jobs array.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=3-QbX1iDbXs