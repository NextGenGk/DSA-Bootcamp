package Arrays;

import java.util.ArrayList;

public class Move_Zeroes_to_End {

    // Method 1 - Brute Force (Using Temporary Array)
    // Time Complexity - O(N)
    // Space Complexity - O(N)
    static int[] moveZeroes(int[] arr, int n) {
        ArrayList<Integer> temp = new ArrayList<>();

        // copy non-zero elements
        // from original -> temp array:
        for (int i=0; i<n; i++) {
            if (arr[i] != 0) {
                temp.add(arr[i]);
            }
        }

        // number of non zero elements
        int nz = temp.size();

        // copy elements from temp
        // fill first nz fields of
        // original array:
        for (int i=0; i<nz; i++) {
            arr[i] = temp.get(i);
        }

        // fill rest of the cells with 0:
        for (int i=nz; i<n; i++) {
            arr[i] = 0;
        }
        return arr;
    }

    // Method 2 - Optimal Solution (Using 2 Pointers)
    // Time Complexity - O(N)
    // Space Complexity - O(1)
    static void pushZerosToEnd(int[] arr, int n) {
        // Initialize a pointer j to -1, which will keep track of the position of the first zero
        int j = -1;
        
        // Iterate through the array to find the first zero
        for (int i = 0; i < n; i++) {
            // Check if the current element is zero
            if (arr[i] == 0) {
                // Set j to the current index and break the loop
                j = i;
                break;
            }
        }
        
        // If no zeros are found, there is no need to move anything, so return
        if (j == -1) return;
        
        // Iterate through the array starting from the element after the first zero
        for (int i = j + 1; i < n; i++) {
            // If the current element is not zero, swap it with the element at index j
            if (arr[i] != 0) {
                // Swap the elements at indices i and j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                
                // Move the pointer j to the next position
                j++;
            }
        }
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 3, 2, 0, 0, 4, 5, 1};
        int n = 10;
        int[] ans = moveZeroes(arr, n);
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println("");
    }
}

// Output :
// 1 2 3 2 4 5 1 0 0 0

// Algorithm: Brute Force
/*
1. Suppose, there are N-X zeros and X non-zero elements in the array. We will first copy those
 X non-zero elements from the original array to a temporary array.
2. Then, we will copy the elements from the temporary array one by one and fill the first X places
of the original array.
3. The last N-X places of the original array will be then filled with zero. Now, our task is completed.
 */

// Algorithm: Optimal Solution
/*
1. First, using a loop, we will place the pointer j. If we don’t find any 0, we will not perform
the following steps.
2. After that, we will point i to index j+1 and start moving the pointer using a loop.
3. While moving the pointer i, we will do the following:
    i. If a[i] != 0 i.e. a[i] is a non-zero element: We will swap a[i] and a[j]. Now, the current
       j is pointing to the non-zero element a[i]. So, we will shift the pointer j by 1 so that it
       can again point to the first zero.
4. Finally, our array will be set in the right manner.
 */

// Striver (Video Link) : https://youtu.be/wvcQg43_V8U
