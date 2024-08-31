package Sliding_Window_and_Two_Pointers;

public class Number_of_Substrings_Containing_All_Three_Characters {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(1)
    public static int numberOfSubstrings(String s) {
        // Get the length of the input string
        int n = s.length();
        // Initialize a counter to keep track of valid substrings
        int cnt = 0;

        // Iterate through all possible starting points of substrings
        for (int i = 0; i < n; i++) {
            // Create a hash array to track the presence of 'A', 'B', and 'C'
            // hash[0] corresponds to 'A', hash[1] to 'B', and hash[2] to 'C'
            int[] hash = new int[3]; // Array to track occurrences of 'A', 'B', 'C'

            // Iterate through all possible ending points of substrings starting from index i
            for (int j = i; j < n; j++) {
                // Mark the presence of the current character in the hash array
                // Subtracting 'a' converts the character to an index (0 for 'A', 1 for 'B', 2 for 'C')
                hash[s.charAt(j) - 'a'] = 1;

                // Check if all three characters are present in the current substring
                // The sum of hash array elements will be 3 if 'A', 'B', and 'C' are all present
                if (hash[0] + hash[1] + hash[2] == 3) {
                    // Increment the count of valid substrings
                    cnt++;
                }
            }
        }
        // Return the total count of valid substrings that contain 'A', 'B', and 'C'
        return cnt;
    }

    // Method 2 : Better Solution
    // Time Complexity : O(N^2)
    // Space Complexity : O(1)
    public static int numberOfSubstrings1(String s) {
        // Get the length of the input string
        int n = s.length();
        // Initialize a counter to keep track of valid substrings
        int cnt = 0;

        // Iterate through all possible starting points of substrings
        for (int i = 0; i < n; i++) {
            // Create a hash array to track the presence of 'A', 'B', and 'C'
            // hash[0] corresponds to 'A', hash[1] to 'B', and hash[2] to 'C'
            int[] hash = new int[3];

            // Iterate through all possible ending points of substrings starting from index i
            for (int j = i; j < n; j++) {
                // Mark the presence of the current character in the hash array
                // Subtracting 'a' converts the character to an index (0 for 'A', 1 for 'B', 2 for 'C')
                hash[s.charAt(j) - 'a'] = 1;

                // Check if all three characters are present in the current substring
                // The sum of hash array elements will be 3 if 'A', 'B', and 'C' are all present
                if (hash[0] + hash[1] + hash[2] == 3) {
                    // Increment the count of valid substrings by the number of valid substrings
                    // that can be formed from the current ending point 'j' to the end of the string
                    cnt += n - j;
                    // Since all substrings starting from index 'i' to 'j' and extending to the end
                    // are valid, we add (n - j) to the count
                    break; // Break out of the inner loop as we only need to count the first valid occurrence
                }
            }
        }
        // Return the total count of valid substrings that contain 'A', 'B', and 'C'
        return cnt;
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    public static int numberOfSubstrings2(String s) {
        // Get the length of the input string
        int n = s.length();
        // Initialize a counter to keep track of valid substrings
        int cnt = 0;
        // Array to track the last seen index of 'a', 'b', and 'c'
        // Using -1 to indicate that the character has not been seen yet
        int[] lastSeen = new int[]{-1, -1, -1};

        // Iterate through each character in the string
        for (int i = 0; i < n; i++) {
            // Update the last seen index for the current character
            // 'a' corresponds to index 0, 'b' to index 1, and 'c' to index 2
            lastSeen[s.charAt(i) - 'a'] = i;

            // Check if all three characters have been seen at least once
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                // Count valid substrings that can be formed
                // The number of valid substrings is determined by the minimum last seen index
                // plus one (to account for the starting point of the substring)
                cnt += (Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2])) + 1);
            }
        }
        // Return the total count of valid substrings that contain 'A', 'B', and 'C'
        return cnt;
    }

    // Main Function
    public static void main(String[] args) {
        String str = "abcabc";
        int res = numberOfSubstrings2(str);
        System.out.println(res);
    }
}

