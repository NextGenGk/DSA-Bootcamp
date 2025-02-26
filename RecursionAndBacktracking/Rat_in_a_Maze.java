package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;

public class Rat_in_a_Maze {

    // Method 1 : Optimal Solution (Without Time/Space Optimization)
    // Time Complexity: O(4^(n^2)). At every cell, we have 4 choices to move to the next cell.
    // So, the time complexity is O(4^(n^2)).
    // Space Complexity: O(n^2) for the visited array and O(4^(n^2)) for the recursive stack.
    // Recursive function to find all paths from (i, j) to (n-1, n-1)
    private static void solve(int i, int j, int[][] a, int n, ArrayList<String> ans, String move, int[][] vis) {
        // Base case: If we reach the bottom-right corner of the grid
        if (i == n - 1 && j == n - 1) {
            ans.add(move); // Add the current path to the answer list
            return;
        }

        // Move downward
        if (i + 1 < n && vis[i + 1][j] == 0 && a[i + 1][j] == 1) {
            vis[i][j] = 1; // Mark the cell as visited
            solve(i + 1, j, a, n, ans, move + 'D', vis); // Recur for the next cell
            vis[i][j] = 0; // Backtrack: unmark the cell
        }

        // Move left
        if (j - 1 >= 0 && vis[i][j - 1] == 0 && a[i][j - 1] == 1) {
            vis[i][j] = 1; // Mark the cell as visited
            solve(i, j - 1, a, n, ans, move + 'L', vis); // Recur for the next cell
            vis[i][j] = 0; // Backtrack: unmark the cell
        }

        // Move right
        if (j + 1 < n && vis[i][j + 1] == 0 && a[i][j + 1] == 1) {
            vis[i][j] = 1; // Mark the cell as visited
            solve(i, j + 1, a, n, ans, move + 'R', vis); // Recur for the next cell
            vis[i][j] = 0; // Backtrack: unmark the cell
        }

        // Move upward
        if (i - 1 >= 0 && vis[i - 1][j] == 0 && a[i - 1][j] == 1) {
            vis[i][j] = 1; // Mark the cell as visited
            solve(i - 1, j, a, n, ans, move + 'U', vis); // Recur for the next cell
            vis[i][j] = 0; // Backtrack: unmark the cell
        }
    }

