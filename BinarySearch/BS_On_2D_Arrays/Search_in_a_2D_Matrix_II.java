package BinarySearch.BS_On_2D_Arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class Search_in_a_2D_Matrix_II {

    // Method 1 : Brute Force
    // Time : O(N X M), where N = given row number, M = given column number.
    // Reason: In order to traverse the matrix, we are using nested loops running for n and m times respectively.
    // Space : O(1) as we are not using any extra space.
    public static boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
        int n = matrix.size(), m = matrix.get(0).size();

        // traverse the matrix:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == target)
                    return true;
            }
        }
        return false;
    }

    // Method 2 : Better Solution
    // Time : O(N + logM), where N = given row number, M = given column number.
    // Reason: We are traversing all rows and it takes O(N) time complexity. But for all rows, we are not applying
    // binary search rather we are only applying it once for a particular row. That is why the
    // time complexity is O(N + logM) instead of O(N*logM).
    // Space : O(1) as we are not using any extra space.
    // Search in a 2D matrix Function
    public static boolean searchMatrixI(ArrayList<ArrayList<Integer>> matrix, int target) {
        int n = matrix.size();
        int m = matrix.get(0).size();

        for (int i = 0; i < n; i++) {
            if (matrix.get(i).get(0) <= target && target <= matrix.get(i).get(m - 1)) {
                return binarySearch(matrix.get(i), target);
            }
        }
        return false;
    }

    // Simple Binary Search
    public static boolean binarySearch(ArrayList<Integer> nums, int target) {
        int n = nums.size(); //size of the array
        int low = 0, high = n - 1;

        // Perform the steps:
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums.get(mid) == target) return true;
            else if (target > nums.get(mid)) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    // Method 3 : Optimal Solution
    // Time : O(log(NxM)), where N = given row number, M = given column number.
    // Reason: We are applying binary search on the imaginary 1D array of size NxM.
    // Space : O(1) as we are not using any extra space.
    public static boolean searchMatrixII(ArrayList<ArrayList<Integer>> matrix, int target) {
        int n = matrix.size();
        int m = matrix.get(0).size();
        int row = 0, col = m - 1;

        //traverse the matrix from (0, m-1):
        while (row < n && col >= 0) {
            if (matrix.get(row).get(col) == target) return true;
            else if (matrix.get(row).get(col) < target) row++;
            else col--;
        }
        return false;
    }

    // Main Function
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        matrix.add(new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        matrix.add(new ArrayList<>(Arrays.asList(9, 10, 11, 12)));

        boolean result = searchMatrixII(matrix, 8);
        System.out.println(result ? "true" : "false");
    }
}

// Examples :
/*
Problem Statement: You have been given a 2-D array ‘mat’ of size ‘N x M’ where ‘N’ and ‘M’ denote the number of
rows and columns, respectively. The elements of each row are sorted in non-decreasing order. Moreover, the first
element of a row is greater than the last element of the previous row (if it exists). You are given an integer ‘target’,
and your task is to find if it exists in the given ‘mat’ or not.
Example 1:
Input Format: N = 3, M = 4, target = 8,
mat[] =
1 2 3 4
5 6 7 8
9 10 11 12
Result: true
Explanation: The ‘target’  = 8 exists in the 'mat' at index (1, 3).

Example 2:
Input Format: N = 3, M = 3, target = 78,
mat[] =
1 2 4
6 7 8
9 10 34
Result: false
Explanation: The ‘target' = 78 does not exist in the 'mat'. Therefore in the output, we see 'false'.
 */

// Algorithm : Brute Force
/*
Algorithm / Intuition
The extremely naive approach is to get the answer by checking all the elements of the given matrix. So,
we will traverse the matrix and check every element if it is equal to the given ‘target’.

Algorithm:
1. We will use a loop(say i) to select a particular row at a time.
2. Next, for every row, we will use another loop(say j) to traverse each column.
3. Inside the loops, we will check if the element i.e. matrix[i][j] is equal to the ‘target’. If we
find any matching element, we will return true.
4. Otherwise, after completing the traversal, we will return false.
 */

