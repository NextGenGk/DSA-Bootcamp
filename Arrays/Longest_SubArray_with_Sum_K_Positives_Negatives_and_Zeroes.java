package Arrays;

public class Longest_SubArray_with_Sum_K_Positives_Negatives_and_Zeroes {

    // Method 1 - Brute Force (Using 3 for Loops)
//    // Time - O(N^3), Space - O(1)
//    static int lenOfLongSubarr(int[] arr, int n, int k) {
//        int len = 0;
//        for (int i=0; i<n; i++) {
//            for (int j=i; j<n; j++) {
//                int sum = 0;
//                for (int K=i; K<=j; K++) {
//                    sum += arr[K];
//                }
//
//                if (sum == k) {
//                    len = Math.max(len, j-i+1);
//                }
//            }
//        }
//        return len;
//    }
//
//    // Method 2 - Brute Force (Using 2 for Loops)
//    // Time - O(N^2), Space - O(1)
//    static int lenOfLongSubarr(int[] arr, int n, int k) {
//        int len = 0;
//        for (int i=0; i<n; i++) {
//            int sum = 0;
//            for (int j=i; j<n; j++) {
//                sum += arr[j];
//
//                if (sum == k) {
//                    len = Math.max(len,j-i+1);
//                }
//            }
//        }
//        return len;
//    }

    // Method 3 - Optimal Solution (Using HashMap)
    // Time - O(N) or O(N*logN) depending on which map data structure we are using, where N = size of the array.
    // Space - O(N) because using Map Data Structure
    static int lenOfLongSubarr(int[] arr, int k) {
        int left = 0;  // Left pointer
        int sum = 0;   // To track the current window sum
        int maxLength = 0;  // To track the maximum length of subarray

        // Traverse the array with the right pointer
        for (int right = 0; right < arr.length; right++) {
            // Add current element to sum
            sum += arr[right];

            // While sum exceeds k, shrink the window from the left
            while (sum > k && left <= right) {
                sum -= arr[left];
                left++;
            }

            // If we get a sum equal to k, update maxLength
            if (sum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }

    // Main Function
    public static void main(String[] args) {
        int[] a = { -1, 1, 1};
        int n = a.length;
        int k = 1;
        int len = lenOfLongSubarr(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}

// Algorithm: Brute Force (Using 3 for Loops)
/*
1. Initialize len = 0
2. Run a loop from i=0 to n-1
    2.1 Run a loop from j=i to n-1
        2.1.1 Initialize sum = 0
        2.1.2 Run a loop from k=i to j
3. If sum == k, then len = max(len, j-i+1)
4. Return len
 */

// Algorithm: Brute Force (Using 2 for Loops)
/*
1. Initialize len = 0
2. Run a loop from i=0 to n-1
    2.1 Initialize sum = 0
    2.2 Run a loop from j=i to n-1
        2.2.1 sum = sum + arr[j]
        2.2.2 If sum == k, then len = max(len, j-i+1)
3. Return len
 */

// Algorithm: Optimal Solution (Using HashMap)
// Edge Case - Positives, Negatives
//           [2,0,0,3]   [-1,1,1]
/*
1. Initialize sum = 0, maxLen = 0
2. Initialize a HashMap
3. Run a loop from i=0 to n-1
    3.1 sum = sum + arr[i]
    3.2 If sum == k, then maxLen = max(maxLen, i+1)
    3.3 rem = sum - k
    3.4 If map contains rem, then
        3.4.1 len = i - map.get(rem)
        3.4.2 maxLen = max(maxLen, len)
    3.5 If map does not contain sum, then
        3.5.1 map.put(sum, i)
4. Return maxLen
 */
