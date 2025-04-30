package SlidingWindowTwoPointers;

import java.util.HashSet;

public class Longest_Substring_without_Repeating_Character {

    // Method 1 : Brute Force
    // Time Complexity : O(N^3)
    // Space Complexity : O(N)
    // Method to find the length of the longest substring without repeating characters
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();  // Get the length of the input string
        int maxLength = 0;  // Variable to store the maximum length of a valid substring

        // Outer loop: iterate over all possible starting points of substrings
        for (int i = 0; i < n; i++) {
            // Inner loop: iterate over all possible ending points of substrings
            for (int j = 0; j < n; j++) {
                // HashSet to keep track of characters in the current substring
                HashSet<Character> seenChars = new HashSet<Character>();
                boolean isValid = true;  // Flag to check if the current substring is valid (no repeating characters)

                // Loop through the characters of the current substring (from index i to j)
                for (int k = i; k <= j; k++) {
                    char c = s.charAt(k);  // Get the character at index k

                    // If the character is already in the set, it means there's a duplicate
                    if (seenChars.contains(c)) {
                        isValid = false;  // Mark the substring as invalid
                        break;  // Exit the loop as we found a duplicate
                    }
                    seenChars.add(c);  // Add the character to the set
                }

                // If the current substring is valid (no duplicates), update the maxLength
                if (isValid) {
                    maxLength = Math.max(maxLength, j - i + 1);  // Calculate the length of the current substring
                }
            }
        }

        // Return the length of the longest substring without repeating characters
        return maxLength;
    }

    // Method 2 : Better Solution
    // Time Complexity : O(N^2)
    // Space Complexity : O(N)
    // Method to find the length of the longest substring without repeating characters
    public static int lengthOfLongestSubstring1(String s) {
        int n = s.length();  // Get the length of the input string
        int maxLength = 0;  // Variable to store the maximum length of a valid substring

        // Outer loop: iterate over all possible starting points of substrings
        for (int i = 0; i < n; i++) {
            // HashSet to track characters in the current substring starting at index i
            HashSet<Character> seenChars = new HashSet<>();
            boolean isValid = true;  // Flag to check if the current substring is valid (no repeating characters)

            // Inner loop: iterate over all possible ending points of the current substring
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);  // Get the character at index j

                // Check if the current character is already in the set (i.e., it's a duplicate)
                if (seenChars.contains(c)) {
                    isValid = false;  // Mark the substring as invalid due to repetition
                    break;  // Break out of the inner loop since we've found a duplicate
                }

                // Add the current character to the set if it's unique
                seenChars.add(c);

                // If the substring is still valid, update the maximum length
                if (isValid) {
                    maxLength = Math.max(maxLength, j - i + 1);  // Calculate the length of the current valid substring
                }
            }
        }

        // Return the length of the longest substring without repeating characters
        return maxLength;
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N) where N is the length of the string. Each character is processed at most twice,
    // once by the right pointer and once by the left pointer when duplicates are found.
    // Space Complexity : The space complexity is O(N), mainly due to the HashSet,
    // which stores characters of a substring.
    // Method to find the length of the longest substring without repeating characters
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();  // Get the length of the string
        int left = 0;  // Left pointer for the sliding window
        int right = 0;  // Right pointer for the sliding window
        int maxLength = 0;  // Variable to track the maximum length of substring without repeating characters
        HashSet<Character> set = new HashSet<>();  // HashSet to store the characters in the current window

        // Loop to move the right pointer across the string
        while (right < n) {
            char c = s.charAt(right);  // Get the character at the right pointer

            // If the current character is a duplicate (already in the set)
            // slide the left pointer to the right until the duplicate is removed
            while (set.contains(c)) {
                set.remove(s.charAt(left));  // Remove the character at the left pointer from the set
                left++;  // Move the left pointer to the right to shrink the window
            }

            // Add the current character (pointed by right) to the set
            set.add(c);

            // Update the maximum length by comparing the current window size (right - left + 1) with the existing maxLength
            maxLength = Math.max(maxLength, right - left + 1);

            // Move the right pointer to the next character
            right++;
        }

        // Return the length of the longest substring without repeating characters
        return maxLength;
    }

    // Main Function
    public static void main(String[] args) {
        String str = "abcabcbb";
        int result = lengthOfLongestSubstring2(str);
        System.out.println(result);
    }
}

// Output : 3

// Approach : Brute Force
// Generate all the substrings and find the longest length of the substring. (Using 3 Loops)

// Approach : Better Solution
// Generate all the substrings and find the longest length of the substring. (Using 2 Loops)

// Approach : Optimal Solution
/*
This code uses the sliding window technique to find the length of the longest substring without
repeating characters. It maintains a dynamic window defined by two pointers, left and right,
which expand and contract as necessary. As the right pointer moves through the string, characters
are added to a HashSet. If a duplicate character is encountered, the left pointer advances to
shrink the window by removing characters from the HashSet until the duplicate is eliminated.
This ensures that the substring within the window remains unique. The length of the window is
tracked and updated throughout, giving the maximum length of a substring without repeating characters.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=-zSxTJkcdAo
