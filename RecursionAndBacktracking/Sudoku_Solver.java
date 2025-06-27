package RecursionAndBacktracking;

public class Sudoku_Solver {

    // Method 1 : Optimal Solution
    // Time Complexity: O(9(n ^ 2)), in the worst case, for each cell in the n2 board,
    // we have 9 possible numbers.
    // Space Complexity: O(1), since we are refilling the given board itself, there is no
    // extra space required, so constant space complexity.
    public static void solveSudoku(char[][] board) {
        // Calls the recursive function to solve the Sudoku board
        solve(board);
    }

    public static boolean solve(char[][] board) {
        // Traverse the board to find an empty cell (represented by '.')
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    // Try placing digits '1' to '9' in the empty cell
                    for (char c = '1'; c <= '9'; c++) {
                        // Check if placing 'c' in board[i][j] is valid
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // Place the digit

                            // Recursively attempt to solve the rest of the board
                            if (solve(board)) {
                                return true; // If successful, return true
                            } else {
                                board[i][j] = '.'; // Backtrack if placing 'c' leads to an invalid state
                            }
                        }
                    }
                    return false; // If no number can be placed, return false (backtracking)
                }
            }
        }
        return true; // Return true when the board is completely solved
    }

    // Function to check a character is valid at given index or not
    public static boolean isValid(char[][] board, int row, int col, char c) {
        // Check the current row, column, and 3x3 subgrid for conflicts
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false; // Check if 'c' already exists in the row
            if (board[i][col] == c) return false; // Check if 'c' already exists in the column
            // Check if 'c' already exists in the corresponding 3x3 grid
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true; // Return true if 'c' can be placed at board[row][col]
    }

    // Main Function
    public static void main(String[] args) {
        char[][] board = {
                {'9', '5', '7', '.', '1', '3', '.', '8', '4'},
                {'4', '8', '3', '.', '5', '7', '1', '.', '6'},
                {'.', '1', '2', '.', '4', '9', '5', '3', '7'},
                {'1', '7', '.', '3', '.', '4', '9', '.', '2'},
                {'5', '.', '4', '9', '7', '.', '3', '6', '.'},
                {'3', '.', '9', '5', '.', '8', '7', '.', '1'},
                {'8', '4', '5', '7', '9', '.', '6', '1', '3'},
                {'.', '9', '1', '.', '3', '6', '.', '7', '5'},
                {'7', '.', '6', '1', '8', '5', '4', '.', '9'}
        };
        solveSudoku(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(board[i][j] + "  ");
            System.out.println();
        }
    }
}

// Output :
/*
9  5  7  6  1  3  2  8  4
4  8  3  2  5  7  1  9  6
6  1  2  8  4  9  5  3  7
1  7  8  3  6  4  9  5  2
5  2  4  9  7  1  3  6  8
3  6  9  5  2  8  7  4  1
8  4  5  7  9  2  6  1  3
2  9  1  4  3  6  8  7  5
7  3  6  1  8  5  4  2  9
 */

// Intuition : Optimal Solution
/*
Intuition
The problem of solving a Sudoku puzzle can be approached using backtracking, a depth-first search
(DFS)-based technique for exploring possible solutions systematically.

We start by identifying empty cells (denoted as '.') and try placing numbers ('1' to '9') in those
cells while ensuring the validity of the placement. If a number is placed successfully, we proceed
recursively to solve the rest of the board. If we encounter a situation where no valid number can
be placed, we backtrack by undoing the previous placement and trying another possibility.

The key to making the approach efficient is the isValid() function, which quickly determines
whether a number can be placed in a given cell by checking row, column, and 3×3 subgrid constraints.

Algorithm
The algorithm follows a recursive backtracking approach:

Step 1: Identify Empty Cells
    1. Iterate through the board to find the first empty cell ('.').
Step 2: Try Filling the Empty Cell
    1. For each empty cell, attempt placing numbers from '1' to '9'.
    2. Use the isValid() function to check if the placement is allowed:
        1. The number must not be present in the same row.
        2. The number must not be present in the same column.
        3. The number must not be present in the same 3×3 subgrid.
Step 3: Recursive Exploration
    1. If the placement is valid, set board[i][j] = c and recursively attempt to solve the rest of the board.
    2. If a valid solution is found, return true.
    3. If no valid number can be placed, backtrack by resetting board[i][j] = '.' and trying the next number.
Step 4: Base Case
    1. If all cells are filled (i.e., no empty cell is found), the board is solved, and we return true.


Input Board (Partially Filled Sudoku)

5 3 . | . 7 . | . . .
6 . . | 1 9 5 | . . .
. 9 8 | . . . | . 6 .
---------------------
8 . . | . 6 . | . . 3
4 . . | 8 . 3 | . . 1
7 . . | . 2 . | . . 6
---------------------
. 6 . | . . . | 2 8 .
. . . | 4 1 9 | . . 5
. . . | . 8 . | . 7 9

Processing Steps
1. Find the first empty cell (row 0, column 2).
2. Try placing numbers '1' to '9' while checking validity.
3. Suppose '4' is a valid choice; place '4' and move to the next empty cell.
4. Recursively fill remaining empty cells.
5. If at any point no valid number can be placed, backtrack by removing the last placed number
   and trying the next possibility.
6. Eventually, all cells are filled correctly, and the function returns true.
 */

// Striver's (Video Explanation) : https://youtu.be/FWAIf_EVUKE?si=oZSqnzFWSy2Pvaq2
