package BitManipulation;

import java.util.ArrayList;
import java.util.List;

public class Print_Prime_Factors {

    // Method 1 : Brute Force
    // Time Complexity: O(N*sqrt(N)) where N is the input number. The algorithm iterates through
    // each number from 1 to N once to check if it is a divisor. For each divisor, we check if it is prime.
    // Space Complexity : O(1) as we are not using any extra space.
    // Function to check if a
    // given number is prime.
    static boolean checkPrime(int n) {
        // Initialize a counter variable to
        // count the number of factors.
        int cnt = 0;

        // Loop through numbers from 1
        // to the square root of n.
        for (int i = 1; i <= Math.sqrt(n); i++) {
            // If n is divisible by i
            // without any remainder.
            if (n % i == 0) {
                // Increment the counter.
                cnt = cnt + 1;

                // If n is not a perfect square,
                // count its reciprocal factor.
                if (n / i != i) {
                    cnt = cnt + 1;
                }
            }
        }

        // If the number of
        // factors is exactly 2.
        if (cnt == 2) {
            // Return true, indicating
            // that the number is prime.
            return true;
        }
        // If the number of
        // factors is not 2.
        else {
            // Return false, indicating
            // that the number is not prime.
            return false;
        }
    }

    // Function to return a list of
    // prime factors of input 'n'
    static List<Integer> getPrimeFactors(int n) {
        // Declare a list to store
        // the prime factors of n.
        List<Integer> primeFactors = new ArrayList<>();

        // Start a loop from 2 to n,
        // iterating through each number i.
        for (int i = 2; i <= n; i++) {
            // Check if n is divisible by
            // i without any remainder.
            if (n % i == 0) {
                // If it is, call the
                // function checkPrime to
                // determine if i is prime.
                if (checkPrime(i)) {
                    // If i is prime, add it
                    // to the list of prime factors.
                    primeFactors.add(i);
                }
            }
        }
        // Return the list
        // containing the prime factors of n.
        return primeFactors;
    }

    // Method 2 : Better Solution
    // Time Complexity: O(sqrt(N) * sqrt(N) where N is the input number. The algorithm iterates through
    // each number from 1 to sqrt(N) once to check if it is a divisor. For each divisor, we check if it is prime.
    // Space Complexity : O(1) as we are not using any extra space.
    // Function to return a list of
    // prime factors of input 'n'
    static ArrayList<Integer> getPrimeFactors1(int n) {
        // Declare an ArrayList to store
        // the prime factors of n.
        ArrayList<Integer> primeFactors = new ArrayList<>();

        // Loop through numbers from 1
        // to the square root of n.
        for (int i = 1; i <= Math.sqrt(n); i++) {

            // Check if n is divisible by
            // i without any remainder.
            if (n % i == 0) {

                // If it is, call the
                // function checkPrime to
                // determine if i is prime.
                if (checkPrime(i)) {

                    // If i is prime, add it
                    // to the ArrayList of prime factors.
                    primeFactors.add(i);
                }

                // Check for the reciprocal factor
                if (n / i != i) {
                    // If it is, call the
                    // function checkPrime to
                    // determine if n/i is prime.
                    if (checkPrime(n / i)) {
                        // If n/i is prime, add it
                        // to the ArrayList of prime factors.
                        primeFactors.add(n / i);
                    }
                }
            }
        }
        // Return the ArrayList
        // containing the prime factors of n.
        return primeFactors;
    }

    // Method 3 : Optimal Solution
    // Time Complexity:  O(sqrt(N)) where N is the input number. This loop runs from 2 up to
    // the square root of n, which means it runs approximately sqrt(n) times. Inside the loop,
    // the operations are basic arithmetic operations and pushing elements into the vector,
    // all of which are constant time operations.
    // Space Complexity : O(1) as we are not using any extra space.
    public static ArrayList<Integer> getPrimeFactors2(int n) {
        // Declare an ArrayList to store
        // the prime factors of n.
        ArrayList<Integer> primeFactors = new ArrayList<>();

        // Loop from 2 up to n
        for (int i = 2; i <= n; i++) {
            // Check if n is divisible by i
            if (n % i == 0) {
                // If divisible, add i to
                // the primeFactors ArrayList
                primeFactors.add(i);
            }
            // Continue dividing n by i
            // until it's no longer divisible
            while (n % i == 0) {
                n = n / i;
            }
        }
        // Return the ArrayList
        // containing the prime factors of n.
        return primeFactors;
    }

    // Main Function
    public static void main(String[] args) {
        int n = 60;
        List<Integer> ans = getPrimeFactors(n);
        System.out.print("Prime Factors for " + n + ": ");
        for (int factor : ans) {
            System.out.print(factor + " ");
        }
        System.out.println();
    }
}

// Output : Prime Factors for 60: 2 3 5

// Intuition : Brute Force
/*
First of all, let's understand what the Prime Factor of a number is. Factors are the numbers
that can be multiplied to get a new number. The factors of a number that are prime are called
Prime Factors. For example: If N=12, 12=2x2x3 here 2 and 3 are prime factors of the 12 and
2^2 x 3 makes up to 12.

A straightforward approach would be to iterate from 2 to the given number and each step check
if that number is a factor of n. As we learnt in the article, Check if a Number if Prime or
not, we can determine if a number is prime or not. If a number is both a factor and prime we
can print it.

Algorithm
Step 1:Initialise an empty vector to store the prime factors of n.
Step 2: Start a loop from 2 to n and iterate through each number i. At each step:
    1. Check if n is divisible by i without any remainder.
    2. If n is divisible by i, call the checkPrime function to determine if i is prime.
    3. If i is prime, add it to the vector to store the prime factors.
Step 3: After iterating through all numbers from 2 to n, return the vector containing prime factors
 */

// Intuition : Better Solution
/*
Algorithm / Intuition
We can optimise the algorithm by only iterating up to the square root of n when checking for
prime factors. This is because if n has a factor greater than its square root, it must also
have a factor smaller than its square root.

This property is symmetric about the square root of n by traversing just the first half we can
avoid redundant iteration and computations improving the efficiency of the algorithm.
Discusses in detail here:Print All Divisors

Algorithm

Step 1: Initialise an empty vector to store the prime factors of n.
Step 2: Start a loop from 1 to the square root of n and iterate through each number i. At each step:
    1. Check if n is divisible by i without any remainder. If i is a factor of n then n/i is also a factor.
    2. If n is divisible by i, call the checkPrime function to determine if i is prime. If i is prime,
       add it to the array to store the prime factors.
    3. Check if i is not equal to n/i as i is not a perfect square. If not, check if n/i is prime or not,
       if it is prime, add it to the array storing prime factors.
Step 3: After iterating through potential factors of n from 1 to the square root of n, return the array
containing the prime factors of n.
 */

// Intuition : Optimal Solution (Using Simple School Method)
/*
A more efficient approach to finding prime factors of a number would be to continuously divide the
number by its smallest prime factor until it becomes a prime number itself.

Algorithm
Step 1: Initialise an empty array to store the prime factors of n.
Step 2: Start a loop from 1 to the square root of n and iterate through each number i. At each step:
    1. Check if n is divisible by i without any remainder. If it is divisible, push it into the array
       to store the prime factors of n.
    2. Enter a while loop to continuously divide n by i until its no longer divisible. This eliminates
       repeat factors and ensures each prime factor is included only once.
Step 3: After iterating through potential factors of n, return the array containing all the unique prime
factors of n.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=LT7XhVdeRyg