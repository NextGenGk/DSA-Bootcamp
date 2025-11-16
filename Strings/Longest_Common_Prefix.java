package Strings;

import java.util.Arrays;

public class Longest_Common_Prefix {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N log N), where N is the number of strings in the array.
    // Space Complexity: O(1), no extra space is needed.
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder res = new StringBuilder();

        // Sort the array
        Arrays.sort(strs);

        // Get the first and last element
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();

        // Start Comparing
        for (int i = 0; i < first.length; i++) {
            if (first[i] != last[i]) {
                break;
            }
            res.append(first[i]);
        }

        // Return the result
        return res.toString();
    }

    // Main Function
    public static void main(String[] args) {
        Longest_Common_Prefix lcp = new Longest_Common_Prefix();
        String[] strs = {"flower", "flow", "flight"};
        String result = longestCommonPrefix(strs);
        System.out.println("Longest Common Prefix: " + result);
    }
}

// Output -
/*
Input: strs = ["flower","flow","flight"]
Output: "fl"
*/

// Algorithm -
/*
1. First, we sort the array.
2. Then, we get the first and last element of the array.
3. Then, we iterate through the first's string length
4. If the first and last element are not equal, we break out of the loop.
5. Otherwise, we append the character to the result string.
*/

// Striver's (Article Explanation): https://takeuforward.org/data-structure/longest-common-prefix
