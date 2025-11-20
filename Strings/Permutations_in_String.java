package Strings;

public class Permutations_in_String {

    // Main Function
    public static void main(String[] args) {
        // Creating an object
        Permutations_in_String sol = new Permutations_in_String();

        // Examples
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(sol.checkInclusion(s1, s2)); // Output: true

        String s3 = "ab";
        String s4 = "eidboaoo";
        System.out.println(sol.checkInclusion(s3, s4)); // Output: false
    }

    // Method 1 : Optimal Approach (Sliding Window Approach + Frequency Array)
    // Time Complexity: O(n), where n is the length of s2.
    // Space Complexity: O(1) as the frequency arrays have a fixed size of 26.
    public boolean checkInclusion(String s1, String s2) {
        // If s1 is longer than s2, it's impossible for s2 to contain a permutation
        if (s1.length() > s2.length()) return false;

        // Frequency arrays for characters in s1 and current window in s2
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        // Build frequency of s1
        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i) - 'a']++;
        }

        // Store the window size
        int windowSize = s1.length();

        // Build the first window in s2 of size = windowSize
        for (int i = 0; i < windowSize; i++) {
            freq2[s2.charAt(i) - 'a']++;
        }

        // Check if first window already matches
        if (matches(freq1, freq2)) return true;

        // Slide the window across s2
        for (int i = windowSize; i < s2.length(); i++) {
            // Add new character entering the window
            freq2[s2.charAt(i) - 'a']++;

            // Remove character going out of the window
            freq2[s2.charAt(i - windowSize) - 'a']--;

            // After update, check for match
            if (matches(freq1, freq2)) return true;
        }

        // No window matched than return false
        return false;
    }

    // Helper method to check if two frequency arrays match
    private boolean matches(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}

// Output:
// true
// false

// Algorithm: Sliding Window with Frequency Arrays
/*
1. If the length of s1 is greater than s2, return false immediately.
2. Create two frequency arrays of size 26 (for each letter in the alphabet) to count character occurrences in s1 and the current window in s2.
3. Populate the frequency array for s1.
4. Initialize the first window in s2 of size equal to s1 and populate its frequency array.
5. Check if the frequency arrays match; if they do, return true.
6. Slide the window across s2:
    6.1 Add the new character entering the window to the frequency array.
    6.2 Remove the character that is leaving the window from the frequency array.
    6.3 After each slide, check if the frequency arrays match; if they do, return true.
7. If no matching window is found after sliding through s2, return false.
 */

// Apna College (Video Explanation): https://www.youtube.com/watch?v=VXewy91P0S4
