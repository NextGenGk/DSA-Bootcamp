package RecursionAndBacktracking;

import java.util.ArrayList;
import java.util.Collections;

public class Kth_Permutation_Sequence {

    // Method 1 : Brute Force
    // Time complexity: O(N! * N) +O(N! Log N!)
    // Reason:  The recursion takes O(N!)  time because we generate every possible permutation
    // and another O(N)  time is required to make a deep copy and store every sequence in the
    // data structure. Also, O(N! Log N!)  time required to sort the data structure
    // Space complexity: O(N)
    // Reason: Result stored in a vector, we are auxiliary space taken by recursion
    // Helper function to swap two characters in an array
    static void swap(char s[], int i, int j) {
        char ch = s[i];
        s[i] = s[j];
        s[j] = ch;
    }

    // Recursive function to generate all permutations of the given character array
    static void permutationHelper(char s[], int index, ArrayList<String> res) {
        // Base case: if we have reached the end of the array, store the permutation
        if (index == s.length) {
            String str = new String(s); // Convert character array to string
            res.add(str); // Add permutation to the result list
            return;
        }

        // Generate all possible permutations by swapping characters
        for (int i = index; i < s.length; i++) {
            swap(s, i, index); // Swap the current character with index
            permutationHelper(s, index + 1, res); // Recur for the next index
            swap(s, i, index); // Backtrack to restore the original state
        }
    }

    // Function to get the k-th permutation of numbers from 1 to n
    static String getPermutation(int n, int k) {
        String s = "";
        ArrayList<String> res = new ArrayList<>();

        // Create a string of numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            s += i;
        }

        // Generate all permutations of the string
        permutationHelper(s.toCharArray(), 0, res);

        // Sort the generated permutations in lexicographical order
        Collections.sort(res);

        // Return the k-th permutation (0-based index, should be k-1)
        return res.get(k - 1);
    }

    // Method 2 : Optimal Solution
    // Time Complexity: O(N) * O(N) = O(N^2)
    // Reason: We are placing N numbers in N positions. This will take O(N) time.
    // For every number, we are reducing the search space by removing the element already
    // placed in the previous step. This takes another O(N) time.
    // Space Complexity: O(N)
    // Reason: We are storing  the numbers in a data structure(here vector)
    // Function to find the k-th permutation sequence of numbers from 1 to n
    static String getPermutationOptimal(int n, int k) {
        int fact = 1; // Variable to store (n-1)!
        ArrayList<Integer> numbers = new ArrayList<>(); // List to store numbers from 1 to n-1

        // Compute (n-1)! and populate the numbers list
        for (int i = 1; i < n; i++) {
            fact = fact * i; // Compute factorial (n-1)!
            numbers.add(i); // Add numbers from 1 to (n-1) in the list
        }
        numbers.add(n); // Add the last number n to the list

        String ans = ""; // Resultant permutation string
        k = k - 1; // Convert k to 0-based index

        // Generate the k-th permutation
        while (true) {
            // Find the index of the current digit in the remaining numbers
            int index = k / fact;
            ans = ans + numbers.get(index); // Append the selected digit to result
            numbers.remove(index); // Remove the used digit

            // Break if no more numbers are left
            if (numbers.size() == 0) {
                break;
            }

            // Update k and factorial for the next iteration
            k = k % fact; // Update k to find the next position
            fact = fact / numbers.size(); // Update factorial (reduce by current size)
        }

        return ans; // Return the k-th permutation as a string
    }

    // Main Function
    public static void main(String[] args) {
        int n = 4, k = 17;
        System.out.println(getPermutationOptimal(n, k));
    }
}

// Output: 3412

// Intuition : Brute Force
/*
Approach:
The extreme naive solution is to generate all the possible permutations of the given sequence.
This is achieved using recursion and every permutation generated is stored in some other data
structure (here we have used a vector). Finally, we sort the data structure in which we have
stored all the sequences and return the Kth sequence from it.
 */

// Intuition : Optimal Solution
/*
Say we have N = 4  and K = 17. Hence, the number sequence is {1,2,3,4}.

Intuition:

Since this is a permutation we can assume that there are four positions that need to be filled
using the four numbers of the sequence. First, we need to decide which number is to be placed
at the first index. Once the number at the first index is decided we have three more positions
and three more numbers.  Now the problem is shorter. We can repeat the technique that was used
previously until all the positions are filled. The technique is explained below.

Approach:

STEP 1:

Mathematically speaking there can be 4 variations while generating the permutation.
We can have our permutation starting with either 1 or 2 or 3 or 4. If the first position
is already occupied by one number there are three more positions left. The remaining three
numbers can be permuted among themselves while filling the 3 positions and will generate
3! = 6 sequences. Hence each block will have 6 permutations adding up to a total of 6*4 = 24
permutations. If we consider the sequences as 0-based and in the sorted form we observe:-

1. The 0th - 5th permutation will start with 1
2. The 6th - 11th permutation will start with 2
3. The 12th - 17th permutation will start with 3
4. The 18th - 23rd permutation will start with 4.

We make K = 17-1 considering 0-based indexing. Since each of the four blocks illustrated
above comprises 6 permutations, therefore, the 16th permutation will lie in (16 / 6 ) = 2nd block,
and our answer is the (16 % 6) = 4th sequence from the 2nd block. Therefore 3 occupies the first
position of the sequence and K = 4.

            3 _ _ _ // Output

STEP 2:

Our new search space comprises three elements {1,2,4} where K = 4 . Using the previous technique
we can consider the second position to be occupied can be any one of these 3 numbers. Again one
block can start with 1, another can start with 2 and the last one can start with 4 .

Since one position is fixed, the remaining two numbers of each block can form 2! = 2  sequences.
In sorted order :

1. The 0th - 1st sequence starts with 1
2. The 2nd - 3rd sequence starts with 2
3. The 4th - 5th sequence starts with 4

The 4th permutation will lie in (4/2) = 2nd block and our answer is the 4%2 = 0th sequence from
the 2nd block. Therefore 4 occupies the second position and K = 0.

            3 4 _ _ // Output

STEP 3:

The new search space will have two elements {1 ,2} and K = 0. One block starts with 1 and the
other block starts with 2. The other remaining number can form only one 1! = 1 sequence.
In sorted form -

1. The  0th sequence starts with 1
2. The  1st sequence. starts with 2

The 0th permutation will lie in the (0/1) = 0th block and our answer is the 0%1 = 0th sequence
from the 0th block. Therefore 1 occupies the 3rd position and K = 0.

            3 4 1 _ // Output

STEP 4:

Now the only block has 2 in the first position and no remaining number is present.

1. This is the point where we place 2 in the last position and stop.

           3 4 1 2 // Output

The final answer is 3412.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=wT7gcXLYoao