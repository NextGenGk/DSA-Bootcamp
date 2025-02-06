package BitManipulation;

public class GCD {

    // Method 1 : Brute Force
    // Time Complexity: O(min(N1, N2)) where N1 and N2 is the input number. The algorithm
    // iterates from 1 to the minimum of N1 and N2 and each iteration checks whether both
    // the numbers are divisible by the current number (constant time operations).
    // Space Complexity: O(1)as the space complexity remains constant and independent of
    // the input size. Only a fixed amount of memory is required to store the integer variables.
    public static int findGcd(int n1, int n2) {
        // Initialize gcd to 1
        int gcd = 1;

        // Iterate from 1 up to
        // the minimum of n1 and n2
        for (int i = 1; i <= Math.min(n1, n2); i++) {
            // Check if i is a common
            // factor of both n1 and n2
            if (n1 % i == 0 && n2 % i == 0) {
                // Update gcd to the
                // current common factor i
                gcd = i;
            }
        }

        // Return the greatest
        // common divisor (gcd)
        return gcd;
    }

    // Method 2 : Better Solution
    // Time Complexity: O(min(N1, N2)) where N1 and N2 is the input number. The algorithm iterates
    // from the minimum of N1 and N2 to 1 and each iteration checks whether both the numbers are
    // divisible by the current number (constant time operations).
    // Space Complexity: O(1) as the space complexity remains constant and independent of the
    // input size. Only a fixed amount of memory is required to store the integer variables.
    public static int findGcd1(int n1, int n2) {
        // Iterate from the minimum of
        // n1 and n2 down to 1
        // Start from the minimum of n1 and n2
        // because the GCD cannot
        // exceed the smaller number

        for (int i = Math.min(n1, n2); i >= 1; i--) {
            // Check if i is a common
            // factor of both n1 and n2
            if (n1 % i == 0 && n2 % i == 0) {
                // If i is a common factor,
                // return it as the GCD
                return i;
            }
        }
        // If no common factors are found,
        // return 1 (as 1 is always a
        // divisor of any number)
        return 1;
    }

    // Method 3 : Optimal Solution (Using Euclidean Algorithm)
    // Time Complexity: O(log(min(N1, N2))) where N1 and N2 is the input number. The algorithm
    // iterates from the minimum of N1 and N2 to 1 and each iteration checks whether both
    // the numbers are divisible by the current number (constant time operations).
    // Space Complexity: O(1) as the space complexity remains constant and independent of
    // the input size. Only a fixed amount of memory is required to store the integer variable
    public static int findGcd3(int a, int b) {
        while (a > 0 && b > 0) {
            // If a is greater than b,
            // subtract b from a and update a
            if (a > b) {
                // Update a to the remainder
                // of a divided by b
                a = a % b;
            }
            // If b is greater than or equal
            // to a, subtract a from b and update b
            else {
                // Update b to the remainder
                // of b divided by a
                b = b % a;
            }
        }
        // Check if a becomes 0,
        // if so, return b as the GCD
        if (a == 0) {
            return b;
        }
        // If a is not 0,
        // return a as the GCD
        return a;
    }

    // Main Function
    public static void main(String[] args) {
        int n1 = 20, n2 = 15;
        // Find the GCD of n1 and n2
        int gcd = findGcd(n1, n2);
        System.out.println("GCD of " + n1 + " and " + n2 + " is: " + gcd);
    }
}

// Output : GCD of 20 and 15 is: 5

// Intuition : Brute Force
/*
The GCD of two numbers is the largest number that divides both of them without leaving a remainder.
We iterate through all numbers from 1 up to the minimum of the two input numbers, checking if each
number is a common factor of both input numbers.

If a number is a common factor, we update our gcd variable to that number. This process continues
until we have iterated through all possible common factors. Finally, we return the gcd variable,
which will hold the greatest common divisor of the two input numbers.

Algorithm:
Step 1: Initialise a variable gcd to 1. This variable will store the greatest common divisor of
the input numbers n1 and n2.
Step 2: Iterate from 1 to the minimum of n1 and n2.
    1. We start from 1 because the GCD of any two numbers is at least 1, and it cannot be greater than
       the smaller of the two numbers.
Step 3: At each iteration, if i is a common factor of both n1 and n2 update the gcd variable to i.
We keep updating gcd as long as we find common factors.
Step 4: After the iteration, the gcd variable will store the greatest common divisor of n1 and n2.
Return this value as the output of the function.
 */

