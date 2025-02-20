package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class Palindrome_Partitioning_I {

    // Method 1 : Optimal Solution
    // Time Complexity: O( (2^n) *k*(n/2) )
    // Reason: O(2^n) to generate every substring and O(n/2)  to check if the substring
    // generated is a palindrome. O(k) is for inserting the palindromes in another data structure,
    // where k  is the average length of the palindrome list.
    // Space Complexity: O(k * x)
    // Reason: The space complexity can vary depending upon the length of the answer. k is the
    // average length of the list of palindromes and if we have x such list of palindromes in our
    // final answer. The depth of the recursion tree is n, so the auxiliary space required is equal to the O(n).

    // Main function to return all possible palindrome partitions of a given string
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        generateValidPartition(s, 0, ans, new ArrayList<>());
        return ans;
    }

    // Helper function to generate partitions recursively
    private static void generateValidPartition(String s, int index,
                                               List<List<String>> ans, List<String> path) {
        // Base case: if we have reached the end of the string, add current partition to the result
        if (index == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // Try partitioning at every possible index
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) { // Check if substring is a palindrome
                path.add(s.substring(index, i + 1)); // Add substring to current partition path
                generateValidPartition(s, i + 1, ans, path); // Recur for remaining substring
                path.remove(path.size() - 1); // Backtrack to explore other partitions
            }
        }
    }

    // Function to check if a given substring is a palindrome
    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) { // Loop until the middle of the substring
            if (s.charAt(start) != s.charAt(end)) // If characters don't match, not a palindrome
                return false;
            start++;
            end--;
        }
        return true;
    }

    // Main Function
    public static void main(String[] args) {
        String s = "aabb";
        System.out.println(partition(s));
    }
}

// Output :
// [ [a, a, b, b], [a, aa, b, b], [aa, b, b], [aa, bb] ]

// Intuition : Optimal Solution
/*
We want to break the given string into smaller parts where each part is a palindrome.
Since the order of characters must remain the same, we will explore different ways to partition
the string while ensuring each substring is a palindrome.

To do this, we use backtracking:

1. Start from the beginning of the string and try cutting it at every possible position.
2. If the current substring is a palindrome, include it in the current partition and move forward.
3. If we reach the end of the string, we have found a valid partition, so we store it.
4. Backtrack to explore other possibilities.

By repeating this process, we generate all possible palindrome partitions of the string. 🚀

Approach :
The initial idea will be to make partitions to generate substring and check if the substring
generated out of the partition will be a palindrome. Partitioning means we would end up generating
every substring and checking for palindrome at every step. Since this is a repetitive task being
done again and again, at this point we should think of recursion. The recursion continues until
the entire string is exhausted. After partitioning, every palindromic substring is inserted in a
data structure When the base case has reached the list of palindromes generated during that recursion
call is inserted in a vector of vectors/list of list.

We have already discussed the initial thought process and the basic outline of the solution. The
approach will get clearer with an example.

Say s = “aabb” and assume indexes of string characters to be 0-based. For a better understanding,
we have divided recursion into some steps.

STEP 1: We consider substrings starting from the 0th index.[0,0] is a palindrome, so partition right
after the 0th index.[0,1] is another palindrome, make a partition after 1st index. Beyond this point,
other substrings starting from index 0 are “aab”  and “aabb”. These are not palindromes, hence no more.
partitions are possible. The strings remaining on the right side of the partition are used as input
to make recursive calls.

STEP 2: Consider the recursive call on the left(refer to image)  where “abb” is the input.[1,1] is a
palindrome, make a partition after it.[1,2] and [1,3] are not palindromes.

STEP 3: Here “bb” is the input.[2,2] as well as [2,3]  are palindromes. Make one partition after
the 2nd index and one after the 3rd index The entire string is exhausted after the 3rd index,
so the right recursion ends here. Palindromes generated from the right recursion are inserted in our answer.

Our answer at this point :[ [“a”,” a”,” bb”] ]

The left recursion will continue with  "b" as its input.[3,3] is a palindrome so one last partition
for the left recursion is made after the 3rd index. Insert the palindromes.

ans = [  [“a”,”a”,”bb”], [ “a”,”a”,”b”,”b”] ]

STEP 4: After the list of palindromic substrings are returned from the left recursive call, continue
the same process for the call on the right that was left to recur. The right recursion is having “bb”
as input, something we have already encountered in step 3. Hence we will repeat the same task which
was done in step 3 onwards.

Final answer : [  [“a”,”a”,”bb”], [ “a”,”a”,”b”,”b”] ,[“aa”,”b”,”b”], [“aa”,”bb”] ]
 */

// Striver's (Video Explanation) : https://youtu.be/WBgsABoClE0?si=qwD8wNe08GZLvD_1