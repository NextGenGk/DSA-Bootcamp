package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class Flood_Fill_Algorithm {

    // Method 1 : Optimal Solution (Using DFS Traversal)
    // Time Complexity: O(NxM + NxMx4) ~ O(N x M)
    // For the worst case, all of the pixels will have the same colour, so DFS function will be
    // called for (N x M) nodes and for every node we are traversing for 4 neighbours, so it will
    // take O(N x M x 4) time.
    // Space Complexity: O(N x M) + O(N x M)
    // O(N x M) for copied input array and recursive stack space takes up N x M locations at max.
    // Method to perform flood fill on a given image
    public static int[][] floodFillDFS(int[][] image, int sr, int sc, int newColor) {
        int iniColor = image[sr][sc]; // Store the initial color of the starting pixel
        int[][] result = image; // Use the same image array to store the modified result

        // Arrays to represent the four possible directions (up, right, down, left)
        int[] delRow = {-1, 0, +1, 0};
        int[] delCol = {0, +1, 0, -1};

        // Start the Depth-First Search (DFS) traversal from the given starting pixel
        dfs(image, result, sr, sc, iniColor, newColor, delRow, delCol);
        return result; // Return the modified image
    }

    // Helper method to perform DFS for flood fill
    private static void dfs(int[][] image, int[][] result, int sr, int sc,
                            int iniColor, int newColor, int[] delRow, int[] delCol) {
        int n = result.length; // Number of rows in the image
        int m = result[0].length; // Number of columns in the image

        // Change the color of the current pixel to the new color
        result[sr][sc] = newColor;

        // Traverse the four possible directions (up, right, down, left)
        for (int i = 0; i < 4; i++) {
            int nRow = sr + delRow[i]; // Compute new row index
            int nCol = sc + delCol[i]; // Compute new column index

            // Check if the new position is within the image boundaries
            // and if the pixel has the initial color and hasn't been changed to newColor yet
            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m &&
                    image[nRow][nCol] == iniColor && result[nRow][nCol] != newColor) {

                // Recursively call DFS on the neighboring pixel
                dfs(image, result, nRow, nCol, iniColor, newColor, delRow, delCol);
            }
        }
    }

    // Method 1 : Optimal Solution (Using BFS Traversal)
    // Time Complexity: O(NxM + NxMx4) ~ O(N x M)
    // For the worst case, all of the pixels will have the same colour, so BFS function will be
    // called for (N x M) nodes and for every node we are traversing for 4 neighbours, so it will
    // take O(N x M x 4) time.
    // Space Complexity: O(N x M)
    // Method to perform flood fill using BFS
    public static int[][] floodFillBFS(int[][] image, int sr, int sc, int newColor) {
        int iniColor = image[sr][sc]; // Store the initial color of the starting pixel
        if (iniColor == newColor) return image; // If the new color is the same as initial color, no change needed

        int n = image.length, m = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr, sc});

        // Define directions: up, right, down, left
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        // Change the starting pixel color
        image[sr][sc] = newColor;

        while (!queue.isEmpty()) {
            int[] pixel = queue.poll();
            int row = pixel[0], col = pixel[1];

            // Traverse all 4 possible directions
            for (int i = 0; i < 4; i++) {
                int nRow = row + delRow[i];
                int nCol = col + delCol[i];

                // Check if the new position is within the image boundaries
                // and has the initial color
                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && image[nRow][nCol] == iniColor) {
                    image[nRow][nCol] = newColor; // Change the color
                    queue.add(new int[]{nRow, nCol}); // Add the pixel to queue for processing
                }
            }
        }
        return image; // Return the modified image
    }

    // Main method to test the flood fill function
    public static void main(String[] args) {
        // Define a sample 2D image (grid)
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        // Define starting pixel coordinates and the new color
        int sr = 1, sc = 1, newColor = 2;

        // Perform flood fill operation
        int[][] result = floodFillBFS(image, sr, sc, newColor);
        int[][] result2 = floodFillDFS(image, sr, sc, newColor);

        // Print the modified image after flood fill
        for (int[] row : result) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}

// Output ;
/*
1 1 1
1 2 0
1 0 1
 */

// Intuition : Using DFS Traversal
/*
The Depth-First Search (DFS) approach to flood fill works like painting a region by going as
deep as possible in one direction before backtracking. Given a starting pixel (sr, sc), DFS
recursively explores its four neighbors (up, right, down, left), changing their color if they
match the original color.

DFS is best suited when the number of connected pixels isn't excessively large, as deep recursion
could lead to stack overflow in larger images.

Algorithm:
1. Store the initial color of the starting pixel.
2. If the new color is the same as the initial color, return the original image.
3. Initialize the DFS traversal with the starting pixel.
4. Define the four directions (up, right, down, left) to traverse neighboring pixels.
5. Change the color of the starting pixel.
6. Recursively call DFS on all connected pixels with the same initial color.
7. Return the modified image after flood fill.

Why DFS?
1. DFS goes deep first, filling an entire connected region before backtracking.
2. Uses recursion instead of a queue (BFS).
3. Less memory-intensive than BFS in some cases but can cause stack overflow for large images.
 */

// Intuition : Using BFS Traversal
/*
The flood fill algorithm is similar to the "bucket fill" tool in MS Paint. Given a starting
pixel (sr, sc), we change its color and propagate the change to all connected pixels of the
same original color.

We use Breadth-First Search (BFS) to ensure that we process all neighboring pixels level by level,
avoiding deep recursive calls (which happens in DFS).

Algorithm:
1. Store the initial color of the starting pixel.
2. If the new color is the same as the initial color, return the original image.
3. Initialize a queue and add the starting pixel to it.
4. Define the four directions (up, right, down, left) to traverse neighboring pixels.
5. Change the color of the starting pixel.
6. While the queue is not empty, process the front pixel.
7. Traverse all four directions and change the color of neighboring pixels.
8. Add the neighboring pixels to the queue for further processing.
9. Return the modified image after flood fill.

Why BFS?
1. BFS processes all pixels at the same level before moving deeper.
2. It avoids deep recursion, preventing stack overflow for large images.
3. Ensures that all connected pixels are filled correctly.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=C-2_uSRli8o