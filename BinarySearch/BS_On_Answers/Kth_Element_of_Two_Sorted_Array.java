package BinarySearch.BS_On_Answers;

public class Kth_Element_of_Two_Sorted_Array {

    // Method 1 : Brute Force
    // Time Complexity: O(m+n), where m and n are the sizes of the given arrays.
    // Reason: We traverse through both arrays linearly.
    // Space Complexity: O(m+n), where m and n are the sizes of the given arrays.
    // Reason: We are using an extra array of size (m+n) to solve this problem.
    public static int kthElementBrute(int[] a, int[] b, int m, int n, int k) {
        int[] arr3 = new int[m + n]; // using array instead of ArrayList

        // apply the merge step:
        int i = 0, j = 0, idx = 0;
        while (i < m && j < n) {
            if (a[i] < b[j]) arr3[idx++] = a[i++];
            else arr3[idx++] = b[j++];
        }

        // copy the left-out elements:
        while (i < m) arr3[idx++] = a[i++];
        while (j < n) arr3[idx++] = b[j++];

        return arr3[k - 1];
    }

    // Method 2 :  Better Solution
    // Time Complexity: O(m+n), where m and n are the sizes of the given arrays.
    // Reason: We traverse through both arrays linearly.
    // Space Complexity: O(1), as we are not using any extra space to solve this problem.
    public static int kthElementBetter(int[] a, int[] b, int m, int n, int k) {
        int ele = -1;
        int cnt = 0; // counter
        // apply the merge step:
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (a[i] < b[j]) {
                if (cnt == k - 1) ele = a[i];
                cnt++;
                i++;
            } else {
                if (cnt == k - 1) ele = b[j];
                cnt++;
                j++;
            }
        }

        // copy the left-out elements:
        while (i < m) {
            if (cnt == k - 1) ele = a[i];
            cnt++;
            i++;
        }
        while (j < n) {
            if (cnt == k - 1) ele = b[j];
            cnt++;
            j++;
        }

        return ele;
    }

    // Method 3 : Optimal Solution
    // Time Complexity: O(log(min(m, n))), where m and n are the sizes of two given arrays.
    // Reason: We are applying binary search on the range [max(0, k-n2), min(k, n1)].
    // The range length <= min(m, n).
    // Space Complexity: O(1), as we are not using any extra space to solve this problem.
    public static int kthElementOptimal(int[] a, int[] b, int m, int n, int k) {
        if (m > n) return kthElementOptimal(b, a, n, m, k);

        int left = k; // length of left half

        // We are binary searching how many elements to take from array a.
        // mid1 = number of elements taken from a
        // mid2 = number of elements taken from b = k - mid1
        //
        // The valid search space for mid1 is:
        //   low  = max(0, k - n)
        //   high = min(k, m)
        //
        // Why?
        // 1. low = max(0, k - n):
        //    - If b has fewer than k elements, we MUST take at least (k - n) from a.
        //    Example:
        //        a = [2, 3, 6, 7, 9], m = 5
        //        b = [1, 4],         n = 2
        //        k = 6
        //        --> b has only 2 elements, so at least (6 - 2 = 4) must come from a.
        //        So mid1 cannot be < 4.
        //
        // 2. high = min(k, m):
        //    - We cannot take more than k total elements, nor more than the size of a.
        //    Example:
        //        a = [2, 3],        m = 2
        //        b = [1, 4, 8, 10], n = 4
        //        k = 5
        //        --> we cannot take more than 2 elements from a (its size).
        //        --> we also cannot take more than 5 in total.
        //        So mid1 cannot be > 2.
        // 
        // Thus mid1 is always bounded safely between [max(0, k-n), min(k, m)].
        int low = Math.max(0, k - n), high = Math.min(k, m);

        // apply binary search:
        while (low <= high) {
            int mid1 = (low + high) >> 1;
            int mid2 = left - mid1;
            // calculate l1, l2, r1, and r2
            int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < m) ? a[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n) ? b[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }

            // eliminate the halves:
            else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return 0; // dummy statement
    }

    // Main Function
    public static void main(String[] args) {
        int[] a = {2, 3, 6, 7, 9};  // using arrays instead of ArrayList
        int[] b = {1, 4, 8, 10};

        System.out.println("The k-th element of two sorted arrays is: " +
                kthElementOptimal(a, b, a.length, b.length, 5));
    }
}

// Output :
// The element at the kth position in the final sorted array is 6

// Approach : Brute Force
/*
The extremely naive approach is to merge the two sorted arrays and then find the K-th element in that merged array.

How to merge two sorted arrays:
The word “merge” suggests applying the merge step of the merge sort algorithm . In that step, we essentially perform
the same actions as required by this solution. By using two pointers on two given arrays,
we fill up the elements into a third array.
 */

// Approach : Better Solution
/*
To optimize the space used in the previous approach, we can eliminate the third array used to store the final merged
result. After closer examination, we realize that we only need the k-th element at index (k-1),
rather than the entire merged array, to solve the problem effectively.

We will stick to the same basic approach, but instead of storing elements in a separate array, we will
use a counter called 'cnt' to represent the imaginary third array's index. As we traverse through the
arrays, when 'cnt' reaches the index (k-1), we will store that particular element. This way, we can
achieve the same goal without using any extra space.
 */

// Approach : Optimal Solution
/*
Approach :

Apply binary search in an array with a small size. Start iterating with two pointers, say left and right.
Find the middle of the range. Take elements from low to middle of array1 and the remaining elements from
the second array. Then using the condition mentioned above, check if the left half is valid. If valid,
print the maximum of both array’s last element. If not, move the range towards the right if l2 > r1,
else move the range towards the left if l1 > r2.
 */

// Video Explanation (Striver) : https://youtu.be/nv7F4PiLUzo
