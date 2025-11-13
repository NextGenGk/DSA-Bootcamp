package Strings;

public class Reverse_Word_in_a_String {

    // Main Function
    public static void main(String[] args) {
        Reverse_Word_in_a_String solution = new Reverse_Word_in_a_String();
        String input = "  Hello   World  from   Java  ";
        String result = solution.reverseWords(input);
        System.out.println("Reversed Words: \"" + result + "\"");
    }

    // Method 1: Optimal Solution

    // Time Complexity: O(N), where N is the length of the string.
    // 1. You scan each character of the string once from right to left.
    // 2. substring operations take O(k) for each word, but since the total characters processed
    // across all substrings = n, the overall time stays O(n)
    // 3. Appending to StringBuilder is O(1) amortized per character.

    // Space Complexity: O(N), for the StringBuilder to store the result.
    // 1. You use a StringBuilder to store the reversed result.
    // 2. In the worst case (no extra spaces), the output will contain all n characters.
    public String reverseWords(String str) {
        // StringBuilder to build the final reversed sentence
        StringBuilder sb = new StringBuilder();

        // Start scanning from the last character
        int i = str.length() - 1;

        while (i >= 0) {

            // Skip trailing spaces at the end (and also multiple spaces in between)
            while (i >= 0 && str.charAt(i) == ' ') {
                i--;
            }

            // Mark the end of the current word
            int j = i;

            // If all characters were spaces, break
            if (i < 0) {
                break;
            }

            // Move i to the start of the current word
            while (i >= 0 && str.charAt(i) != ' ') {
                i--;
            }

            // Extract the word from i+1 to j (inclusive)
            String word = str.substring(i + 1, j + 1);

            // Add the word to the result
            // If it's the first word, add directly; otherwise add a space before it
            if (sb.isEmpty()) {
                sb.append(word);
            } else {
                sb.append(" ").append(word);
            }
        }

        // Return the reversed sentence
        return sb.toString();
    }
}

// Output:
// Reversed Words: "Java from World Hello"

// Algorithm:
/*
1. Initialize a StringBuilder to build the final reversed sentence.
2. Start scanning the input string from the last character.
3. While the current index is valid (i >= 0):
    3.1 Skip any trailing spaces by decrementing the index.
    3.2 Mark the end of the current word.
    3.3 If all characters were spaces, break the loop.
    3.4 Move the index to the start of the current word.
    3.5 Extract the word using substring from i+1 to j (inclusive).
    3.6 Append the extracted word to the StringBuilder, adding a space if it's not the first word.
4. Return the final reversed sentence as a string.
 */

// Intuition:
/*
Reversing the words in a sentence can be efficiently achieved by scanning the string from the end to the beginning.
This approach allows us to easily identify words and handle multiple spaces without needing extra space for splitting
the string. By using a StringBuilder, we can efficiently build the final result while maintaining the correct order of words.
 */

// Striver's (Article Explanation): https://takeuforward.org/data-structure/reverse-words-in-a-string
