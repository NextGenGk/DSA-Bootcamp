package Sliding_Window_and_Two_Pointers;

public class Max_Points_You_Can_Obtain_From_Cards {

    // Method 1 : Optimal Solution
    // Time Complexity : O(2 * K)
    // Space Complexity : O(1)
    // Method to calculate the maximum score by picking up exactly k cards
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;  // Get the length of the cardPoints array

        int leftSum = 0;  // Sum of the leftmost k cards
        int rightSum = 0;  // Sum of the rightmost cards that are not part of the initial k
        int maxScore = 0;  // Variable to store the maximum score

        // Calculate the sum of the first k cards (from the left end of the array)
        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];  // Add the card value to leftSum
            maxScore = leftSum;  // Initialize maxScore with the sum of the first k cards
        }

        // Initialize the index for the right end of the array
        int rightIndex = n - 1;

        // Adjust the sums to consider removing one card from the left and adding one card from the right
        for (int i = k - 1; i >= 0; i--) {
            leftSum -= cardPoints[i];  // Remove the value of the card being excluded from the left sum
            rightSum += cardPoints[rightIndex];  // Add the value of the card from the right end
            rightIndex--;  // Move to the next card from the right end
            maxScore = Math.max(maxScore, leftSum + rightSum);  // Update maxScore with the maximum of current and previous scores
        }

        // Return the maximum score achieved
        return maxScore;
    }

    // Main method to test the function (for demonstration purposes)
    public static void main(String[] args) {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};  // Example array of card points
        int k = 3;  // Number of cards to pick
        // Print the result: maximum score by picking exactly k cards
        System.out.println("Maximum score by picking " + k + " cards: " + maxScore(cardPoints, k));  // Output will be the maximum score
    }
}

// Output : Maximum score by picking 3 cards: 12

// Approach : Optimal Solution
/*
This code calculates the maximum score achievable by picking exactly k cards from either end of a
given array cardPoints. It first computes the sum of the first k cards from the left, then uses
a sliding window approach to iteratively adjust this sum by removing one card from the left and
adding one card from the right end of the array. By comparing the sum of the current configuration
of cards with the maximum score found so far, it efficiently determines the maximum score possible.
This approach leverages the fact that to maximize the score, you need to consider the optimal
balance between picking cards from the start and the end of the array.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=pBWCOCS636U