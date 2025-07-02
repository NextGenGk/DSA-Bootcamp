package GreedyAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

public class Fractional_Knapsack {

    // Method 1 : Optimal Solution
    // Time Complexity: O(n log n + n). O(n log n) to sort the items and O(n)
    // to iterate through all the items for calculating the answer.
    // Space Complexity: O(1), no additional data structure has been used.
    // Class to represent an item with value and weight
    static class Item {
        int value;
        int weight;
        // Constructor to initialize value and weight
        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    // Comparator to sort items based on value/weight ratio in descending order
    static class ItemComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b) {
            // Calculate value/weight ratio for both items
            double r1 = (double) a.value / (double) a.weight;
            double r2 = (double) b.value / (double) b.weight;

            // Sort in descending order of ratio
            if (r1 < r2) return 1;
            else if (r1 > r2) return -1;
            else return 0;
        }
    }

    // Function to get maximum value in knapsack of capacity W
    static double fractionalKnapsack(int W, Item arr[], int n) {
        // Sort items based on value/weight ratio
        Arrays.sort(arr, new ItemComparator());

        int currentWeight = 0;  // To track current weight of knapsack
        double finalValue = 0.0; // To track total value

        // Loop through each item
        for (int i = 0; i < n; i++) {
            // If adding the current item doesn't exceed capacity, add it fully
            if (currentWeight + arr[i].weight <= W) {
                currentWeight += arr[i].weight;   // Add weight of current item
                finalValue += arr[i].value;      // Add value of current item
            }
            // If adding the current item exceeds capacity, take the fraction
            else {
                int remain = W - currentWeight;  // Calculate remaining capacity
                finalValue += (double) arr[i].value / (double) arr[i].weight * (double) remain; // Take fraction
                break; // Stop after taking the fraction
            }
        }
        return finalValue;
    }

    // Main Function
    public static void main(String[] args) {
        Item[] arr = { new Item(100, 20), new Item(60,
                10), new Item(100, 50), new Item(200, 50) }; // Example items
        int W = 90; // Knapsack capacity

        System.out.println("Maximum value in Knapsack = " + fractionalKnapsack(W, arr, arr.length));
    }
}

// Working on Online Editor like GFG
/*
import java.util.*;

class Solution {

    // Item class to hold value and weight
    static class Item {
        int value, weight;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    // Main method to compute max value of fractional knapsack
    double fractionalKnapsack(int[] values, int[] weights, int W) {
        int n = values.length;
        Item[] items = new Item[n];

        // Step 1: Build item array from value and weight arrays
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i]);
        }

        // Step 2: Sort items by decreasing value-to-weight ratio using Comparator
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                double r1 = (double) a.value / a.weight;
                double r2 = (double) b.value / b.weight;
                return Double.compare(r2, r1); // Sort in descending order
            }
        });

        double finalValue = 0.0; // Total value we can take
        int currentWeight = 0;   // Total weight accumulated

        // Step 3: Iterate through sorted items and pick greedily
        for (Item item : items) {
            // If full item can be taken, take it
            if (currentWeight + item.weight <= W) {
                finalValue += item.value;
                currentWeight += item.weight;
            } 
            // Otherwise take fraction of item that fits
            else {
                int remain = W - currentWeight;
                finalValue += ((double) item.value / item.weight) * remain;
                break; // Knapsack is full, break the loop
            }
        }

        // Step 4: Return value rounded to 6 decimal places
        return Math.round(finalValue * 1e6) / 1e6;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;

        double result = sol.fractionalKnapsack(values, weights, capacity);
        System.out.printf("Maximum value in Knapsack = %.6f\n", result);
    }
}
*/

// Output : Maximum value in Knapsack = 380.0

// Approach / Intuition - Optimal Solution
/*
The greedy method to maximize our answer will be to pick up the items with higher values. Since it is
possible to break the items as well we should focus on picking up items having higher value /weight
first. To achieve this, items should be sorted in decreasing order with respect to their value /weight.
Once the items are sorted we can iterate. Pick up items with weight lesser than or equal to the current
capacity of the knapsack. In the end, if the weight of an item becomes more than what we can carry,
break the item into smaller units. Calculate its value according to our current capacity and add this
new value to our answer.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=1ibsQrnuEEg
