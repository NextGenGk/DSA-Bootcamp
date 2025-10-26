package Strings;

public class Remove_All_Occurrences {

    // Method 1 : Optimal Solution
    // Time Complexity: O(K * N) where K is the length of 'part' and N is the length of 's'
    // Space Complexity: O(N) because a new string is created with each call.
    public static String removeOccurrences(String s, String part) {
        // Keep removing 'part' from 's' while it exists
        while (s.contains(part)) {
            // Replace the first occurrence of 'part' with an empty string
            s = s.replaceFirst(part, "");
        }
        return s; // Return the modified string
    }


    // Method 2 : Optimal Solution
    // Time Complexity: O(K * N) where K is the length of 'part' and N is the length of 's'.
    // Space Complexity: O(N) because a new string is created with each call.
    public static String removeOccurrences1(String s, String part) {
        // Step 1: Find the index of the first occurrence of 'part' in 's'
        int index = s.indexOf(part);

        // Step 2: Keep looping as long as 'part' exists in 's'
        while (index != -1) { // indexOf returns -1 if 'part' is not found
            // Step 3: Remove the 'part' from 's'
            // Concatenate two parts:
            // - the substring before 'part' (from index 0 to 'index')
            // - the substring after 'part' (from index + part.length() to the end)
            s = s.substring(0, index) + s.substring(index + part.length());

            // Step 4: After removal, find the next occurrence of 'part' in the updated 's'
            index = s.indexOf(part);
        }

        // Step 5: Return the modified string with all occurrences of 'part' removed
        return s;
    }

    // Main Function
    public static void main(String[] args) {
        String s = "daabcbaabcbc";
        String part = "abc";
        System.out.println(removeOccurrences(s, part));
        System.out.println(removeOccurrences1(s, part));
    }
}

// Output :
/*
dab
dab
 */

// Intuition : Optimal Solution 1
/*
Intuition:
We are trying to remove all occurrences of the substring part from the string s.
We do this by:

1. Checking if 'part' exists in 's'.
2. If it exists, remove the first occurrence of 'part' from 's'.
3. Repeat this until 'part' no longer exists in 's'.
4. Finally, return the modified string 's'.

Algorithm :
1. Check if 'part' is in s using contains(part). If it's not found, we stop.
2. Remove the first occurrence of 'part' using replaceFirst(part, "").
3. Repeat the process until no occurrences of 'part' are left in 's'.
4. Return the updated string 's' after all removals.

Time Complexity :
1. Each contains and replaceFirst call takes O(n) time, where n is the length of the string s.
2. We repeat the process for each occurrence of part in s. If there are k occurrences,
the total time complexity is O(k * n).

Space Complexity :
1. The space complexity is O(n) because we are creating a new string each time we call replaceFirst.
 */

// Intuition : Optimal Solution 2
/*
Intuition:
This method is designed to remove all occurrences of the substring part from the string s.
Here's how it works:

1. Find the first occurrence of 'part' in 's'.
2. Remove that occurrence.
3. Repeat the process until there are no more occurrences of 'part' in 's'.
4. Return the final modified string.

Algorithm :
1. Find the first occurrence of part in s using indexOf(). If part is not found,
indexOf() returns -1, which means we stop.
2. Remove the first occurrence of part:
    i. Use substring(0, index) to get the part of s before part.
   ii. Use substring(index + part.length()) to get the part of s after part.
  iii. Concatenate these two parts together to remove part.
3. Find the next occurrence of part in the updated string s using indexOf().
4. Repeat the process until no more occurrences of part are found.
5. Return the updated string.

Time Complexity :
1. The indexOf() method takes O(n) time where n is the length of the string 's'.
2. For each occurrence of 'part', we remove it using substring(), which also takes O(n) time.
3. If there are k occurrences of 'part', the total time complexity is O(k * n).

Space Complexity :
1. The space complexity is O(n) because each time we create a new string by concatenating parts of s.
The space used is proportional to the length of s.
 */

// Sharadha Ma'am (Video Explanation) : https://www.youtube.com/watch?v=dSRFgEs3a6A&list=PLfqMhTWNBTe137I_EPQd34TsgV6IO55pt