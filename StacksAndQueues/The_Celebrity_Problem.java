package StacksAndQueues;

public class The_Celebrity_Problem {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2) + O(N), O(N^2) for traversing the matrix and another O(N) for finding the celebrity.
    // Space Complexity : O(2N), for tracking how many people know me and how many people I know.
    static int findCelebrity(int[][] matrix) {
        // Get the number of people (n) from the length of the matrix
        int n = matrix.length;
        // Get the number of columns (m) from the matrix (typically m should also be n in a square matrix)
        int m = matrix[0].length;

        // Arrays to track how many people know me and how many people I know
        int[] knowMe = new int[n]; // knowMe[i] will store how many people know person i
        int[] iKnow = new int[n];  // iKnow[i] will store how many people person i knows

        // Traverse the matrix to populate the knowMe and iKnow arrays
        for (int i = 0; i < n; i++) { // Iterate over each person (i)
            for (int j = 0; j < m; j++) { // Iterate over each person (j) for person i
                if (matrix[i][j] == 1) {  // If person i knows person j
                    knowMe[j]++; // Increment the count of people who know person j
                    iKnow[i]++;  // Increment the count of people person i knows
                }
            }
        }

        // Iterate through each person to check if they are a celebrity
        for (int i = 0; i < n; i++) {
            // A celebrity is known by n-1 people but knows nobody
            if (knowMe[i] == n - 1 && iKnow[i] == 0) {
                return i; // Return the index of the celebrity
            }
        }

        // If no celebrity is found, return -1
        return -1;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(2N), O(N) for find the potential celebrity and another O(N) for verify this celebrity.
    // Space Complexity : O(1) no extra space is used
    static int findCelebrity1(int[][] matrix) {
        int n = matrix.length;  // Get the number of people (n)
        int m = matrix[0].length;  // Get the number of columns (usually n in a square matrix)

        int top = 0;  // Pointer for the potential celebrity (starts at the top)
        int bottom = n - 1;  // Pointer for the end (starts at the bottom)

        // Eliminate non-celebrities by comparing top and bottom
        while (top < bottom) {
            if (matrix[top][bottom] == 1) {
                top++;  // If top knows bottom, top can't be a celebrity, move top pointer
            } else {
                bottom--;  // If top doesn't know bottom, bottom can't be a celebrity, move bottom pointer
            }
        }

        // At this point, top is the potential celebrity
        // Need to verify whether the person at 'top' is truly a celebrity

        for (int i = 0; i < n; i++) {
            if (i == top) continue;  // Skip the comparison with the person themselves

            // A celebrity shouldn't know anyone and everyone should know them
            if (matrix[top][i] == 1 || matrix[i][top] == 0) {
                return -1;  // If top knows someone or someone doesn't know top, return -1 (no celebrity)
            }
        }

        // If the loop completes, top is a celebrity
        return top;
    }

    // Main Function
    public static void main(String[] args) {
        // Example test case with 4 people (0, 1, 2, 3)
        int[][] matrix = {
                {0, 1, 1, 1}, // Person 0 knows 1, 2, 3
                {0, 0, 0, 0}, // Person 1 knows nobody
                {1, 1, 0, 1}, // Person 2 knows 0, 1, 3
                {0, 1, 0, 0}  // Person 3 knows 1
        };

        // Call the findCelebrity function and store the result
        int celebrity = findCelebrity1(matrix);

        // Print the result
        if (celebrity == -1) {
            System.out.println("There is no celebrity.");
        } else {
            System.out.println("The celebrity is person " + celebrity + ".");
        }
    }
}

// Output : The celebrity is person 1.

// Approach : Brute Force
/*
1. Understanding Celebrity Characteristics:
A celebrity is someone who:
    i. Is known by everyone (i.e., all other n-1 people should know this person).
   ii. Does not know anyone (i.e., this person should not know any other person).

2. Matrix Representation:
The matrix matrix[i][j] == 1 means that person i knows person j. Using this, we can gather information
about how many people each person knows (iKnow) and how many people know them (knowMe).

3. Tracking Information with Arrays:
The code uses two arrays:
    i. knowMe[i]: How many people know person i.
   ii. iKnow[i]: How many people person i knows.
By populating these arrays, the code can check for the celebrity conditions:
    i. knowMe[i] == n - 1: This means person i is known by everyone else.
   ii. iKnow[i] == 0: This means person i knows no one.

4. Checking for the Celebrity:
After counting the "known by" and "knows" relationships, the algorithm checks if any person satisfies
the celebrity criteria. If such a person exists, their index is returned. If no person meets the criteria,
it returns -1 to indicate no celebrity.
 */

// Approach : Optimal Solution
/*

The intuition behind this code is rooted in efficiently narrowing down the possible celebrity among n people
based on specific characteristics of a celebrity. The goal is to find one person who is known by everyone
else but does not know anyone.

Core Celebrity Characteristics:

A celebrity is known by everyone: This means, for a potential celebrity at index i, all matrix[j][i] == 1 for j ≠ i.
A celebrity knows no one: This means, for a potential celebrity at index i, all matrix[i][j] == 0 for j ≠ i.

Algorithm Intuition:
1. Two-Pointer Elimination Strategy:
    1. The idea is to eliminate non-celebrities by comparing pairs of people using two pointers: top starting
        at 0 and bottom starting at n-1.
    2. If matrix[top][bottom] == 1, it means top knows bottom, so top cannot be a celebrity, and the top
        pointer moves up.
    3. If matrix[top][bottom] == 0, it means top might be a celebrity since they don’t know bottom,
    so bottom cannot be a celebrity, and the bottom pointer moves down.
        4. This process narrows down the possible celebrity to one candidate by eliminating potential candidates
    from both ends of the array.

2. Verifying the Candidate:
    1. After the elimination loop, the potential celebrity will be at index top.
    2. To confirm if the person at top is indeed the celebrity, we perform a second check:
        1. The potential celebrity should not know anyone (matrix[top][i] == 0 for all i).
        2. Everyone should know the potential celebrity (matrix[i][top] == 1 for all i).
    3. If the person at top satisfies these conditions, they are the celebrity; otherwise, no celebrity exists.

Why It Works:
1. Efficiency: The two-pointer strategy reduces the problem space by eliminating non-celebrities in a
single pass (O(n) complexity). Instead of comparing every person to every other person
(which would take O(n²)), we only focus on narrowing down the potential celebrity and then validating
the candidate.

2. Intuition of Celebrity Definition: The elimination works because of the symmetric nature of the "knows"
relationship. If person top knows person bottom, we can definitively say that top cannot be a celebrity.
Conversely, if top doesn't know bottom, then bottom can't be a celebrity. This process continues until
one candidate remains.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=cEadsbTeze4