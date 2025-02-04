package BitManipulation;

import java.util.ArrayList;

public class Print_All_Divisors {

    // Method 1 : Brute Force Approach
    // Time Complexity: O(N) where N is the input number. The algorithm iterates through
    // each number from 1 to N once to check if it is a divisor.
    // Space Complexity : O(1) as we are not using any extra space.
    static void printDivisorsBruteForce(int n) {
        // Traverse through all the numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            // If i is a divisor of n
            if (n % i == 0) {
                // Print the divisor
                System.out.print(i + " ");
            }
        }
    }

    // Method 2 : Optimal Approach
    // Time Complexity: O(sqrt(N)) where N is the input number. The algorithm iterates
    // through each number from 1 to the square root of N once to check if it is a divisor.
    // Space Complexity : O(2*sqrt(N))where N is the input number. This approach allocates
    // memory for an array to hold all the divisors. The size of this array could go to be 2*(sqrt(N)).
    static ArrayList<Integer> printDivisorsOptimal(int n) {
        // Create an arraylist to store all the divisors
        ArrayList<Integer> divisors = new ArrayList<>();
        // Traverse through all the numbers from 1 to sqrt(n)
        for (int i = 1; i <= Math.sqrt(n); i++) {
            // If i is a divisor of n
            if (n % i == 0) {
                // If i is a perfect square, add only once
                // eg. 16 has 4 as a divisor, so add only once to the list
                if (n / i == i) {
                    divisors.add(i);
                } else {
                    // If i is not a perfect square, add both i and n/i
                    divisors.add(i);
                    divisors.add(n / i);
                }
            }
        }
        // Return the list of divisors
        return divisors;
    }

    // Main Function
    public static void main(String[] args) {
        int n = 16;
        ArrayList<Integer> divisors = printDivisorsOptimal(n);
        for (int i : divisors) {
            System.out.print(i + " ");
        }
    }
}

// Output : 1 16 2 8 4

// Intuition : Brute Force
/*
The brute force approach is to iterate through all the numbers from 1 to n and check if the number is a divisor of n.
If the number is a divisor, then print it.

Algorithm :
1. Traverse through all the numbers from 1 to n.
2. If i is a divisor of n, then print i.
3. Repeat the above steps for all the numbers from 1 to n.
 */

// Intuition : Optimal Approach
/*
The optimal approach is to iterate through all the numbers from 1 to sqrt(n) and check if the number is a divisor of n.
If the number is a divisor, then add it to the list of divisors.
If the number is a perfect square, then add it only once to the list of divisors.

Algorithm :
1. Create an arraylist to store all the divisors.
2. Traverse through all the numbers from 1 to sqrt(n).
3. If i is a divisor of n, then add i to the list of divisors.
4. If i is a perfect square, then add i only once to the list of divisors.
5. If i is not a perfect square, then add both i and n/i to the list of divisors.
6. Repeat the above steps for all the numbers from 1 to sqrt(n).
7. Return the list of divisors.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=Ae_Ag_saG9s&list=PLgUwDviBIf0oFON1SRGcMqMIhiZ4EXx_F