    // Function to find all paths in a grid from (0, 0) to (n-1, n-1)
    public static ArrayList<String> findPath(int[][] m, int n) {
        int vis[][] = new int[n][n]; // Visited array to keep track of visited cells
        // Initialize the visited array to 0 (not visited)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = 0;
            }
        }

        ArrayList<String> ans = new ArrayList<>(); // List to store all valid paths
        // Start the recursive search from the top-left corner if it's valid
        if (m[0][0] == 1) solve(0, 0, m, n, ans, "", vis);
        return ans; // Return the list of paths found
    }

    // Method 2 : Optimal Solution (With Time Optimization)
    // Time Complexity: O(4^(n^2)). At every cell, we have 4 choices to move to the next cell.
    // So, the time complexity is O(4^(n^2)).
    // Space Complexity: O(n^2) for the visited array and O(4^(n^2)) for the recursive stack.
    static void solveTimeOptimize(int row, int col, int[][] mat, int n,
                                  ArrayList<String> ans, String path, boolean[][] visited) {
        // Boundary conditions: check if the current cell is out of bounds, blocked, or already visited
        if (row < 0 || col < 0 || row >= n || col >= n || mat[row][col] == 0 || visited[row][col]) {
            return;
        }

        // Base case: If we reach the bottom-right corner of the grid, add the current path to the answer list
        if (row == n - 1 && col == n - 1) {
            ans.add(path);
            return;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        // Move downward and recur
        solveTimeOptimize(row + 1, col, mat, n, ans, path + "D", visited);
        // Move left and recur
        solveTimeOptimize(row, col - 1, mat, n, ans, path + "L", visited);
        // Move right and recur
        solveTimeOptimize(row, col + 1, mat, n, ans, path + "R", visited);
        // Move upward and recur
        solveTimeOptimize(row - 1, col, mat, n, ans, path + "U", visited);

        // Backtrack: unmark the current cell as visited
        visited[row][col] = false;
    }

    // Function to find all paths in a grid from (0, 0) to (n-1, n-1)
    public static ArrayList<String> findPathTimeOptimize(int[][] m, int n) {
        // Create a visited array to keep track of visited cells
        boolean[][] vis = new boolean[n][n];
        // Initialize the visited array to false (not visited)
        for (int i = 0; i < n; i++) {
            Arrays.fill(vis[i], false);
        }

        // List to store all valid paths
        ArrayList<String> ans = new ArrayList<>();
        // Start the recursive search from the top-left corner if it's valid
        if (m[0][0] == 1) solveTimeOptimize(0, 0, m, n, ans, "", vis);
        // Return the list of paths found
        return ans;
    }

    // Method 3 : Optimal Solution (With Space Optimization)
    // Time Complexity: O(4^(n^2)). At every cell, we have 4 choices to move to the next cell.
    // So, the time complexity is O(4^(n^2)).
    // Space Complexity: (O(n^2)) for the grid and (O(n^2)) for the recursion stack, resulting
    // in a total space complexity of (O(n^2)).
    // Space Optimization
    static void solveSpaceOptimize(int row, int col, int[][] mat, int n,
                                   ArrayList<String> ans, String path) {
        // Boundary conditions: check if the current cell is out of bounds, blocked, or already visited
        if (row < 0 || col < 0 || row >= n || col >= n || mat[row][col] == 0 || mat[row][col] == -1) {
            return;
        }

        // Base case: If we reach the bottom-right corner of the grid, add the current path to the answer list
        if (row == n - 1 && col == n - 1) {
            ans.add(path);
            return;
        }

        // Mark the current cell as visited by setting it to -1
        mat[row][col] = -1;

        // Move downward and recur
        solveSpaceOptimize(row + 1, col, mat, n, ans, path + "D");
        // Move left and recur
        solveSpaceOptimize(row, col - 1, mat, n, ans, path + "L");
        // Move right and recur
        solveSpaceOptimize(row, col + 1, mat, n, ans, path + "R");
        // Move upward and recur
        solveSpaceOptimize(row - 1, col, mat, n, ans, path + "U");

        // Backtrack: unmark the current cell by setting it back to 1
        mat[row][col] = 1;
    }

    // Function to find all paths in a grid from (0, 0) to (n-1, n-1)
    public static ArrayList<String> findPathSpaceOptimize(int[][] m, int n) {
        // List to store all valid paths
        ArrayList<String> ans = new ArrayList<>();
        // Start the recursive search from the top-left corner if it's valid
        if (m[0][0] == 1) solveSpaceOptimize(0, 0, m, n, ans, "");
        // Return the list of paths found
        return ans;
    }

    // Main Function
    public static void main(String[] args) {
        // Input grid
        int n = 4;
        int[][] a = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };

        // Find all paths from (0, 0) to (n-1, n-1)
        ArrayList<String> res = findPathSpaceOptimize(a, n);
        // Print the paths found
        if (res.size() > 0) {
            for (int i = 0; i < res.size(); i++)
                System.out.print(res.get(i) + " ");
            System.out.println();
        } else {
            System.out.println(-1);
        }
    }
}

// Output :
// DDRDRR DRDDRR

