package Strings;

public class Remove_All_Occurrences {

    // Main Function
    public static void main(String[] args) {
        Remove_All_Occurrences sol = new Remove_All_Occurrences();

        String s1 = "daabcbaabcbc";
        String part1 = "abc";
        System.out.println(sol.removeOccurrences(s1, part1)); // Output: "dab"

        String s2 = "axxxxyyyyb";
        String part2 = "xy";
        System.out.println(sol.removeOccurrences(s2, part2)); // Output: "ab"
    }

    // Method 1: Optimal Approach (Using String's inbuilt methods)
    // Time Complexity: O(n * m) in the worst case, where n is the length of s and m is the length of part.
    // Space Complexity: O(1) if we ignore the space used for the output string.
    public String removeOccurrences(String s, String part) {
        // Keep removing 'part' from 's' while it exists
        while (s.contains(part)) {
            s = s.replaceFirst(part, ""); // Replace the first occurrence of 'part' with an empty string
        }
        return s; // Return the modified string
    }
}

// Output:
// dab
// ab

// Algorithm: Iterative Removal
/*
1. While the string s contains the substring part:
    1.1 Replace the first occurrence of part in s with an empty string.
2. Return the modified string s.
 */

// Apna College (Video Explanation): https://www.youtube.com/watch?v=dSRFgEs3a6A
