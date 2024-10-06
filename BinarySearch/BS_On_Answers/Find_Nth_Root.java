package BinarySearch.BS_On_Answers;

public class Find_Nth_Root {

    // Power Exponential Method
    // Time Complexity : O(N)
    static int pow(int x, int n) {
        long power = 1;
        for (int i = 0; i < n; i++) {
            power *= x;
        }
        return (int) power;
    }

    // Find Nth Root Function
    // Time Complexity : O(N * log2M)
    // Space Complexity : O(1)
    static int findNthRoot(int m, int n) {
        int low = 1;
        int high = m;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int value = pow(mid, mid);
            if (value == m) return mid;

            if (value > m) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    // Power Exponential Method
    // Time Complexity - O(log2N)
    // Return 1 if mid == m
    // Return 0 if mid < m
    // Return 2 if mid > m
    // Find (mid ^ n) function
    public static int func(int mid, int n, int m) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            if (ans > m) return 2;
        }
        if (ans == m) return 1;
        return 0;
    }

    // Find NthRoot function
    public static int NthRoot(int n, int m) {
        // Use binary search on the answer space:
        int low = 1, high = m;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midN = func(mid, n, m);
            if (midN == 1) {
                return mid;
            } else if (midN == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Main Function
    public static void main(String[] args) {
        int x = 27;
        int n = 3;
        int result = findNthRoot(x, n);
        if (result != -1) {
            System.out.println("The " + n + "th root of " + x + " is: " + result);
        } else {
            System.out.println(x + " does not have an integer " + n + "th root.");
        }
    }
}

// Intuition : Brute Force
/*
We can guarantee that our answer will lie between the range from 1 to m i.e. the given number.
So, we will perform a linear search on this range and we will find the number x, such that
func(x, n) = m. If no such number exists, we will return -1.

Note: func(x, n) returns the value of x raised to the power n i.e. xn.
 */

// Intuition : Optimal Solution
/*
Optimal Approach(Using Binary Search): 
We are going to use the Binary Search algorithm to optimize the approach.

The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

Now, we are not given any sorted array on which we can apply binary search. But, if we observe closely, we can notice that our answer space i.e. [1, n] is sorted. So, we will apply binary search on the answer space.

Edge case: How to eliminate the halves:

Our first approach should be the following:

After placing low at 1 and high m, we will calculate the value of ‘mid’.
Now, based on the value of ‘mid’ raised to the power n, we will check if ‘mid’ can be our answer, and based on this value we will also eliminate the halves. If the value is smaller than m, we will eliminate the left half and if greater we will eliminate the right half.
But, if the given numbers m and n are big enough, we cannot store the value midn in a variable. So to resolve this problem, we will implement a function like the following:

func(n, m, mid):

1. We will first declare a variable ‘ans’ to store the value midn.
2. Now, we will run a loop for n times to multiply the ‘mid’ n times with ‘ans’. 
3. Inside the loop, if at any point ‘ans’ becomes greater than m, we will return 2.
4. Once the loop is completed, if the ‘ans’ is equal to m, we will return 1.
5. If the value is smaller, we will return 0.
Now, based on the output of the above function, we will check if ‘mid’ is our possible answer or we will eliminate the halves. Thus we can avoid the integer overflow case.

Algorithm:

1. Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to 1 and the high will point to m.
2. Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
3. Eliminate the halves accordingly: 
    1. If func(n, m, mid) == 1: On satisfying this condition, we can conclude that the number ‘mid’ is our answer. So, we will return to ‘mid’.
    2. If func(n, m, mid) == 0: On satisfying this condition, we can conclude that the number ‘mid’ is smaller than our answer. So, we will eliminate the left half and consider the right half(i.e. low = mid+1).
    3. If func(n, m, mid) == 2: the value mid is larger than the number we want. This means the numbers greater than ‘mid’ will not be our answers and the right half of ‘mid’ consists of such numbers. So, we will eliminate the right half and consider the left half(i.e. high = mid-1).
4. Finally,  if we are outside the loop, this means no answer exists. So, we will return -1.

The steps from 2-3 will be inside a loop and the loop will continue until low crosses high.
*/

// Striver Video Explanation : https://www.youtube.com/watch?v=rjEJeYCasHs
