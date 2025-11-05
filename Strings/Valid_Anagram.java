package Strings;

public class Valid_Anagram {

    // Main Function
    public static void main(String[] args) {
        Valid_Anagram solution = new Valid_Anagram();
        String s = "anagram";
        String t = "nagaram";
        boolean result = solution.isAnagram(s, t);
        System.out.println("Are \"" + s + "\" and \"" + t + "\" anagrams? " + result);
    }

    // Method 1: Optimal Approach
    // Time Complexity: O(n), where n is the length of the strings.
    // Space Complexity: O(1), since the size of the alphabet array is constant (26).
    public boolean isAnagram(String s, String t) {
        // Step 1: If lengths differ, they cannot be anagrams
        if (s.length() != t.length()) return false;

        // Array to store frequency of characters (only lowercase a-z)
        int[] alphabet = new int[26];

        // Count each character from string s
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++; // increment the count of the character
        }

        // Reduce count based on characters in string t
        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--; // decrement the count of the character
        }

        // If any count is non-zero, strings are not anagrams
        for (int i : alphabet) {
            if (i != 0) return false;
        }

        // All character counts balanced → strings are anagrams
        return true;
    }
}

// Output :
// Are "anagram" and "nagaram" anagrams? true

// Algorithm :
/*
1. Check if the lengths of the two strings are equal. If not, return false.
2. Create an integer array of size 26 to store the frequency of each character (assuming only lowercase letters a-z).
3. Traverse the first string and increment the count for each character in the frequency array.
4. Traverse the second string and decrement the count for each character in the frequency array.
5. Finally, check the frequency array. If all counts are zero, the strings are anagrams; otherwise, they are not.
 */

// Striver's (Article Explanation): https://takeuforward.org/data-structure/check-if-two-strings-are-anagrams