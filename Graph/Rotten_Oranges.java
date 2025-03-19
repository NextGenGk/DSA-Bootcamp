package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class Rotten_Oranges {

    // Main Function
    public static void main(String[] args) {
        int[][] mat = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 2, 1}
        };

        Rotten_Oranges obj = new Rotten_Oranges();
        int time = obj.orangesRotting(mat);

        System.out.println("Time taken to rot all oranges: " + time);
    }

    public int orangesRotting(int[][] mat) {
        int n = mat.length; // Number of rows
        int m = mat[0].length; // Number of columns

        Queue<Pair> queue = new LinkedList<>();
        int[][] visited = new int[n][m]; // Visited array to track rotten oranges

        int cntFresh = 0; // Count of fresh oranges

        // Step 1: Find all initially rotten oranges and count fresh ones
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 2) {
                    queue.add(new Pair(i, j, 0)); // Add rotten orange to queue
                    visited[i][j] = 2; // Mark as visited
                } else {
                    visited[i][j] = 0; // Mark as unvisited
                }

                if (mat[i][j] == 1) cntFresh++; // Count fresh oranges
            }
        }

        // Possible movements (up, right, down, left)
        int[] dRow = {-1, 0, +1, 0};
        int[] dCol = {0, +1, 0, -1};

        int timeElapsed = 0;
        int cntRotten = 0; // Count of newly rotten oranges

        // Step 2: BFS traversal to rot fresh oranges
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int r = p.row;
            int c = p.col;
            int time = p.time;

            timeElapsed = Math.max(timeElapsed, time); // Update the max time

            // Check all 4 adjacent cells
            for (int i = 0; i < 4; i++) {
                int nRow = r + dRow[i];
                int nCol = c + dCol[i];

                // If the neighboring cell is within bounds and is a fresh orange
                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                        && visited[nRow][nCol] == 0 && mat[nRow][nCol] == 1) {

                    queue.add(new Pair(nRow, nCol, time + 1)); // Rot it and add to queue
                    visited[nRow][nCol] = 2; // Mark as visited
                    cntRotten++; // Increase rotten count
                }
            }
        }

        // Another method
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         if (visited[i][j] == 0 && mat[i][j] == 1) {
        //             return -1;
        //         }
        //     }
        // }

        // Step 3: If all fresh oranges could not rot, return -1
        if (cntRotten != cntFresh) return -1;

        return timeElapsed; // Return the time taken to rot all oranges
    }

    // Method 1 : Optimal Solution
    // Time Complexity: O ( n x n ) x 4
    // Reason: Worst-case - We will be making each fresh orange rotten in the grid and for each
    // rotten orange will check in 4 directions
    // Space Complexity: O ( n x n )
    // Reason: worst-case -  If all oranges are Rotten, we will end up pushing all rotten oranges
    // into the Queue data structure
    private static class Pair {
        int row;
        int col;
        int time;

        Pair(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}

// Intuition : Optimal Solution
/*
The problem requires us to determine the minimum time required to rot all fresh oranges in a grid,
given that rotten oranges can infect adjacent fresh oranges in four directions (up, right, down, left)
every minute.

This naturally suggests a Breadth-First Search (BFS) approach:
1. Multi-source BFS: Since multiple oranges can start as rotten, we process all rotten oranges
   at the same time (level by level).
2. Time Simulation: We keep track of the time elapsed as we propagate the rot.
3. Checking for Impossible Cases: If at the end, some fresh oranges remain uninfected, return -1.

Why BFS?
1. BFS ensures that all oranges at the same time level rot simultaneously, correctly simulating real-time
   infection spread.

2. DFS would go deep into one path before exploring others, which is inefficient for problems like this
   where all sources spread simultaneously.
Algorithm
1. Initialization:
    i. Find all rotten oranges (2) in the grid and add them to a queue with time = 0.
   ii. Keep track of the number of fresh oranges (cntFresh).
  iii. Create a visited array to prevent reprocessing of cells.

2. Breadth-First Search (BFS) Traversal:
    i. Process each rotten orange level by level:
        i. Remove the front element from the queue.
       ii. Check all four directions (up, right, down, left).
      iii. If a fresh orange (1) is found:
            i. Convert it into a rotten orange (2).
           ii. Add it to the queue with time + 1.
          iii. Mark it as visited.
           iv. Increase the count of newly rotten oranges (cntRotten).

3. Final Check:
    i. If all fresh oranges were rotted (cntRotten == cntFresh), return the total time taken.
   ii. Otherwise, return -1, meaning some fresh oranges were unreachable.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=yf3oUhkvqA0