// Output : 10

// Approach : Brute Force
/*
The intuition behind this code is to count the number of substrings that contain all three characters
'A', 'B', and 'C' by iterating through all possible starting and ending points of substrings, and using
a hash array to track the presence of each character. The outer loop iterates through each character as
a potential starting point, while the inner loop extends the substring to every possible ending point.
For each substring, the hash array is used to mark the presence of 'A', 'B', and 'C', and whenever all
three characters are present (indicated by the sum of the hash array being 3), the count of valid
substrings is incremented. This brute force approach examines all possible substrings, resulting
in a time complexity of O(n^2), where n is the length of the input string.
 */

// Approach : Better Solution
/*
The intuition behind this code is to efficiently count the number of substrings that contain all three
characters 'A', 'B', and 'C' by leveraging a two-pointer technique combined with a presence-tracking
array. The key ideas behind this approach are:

1. Iterating through Starting Points: The outer loop iterates through each character in the string as
   a potential starting point for substrings. This ensures that all possible starting points are considered.
2. Extending Substrings: For each starting point, the inner loop extends the substring to the right,
   marking the presence of 'A', 'B', and 'C' in a hash array. This allows the code to efficiently explore
   all possible substrings starting from the current index.
3. Tracking Character Presence: The hash array is used to keep track of whether 'A', 'B', and 'C' are
   present in the current substring. When the sum of the hash array elements equals 3, it indicates that
   all three characters are present.
4. Counting Valid Substrings: When a valid substring is found (i.e., all three characters are present),
   the code counts the number of valid substrings that can be formed from the current ending point to the
   end of the string. This is done by adding (n - j) to the count, where n is the length of the string and
   j is the current ending point. This approach avoids counting overlapping substrings multiple times.
5. Optimizing with Break: After finding the first valid occurrence, the inner loop breaks to minimize
   unnecessary iterations. This optimization helps to make the algorithm more efficient, as it avoids
   counting the same valid substrings repeatedly.

By combining these ideas, the code efficiently captures all valid substrings while avoiding redundant
computations. The use of two pointers and a presence-tracking array allows the algorithm to explore
the string in a structured manner, leading to a more optimal solution compared to a
naive brute-force approach.
 */

// Approach : Optimal Solution
/*
The intuition behind this code is to efficiently count the number of substrings that contain all three
characters 'a', 'b', and 'c' by leveraging the concept of tracking the last seen indices of each character
as we iterate through the string. Here’s a breakdown of the thought process:

1. Last Seen Indices: The code maintains an array called lastSeen that records the most recent index of
   each character ('a', 'b', and 'c'). This allows the algorithm to quickly determine whether all three
   characters have been encountered as we process the string.
2. Single Pass Approach: By iterating through the string just once (O(n) time complexity), the code
   avoids the inefficiencies of nested loops that would examine every possible substring. Instead, it
   focuses on the last occurrences of the characters, which simplifies the counting process.
3. Counting Valid Substrings: Whenever all three characters have been seen (i.e., none of the indices
   in lastSeen is -1), the code calculates the number of valid substrings that can be formed. The number
   of valid substrings is determined by the position of the earliest occurrence of any of the three
   characters, which is found using Math.min(lastSeen, lastSeen, lastSeen). This value indicates how many
   substrings can start from the beginning of the string up to that index, ensuring that each counted substring contains all three characters.
4. Dynamic Counting: As the loop progresses, the code dynamically counts valid substrings, allowing
   for efficient updates without needing to re-evaluate previously counted substrings. Each time a new
   character is processed, the potential starting points for valid substrings are recalculated based on
   the most recent positions of 'a', 'b', and 'c'.

In summary, this approach combines the efficiency of a single-pass algorithm with the strategic use
of an array to track character positions, resulting in a solution that effectively counts the desired
substrings while minimizing computational complexity.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=xtqN4qlgr8s