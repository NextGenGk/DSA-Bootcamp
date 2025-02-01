package Strings;

public class Reverse_String {

    // Method 1 : Optimal Solution
    // Time Complexity : O(N) where N is the number of characters in the string
    // This is because we process each character once.
    // Space Complexity : O(N) since we use a character array for manipulation.
    public static String reverse(String input) {
        // Convert string to character array
        char[] charArray = input.toCharArray();

        // Initialize two pointers
        int left = 0;                  // Start pointer
        int right = charArray.length - 1; // End pointer

        // Swap characters until pointers meet
        while (left < right) {
            // Swap the characters
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            // Move pointers closer to the middle
            left++;
            right--;
        }

        // Convert char array back to a string and return
        return new String(charArray);
    }

    // Main Function
    public static void main(String[] args) {
        String input = "hello";
        String reversed = reverse(input);
        System.out.println("Original String: " + input);
        System.out.println("Reversed String: " + reversed);
    }
}

// Output :
/*
Original String: hello
Reversed String: olleh
 */

// Intuition : Optimal Solution
/*
Reversing a string involves rearranging the characters in the string so that the
last character becomes the first, the second last becomes the second, and so on.
 */

// Algorithm :
/*
Algorithm
1. Convert the string into a character array for mutable manipulation.
2. Use two pointers:
    i. One starting at the beginning of the array.
   ii. The other starting at the end of the array.
3. Swap the characters at the two pointers.
4. Move the pointers closer to the center and repeat until they meet or cross each other.
5. Convert the reversed character array back into a string.
 */

// Sharadha Ma'am (Video Explanation) : https://www.youtube.com/watch?v=MOSjYaVymcU&list=PLfqMhTWNBTe137I_EPQd34TsgV6IO55pt