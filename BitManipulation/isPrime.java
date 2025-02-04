package BitManipulation;

public class isPrime {

    // Method 1 : Brute Force Approach
    // Time Complexity: O(N) where N is the input number. The algorithm iterates through
    // each number from 1 to N once to check if it is a divisor.
    // Space Complexity : O(1) as we are not using any extra space.
    static boolean isPrimeBruteForce(int n) {
        // Count variable to keep track of the number of divisors
        int count = 0;
        // Traverse through all the numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            // If i is a divisor of n
            if (n % i == 0) {
                // Increment the count
                count++;
            }
            // If the number of divisors is greater than 2, then it is not a prime number
            if (count > 2) break;
        }
        // If the number of divisors is 2, then it is a prime number
        if (count == 2) return true;
        // If the number of divisors is not 2, then it is not a prime number
        return false;
    }

    // Method 2 : Optimal Approach
    // Time Complexity: O(sqrt(N)) where N is the input number. The algorithm iterates
    // through each number from 2 to the square root of N once to check if it is a divisor.
    // Space Complexity : O(1) as we are not using any extra space.
    static boolean isPrimeOptimal(int n) {
        // Count variable to keep track of the number of divisors
        int count = 0;
        // Traverse through all the numbers from 2 to sqrt(n)
        for (int i = 1; i <= Math.sqrt(n); i++) {
            // If i is a divisor of n
            if (n % i == 0) {
                // Increment the count
                count++;

                // If i is a perfect square, add only once
                // eg. 16 has 4 as a divisor, so add only once to the list
                if (n / i == i) {
                    // Increment the count
                    count++;
                }
            }
        }
        // If the number of divisors is greater than 2, then it is not a prime number
        if (count > 2) return false;
        // If the number of divisors is 2, then it is a prime number
        return true;
    }

    // Main Function
    public static void main(String[] args) {
        int n = 16;
        System.out.println(isPrimeOptimal(n));
    }
}

// Output : false

// Intuition : Brute Force
/*
The brute force approach is to iterate through all the numbers from 1 to n and check if the
number is a divisor of n. If the number is a divisor, then increment the count. If the count
is 2, then the number is a prime number. If the count is greater than 2, then the number is not

Algorithm :
1. Create a variable count to keep track of the number of divisors.
2. Traverse through all the numbers from 1 to n.
3. If i is a divisor of n, increment the count.
4. If the count is 2, then the number is a prime number.
5. If the count is greater than 2, then the number is not a prime number.
6. Return true if the number is a prime number, else return false.
 */

// Intuition : Optimal Solution (Using Square Root)
/*
The optimal approach is to iterate through all the numbers from 2 to the square root of n
and check if the number is a divisor of n. If the number is a divisor, then increment the
count. If the count is greater than 2, then the number is not a prime number. If the count
is 2, then the number is a prime number.

Algorithm :
1. Create a variable count to keep track of the number of divisors.
2. Traverse through all the numbers from 2 to sqrt(n).
3. If i is a divisor of n, increment the count.
4. If i is a perfect square, add only once to the list of divisors.
5. If the count is greater than 2, then the number is not a prime number.
6. If the count is 2, then the number is a prime number.
7. Return true if the number is a prime number, else return false.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=MJcckSfoYdI&list=PLgUwDviBIf0oFON1SRGcMqMIhiZ4EXx_F