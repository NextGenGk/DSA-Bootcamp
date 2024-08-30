package Sliding_Window_and_Two_Pointers;

public class Longest_Repeating_Character_Replacement {

    // Method 1 : Brute Force
    // Time Complexity: O(N^2 * 26), N is the length of the string and 26 accounts for the hash
    // array size (for each character in the alphabet).
    // Space Complexity : O(26) for the hash array.
    public static int characterReplacement(String s, int k) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            int[] hashArray = new int[26];  // Array to count frequency of characters in the current window
            int maxFreq = 0;  // Maximum frequency of any character in the current window

            for (int j = i; j < n; j++) {
                hashArray[s.charAt(j) - 'A']++;  // Increment the frequency of the current character
                maxFreq = Math.max(maxFreq, hashArray[s.charAt(j) - 'A']);  // Update the max frequency

                // Calculate the number of replacements needed
                int numReplacements = (j - i + 1) - maxFreq;

                // If the number of replacements is within the allowed limit k
                if (numReplacements <= k) {
                    maxLength = Math.max(maxLength, j - i + 1);  // Update the maximum length
                }
            }
        }

        return maxLength;
    }

    // Method 2 : Better Solution
    // Time Complexity : O(2N * 26)
    // Space Complexity : O(26)
    public static int characterReplacement1(String s, int k) {
        int n = s.length();  // Length of the input string
        int left = 0;  // Left pointer for the sliding window
        int right = 0;  // Right pointer for the sliding window
        int maxLength = 0;  // Variable to store the maximum length of the valid substring
        int[] hashArray = new int[26];  // Array to keep track of the frequency of characters in the current window
        int maxFreq = 0;  // Variable to store the maximum frequency of any character in the current window

        while (right < n) {  // Expand the sliding window until the right pointer reaches the end of the string
            // Increment the frequency of the character at the right pointer
            hashArray[s.charAt(right) - 'A']++;

            // Update maxFreq to the maximum frequency of any character in the current window
            maxFreq = Math.max(maxFreq, hashArray[s.charAt(right) - 'A']);

            // If the number of characters that need to be replaced exceeds k, shrink the window
            while ((right - left + 1) - maxFreq > k) {
                // Decrease the frequency of the character at the left pointer since it is about to be excluded from the window
                hashArray[s.charAt(left) - 'A']--;

                // Recalculate maxFreq after shrinking the window
                maxFreq = 0;  // Reset maxFreq to recalculate it
                for (int i = 0; i < hashArray.length; i++) {
                    maxFreq = Math.max(maxFreq, hashArray[i]);  // Find the maximum frequency in the current window
                }
                left++;  // Move the left pointer to the right to shrink the window
            }

            // Calculate the maximum length of the valid substring where we can replace up to k characters
            if ((right - left + 1) - maxFreq <= k) {
                maxLength = Math.max(maxLength, right - left + 1);  // Update maxLength if the current window is valid
            }

            right++;  // Move the right pointer to the right to expand the window
        }

        return maxLength;  // Return the maximum length of the valid substring found
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(26)
    public static int characterReplacement2(String s, int k) {
        int n = s.length();  // Length of the input string
        int left = 0;  // Left pointer for the sliding window
        int right = 0;  // Right pointer for the sliding window
        int maxLength = 0;  // Variable to store the maximum length of a valid substring
        int[] hashArray = new int[26];  // Array to keep track of the frequency of characters in the current window
        int maxFreq = 0;  // Variable to store the maximum frequency of any single character in the current window

        while (right < n) {  // Expand the sliding window until the right pointer reaches the end of the string
            // Increment the frequency of the character at the right pointer
            hashArray[s.charAt(right) - 'A']++;

            // Update maxFreq to the maximum frequency of any character in the current window
            maxFreq = Math.max(maxFreq, hashArray[s.charAt(right) - 'A']);

            // Check if the number of characters to be replaced exceeds k
            if ((right - left + 1) - maxFreq > k) {
                // If yes, decrease the frequency of the character at the left pointer since it's excluded from the window
                hashArray[s.charAt(left) - 'A']--;
                left++;  // Move the left pointer to the right to shrink the window
            }

            // After adjusting the window, calculate the maximum length of a valid substring where up to k characters can be replaced
            maxLength = Math.max(maxLength, right - left + 1);  // Update maxLength if the current window is valid

            right++;  // Move the right pointer to the right to expand the window
        }

        return maxLength;  // Return the maximum length of a valid substring found
    }

    // Main Function
    public static void main(String[] args) {
        String str = "AABABBA";
        int k = 1;
        int res = characterReplacement2(str, k);
        System.out.println(res);
    }
}

// Output : 4

// Approach : Brute Force
/*
This code iterates through every possible substring of the given string, using a hash array to track
the frequency of each character within the substring. For each substring, it calculates how many
characters need to be replaced to make all characters the same, leveraging the frequency of the most
common character. If the number of replacements required is within the allowed limit (`k`), it updates
the maximum possible length of such a substring. The approach essentially tries to find the longest
substring that can be transformed into a uniform character string by replacing up to `k` characters.

Algorithm :
1. Iterate through each starting point of the substring.
2. For each starting point, create a hash array to store the frequency of characters in the current substring.
3. For every possible ending point, update the hash array, then calculate how many replacements are needed.
4. If the replacements are within the allowed limit (k), update the maximum length.
 */

// Approach : Better Solution
/*
The intuition behind this code is to use a sliding window to find the longest substring where you can
replace up to `k` characters to make all characters in the substring the same. By maintaining a frequency
count of characters in the current window, the code tracks the most frequent character (`maxFreq`) and
ensures that the number of characters that need to be replaced (i.e., the total characters in the
window minus `maxFreq`) does not exceed `k`. If it does, the window is adjusted by moving the left
pointer to maintain validity, and the maximum length of such a valid window is continuously updated.
 */

// Approach : Optimal Solution
/*
The intuition behind this code is to find the longest substring in which you can replace up to `k`
characters to make all the characters in the substring the same. The code uses a sliding window approach
to maintain a window of characters, tracking the frequency of each character within that window. The key
idea is to keep the most frequent character in the window unchanged and determine if the remaining
characters can be transformed into this character by replacing up to `k` of them. If the number of
replacements needed exceeds `k`, the window is adjusted by moving the left pointer to shrink the
window. This approach ensures that we find the maximum possible length of a valid substring in an
efficient manner.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=_eNhaDCr6P0