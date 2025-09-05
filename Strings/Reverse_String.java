package Strings;

public class Reverse_String {

    public static String reverseString(String s) {
        // Convert string to char array
        char[] arr = s.toCharArray();

        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            // swap characters
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }

        // convert back to string
        return new String(arr);
    }

    // Main Function
    public static void main(String[] args) {
        String s = "Hello, World!";
        String reversed = reverseString(s);
        System.out.println("Original String: " + s);
        System.out.println("Reversed String: " + reversed);
    }
}

// Output:
// Original String: Hello, World!
// Reversed String: !dlroW ,olleH

// Algorithm: Two Pointer Approach
/*
1. Convert the string to a character array.
2. Initialize two pointers, i at the start (0) and j at the end (length - 1) of the array.
3. While i is less than j:
    3.1 Swap the characters at positions i and j.
    3.2 Increment i and decrement j.
4. Convert the character array back to a string and return it.
 */

// Apna College (Video Explanation): https://youtu.be/MOSjYaVymcU?si=JC1lC02cLhwrKnxz