package Strings;

public class isPalindrome {

    // Main Function
    public static void main(String[] args) {
        isPalindrome sol = new isPalindrome();

        System.out.println(sol.isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println(sol.isPalindrome("race a car")); // false
        System.out.println(sol.isPalindrome(" ")); // true
        System.out.println(sol.isPalindrome("No 'x' in Nixon")); // true
    }

    // Method 1: Simple Palindrome Check
    // Time Complexity: O(n), where n is the length of the string.
    // Space Complexity: O(1) as we are using only constant space.
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1; // fix: last index

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false; // mismatch found
            }
            i++;
            j--;
        }
        return true; // all matched
    }

    // Method 2: Palindrome Check with Alphanumeric and Case Insensitivity
    // Time Complexity: O(n), where n is the length of the string.
    // Space Complexity: O(1) as we are using only constant space.
    public boolean isPalindromev2(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            // skip non-alphanumeric chars
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            // compare ignoring case
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }

            i++;
            j--;
        }
        return true; // all matched
    }
}

// Output:
/*
true
false
true
true
 */

// Algorithm: Optimal Solution
/*
Simple Palindrome check
*/

// Algorithm: Optimal Solution (Two Pointer Approach) 
/*
1. Initialize two pointers, i at the start (0) and j at the end (length - 1) of the string.
2. While i is less than j:
    2.1 Move i forward if the character at i is not alphanumeric.
    2.2 Move j backward if the character at j is not alphanumeric.
    2.3 Compare the characters at i and j (ignoring case). If they don't match, return false.
    2.4 Increment i and decrement j.
3. If all characters matched, return true.
 */

// Apna College (Video Explanation): https://www.youtube.com/watch?v=dSRFgEs3a6A
