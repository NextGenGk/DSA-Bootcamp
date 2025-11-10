package Strings;

import java.util.HashMap;

public class Count_Number_of_Substrings {

    // Helper Function
    // Time Complexity: O(N), where N is the length of the string.
    // Space Complexity: O(K), where K is the number of distinct characters in the map.
    // Returns substrings with at most K distinct characters
    public static int atMostKDistinct(String s, int k) {

        // HashMap to store character frequency in current window
        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;        // Left pointer of sliding window
        int count = 0;       // Total count of substrings with at most k distinct chars

        // Right pointer expands the window
        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // Insert the current character into the map (or increase its frequency)
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // If window has more than k distinct characters, shrink it from the left
            while (map.size() > k) {
                char leftChar = s.charAt(left);

                // Reduce the frequency of the leftmost character
                map.put(leftChar, map.get(leftChar) - 1);

                // If frequency becomes zero, remove it from the map
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                // Move left pointer ahead to shrink the window
                left++;
            }

        /*
          At this point, the window from left → right has at most k distinct characters.
          Number of NEW substrings created ending at 'right' is:
          (right - left + 1)

          Why?
          If left = 2 and right = 5, valid substrings are:
          s[5:5], s[4:5], s[3:5], s[2:5] → total = 4 = (5 - 2 + 1)
        */
            count += (right - left + 1);
        }

        return count;
    }

    // Method 1 - Optimal Solution
    // Time Complexity: O(N), where N is the length of the string.
    // Space Complexity: O(K), where K is the number of distinct characters in the map.
    // Returns substrings with EXACTLY K distinct characters
    public static int exactlyKDistinct(String s, int k) {
        return atMostKDistinct(s, k) - atMostKDistinct(s, k - 1);
    }

    // Main Function
    public static void main(String[] args) {
        String s = "pqpqs";
        int k = 2;

        System.out.println(exactlyKDistinct(s, k)); // Output: 7
    }
}

// Output:
// 7

// Explanation:
// The substrings of "pqpqs" that contain exactly 2 distinct characters are:
// "pq", "pqp", "qp", "qpq", "pq", "pqs", "qs"
// Thus, the output is 7.

// Algorithm :
/*
1. Define a helper function `atMostKDistinct` that counts the number of substrings with at most K distinct
   characters using a sliding window approach.
2. In the main function `exactlyKDistinct`, calculate the number of substrings with exactly K distinct characters
   by subtracting the count of substrings with at most (K-1) distinct characters from the count of substrings with
   at most K distinct characters.
 */

// Intuition :
/*

| Function    | What it counts                              |
| ----------- | ------------------------------------------- |
| `atMost(2)` | all substrings having 1 or 2 distinct chars |
| `atMost(1)` | all substrings having only 1 distinct char  |

 */

// Striver's (Article Explanation): https://takeuforward.org/data-structure/count-number-of-substrings-with-exactly-k-distinct-characters/