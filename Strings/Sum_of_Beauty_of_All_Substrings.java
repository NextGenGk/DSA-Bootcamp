package Strings;

import java.util.HashMap;

public class Sum_of_Beauty_of_All_Substrings {

    // Main Function
    public static void main(String[] args) {
        Sum_of_Beauty_of_All_Substrings solution = new Sum_of_Beauty_of_All_Substrings();
        String input = "aabcb";
        int result = solution.beautySum(input);
        System.out.println("The sum of beauty of all substrings of \"" + input + "\" is: " + result);
    }

    // Method 1: Optimal Solution
    // Time Complexity: O(N^3) in worst case (due to nested loops + frequency scan each time)
    // Space Complexity: O(26) ~ O(1), (only storing at most 26 characters in hashmap)
    public int beautySum(String s) {
        // Variable to store the total beauty sum of all substrings
        int sum = 0;

        // Loop through each starting index of substring
        for (int i = 0; i < s.length(); i++) {

            // HashMap to store the frequency of each character in the current substring
            HashMap<Character, Integer> map = new HashMap<>();

            // Extend the substring from index i to j
            for (int j = i; j < s.length(); j++) {

                // Add/update character frequency in HashMap
                map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);

                // Initialize maxi as smallest possible, mini as largest possible value
                int maxi = Integer.MIN_VALUE;
                int mini = Integer.MAX_VALUE;

                // Iterate over all frequency values to find max and min frequency
                for (int val : map.values()) {
                    maxi = Math.max(maxi, val);
                    mini = Math.min(mini, val);
                }

                // Add beauty of current substring (max frequency - min frequency)
                sum += (maxi - mini);
            }
        }

        // Return the total beauty of all substrings
        return sum;
    }
}

// Output :
// The sum of beauty of all substrings of "aabcb" is: 5

// Algorithm :
/*
1. Initialize a variable `sum` to store the total beauty sum of all substrings.
2. Loop through each starting index `i` of the substring from 0 to length of string - 1.
3. For each starting index `i`, create a new HashMap to store the frequency of characters in the current substring.
4. Extend the substring by looping through each ending index `j` from `i` to length of string - 1:
   a. Update the frequency of the character at index `j` in the HashMap.
   b. Initialize `maxi` to the smallest possible integer and `mini` to the largest possible integer.
   c. Iterate over the frequency values in the HashMap to find the maximum and minimum frequencies.
   d. Calculate the beauty of the current substring as `maxi - mini` and add it to `sum`.
5. After processing all substrings, return the total beauty sum `sum`.
 */

// Intuition :
/*
The beauty of a substring is defined as the difference between the maximum and minimum frequency of characters
in that substring. To find the sum of beauty for all substrings, we can use a nested loop approach where we
consider every possible substring by varying the starting and ending indices. For each substring, we maintain
a frequency map of characters and compute the maximum and minimum frequencies to determine its beauty.
By accumulating these beauty values for all substrings, we arrive at the final result.
 */

// Striver's (Article Explanation): https://takeuforward.org/data-structure/sum-of-beauty-of-all-substrings/