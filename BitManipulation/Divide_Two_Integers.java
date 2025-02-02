package BitManipulation;

public class Divide_Two_Integers {

    // Method 1 : Brute Force
    // Time Complexity:O(dividend) as in the worst case scenario, divisor is very small
    // compared to dividend, the loop iterates a dividend number of times leading to a linear
    // time complexity. In a balanced case the time complexity will be O(Quotient) as it
    // depends on how many iterations it takes to reach the condition where sum + divisor
    // exceeds dividend.
    // Space Complexity : O(1) as the space complexity remains constant and independent of
    // input size. Only a fixed amount of memory is required to store the integer variables.
    // This function takes two integers,
    // dividend and divisor, and
    // performs integer division.
    public static int divide(int dividend, int divisor) {
        // Initialize a variable to
        // keep track of the current sum.
        int sum = 0;

        // Initialize a variable to count
        // the number of times the divisor
        // is added to the sum.
        int cnt = 0;

        // Continue looping until the
        // sum plus the divisor is
        // greater than the dividend.
        while (sum + divisor <= dividend) {
            // Increment the counter
            // representing the quotient.
            cnt = cnt + 1;
            // Add the divisor to the current sum.
            sum += divisor;
        }
        // Return the quotient.
        return cnt;
    }

    // Method 2 : Optimal Solution (Repeated Subtraction)
    // Time Complexity: O(N/M * log(d))where N is the number of bits required to represent
    // the dividend and M is the number of bits required to represent the divisor. Inside each
    // iteration, there’s a nested loop to find how many times the divisor can be doubled before
    // exceeding the dividend. This nested loop iterates log(divisor) times.
    // Space Complexity : O(1)because it uses only a constant amount of extra space regardless
    // of the size of the input.
    public static int divide1(int dividend, int divisor) {
        // Handle the overflow case when dividing Integer.MIN_VALUE by -1
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Check if dividend and divisor
        // are equal, return 1 if true
        if (dividend == divisor) {
            return 1;

        }

        // Determine the sign of the result,
        // true for positive, false for negative
        boolean sign = true;

        // If dividend is positive and
        // divisor is negative, set sign to negative
        if (dividend >= 0 && divisor < 0) {
            sign = false;
        }
        // If dividend is negative and divisor
        // is positive, set sign to negative
        else if (dividend <= 0 && divisor > 0) {
            sign = false;
        }

        // Take absolute values
        // of dividend and divisor
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        // Initialize quotient to 0
        long quotient = 0;

        // Perform division using
        // repeated subtraction
        while (n >= d) {
            // Count how many times divisor can
            // be doubled before exceeding dividend
            int cnt = 0;
            while (n >= (d << (cnt + 1))) {
                cnt += 1;
            }
            // Add the value corresponding
            // to the current doubling to the quotient
            quotient += 1 << cnt;
            // Subtract the product of divisor
            // and the doubled value from dividend
            n -= (d << cnt);
        }

        // Handle overflow cases
        // If quotient equals (2^31) and the result
        // is supposed to be positive, return Integer.MAX_VALUE
        if (quotient == (1L << 31) && sign) {
            return Integer.MAX_VALUE;
        }
        // If quotient equals (2^31) and the result
        // is supposed to be negative, return Integer.MIN_VALUE
        if (quotient == (1L << 31) && !sign) {
            return Integer.MIN_VALUE;
        }
        // Return the quotient with correct sign
        return sign ? (int) quotient : (int) -quotient;
    }

    // Main Function
    public static void main(String[] args) {
        int dividend = 45;
        int divisor = 7;
        System.out.println("Dividend: " + dividend + " Divisor: " + divisor);
        int result = divide1(dividend, divisor);

        System.out.println("Result of division: " + result);
    }
}

// Output :
/*
Dividend: 45 Divisor: 7
Result of division: 6
 */

// Intuition : Brute Force
/*
Algorithm / Intuition
We can simulate division by repeated addition of the divisor until the sum exceeds the dividend.
The quotient is then determined by the number of times the divisor was added to reach or slightly
exceed the dividend.

Note: This approach will not handle certain edge cases like negative integers and overflow.

Algorithm:

Step 1:Initialise two integer variables sum and cnt to 0.
    1. sum represents the current accumulated sum.
    2. cnt represents the quotient
Step 2: Enter a loop that continues as long as sum + divisor is less than or equal to dividend.
    1. At each iteration of the loop, increment cnt by 1.
    2. Add divisor to sum.
Step 3:Return the quotient cnt as a result.
 */

// Intuition : Optimal Solution (Repeated Subtraction)
/*
Algorithm / Intuition
Repeated Subtraction is the approach for integer division where we break down the division
process by repeatedly subtracting multiples of the divisor from the dividend until the
dividend becomes smaller than the divisor.

Algorithm :
1. Handle the overflow case when dividing Integer.MIN_VALUE by -1.
2. Check if dividend and divisor are equal, return 1 if true.
3. Determine the sign of the result, true for positive, false for negative.
4. If dividend is positive and divisor is negative, set sign to negative.
5. If dividend is negative and divisor is positive, set sign to negative.
6. Take absolute values of dividend and divisor.
7. Initialize quotient to 0.
8. Perform division using repeated subtraction.
9. Count how many times divisor can be doubled before exceeding dividend.
10. Add the value corresponding to the current doubling to the quotient.
11. Subtract the product of divisor and the doubled value from dividend.
12. Handle overflow cases.
13. If quotient equals (2^31) and the result is supposed to be positive, return Integer.MAX_VALUE.
14. If quotient equals (2^31) and the result is supposed to be negative, return Integer.MIN_VALUE.
15. Return the quotient with correct sign.
 */
