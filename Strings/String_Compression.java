package Strings;

public class String_Compression {

    // Main Function
    public static void main(String[] args) {
        String_Compression sol = new String_Compression();

        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c' };
        int newLength1 = sol.compress(chars1);
        System.out.println("Compressed Length: " + newLength1); // Output: 6
        System.out.print("Compressed Array: ");
        for (int i = 0; i < newLength1; i++) {
            System.out.print(chars1[i] + " ");
        }
        System.out.println();

        char[] chars2 = {'a' };
        int newLength2 = sol.compress(chars2);
        System.out.println("Compressed Length: " + newLength2); // Output: 1
        System.out.print("Compressed Array: ");
        for (int i = 0; i < newLength2; i++) {
            System.out.print(chars2[i] + " ");
        }
        System.out.println();
    }

    // Method 1 : Optimal Approach (Two Pointers Approach)
    // Time Complexity: O(n), where n is the length of the input array.
    // Space Complexity: O(1) as we are using only constant space.
    public int compress(char[] chars) {
        int i = 0;           // Read pointer
        int write = 0;       // Write pointer

        while (i < chars.length) {
            char current = chars[i];
            int count = 0;
            // Count occurrences of current char
            while (i < chars.length && chars[i] == current) {
                i++;
                count++;
            }
            // Write the character
            chars[write++] = current;

            /*
            Detailed Explanation
            1. if (count > 1) { ... }
                i. Only runs when a character appears more than once consecutively, since for a single occurrence,
                the count is omitted by convention in string compression.

            2. String cntStr = Integer.toString(count);
                i. Converts the integer count (e.g., 12) into its string representation ("12"). This is necessary
                   because direct casting from int to char yields the corresponding Unicode character, not the digit
                   character you want in output.

            3. for (char c : cntStr.toCharArray()) { ... }
                i. Converts the string (such as "12") to a char array (['1','2']), so each digit can be written
                   separately to the compressed array.

            4. chars[write++] = c;
                i. Writes each count digit (as chars) into the chars array at the current write position, and advances
                   the pointer for each digit.
             */
            // Write each digit of count if > 1
            if (count > 1) {
                String cntStr = Integer.toString(count);
                for (char c : cntStr.toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        return write;
    }
}

// Output :
// Compressed Length: 6
// Compressed Array: a 2 b 2 c 3

// Intuition:
/*
The idea is to use two pointers: one for reading the input array and another for writing the compressed characters.
We count consecutive characters and write the character followed by its count (if greater than 1) to the array.
 */

// Algorithm: Two Pointer Approach
/*
1. Initialize two pointers: `i` (read pointer) and `write` (write pointer).
2. While `i` is less than the length of the array:
    2.1 Set `current` to `chars[i]` and initialize `count` to 0.
    2.2 Count occurrences of `current` by incrementing `i` and `count` while `chars[i]` equals `current`.
    2.3 Write `current` to `chars[write]` and increment `write`.
    2.4 If `count` is greater than 1, convert `count` to a string and write each digit to `chars[write]`, incrementing `write` for each digit.
3. Return `write` as the new length of the compressed array.
 */

// Apna College (Video Explanation): https://www.youtube.com/watch?v=cAB15h6-sWA
