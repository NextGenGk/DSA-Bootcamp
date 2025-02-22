package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N_Queens {

    // Method 1 : Brute Force
    // Time Complexity: Exponential in nature since we are trying out all ways, to be precise its O(N! * N).
    // Space Complexity: O( N2 )
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

        row = duprow;
        col = dupcol;
        // Check left side in the same row
        while (col >= 0) {
            if (board[row][col] == 'Q') return false;
            col--;
        }

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

    // Main function to test the solution
    public static void main(String[] args) {
        int n = 4; // Change n to test for different board sizes
        List<List<String>> solutions = solveNQueens(n);

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