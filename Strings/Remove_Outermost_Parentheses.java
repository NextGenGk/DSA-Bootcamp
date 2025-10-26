package Strings;

public class Remove_Outermost_Parentheses {

    // Method 1: Optimal Approach (Depth Counter)
    // Time Complexity: O(n), where n is the length of the input string.
    // Space Complexity: O(n) for the result string.
    public static String removeOuterParentheses(String s) {
        // StringBuilder to build the final result (faster than string concatenation)
        StringBuilder result = new StringBuilder();

        // Depth counter to track how deep we are inside parentheses
        int depth = 0;

        // Iterate through each character of the string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If depth > 0, it means this '(' is not the outermost one, so include it
                if (depth > 0) {
                    result.append(c);
                }
                // Increase depth when encountering '('
                depth++;
            } else { // when character is ')'
                // Decrease depth first because this ')' closes a parenthesis
                depth--;
                // If depth > 0 after decrement, then it's not the outermost ')', so include it
                if (depth > 0) {
                    result.append(c);
                }
            }
        }

        // Return the final string after removing outermost parentheses
        return result.toString();
    }

    // Main Function
    public static void main(String[] args) {
        String input = "(()())(())";
        System.out.println(removeOuterParentheses(input)); // Output: ()()()
    }
}

// Output :
// ()()() (This is Valid Parentheses)

// Intuition:
/*
Problem Understanding

A valid parentheses string is made up of primitive substrings (smallest valid pieces).
For example:
    i. "(()())(())" is made of primitives: "(()())" and "(())".
   ii. Each primitive has an outermost pair of parentheses that we want to remove.
So the task is:
Identify the outer layer of each primitive and skip it while building the answer.

Intuition :

1. Use a depth counter to track how "deep" we are inside parentheses.
    i. When you see '(', you go deeper (depth++).
   ii. When you see ')', you go shallower (depth--).

2. Outer parentheses rule:
    i. The very first '(' of a primitive starts at depth = 0. Don’t include it in the result.
   ii. The very last ')' of a primitive brings depth back to 0. Don’t include it either.

3. All other characters (when depth > 0) belong to the inside of some primitive, so include them.

🔎 Example Walkthrough

Input: "(()())(())"

1. Start with depth = 0.
2. First '(': depth becomes 1 → skip (outermost).
3. Next '(': depth becomes 2 → include.
4. Next ')': depth becomes 1 → include.
5. Next '(': depth becomes 2 → include.
6. Next ')': depth becomes 1 → include.
7. Next ')': depth becomes 0 → skip (outermost).

Continue for next primitive "(())" similarly.

Result = "()()()".

In short:
1. We remove the first '(' when going from depth 0→1 and the last ')' when going from 1→0 for each primitive substring.

Great question 👍 — let’s go a little deeper into why this logic always works.

✅ Key Insight
1. Every valid parentheses string can be broken into primitive substrings.
    i. A primitive substring is the smallest valid block that can’t be split further into two valid parts.
   ii. Example:
        "(()())(())" → primitives = "(()())", "(())"

👉 In every primitive, the outermost pair of parentheses is the first '(' and the last ')'.
So if we skip those two, we are left with exactly the "inside content."

🧠 Why the depth-based logic works

1. Depth meaning:
    i. depth = 0 → we are outside any primitive.
   ii. depth = 1 → we are at the outermost level of a primitive.
  iii. depth > 1 → we are inside the primitive.

2. When we see '(':
    i. If we are at depth = 0, this '(' starts a new primitive. It’s outermost → skip it.
   ii. Otherwise (depth > 0), it belongs inside → include it.
  iii. Then we increment depth.

3. When we see ')':
    i. First decrement depth (because this closes a bracket).
   ii. If after decrementing, depth = 0, that means this was the closing of the primitive → skip it.
  iii. Otherwise (depth > 0), it’s still inside → include it.

🔎 Why it never breaks
> The input is guaranteed to be a valid parentheses string, so depth will never go negative and will always return to 0 at the end.
> By construction, the first '(' of a primitive is when depth moves from 0 → 1 → we skip it.
> Similarly, the last ')' of a primitive is when depth moves from 1 → 0 → we skip it.
> Everything else lies strictly inside and must be kept.
 */

// Algorithm: Depth Counter
/*
1. Initialize an empty StringBuilder to store the result.
2. Initialize a depth counter to 0.
3. Iterate through each character in the input string:
    3.1 If the character is '(', check if depth > 0:
        3.1.1 If true, append '(' to the result.
        3.1.2 Increment depth by 1.
    3.2 If the character is ')', decrement depth by 1:
        3.2.1 If depth > 0 after decrementing, append ')' to the result.
4. Convert the StringBuilder to a string and return it.
 */