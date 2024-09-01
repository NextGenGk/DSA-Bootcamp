package Sliding_Window_and_Two_Pointers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Longest_Substring_with_At_Most_K_Distinct_Characters {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(256)
    public static int kDistinctChars(String str, int k) {
        // Function to find the length of the longest substring with exactly k distinct characters
        int n = str.length(); // Get the length of the input string
        int maxLength = 0; // Initialize the maximum length of the substring to 0

        // Loop through each character in the string as the starting point of the substring
        for (int i = 0; i < n; i++) {
            // Use a HashMap to keep track of character frequencies in the current substring
            HashMap<Character, Integer> map = new HashMap<Character, Integer>();

            // Loop through the string starting from the current position 'i'
            for (int j = i; j < n; j++) {
                // Add the current character to the map or update its frequency
                map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + 1);

                // If the size of the map (i.e., number of distinct characters) exceeds k, break the loop
                if (map.size() > k) {
                    break;
                }

                // If the size of the map is exactly k, calculate the length of the current substring
                // and update maxLength if the current substring is longer than the previous ones
                if (map.size() == k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        // Return the length of the longest substring with exactly k distinct characters
        return maxLength;
    }

    // Method 2 : Better Solution
    // Time Complexity : O(2N)
    // Space Complexity : O(256)
    public static int kDistinctChars1(String str, int k) {
        // Function to find the length of the longest substring with exactly k distinct characters
        int n = str.length(); // Get the length of the input string
        int left = 0; // Initialize the left pointer of the sliding window
        int right = 0; // Initialize the right pointer of the sliding window
        int maxLength = 0; // Initialize the maximum length of the substring to 0
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // HashMap to track character frequencies

        // Expand the window with the right pointer
        while (right < n) {
            // Add the current character to the map or update its frequency
            map.put(str.charAt(right), map.getOrDefault(str.charAt(right), 0) + 1);

            // If the number of distinct characters in the window exceeds k, shrink the window from the left
            while (map.size() > k) {
                // Decrease the frequency of the character at the left pointer
                map.put(str.charAt(left), map.get(str.charAt(left)) - 1);

                // If the frequency becomes 0, remove the character from the map
                if (map.get(str.charAt(left)) == 0) {
                    map.remove(str.charAt(left));
                }

                // Move the left pointer to the right to shrink the window
                left++;
            }

            // If the window contains exactly k distinct characters, update the maximum length
            if (map.size() == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            // Move the right pointer to the right to expand the window
            right++;
        }

        // Return the length of the longest substring with exactly k distinct characters
        return maxLength;
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(256)
    public static int kDistinctChars2(String str, int k) {
        // Function to find the length of the longest substring with exactly k distinct characters
        int n = str.length(); // Get the length of the input string
        int left = 0; // Initialize the left pointer of the sliding window
        int right = 0; // Initialize the right pointer of the sliding window
        int maxLength = 0; // Initialize the maximum length of the substring to 0
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // HashMap to track character frequencies

        // Expand the window with the right pointer
        while (right < n) {
            // Add the current character to the map or update its frequency
            map.put(str.charAt(right), map.getOrDefault(str.charAt(right), 0) + 1);

            // If the number of distinct characters in the window exceeds k, shrink the window from the left
            if (map.size() > k) {
                // Decrease the frequency of the character at the left pointer
                map.put(str.charAt(left), map.get(str.charAt(left)) - 1);

                // If the frequency becomes 0, remove the character from the map
                if (map.get(str.charAt(left)) == 0) {
                    map.remove(str.charAt(left));
                }

                // Move the left pointer to the right to shrink the window
                left++;
            }

            // If the window contains exactly k distinct characters, update the maximum length
            if (map.size() == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            // Move the right pointer to the right to expand the window
            right++;
        }

        // Return the length of the longest substring with exactly k distinct characters
        return maxLength;
    }

    // Main Function
    public static void main(String[] args) {
        String str = "aabbcc";
        int k = 2;
        int res = kDistinctChars2(str, k);
        System.out.println(res);
    }
}

// Approach : Brute Force
/*
The intuition behind this code is to systematically explore all possible substrings in the given string
by starting from each character and expanding outwards while tracking the number of distinct characters
using a `HashMap`. By maintaining the frequency of each character, the code checks when the number of
distinct characters in the substring matches `k`. If it exceeds `k`, the loop breaks, and if it matches,
the length of the current substring is calculated and compared to find the maximum length of such a valid
substring. This approach ensures that all possible substrings are checked to find the longest one with
exactly `k` distinct characters.
 */

// Approach : Better Solution
/*
The intuition behind this code is to use a sliding window approach to efficiently find the longest
substring with exactly `k` distinct characters. The `right` pointer expands the window by adding characters
from the string and tracking their frequencies using a `HashMap`. If the number of distinct characters
exceeds `k`, the `left` pointer is moved to shrink the window until the count of distinct characters
is reduced back to `k` or less. This dynamic adjustment allows the code to maintain a valid substring
and continuously check if it's the longest one with exactly `k` distinct characters, resulting in an
optimal solution compared to checking all possible substrings.
 */

// Approach : Optimal Solution
/*
The intuition behind this code is to use a sliding window technique to efficiently find the longest substring
with exactly `k` distinct characters. The `right` pointer expands the window by adding characters and
tracking their frequencies using a `HashMap`. If the number of distinct characters exceeds `k`, the `left`
pointer is moved to shrink the window until the count of distinct characters is reduced back to `k` or less.
By dynamically adjusting the window, the code continuously checks for the longest valid substring, ensuring
an optimal solution without needing to examine all possible substrings.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=teM9ZsVRQyc