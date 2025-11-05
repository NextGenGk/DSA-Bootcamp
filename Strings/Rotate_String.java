package Strings;

public class Rotate_String {

    // Main Function
    public static void main(String[] args) {
        Rotate_String rs = new Rotate_String();
        String s = "abcde";
        String goal = "cdeab";
        boolean result = rs.rotateString(s, goal);
        System.out.println("Input: s = \"" + s + "\", goal = \"" + goal + "\"");
        System.out.println("Output: " + result);
    }

    // Method 1: Optimal Approach
    // Time Complexity: O(n), where n is the length of the string.
    // Space Complexity: O(n), for the concatenated string.
    public boolean rotateString(String s, String goal) {
        return (s.length() == goal.length() && (s + s).contains(goal));
    }
}

// Output -
/*
Input: s = "abcde", goal = "cdeab"
Output: true
*/

/* Idea -
We can easily see whether it is rotated if goal can be found in (s + s).
For example, with s = "abcde", goal = "cdeab", we have

“abcdeabcde” (s + s)
  “cdeab” (goal)
goal is found in (s + s), so goal is a rotated string of A.
*/

// Strivers's (Article Explanation): https://takeuforward.org/data-structure/rotate-string-check-if-one-string-is-rotation-of-another/