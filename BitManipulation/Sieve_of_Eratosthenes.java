package BitManipulation;

import java.util.ArrayList;
import java.util.List;

public class Sieve_of_Eratosthenes {

    // Method 1 : Brute Force
    // Time Complexity: O(N*sqrt(N)) where N is the input number. The algorithm iterates through
    // each number from 1 to N once to check if it is a divisor. For each divisor, we check if it is prime.
    // Space Complexity : O(1) as we are not using any extra space.
    // Function to check if a number is prime
    static boolean isPrime(int n) {
        // Counter to count the
        // number of divisors of n
        int cnt = 0;

        // Loop from 1 to
        // the square root of n
        for (int i = 1; i * i <= n; i++) {

            // Check if i is a divisor of n
            if (n % i == 0) {
                // Increment the
                // count of divisors
                cnt++;

                // Check if i is not
                // the square root of n
                if (n / i != i) {

                    // Increment the count
                    // of divisors again
                    cnt++;
                }
            }
        }

        // Check if the number of
        // divisors is exactly 2
        if (cnt == 2) {

            // If there are exactly 2
            // divisors, return true (prime)
            return true;
        }
        // If the number of divisors is
        // not 2, return false (not prime)
        return false;
    }

    // Function to find all prime
    // numbers up to 'n'
    static List<Integer> findAllPrimes(int n) {
        // Initialize an empty list to
        // store the prime numbers
        List<Integer> ans = new ArrayList<>();

        // Iterate from 2 to 'n'
        for (int i = 2; i <= n; i++) {
            // Check if the current
            // number 'i' is prime
            if (isPrime(i)) {
                // If 'i' is prime, add
                // it to the list 'ans'
                ans.add(i);
            }
        }

        // Return the list containing
        // all prime numbers up to 'n'
        return ans;
    }

    // Method 2 : Optimal Solution
    // Time Complexity: O(N + N*log(log(N)))where N is the input number. The outer loop iterates
    // from 2 to the square root of n which is O(sqrt(n)) iterations.
    // 1. The inner loop iterates over multiples of each prime number found, marking them as not prime.
    // 2. In total, the number of operations performed is: O(n/2 + n/3 + n/5 + ….) = O(n log(log(N))
    // Space Complexity : O(N) where N is the input number as additional space proportional to
    // the input size is needed to store a vector of size ‘n+1’ to store whether each number up to n is prime.
    // Function to find all prime
    // numbers up to 'n'
    public static List<Integer> findAllPrimes1(int n) {
        // Initialize with 1 (assuming all
        // numbers are prime initially)
        List<Integer> prime = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            prime.add(1);
        }

        // 0 and 1 are not prime
        prime.set(0, 0);
        prime.set(1, 0);

        // Apply Sieve of Eratosthenes
        for (int i = 2; i <= Math.sqrt(n); ++i) {
            if (prime.get(i) == 1) {
                for (int j = i * i; j <= n; j += i) {
                    // Mark multiples of prime
                    // numbers as not prime
                    prime.set(j, 0);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        // Collect prime numbers
        for (int i = 2; i <= n; ++i) {
            if (prime.get(i) == 1) {
                ans.add(i);
            }
        }
        return ans;
    }

    // Main Function
    public static void main(String[] args) {
        int n = 32;
        List<Integer> primes = findAllPrimes1(n);

        System.out.print("Prime numbers less than or equal to " + n + ": ");
        for (int prime : primes) {
            System.out.print(prime + " ");
        }
        System.out.println();
    }
}

// Output: Prime numbers less than or equal to 32: 2 3 5 7 11 13 17 19 23 29 31

// Intuition : Brute Force
/*
Algorithm / Intuition
A brute force approach would be to just iterate over all numbers from 2 to n and for each number
check if it is prime or not.

Algorithm:

Step 1: Initialise an empty array to store the prime numbers found.
Step 2:Iterate over numbers from 2 to n (inclusive). For each number i in range:
    1. Check if i is prime using the methods discussed in Check for Prime. If it is prime,
       add it to the answer vector.
Step 3:After iterating over all numbers from 2 to n, return the array containing all prime numbers found.
 */

// Intuition : Optimal Solution
/*
Algorithm / Intuition
Based on the previous approach, the complexity of checking if a number is prime can be optimised
 using Sieve of Eratosthenes. We can create a list of consecutive integers from 2 to ‘n’.

Start with the prime number which is 2. Mark all of its multiple greater than 2 as composite
(ie. 4, 6, 8, 10 and so on). Find the next number in the list that is not marked as composite.
This is the next prime number. Repeat until all numbers up to ‘n’ have to be processed.

This algorithm efficiently identifies primes because it eliminates composite numbers early in
the process. It basically works like a sieve, we just filter the numbers in each iteration, to
make our search space smaller and smaller, which makes this more efficient.

Algorithm :
Step 1: Initialise a vector of size n+1 with all elements equal to 1. This vector stores the
status of all numbers from 2 to n, whether they are prime or not. Initially, we assume all numbers are prime.
Step 2: We iterate through all numbers from 2 to the square root of n. For each prime number found,
we marks its multiples as not prime starting from i*i up to n.
    1. We mark multiples of prime numbers as not prime, effectively sieving out composite numbers.
Step 3: We iterate through numbers 2 to n. For each number i that is prime, we add it to the result
vector ans.
    1. We collect all numbers marked as prime in the answer vector to return as the final result.
 */

// Striver's (Video Explanation): https://www.youtube.com/watch?v=g5Fuxn_AvSk