// Intuition : Better Solution
/*
We can optimise the time complexity of the previous approach. In the worst case, the loop iterates
from 1 up to the minimum of N1 and N2. This could potentially result in a large number of iterations,
especially when one input number is significantly larger than the other.

If we iterate from the minimum of N1 and N2 down to 1, we reduce the number of iterations because
we start from the potentially largest common factor and work downwards.

The time complexity of this approach remains O(min(N1, N2)) but in practice, it will execute fewer
iterations on average.

Algorithm:
Step 1: Iterate from the minimum of n1 and n2 because the greatest common divisor of two numbers
cannot exceed the smaller number.
Step 2: For each i in the iteration, we check if it is a common factor of both n1 and n2.
    1. If a common factor i is found, we return it as the gcd as we are iterating from the largest
       potential gcd to 1, the first common factor we encounter will be the greatest common divisor.
Step 3: If the loop completes without finding any common factors we return 1. This is because 1
is always a divisor of any number hence is also the GCD of any pair of numbers where
no other common factors exist.
 */

// Intuition : Optimal Solution
/*
The Euclidean Algorithm is a method for finding the greatest common divisor of two numbers.
It operates on the principle that the GCD of two numbers remains the same even if the smaller
number is subtracted from the larger number.

Formula : gcd(n1, n2) = gcd(n1-n2, n2) if n1 > n2

To find the GCD of n1 and n2 where n1 > n2:

Repeatedly subtract the smaller number from the larger number until one of them becomes 0.
Once one of them becomes 0, the other number is the GCD of the original numbers.
Eg, n1 = 20, n2 = 15:

gcd(20, 15) = gcd(20-15, 15) = gcd(5, 15)
gcd(5, 15) = gcd(15-5, 5) = gcd(10, 5)
gcd(10, 5) = gcd(10-5, 5) = gcd(5, 5)
gcd(5, 5) = gcd(5-5, 5) = gcd(0, 5)

Hence, return 5 as the gcd.

Eg, n1 = 52, n2 = 10:
gcd(52, 10) = gcd(52-10, 10) = gcd(42, 10)
gcd(42, 10) = gcd(42-10, 10) = gcd(32, 10)
gcd(32, 10) = gcd(32-10, 10) = gcd(22, 10)
gcd(22, 10) = gcd(22-10, 10) = gcd(12, 10)
gcd(12, 10) = gcd(12-10, 10) = gcd(2, 10)
gcd(2, 10) = gcd(10-2, 2) = gcd(8, 2)
gcd(8, 2) = gcd(8-2, 2) = gcd(6, 2)
gcd(6, 2) = gcd(6-2, 2) = gcd(4, 2)
gcd(4, 2) = gcd(4-2, 2) = gcd(2, 2)
gcd(2, 2) = gcd(2-2, 2) = gcd(0, 2)

By observing the above examples :
if we modulo greater value modulo smaller value then the result will be the same as the GCD of the two numbers.
means we could definitely say that gcd(n1, n2) = gcd(n1 % n2, n2) if n1 > n2
gcd(52, 10) = gcd (52 % 10, 10) = gcd(2, 10) (Skip the steps from 52 to 2 as we already know the result)
gcd(2, 10) = gcd(2, 10 % 2) = gcd(2, 0) = 2 (Skip the steps from 2 to 0 as we already know the result)

We see that after two iterations our gcd is calculated as 2. Hence, we can use the Euclidean Algorithm
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=1xNbjMdbjug&t=2695s