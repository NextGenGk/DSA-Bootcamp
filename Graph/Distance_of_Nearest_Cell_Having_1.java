package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class Distance_of_Nearest_Cell_Having_1 {

    // Method 1 : Optimal Solution (Using BFS)
    // Time Complexity: O(NxM + NxMx4) ~ O(N x M)
    // For the worst case, the BFS function will be called for (N x M) nodes, and for every node,
    // we are traversing for 4 neighbors, so it will take O(N x M x 4) time.
    // Space Complexity: O(N x M) + O(N x M) + O(N x M) ~ O(N x M)
    // O(N x M) for the visited array, distance matrix, and queue space takes up N x M locations at max.
    // Helper class to store row, column, and distance
    class Cell {
        int row;
        int col;
        int distance;

        // Constructor to initialize a cell object
        Cell(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    // Function to find the distance of the nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid) {
        int rows = grid.length;  // Number of rows in the grid
        int cols = grid[0].length;  // Number of columns in the grid

        Queue<Cell> queue = new LinkedList<>();  // Queue to perform BFS
        int[][] visited = new int[rows][cols];   // Visited array to track processed cells
        int[][] distance = new int[rows][cols];  // Distance array to store results

        // Iterate through the grid and add all '1' cells to the queue as starting points
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new Cell(i, j, 0));  // Add the 1-cell with distance 0
                    visited[i][j] = 1;  // Mark this cell as visited
                } else {
                    visited[i][j] = 0;  // Mark 0-cells as unvisited
                }
            }
        }

        // Direction vectors for moving up, right, down, and left
        int[] rowDirection = {-1, 0, +1, 0};
        int[] colDirection = {0, +1, 0, -1};

        // Perform BFS traversal
        while (!queue.isEmpty()) {
            Cell current = queue.poll();  // Get the front cell from the queue
            int row = current.row;
            int col = current.col;
            int steps = current.distance;

            distance[row][col] = steps;  // Store the distance

            // Traverse all 4 possible directions
            for (int i = 0; i < 4; i++) {
                int newRow = row + rowDirection[i];
                int newCol = col + colDirection[i];

                // Check for valid unvisited cells
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && visited[newRow][newCol] == 0) {
                    queue.add(new Cell(newRow, newCol, steps + 1));  // Add the new cell with incremented distance
                    visited[newRow][newCol] = 1;  // Mark as visited
                }
            }
        }
        return distance;  // Return the computed distance matrix
    }

    // Main Function
    public static void main(String[] args) {
        Distance_of_Nearest_Cell_Having_1 solution = new Distance_of_Nearest_Cell_Having_1();

        // Test Case 1
        int[][] grid1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] result1 = solution.nearest(grid1);
        System.out.println("Test Case 1:");
        for (int[] row : result1) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        // Test Case 2
        int[][] grid2 = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] result2 = solution.nearest(grid2);
        System.out.println("\nTest Case 2:");
        for (int[] row : result2) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}

// Output :
/*
Test Case 1:
2 1 2
1 0 1
2 1 2

Test Case 2:
2 1 2
1 0 1
0 0 0
 */

// Intuition : Optimal Solution
/*
Intuition Behind the Code
The goal is to find the shortest distance of each cell from the nearest cell containing 1.
This is a classic multi-source shortest path problem and can be efficiently solved using
Breadth-First Search (BFS).

Key Observations:
1. Why BFS?
- BFS is ideal for shortest path problems in an unweighted grid, as it explores all neighbors
  at the current distance before moving to the next level.
- Unlike DFS (which can go deep before backtracking), BFS guarantees that we process the nearest 1 first.

2. Multi-Source BFS
- Instead of starting from one source, we start from all cells containing 1 simultaneously.
T- his allows us to expand in all directions evenly, ensuring optimal distance calculations.

Algorithm
1. Initialize the Queue and Visited Matrix:
    - Create a queue for BFS traversal.
    - Create a visited matrix to track processed cells.
    - Create a distance matrix to store results.
2. Enqueue All 1s with Distance 0:
    - Traverse the entire grid and enqueue all cells containing 1 with an initial distance of 0.
    - Mark these cells as visited.
3. Perform BFS Traversal:
    - Dequeue a cell from the queue.
    - Update the distance matrix with the current step count.
    - Explore all 4 possible directions (up, down, left, right).
    - If a neighboring cell is unvisited and within bounds:
        - Enqueue it with an incremented distance.
        - Mark it as visited to prevent duplicate processing.
4. Return the Distance Matrix:
    - Once the queue is empty, return the updated distance matrix.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=edXdVwkYHF8