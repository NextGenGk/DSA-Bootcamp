package BitManipulation;

import java.util.ArrayList;
import java.util.List;

public class Smallest_Prime_Factor {

    // Method 1 : Brute Force Approach
    // Time Complexity: O(Q) * O(sqrt(N)) where N is the input number and Q is the query length. The
    // algorithm iterates through each number from 1 to N once to check if it is a divisor. For each divisor,
    // we check if it is prime.
    // Space Complexity : O(1) as we are not using any extra space.
    public static ArrayList<Integer> getPrimeFactors(int n) {
        // Declare an ArrayList to store
        // the prime factors of n.
        ArrayList<Integer> primeFactors = new ArrayList<>();

        // Loop from 2 up to n
        for (int i = 2; i <= n; i++) {
            // Check if n is divisible by i
            if (n % i == 0) {
                while (n % i == 0) {
                    // If divisible, add i to
                    // the primeFactors ArrayList
                    primeFactors.add(i);
                    // Continue dividing n by i
                    // until it's no longer divisible
                    n = n / i;
                }
            }
        }
        // If n is greater than 1, add
        // it to the primeFactors ArrayList
        if (n > 1) {
            primeFactors.add(n);
        }
        // Return the ArrayList
        // containing the prime factors of n.
        return primeFactors;
    }

    // Get Factorization of a number
    static List<Integer> getFactorization(List<Integer> query) {
        // Get the prime factors of the number
        List<Integer> result = new ArrayList<>();
        // Loop through each number in the query
        for (int i = 0; i < query.size(); i++) {
            // Get the prime factors of the number
            result = getPrimeFactors(query.get(i));
            // Print the prime factors
            System.out.println(result);
        }
        return result;
    }

    // Method 2 : Optimal Approach
    // Time Complexity: O(Nlog(logN)) + O(Q * logN) where N is the input number and Q is the query length.
    // The algorithm iterates through each number from 1 to N once to check if it is a divisor. For each divisor,
    // we check if it is prime. The optimal approach uses the Sieve of Eratosthenes to precompute the smallest
    // prime factor for each number from 1 to MAXN. The getFactorization function uses the precomputed SPF array
    // to find the prime factorization of the given number by repeatedly dividing the number by its SPF until it
    // becomes 1.
    // Space Complexity : O(N) as we are using an array of size N to store the smallest
    // prime factor for each number.
    static void getFactorization1(List<Integer> query) {
        final int MAXN = 100001; // Upper limit for precomputation
        int[] spf = new int[MAXN]; // Smallest prime factor array

        // Initialize each number as its own smallest prime factor
        for (int i = 1; i <= MAXN; i++) {
            spf[i] = i;
        }

        spf[0] = 0; // 0 is not a prime number
        spf[1] = 1; // 1 is not a prime number

        // Sieve of Eratosthenes to find the smallest prime factor for each number
        for (int i = 2; i * i < MAXN; i++) {
            // if the number is prime ,mark
            // all its multiples who havent
            // gotten their spf yet
            if (spf[i] == i) {
                // Mark all multiples of i with i as smallest prime factor
                for (int j = i * i; j < MAXN; j += i) {
                    // If j is still unmarked, assign SPF as i
                    if (spf[j] == j) {
                        // Assign i as the smallest prime factor of j
                        spf[j] = i;
                    }
                }
            }
        }

        // Factorize each number in the query list
        for (int i = 0; i < query.size(); i++) {
            int x = query.get(i);
            // Print the prime factors of x
            while (x != 1) {
                System.out.print(spf[x] + " ");
                x = x / spf[x]; // Divide x by its smallest prime factor
            }
            System.out.println(); // Print a new line after each number's factorization
        }
    }

    // Main Function
    public static void main(String[] args) {
        List<Integer> query = new ArrayList<>();
        query.add(10);
        query.add(17);
        query.add(19);
        query.add(36);
        query.add(60);
        // Get the prime factors of the number
        getFactorization(query);
    }
}

// Intuition : Brute Force
/*
Algorithm / Intuition
A brute force approach would be to just iterate over all numbers from 2 to n and for each number
check if it is prime or not.

Algorithm :
1. Initialise an empty array to store the prime numbers found.
2. Loop from 2 to n and for each number check if it is prime or not.
3. If the number is prime, add it to the array.
4. Repeat the above steps for all numbers from 2 to n.
5. Return the array containing all prime numbers.
 */

// Intuition : Optimal Approach (Using Seive of Eratosthenes)
/*
Algorithm / Intuition
We can calculate the prime factorization of a number “n” in O(sqrt(n)) as discussed here.
But O(sqrt n) method times out when we need to answer multiple queries regarding prime factorization.

An efficient method to calculate the prime factorization using
O(n) space and O(log n) time complexity with pre-computation allowed.

Approach :

The main idea is to precompute the Smallest Prime Factor (SPF) for each number from 1 to
MAXN using the sieve function. SPF is the smallest prime number that divides a given number
without leaving a remainder. Then, the getFactorization function uses the precomputed SPF
array to find the prime factorization of the given number by repeatedly dividing the number
by its SPF until it becomes 1.

To calculate to smallest prime factor for every number we will use the sieve of eratosthenes.
In the original Sieve, every time we mark a number as not prime, we store the corresponding
smallest prime factor for that number (Refer this article for better understanding).

Algorithm :
1. The prime factor of a number is the smallest prime number that divides the number without leaving
   a remainder.
2. We will use the Sieve of Eratosthenes to find the smallest prime factor for each number from 1 to MAXN.
3. Initialize the smallest prime factor array spf with each number as its own smallest prime factor.
4. Mark 0 and 1 as not prime numbers.
5. For each number i from 2 to sqrt(MAXN), if the number is prime, mark all its multiples as
   not prime and assign i as the smallest prime factor for each multiple.
6. Factorize each number in the query list using the spf array.
7. For each number x in the query list, print the smallest prime factor of x and divide x
   by its smallest prime factor until x becomes 1.
8. Print a new line after each number's factorization.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=glKWkmKFlMw