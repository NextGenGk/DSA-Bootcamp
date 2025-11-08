package Strings;

public class Integer_to_Roman {

    // Main Function
    public static void main(String[] args) {
        Integer_to_Roman solution = new Integer_to_Roman();
        int number = 1994; // Example input
        String result = solution.intToRoman(number);
        System.out.println("The Roman numeral representation of " + number + " is: " + result);
    }

    // Method 1 - Optimal Solution
    // Time Complexity: O(1), as the number of Roman numeral symbols is fixed.
    // Space Complexity: O(1), since we use a fixed amount of space.
    public String intToRoman(int num) {

        // All possible Roman numeral values (including subtractive forms)
        // Why these values?
        // These values cover all the unique Roman numeral symbols and their combinations
        // that are needed to represent any integer from 1 to 3999.
        // The subtractive forms (like 4 as IV, 9 as IX, etc.) are included
        // to ensure that the Roman numeral representation is in its standard form.
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        // Corresponding Roman symbols for each value
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        // StringBuilder used to efficiently build the result
        StringBuilder sb = new StringBuilder();

        // Iterate through all Roman numeral values (largest → smallest)
        for (int i = 0; i < values.length; i++) {

            // Greedy approach:
            // Subtract the highest possible Roman value until num becomes smaller
            while (num >= values[i]) {
                num -= values[i];         // reduce the number
                sb.append(symbols[i]);    // append the corresponding Roman symbol
            }
        }

        // Final Roman numeral string
        return sb.toString();
    }
}

// Output:
// The Roman numeral representation of 1994 is: MCMXCIV

// Algorithm :
/*
1. Define two arrays: one for integer values of Roman numerals (including subtractive forms) and another for their corresponding symbols.
2. Initialize a StringBuilder to build the resulting Roman numeral string.
3. Iterate through the integer values from largest to smallest:
   a. While the current integer value can be subtracted from `num`, do the following:
      i. Subtract the integer value from `num`.
      ii. Append the corresponding Roman symbol to the StringBuilder.
4. Once all values have been processed, convert the StringBuilder to a string and return it as the final Roman numeral representation.
 */

// Own Logic