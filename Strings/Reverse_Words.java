package Strings;

public class Reverse_Words {

    // Main Function
    public static void main(String[] args) {
        Reverse_Words sol = new Reverse_Words();

        String s1 = "the sky is blue";
        System.out.println(sol.reverseWords(s1)); // Output: "blue is sky the"

        String s2 = "  hello world  ";
        System.out.println(sol.reverseWords(s2)); // Output: "world hello"

        String s3 = "a good   example";
        System.out.println(sol.reverseWords(s3)); // Output: "example good a"
    }

    // Method 1: Reverse Words by Traversing Backwards
    // Time Complexity: O(n), where n is the length of the string.
    // Space Complexity: O(n) for the result string.
    public String reverseWords(String str) {
        // To store the final reversed words
        StringBuilder result = new StringBuilder();

        // Start from the last character of the string
        int i = str.length() - 1;

        // Process the string from right to left
        while (i >= 0) {

            // Skip trailing spaces (ignore multiple spaces at the end or between words)
            while (i >= 0 && str.charAt(i) == ' ') {
                i--;
            }

            // Mark the end of a word
            int j = i;

            // If we already reached before the first character, break
            if (i < 0)
                break;

            // Move left until a space or beginning of string is found
            while (i >= 0 && str.charAt(i) != ' ') {
                i--;
            }

            // Extract the word between (i+1) and j
            String word = str.substring(i + 1, j + 1);

            // If this is the first word, directly append
            if (result.isEmpty()) {
                result.append(word);
            }
            // Otherwise, add a space before appending the next word
            else {
                result.append(" ").append(word);
            }
        }

        // Return the reversed words as a string
        return result.toString();
    }
}

// Output:
// blue is sky the
// world hello

// Algorithm: Reverse Words by Traversing Backwards
/*
1. Initialize an empty StringBuilder to store the result.
2. Start from the end of the string and move backwards:
    2.1 Skip any trailing spaces.
    2.2 Mark the end of a word.
    2.3 Move left until a space or the beginning of the string is found to identify the start of the word.
    2.4 Extract the word and append it to the result, adding a space if it's not the first word.
3. Return the result as a string.
 */

// Apna College (Video Explanation): https://www.youtube.com/watch?v=RitppzIdMCo&list=PLfqMhTWNBTe137I_EPQd34TsgV6IO55pt