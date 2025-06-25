package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N_Queens {

    // Method 1 : Brute Force
    // Time Complexity: Exponential in nature since we are trying out all ways, to be precise its O(N! * N).
    // Space Complexity: O(N^2)
    // Function to check if it's safe to place a queen at board[row][col]
    public static boolean isSafe(int row, int col, char[][] board, int n) {
        int duprow = row;
        int dupcol = col;

        // Check upper diagonal (left direction)
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row--;
            col--;
        }

        // You assign row = duprow; col = dupcol; every time to restore the original cell 
        // coordinates before checking each new direction. This ensures each direction check 
        // starts from the correct position on the board.
        row = duprow;
        col = dupcol;
        // Check left side in the same row
        while (col >= 0) {
            if (board[row][col] == 'Q') return false;
            col--;
        }

        // You assign row = duprow; col = dupcol; every time to restore the original cell 
        // coordinates before checking each new direction. This ensures each direction check 
        // starts from the correct position on the board.
        row = duprow;
        col = dupcol;
        // Check lower diagonal (left direction)
        while (row < n && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row++;
            col--;
        }
        return true; // If no conflicts, it's safe to place the queen
    }

    // Recursive function to solve N-Queens using backtracking
    public static void solve(int col, char[][] board, List<List<String>> ans, int n) {
        // Base case: If all queens are placed, add the solution to the answer list
        if (col == n) {
            ans.add(construct(board)); // Convert board state to list format
            return;
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < n; row++) {
            if (isSafe(row, col, board, n)) { // Check if it's safe to place a queen
                board[row][col] = 'Q'; // Place the queen
                solve(col + 1, board, ans, n); // Recur for the next column
                board[row][col] = '.'; // Backtrack (remove the queen)
            }
        }
    }

    // Function to find all valid placements of N queens
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>(); // Stores all valid solutions
        char[][] board = new char[n][n]; // Create an empty board

        // Initialize board with empty spaces ('.')
        for (char[] row : board) Arrays.fill(row, '.');

        // Start solving from the first column
        solve(0, board, ans, n);
        return ans;
    }

    // Function to convert the board into a list of strings
    private static List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] row : board) {
            res.add(new String(row)); // Convert char array to string
        }
        return res;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N!) Exponential in nature since we are trying out all ways
    // Space Complexity : O(N)
    // Function to find all possible solutions to the N-Queens problem
    public static List<List<String>> solveNQueensOptimal(int n) {
        char[][] board = new char[n][n]; // Create an NxN chessboard

        // Initialize the board with empty spaces ('.')
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';

        List<List<String>> res = new ArrayList<>(); // Stores all valid solutions

        // Arrays to optimize queen placement checks
        int[] leftRow = new int[n]; // Tracks occupied rows
        int[] upperDiagonal = new int[2 * n - 1]; // Tracks occupied upper diagonals
        int[] lowerDiagonal = new int[2 * n - 1]; // Tracks occupied lower diagonals

        // Start solving from the first column
        solve(0, board, res, leftRow, lowerDiagonal, upperDiagonal);
        return res;
    }

    // Recursive function to solve N-Queens using backtracking and optimization
    static void solve(int col, char[][] board, List<List<String>> res, int[] leftRow,
                      int[] lowerDiagonal, int[] upperDiagonal) {
        // Base case: If all queens are placed, add the solution to the answer list
        if (col == board.length) {
            res.add(constructOptimal(board)); // Convert board state to list format
            return;
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < board.length; row++) {
            // Check if the row, upper diagonal, and lower diagonal are free
            if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 &&
                    upperDiagonal[board.length - 1 + col - row] == 0) {
                // Place the queen
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[board.length - 1 + col - row] = 1;

                // Recur for the next column
                solve(col + 1, board, res, leftRow, lowerDiagonal, upperDiagonal);

                // Backtrack (remove the queen)
                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[board.length - 1 + col - row] = 0;
            }
        }
    }

    // Function to convert the board into a list of strings
    static List<String> constructOptimal(char[][] board) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            res.add(new String(board[i])); // Convert char array to string and add to list
        }
        return res;
    }


    // Main Function
    public static void main(String[] args) {
        int n = 4; // Change n to test for different board sizes
        List<List<String>> solutions = solveNQueensOptimal(n);

        // Print all valid board configurations
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println(); // Separate different solutions
        }
    }
}

// Output :
/*
..Q.
Q...
...Q
.Q..

.Q..
...Q
Q...
..Q.
 */

// Intuition : Brute Force
/*
Intuition :
The N-Queens problem is a classic backtracking problem where the goal is to place N queens
on an N×N chessboard such that no two queens threaten each other. This means that no two queens
can be in the same row, column, or diagonal.

Algorithm :
1. **Initialization**:
   - Create an empty board of size N×N initialized with '.' to represent empty spaces.
   - Create a list to store all valid solutions.

2. **Recursive Function (`solve`)**:
   - Base Case: If all queens are placed (i.e., `col == n`), convert the board to a list of
     strings and add it to the solutions list.
   - For each row in the current column:
     - Check if placing a queen at `board[row][col]` is safe using the `isSafe` function.
     - If it is safe, place the queen (`board[row][col] = 'Q'`).
     - Recur to place the rest of the queens in the next columns.
     - Backtrack by removing the queen (`board[row][col] = '.'`).

3. **Safety Check (`isSafe`)**:
   - Check the upper diagonal on the left side.
   - Check the left side in the same row.
   - Check the lower diagonal on the left side.
   - If no conflicts are found, it is safe to place the queen.

4. **Constructing the Solution**:
   - Convert the board state to a list of strings to represent the solution.

5. **Main Function**:
   - Initialize the board and call the recursive function starting from the first column.
   - Print all valid solutions.
 */

// Intuition : Optimal Solution
/*
Intuition :
The N-Queens problem is a classic backtracking problem where the goal is to place N queens
on an N×N chessboard such that no two queens threaten each other. This means that no two queens
can be in the same row, column, or diagonal. The optimal solution uses additional arrays to
keep track of the occupied rows and diagonals, which helps in reducing the time complexity by
avoiding redundant checks.

Algorithm :
1. **Initialization**:
   - Create an empty board of size N×N initialized with '.' to represent empty spaces.
   - Create a list to store all valid solutions.
   - Create arrays to track occupied rows and diagonals.

2. **Recursive Function (`solve`)**:
   - Base Case: If all queens are placed (i.e., `col == n`), convert the board to a list of
     strings and add it to the solutions list.
   - For each row in the current column:
     - Check if placing a queen at `board[row][col]` is safe using the tracking arrays.
     - If it is safe, place the queen and update the tracking arrays.
     - Recur to place the rest of the queens in the next columns.
     - Backtrack by removing the queen and updating the tracking arrays.

3. **Safety Check**:
   - Use the tracking arrays to check if the current row and diagonals are free.

4. **Constructing the Solution**:
   - Convert the board state to a list of strings to represent the solution.

5. **Main Function**:
   - Initialize the board and call the recursive function starting from the first column.
   - Return all valid solutions.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=i05Ju7AftcM