// Algorithm : Better Solution
/*
Algorithm / Intuition
We are going to use the Binary Search algorithm to optimize the approach.

The primary objective of the Binary Search algorithm is to efficiently determine the appropriate
half to eliminate, thereby reducing the search space by half. It does this by determining a
specific condition that ensures that the target is not present in that half.

The question specifies that each row in the given matrix is sorted. Therefore, to determine if
the target is present in a specific row, we don’t need to search column by column. Instead,
we can efficiently use the binary search algorithm.

To make the time complexity even better, we won’t use binary search on every row. We’ll focus
only on the particular row where the target might be located.

How to check if a specific row is containing the target:

If the target lies between the first and last element of the row, i
(i.e. matrix[i][0] <= target && target <= matrix[i][m-1]), we can conclude that the target
might be present in that specific row.

Once we locate the potentially relevant row containing the ‘target’, we need to confirm its presence.
To accomplish this, we will utilize the Binary search algorithm, effectively reducing the time complexity.

Algorithm:

1. We will use a loop(say i) to select a particular row at a time.
2. Next, for every row, i, we will check if it contains the target.
    1.  If matrix[i][0] <= target && target <= matrix[i][m-1]: If this condition is met, we can conclude
        that row i has the possibility of containing the target.
        So, we will apply binary search on row i, and check if the ‘target’ is present. If it is present,
        we will return true from this step. Otherwise, we will return false.
3. Otherwise, after completing the traversal, we will return false.
 */

// Algorithm : Optimal Solution
/*
Algorithm / Intuition
We can enhance this method by adjusting how we move through the matrix. Let's take a look at the four corners:
(0, 0), (0, m-1), (n-1, 0), and (n-1, m-1). By observing these corners, we can identify variations in how we traverse the matrix.

Assume the given ‘target’ = 14

Observations:

1. Cell (0, 0): Assume we are starting traversal from (0, 0) and we are searching for 14. Now, 
this row and column are both sorted in increasing order. So, we cannot determine, how to move i.e. 
row-wise or column-wise. That is why, we cannot start traversal from (0, 0).

2. Cell (0, m-1): Assume we are starting traversal from (0, m-1) and we are searching for 14. Now, in 
this case, the row is in decreasing order and the column is in increasing order. Therefore, if we
start traversal from (0, m-1), in the following way, we can easily determine how we should move.

    1. If matrix[0][m-1] > target: We should move row-wise.
    2. If matrix[0][m-1] < target: We need bigger elements and so we should move column-wise.

3. Cell (n-1, m-1): Assume we are starting traversal from (n-1, m-1) and we are searching for 14. Now, 
this row and column are both sorted in decreasing order. So, we cannot determine, how to move i.e. 
row-wise or column-wise. That is why, we cannot start traversal from (n-1, m-1).

4. Cell (n-1, 0): Assume we are starting traversal from (n-1, 0) and we are searching for 14. Now, in this case,
the row is in increasing order and the column is in decreasing order. Therefore, if we start traversal from 
(n-1, 0), in the following way,  we can easily determine how we should move.

    1. If matrix[n-1][0] < target: We should move row-wise.
    2. If matrix[n-1][0] > target: We need smaller elements and so we should move column-wise.

From the above observations, it is quite clear that we should start the matrix traversal from either the cell 
(0, m-1) or (n-1, 0).

Note: Here in this approach, we have chosen the cell (0, m-1) to start with. You can choose otherwise.

Using the above observations, we will start traversal from the cell (0, m-1) and every time we will compare
the target with the element at the current cell. After comparing we will either eliminate the row or the column 
accordingly like the following:

If current element > target: We need the smaller elements to reach the target. But the column is in increasing 
order and so it contains only greater elements. So, we will eliminate the column by decreasing the current column 
value by 1(i.e. col--) and thus we will move row-wise.

If current element < target: In this case, We need the bigger elements to reach the target. But the row is 
in decreasing order and so it contains only smaller elements. So, we will eliminate the row by increasing the 
current row value by 1(i.e. row++) and thus we will move column-wise.

Algorithm:

1. As we are starting from the cell (0, m-1), the two variables i.e. ‘row’ and ‘col’ will point to 0 and m-1 respectively.
2. We will do the following steps until row < n and col >= 0(i.e. while(row < n && col >= 0)):
    1. If matrix[row][col] == target: We have found the target and so we will return true.
    2. If matrix[row][col] > target: We need the smaller elements to reach the target. But the column is in
    increasing order and so it contains only greater elements. So, we will eliminate the column by decreasing
    the current column value by 1(i.e. col--) and thus we will move row-wise.
    3. If matrix[row][col] < target: In this case, We need the bigger elements to reach the target. But the
    row is in decreasing order and so it contains only smaller elements. So, we will eliminate the row by 
    increasing the current row value by 1(i.e. row++) and thus we will move column-wise.
3. . If we are outside the loop without getting any matching element, we will return false.
 */

// Striver (Video Explanation) : https://youtu.be/JXU4Akft7yk
