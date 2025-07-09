package GreedyAlgorithms;

import java.util.Arrays;

public class Job_Sequencing_Problem {

    // Job Class
    static class Job {
        int id, deadline, profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    // Function to schedule jobs and return the maximum profit along with the number of jobs done
    static int[] scheduleJobs(Job[] arr, int n) {
        // Step 1: Sort jobs based on profit in decreasing order
        Arrays.sort(arr, (a, b) -> (b.profit - a.profit));

        int jobProfit = 0; // to store total profit
        int countJobs = 0; // to store count of jobs done
        int maxi = -1;     // to store the maximum deadline

        // Step 2: Find the maximum deadline to size the result array
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, arr[i].deadline);
        }

        // Step 3: Create a result array to track which job is scheduled on which day
        // Initialize result array with -1 (indicating free slots)
        int[] result = new int[maxi + 1]; // Array indexes represent days
        Arrays.fill(result, -1);

        // Step 4: Schedule jobs in the result array
        for (int i = 0; i < n; i++) {
            // Try to schedule the current job on the last possible day before or on its deadline
            for (int j = arr[i].deadline; j > 0; j--) {
                // If the day is free, assign the job to this day
                if (result[j] == -1) {
                    result[j] = i;  // Mark the day as filled with the job's index
                    countJobs++;    // Increment job count
                    jobProfit += arr[i].profit;  // Add the job's profit
                    break;          // Move to the next job after scheduling the current one
                }
            }
        }

        // Step 5: Prepare the answer array with number of jobs done and total profit
        int[] ans = new int[2];
        ans[0] = countJobs;  // Number of jobs done
        ans[1] = jobProfit;  // Total profit
        return ans;
    }

    // Main Function
    public static void main(String[] args) {
        Job[] jobs = {
                new Job(1, 4, 20),
                new Job(2, 1, 10),
                new Job(3, 1, 40),
                new Job(4, 1, 30)
        };

        int[] result = scheduleJobs(jobs, jobs.length);
        System.out.println("Number of jobs done: " + result[0]);
        System.out.println("Total profit: " + result[1]);
    }
}

// Output :
/*
Number of jobs done: 2
Total profit: 60
 */

// Approach / Intuition : Optimal Solution
/*
The intuition behind this code is to maximize profit by scheduling jobs with deadlines using a
greedy approach. The jobs are first sorted in descending order of profit to prioritize high-reward
tasks. Then, for each job, the algorithm attempts to schedule it on the latest available day before
its deadline, ensuring as many jobs as possible are completed within their respective time limits.
This strategy ensures that the highest profit is achieved while respecting the job deadlines and
limited time slots.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=QbwltemZbRg
