package GreedyAlgorithms;

import java.util.ArrayList;

public class Find_Minimum_No_of_Coins {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) (where N is the number of coin denominations).
    // Space Complexity: O(N) for the space needed to store the result list.
    // Method to find the minimum coins required to make a given value
    static ArrayList<Integer> findMinCoins(int[] coins, int value) {
        // n holds the number of coin denominations available
        int n = coins.length;

        // Create an ArrayList to store the result (coins used)
        ArrayList<Integer> list = new ArrayList<Integer>();

        // Traverse the coin array from the largest denomination to the smallest
        for (int i = n - 1; i >= 0; i--) {
            // While the current coin can still be used to reduce the value
            while (value >= coins[i]) {
                // Subtract the coin's value from the remaining amount
                value -= coins[i];

                // Add the coin to the result list
                list.add(coins[i]);
            }
        }
        // Return the list of coins used to make the given value
        return list;
    }

    // Main Function
    public static void main(String[] args) {
        // Array representing the available denominations of coins
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 500, 1000};

        // Value for which we need to find the minimum number of coins
        int value = 121;

        // Call the method to find the coins and store the result
        ArrayList<Integer> result = findMinCoins(coins, value);

        // Print the result list (coins used to make the value)
        System.out.println(result);
    }
}

// Output : [100, 20, 1]

// Approach / Intuition : Optimal Solution
/*
Approach: We will keep a pointer at the end of the array i. Now while(V >= coins[i]) we will reduce V by
coins[i] and add it to the ans array.

We will also ignore the coins which are greater than V and the coins which are less than V. We consider
them and reduce the value of V by coins[I].
 */

// Striver's (Article) : https://takeuforward.org/data-structure/find-minimum-number-of-coins/