// Intuition : Optimal Solution (Without Time/Space Optimization)
/*
Intuition:
The best way to solve such problems is using recursion.

Approach:
1. Start at the source(0,0) with an empty string and try every possible path i.e upwards(U),
   downwards(D), leftwards(L) and rightwards(R).
2. As the answer should be in lexicographical order so it's better to try the directions in
   lexicographical order i.e (D,L,R,U)
3. Declare a 2D-array named visited because the question states that a single cell should be
   included only once in the path,so it's important to keep track of the visited cells in a particular path.
4. If a cell is in path, mark it in the visited array.
5. Also keep a check of the “out of bound” conditions while going in a particular direction
   in the matrix.
6. Whenever you reach the destination(n,n) it's very important to get back as shown in the recursion tree.
7. While getting back, keep on unmarking the visited array for the respective direction.
   Also check whether there is a different path possible while getting back and if yes, then mark
   that cell in the visited array.
 */

// Intuition : Optimal Solution (Time Optimization)
/*
### Intuition :

The given code solves the "Rat in a Maze" problem using recursion and backtracking. The goal
is to find all possible paths from the top-left corner (0, 0) to the bottom-right corner
(n-1, n-1) of a grid, where some cells are blocked. The rat can move in four directions: down,
left, right, and up. The code ensures that the rat does not revisit any cell in the same path.

### Algorithm :

1. **Initialization**:
   - Create a visited array to keep track of visited cells.
   - Initialize the visited array to `false` (not visited).

2. **Recursive Function (`solveTimeOptimize`)**:
   - **Boundary Conditions**: Check if the current cell is out of bounds, blocked, or already visited.
     If any of these conditions are met, return.
   - **Base Case**: If the current cell is the bottom-right corner, add the current path to the
     answer list and return.
   - **Mark the Current Cell as Visited**: Set the current cell in the visited array to `true`.
   - **Move in Four Directions**: Recur for the next cell in each of the four possible directions
     (down, left, right, up), appending the corresponding direction character to the path.
   - **Backtrack**: Unmark the current cell as visited before returning to explore other paths.

3. **Main Function (`findPathTimeOptimize`)**:
   - Initialize the visited array.
   - Start the recursive search from the top-left corner if it is not blocked.
   - Return the list of paths found.

### Edge Case Example

Consider the following edge case where the start or end cell is blocked:

int n = 4;
int[][] a = {
    {0, 0, 0, 0},
    {1, 1, 0, 1},
    {1, 1, 0, 0},
    {0, 1, 1, 1}
};

In this case, the start cell (0, 0) is blocked, so no paths can be found. The function will
return an empty list.
 */

// Intuition : Optimal Solution (With Space Optimization)
/*
### Intuition
The "Rat in a Maze" problem involves finding all possible paths from the top-left corner to the
bottom-right corner of a grid, where some cells are blocked. The rat can move in four directions:
down, left, right, and up. The solution uses recursion and backtracking to explore all possible
paths while ensuring that no cell is visited more than once in a single path. This version of the
solution optimizes space by marking cells directly in the grid instead of using a separate visited array.

### Algorithm
1. **Initialization**:
   - Create a list to store all valid paths.

2. **Recursive Function (`solveSpaceOptimize`)**:
   - **Boundary Conditions**: Check if the current cell is out of bounds, blocked, or already visited.
       If any of these conditions are met, return.
   - **Base Case**: If the current cell is the bottom-right corner, add the current path to the answer
       list and return.
   - **Mark the Current Cell as Visited**: Set the current cell in the matrix to `-1`.
   - **Move in Four Directions**: Recur for the next cell in each of the four possible directions
       (down, left, right, up), appending the corresponding direction character to the path.
   - **Backtrack**: Unmark the current cell by setting it back to `1` before returning to explore other paths.

3. **Main Function (`findPathSpaceOptimize`)**:
   - Start the recursive search from the top-left corner if it is not blocked.
   - Return the list of paths found.
 */

// Striver's (Video Explanation) :
// https://youtu.be/bLGZhJlt4y0?si=C6cY4Je-vdQwmVfI

// Apna College (Video Explanation) : (With Space Optimization) :
// https://youtu.be/D8Yze9CDDAw?si=wwYDOaZHxAIcJ9Y6
