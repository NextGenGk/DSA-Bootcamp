package BitManipulation;

public class Binary_Exponentiation {

    // Method 1 : Optimal Solution
    // Time Complexity: O(log(N)) where N is the input number. The algorithm divides the
    // input number by 2 at each step, so the time complexity is logarithmic.
    // Space Complexity : O(1) as we are not using any extra space.
    // Function to calculate the power of a number
    public static double myPow(double x, int n) {
        if (n == 0) return 1.0; // Base case: x^0 = 1
        if (x == 0) return 0.0; // Edge case: 0^0 is considered 0 here
        if (x == 1) return 1.0; // Edge case: 1^n = 1
        if (x == -1 && n % 2 == 1) return -1.0; // Special case for -1

        // Convert n to long to avoid integer overflow when n = Integer.MIN_VALUE
        long binaryForm = n;
        if (binaryForm < 0) {
            x = 1 / x; // Handle negative exponent by taking reciprocal
            binaryForm = -binaryForm; // Convert n to positive
        }

        double ans = 1; // Result variable

        while (binaryForm > 0) {
            if (binaryForm % 2 == 1) { // If exponent is odd, multiply x to result
                ans *= x;
            }
            x *= x; // Square the base
            binaryForm /= 2; // Reduce exponent by half
        }
        return ans;
    }

    // Main Function
    public static void main(String[] args) {
        double x = 2.0;
        int n = 10;
        System.out.println(myPow(x, n));
    }
}

// Output: 1024.0

// Intuition : Optimal Solution
/*
The problem is to compute x^n efficiently. A naive approach would multiply x by itself n times,
which results in O(n) time complexity. However, we can use Exponentiation by Squaring, which
reduces the complexity to O(log n).

Key Idea Behind Exponentiation by Squaring

1. If n is even, then: x^n = (x^(n/2))^2
2. If n is odd, then: x^n = x * (x * x^(n-1))
Here, we make n even by subtracting 1 and then apply the squaring method.
3. If n is negative, we take the reciprocal: (x^-n) = (1/x^n)
This approach effectively reduces the number of multiplications from O(n) to O(log n).
 */

// Apna College (Video Explanation): https://www.youtube.com/watch?v=WBzZCm46mFo&