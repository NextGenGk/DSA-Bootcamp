package GreedyAlgorithms;

import java.util.Arrays;

public class Assign_Cookies {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N logN + M logM + M) where N is the length of the greedy array, M is the length of the
    // cookies array. To sort the greedy and cookies array, the complexity is O(N logN) and O(M logM).
    // After sorting, we iterate over the arrays at most M times as M is the total number of cookies.
    // Since each child and each cookie is considered at most once, the time complexity of this part is
    // linear in terms of the size of the cookie array, which is O(M).
    // Space Complexity: O(1) as the algorithm uses only a constant amount of extra space regardless of the size
    // of the input array. It does not require any additional data structures that scale with the input size.
    // Function to find the maximum number of content children
    public static int findContentChildren(int[] greed, int[] cookieSize) {
        // Get the size of the greed array
        int n = greed.length;

        // Get the size of the cookieSize array
        int m = cookieSize.length;

        // Sort the greed factors in ascending
        // order to try and satisfy the least greedy children first
        Arrays.sort(greed);

        // Sort the cookie sizes in ascending
        // order to use the smallest cookies first
        Arrays.sort(cookieSize);

        // Initialize a pointer for the cookieSize array,
        // starting from the first cookie
        int l = 0;

        // Initialize a pointer for the greed array,
        // starting from the first child
        int r = 0;

        // Iterate while there are cookies and children left to consider
        while (l < m && r < n) {
            // If the current cookie can
            // satisfy the current child's greed
            if (greed[r] <= cookieSize[l]) {
                // Move to the next child,
                // as the current child is satisfied
                r++;
            }
            // Always move to the next cookie whether the current child was satisfied or not
            l++;
        }

        // The value of r at the end of the loop represents the number of children that were satisfied
        return r;
    }

    // Main Function
    public static void main(String[] args) {
        int[] greed = {1, 5, 3, 3, 4};
        int[] cookieSize = {4, 2, 1, 2, 1, 3};

        System.out.print("Array Representing Greed: ");
        for (int i = 0; i < greed.length; i++) {
            System.out.print(greed[i] + " ");
        }
        System.out.println();

        System.out.print("Array Representing Cookie Size: ");
        for (int i = 0; i < cookieSize.length; i++) {
            System.out.print(cookieSize[i] + " ");
        }

        int ans = findContentChildren(greed, cookieSize);

        System.out.println();
        System.out.println("No. of kids assigned cookies " + ans);
        System.out.println();
    }
}

// Output : No. of kids assigned cookies 3

// Intuition / Approach : Optimal Solution
/*
Greedy Algorithms are approached to solve problems by making the current best choice at each stage with
the hope of getting the best answer overall as well. At each step of the algorithm, we choose the best
possible option available without considering the consequences of the choice in future steps.

To solve this question, we sort the greed and cookies array to easily make decisions about which cookie
to assign to which child. We then iterate over the sorted arrays comparing elements and assigning the
smallest available cookie with the least greedy child.

This way we are able to leave larger cookies for potentially gredier children so that we are able to
maximise the overall satisfaction.

Algorithm :

Step 1:Sort both the greed and cookieSize array. By sorting, we are able to pair the smallest cookies
with the least greedy children, maximising overall content.

Step 2: Use two pointers, l and r to iterate through the cookieSize and greed arrays. These pointers
represent the positions of the smallest available cookie and the least greedy child.

Step 3: We iterate through the arrays, checking if the current cookie can satisfy the current child’s greed.
i.e. cookieSize[l] >= greed[r].

    i. If the current cookie can satisfy the current child’s greed, we move to the next child.
   ii. Regardless of whether a child is satisfied or not, we move to the next cookie.

Step 4: At the end, the value of r, represents the number of children that were satisfied as we increment it
each time a child’s greed is satisfied. We return this value as the total number of satisfied children.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=DIX2p7vb9co