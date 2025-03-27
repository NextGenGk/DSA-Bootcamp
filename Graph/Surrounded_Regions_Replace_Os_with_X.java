package Graph;

public class Surrounded_Regions_Replace_Os_with_X {

    // Method 1 : Optimal Solution (Using DFS)
    // Time Complexity: O(N) + O(M) + O(NxMx4) ~ O(N x M), For the worst case, every element will
    // be marked as ‘O’ in the matrix, and the DFS function will be called for (N x M) nodes and
    // for every node, we are traversing for 4 neighbors, so it will take O(N x M x 4) time. Also,
    // we are running loops for boundary elements so it will take O(N) + O(M).
    // Space Complexity ~ O(N x M), O(N x M) for the visited array, and auxiliary stack space takes
    // up N x M locations at max.
    // DFS function to mark 'O' cells connected to the boundary
    static void dfs(int row, int col, int[][] visited, char[][] mat, int[] delRow, int[] delCol) {
        int numRows = mat.length;
        int numCols = mat[0].length;
        visited[row][col] = 1;

        // Explore all four possible directions
        for (int i = 0; i < 4; i++) {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];

            // Check boundary conditions and continue DFS for unvisited 'O' cells
            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols &&
                    visited[newRow][newCol] == 0 && mat[newRow][newCol] == 'O') {
                dfs(newRow, newCol, visited, mat, delRow, delCol);
            }
        }
    }

    // Function to replace all surrounded 'O' regions with 'X'
    static char[][] fill(int numRows, int numCols, char[][] mat) {
        // Arrays to move in the four directions: Up, Left, Down, Right
        int[] delRow = {-1, 0, +1, 0};
        int[] delCol = {0, -1, 0, +1};

        // Visited matrix to track boundary-connected 'O's
        int[][] visited = new int[numRows][numCols];

        // Traverse the first and last row, marking all boundary-connected 'O's
        for (int col = 0; col < numCols; col++) {
            if (visited[0][col] == 0 && mat[0][col] == 'O') {
                dfs(0, col, visited, mat, delRow, delCol);
            }
            if (visited[numRows - 1][col] == 0 && mat[numRows - 1][col] == 'O') {
                dfs(numRows - 1, col, visited, mat, delRow, delCol);
            }
        }

        // Traverse the first and last column, marking all boundary-connected 'O's
        for (int row = 0; row < numRows; row++) {
            if (visited[row][0] == 0 && mat[row][0] == 'O') {
                dfs(row, 0, visited, mat, delRow, delCol);
            }
            if (visited[row][numCols - 1] == 0 && mat[row][numCols - 1] == 'O') {
                dfs(row, numCols - 1, visited, mat, delRow, delCol);
            }
        }

        // Replace all remaining unvisited 'O's with 'X'
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (visited[i][j] == 0 && mat[i][j] == 'O') {
                    mat[i][j] = 'X';
                }
            }
        }

        return mat;
    }

    // Main Function
    public static void main(String[] args) {
        // Input matrix
        char mat[][] = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'O'}
        };

        // Number of rows and columns
        int numRows = 5, numCols = 4;

        // Call fill function to replace surrounded regions
        char[][] result = fill(numRows, numCols, mat);

        // Print the final matrix
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Output :
/*
X X X X
X X X X
X X X X
X X X X
X X O O
 */

// Intuition : Optimal Solution
/*
Intuition
The problem requires us to replace all 'O's that are not connected to the boundary with 'X'.
The 'O's that are connected to the boundary (directly or indirectly) must remain unchanged.
This means we need to identify the regions of 'O' that are surrounded by 'X' and convert them.

We use Depth-First Search (DFS) to traverse and mark all 'O's that are connected to the boundary.
Once we have identified these, we iterate through the matrix again and replace the remaining 'O's with 'X'.

Algorithm :
1. Initialize Directions:
    - Define movement arrays delRow and delCol to traverse in the four possible directions
      (up, left, down, right).

2. Mark Boundary-Connected 'O's:
    - Use DFS from the first and last row to mark all 'O's that are connected to the boundary.
    - Use DFS from the first and last column to mark all 'O's that are connected to the boundary.

3. Convert Surrounded Regions:
    - Iterate through the matrix.
    - If an 'O' is not visited (meaning it is surrounded), convert it to 'X'.

4. Print the Final Matrix:
    - Print the transformed matrix after replacing the surrounded 'O's with 'X'.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=BtdgAys4yMk
