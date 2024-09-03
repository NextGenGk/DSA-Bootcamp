package Sliding_Window_and_Two_Pointers;

public class Minimum_Window_Substring {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(256)
    public static String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        int minLength = Integer.MAX_VALUE;
        int startingIndex = -1;

        for (int i = 0; i < m; i++) { // Adjusted loop to prevent out-of-bound
            int[] hash = new int[256];
            int cnt = 0;

            // Count frequency of characters in t
            for (int j = 0; j < n; j++) {
                hash[t.charAt(j)]++;
            }

            // Iterate over substring of s starting at index i
            for (int j = i; j < m; j++) {
                if (hash[s.charAt(j)] > 0) cnt++;
                hash[s.charAt(j)]--;

                // Check if the current window contains all characters of t
                if (cnt == n) {
                    if ((j - i + 1) < minLength) {
                        minLength = j - i + 1;
                        startingIndex = i;
                    }
                    break;
                }
            }
        }

        // Ensure valid substring is returned
        return startingIndex == -1 ? "" : s.substring(startingIndex, startingIndex + minLength);
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(2N) + O(M), O(2N) for traversing all the characters at most twice in worst case
    // & O(M) for inserting all the characters in the hashArray.
    // Space Complexity : O(256), size of the hashArray with all the unique character in alphabets.
    public static String minWindow1(String s, String t) {
        // Get the lengths of the strings s and t
        int m = s.length();
        int n = t.length();

        // Initialize variables to store the minimum window length and starting index
        int minLength = Integer.MAX_VALUE;
        int startingIndex = -1;

        // Variable to count how many characters of t are found in the current window
        int cnt = 0;

        // Hash array to track character frequencies in t
        int[] hash = new int[256];

        // Pointers to represent the current window's left and right boundaries
        int left = 0;
        int right = 0;

        // Populate the hash array with the frequency of characters in t
        for (int i = 0; i < n; i++) {
            hash[t.charAt(i)]++;
        }

        // Expand the window by moving the right pointer
        while (right < m) {
            // If the character at the right pointer is part of t, increase the count
            if (hash[s.charAt(right)] > 0) {
                cnt++;
            }

            // Decrease the frequency of the current character in the hash array
            hash[s.charAt(right)]--;

            // Check if the current window contains all characters of t
            while (cnt == n) {
                // Update the minimum length and starting index if the current window is smaller
                if ((right - left + 1) < minLength) {
                    minLength = right - left + 1;
                    startingIndex = left;
                }

                // Increase the frequency of the character at the left pointer back in the hash array
                hash[s.charAt(left)]++;

                // If the character at the left pointer is part of t and is no longer fully matched, decrease the count
                if (hash[s.charAt(left)] > 0) {
                    cnt--;
                }

                // Move the left pointer to the right
                left++;
            }

            // Move the right pointer to the right
            right++;
        }

        // If no valid window is found, return an empty string; otherwise, return the minimum window
        return startingIndex == -1 ? "" : s.substring(startingIndex, startingIndex + minLength);
    }

    // Main Function
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        // Call the minWindow1 function and print the result
        String result = minWindow1(s, t);
        System.out.println("The smallest window in '" + s + "' containing all characters of '" + t + "' is: '" + result + "'");
    }
}

// Output :

// Approach : Brute Force
/*
The code aims to find the smallest substring in `s` that contains all the characters of `t`. It does this
by iterating over all possible starting points in `s`, then checks each potential substring starting from
that index to see if it contains all characters from `t`. It uses a frequency array to track the occurrence
of characters from `t` within the current window of `s`. If the window contains all characters of `t`, the
code updates the minimum length of the valid substring. Finally, it returns the smallest valid substring
found, or an empty string if none is found. The approach is a brute-force solution, checking every possible
window in `s`.
 */

// Approach : Optimal Solution
/*
The code is designed to find the smallest substring in string `s` that contains all the characters of
string `t` using a sliding window approach. The idea is to maintain a window that expands by moving the
right pointer and shrinks by moving the left pointer. The algorithm uses a hash array to keep track of
character frequencies in `t`. As the right pointer moves, characters from `s` are included in the window,
and their frequencies are updated. Once all characters of `t` are found within the window, the left
pointer moves to minimize the window size while ensuring it still contains all characters of `t`.
The process continues until the entire string `s` is processed, and the smallest valid window is returned.
If no such window exists, an empty string is returned.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=WJaij9ffOIY