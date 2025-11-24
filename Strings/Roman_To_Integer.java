package Strings;

import java.util.HashMap;

public class Roman_To_Integer {

    // Main Function
    public static void main(String[] args) {
        Roman_To_Integer solution = new Roman_To_Integer();
        String romanNumeral = "MCMXCIV"; // Example: 1994
        int result = solution.romanToInt(romanNumeral);
        System.out.println("The integer value of the Roman numeral \"" + romanNumeral + "\" is: " + result);
    }

    // Method 1 - Optimal Solution
    // Time Complexity: O(n), where n is the length of the string.
    // Space Complexity: O(1), since the size of the HashMap is constant (7 Roman numerals).
    public int romanToInt(String s) {

        // Mapping Roman numerals to their integer values
        HashMap<Character, Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        int res = 0; // stores the final result

        // Loop through the string (except the last character)
        for (int i = 0; i < s.length() - 1; i++) {

            // If current value is less than the next one → subtract (subtractive notation)
            // Example: IV → I(1) < V(5) → 1 should be subtracted
            if (roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
                res -= roman.get(s.charAt(i));
            }
            // Otherwise, add the value
            else {
                res += roman.get(s.charAt(i));
            }
        }

        // Add the value of the last character (always added, no next-character check)
        res += roman.get(s.charAt(s.length() - 1));

        // Finally, return the result
        return res;
    }
}

// Output:
// The integer value of the Roman numeral "MCMXCIV" is: 1994

// Algorithm :
/*
1. Create a HashMap to map each Roman numeral character to its corresponding integer value.
2. Initialize a variable `res` to store the final integer result.
3. Loop through the string from the first character to the second-to-last character:
   a. For each character, compare its value with the value of the next character using the HashMap.
   b. If the current character's value is less than the next character's value, subtract the current value from `res` (this handles subtractive notation).
   c. Otherwise, add the current value to `res`.
4. After the loop, add the value of the last character in the string to `res` (since it is always added).
5. Return the final result `res`.
 */

// Striver's (Article Explanation): https://takeuforward.org/data-structure/roman-to-integer-conversion/
