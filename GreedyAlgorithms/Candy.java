package GreedyAlgorithms;

public class Candy {

    // Method 1 : Brute Force
    // Time Complexity : O(3N)
    // Space Complexity : O(2N)
    static int calculate(int[] ratings) {
        int n = ratings.length;

        // Edge case: if no ratings are provided
        if (n == 0) return 0;

        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n - 1] = 1;

        // Forward pass: Fill arr1
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Backward pass: Fill arr2
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // Calculate the sum
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }

    // Method 1 : Better Solution
    // Time Complexity : O(2N)
    // Space Complexity : O(N)
    static int calculate1(int[] ratings) {
        int n = ratings.length;

        // Edge case: if no ratings are provided
        if (n == 0) return 0;

        int[] left = new int[n];
        left[0] = 1;  // First element gets at least 1 candy

        // Forward pass: Fill left[] based on left neighbors
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Backward pass: Use a single variable 'current' to track right-side comparison
        int right = 1;  // Start from the last element
        int sum = left[n - 1];  // Start the sum with the last element from left[]

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right = right + 1;  // If current rating is higher than next, increase right-side candies
            } else {
                right = 1;  // Reset to 1 if current rating is not higher
            }

            sum += Math.max(right, left[i]); // Take the maximum of left[] and right for each element
        }

        return sum;
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    public static int calculate2(int[] ratings) {
        int n = ratings.length;

        if (n == 0) return 0; // Handle edge case for empty ratings array

        int sum = 1;  // Initialize sum with 1 candy for the first rating
        int i = 1;

        while (i < n) {
            // Handle flat surface (equal ratings)
            if (ratings[i] == ratings[i - 1]) {
                sum += 1;  // Give 1 candy for flat ratings
                i++;
                continue;  // Move to the next rating
            }

            // For increasing slope (ratings[i] > ratings[i - 1])
            int peak = 1;  // Start with 1 candy for the first element of the slope
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak += 1;  // Increment candy count for increasing slope
                sum += peak;  // Add the peak value to the sum
                i++;
            }

            // For decreasing slope (ratings[i] < ratings[i - 1])
            int down = 1;  // Start with 1 candy for the first element of the downward slope
            while (i < n && ratings[i] < ratings[i - 1]) {
                down += 1;  // Increment candy count for decreasing slope
                sum += down;  // Add the down value to the sum
                i++;
            }

            // Adjust the sum for peak, since peak has been counted multiple times
            // If we had a peak higher than the downward slope, we need to subtract
            // extra candies that we might have added earlier.
            /*
            Why is this adjustment needed?
            Suppose the downward slope after a peak is longer than the upward slope. In that case, the last child on 
            the downward slope will receive more candies than the peak child, which breaks the problem's rule: the peak 
            (highest rating) should have more candies than neighbors.
            
            To fix this, if the number of candies on the peak (peak) is greater than or equal to the length of the downward 
            slope (down), you need to add peak - down candies to the sum. This ensures the peak is still higher than any 
            child in the downward run.
            
            Example
            Suppose ratings: [1, 2, 3, 2, 1]
            
            Upward slope: 1, 2, 3 (peak = 3)
            Downward slope: 2, 1 (down = 2)
            Without adjustment:
            
            Candies: 1, 2, 3, 2, 1
            The peak (3) is higher than the downward values (2, 1), so no adjustment needed.
            But if the downward slope was longer, for example: [1, 2, 3, 2, 1, 0]
            
            Downward run is 2, 1, 0 (down = 3)
            Now, without adjustment, the last child would have as many candies as the peak.
            So, the code checks:
            
            If peak >= down, add the difference to the sum, ensuring the peak remains the highest.
            In short
            
            This adjustment ensures the "peak" in the ratings always gets more candies than anyone on the downward slope next to it,
            maintaining the required rule of the problem.
            */
            if (peak >= down) {
                sum += peak - down;  // Adjust for peak if down slope is longer
            }
        }

        return sum;
    }

    // Method 4 : Optimal Solution (For Leetcode Problem)
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    public static int candy(int[] arr) {
        int i = 1;           // Initialize index to start from the second child
        int sum = 1;         // Start with giving 1 candy to the first child
        int n = arr.length - 1;  // Set `n` as the last valid index

        while (i <= n) {
            int peek = 1;    // Initialize `peek` for the number of candies during the upward slope (higher ratings)

            // First loop: Handle the upward slope (ratings[i] > ratings[i-1])
            while (i <= n && arr[i] > arr[i - 1]) {
                peek = peek + 1;   // Increase the candy count as ratings increase
                sum += peek;       // Add the current peak value to the total sum of candies
                i++;               // Move to the next child
            }

            int down = 0;  // Initialize `down` for the downward slope (ratings[i] < ratings[i-1])

            // Second loop: Handle the downward slope (ratings[i] < ratings[i-1])
            while (i <= n && arr[i] < arr[i - 1]) {
                // Check if the current point is either:
                // - The start of the array
                // - A flat peak where two consecutive ratings are the same
                if (i == 1 || arr[i - 1] == arr[i - 2])
                    down = 1;  // Reset `down` to 1 in such cases

                down = down + 1;   // Increase the candy count as ratings decrease
                sum += down;       // Add the `down` value to the total sum of candies
                i++;               // Move to the next child
            }

            // Third loop: Handle equal ratings (ratings[i] == ratings[i-1])
            while (i <= n && arr[i] == arr[i - 1]) {
                sum += 1;          // Give 1 candy to children with the same rating as their previous neighbor
                ++i;               // Move to the next child
            }

            // If the downward slope is longer than or equal to the upward slope,
            // we need to compensate for the peak child having fewer candies than required.
            // We only add this correction when `peek != 1` to avoid flat peaks or downward-only slopes.
            if (down >= peek && peek != 1) {
                sum += down - peek + 1;  // Adjust the total candies for the peak child
            }
        }

        return sum;  // Return the total number of candies distributed
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {0, 2, 4, 3, 2, 1, 1, 3, 5, 6, 4, 0, 0};
        int res = candy(arr);
        System.out.println(res);
    }
}

// Approach / Intuition : Brute Force
/*
This code distributes "candies" based on ratings, ensuring each element gets at least 1 candy, and
higher-rated elements receive more than their neighbors. It uses two passes: a forward pass (`arr1[]`)
to give more candies to higher-rated elements than their left neighbors, and a backward pass (`arr2[]`)
to handle comparisons with right neighbors. The final result takes the maximum candies from both passes
for each element, ensuring the conditions are satisfied in both directions. The total candies are then
summed to get the result.
 */

// Approach / Intuition : Better Solution
/*
Instead of creating another array for the backward pass, you use a right variable to track the necessary
candies from the right side. If an element has a higher rating than the next one, it should receive
more candies (increasing right).

For each element, you take the maximum value between the forward pass (left[]) and the backward pass
(right), ensuring the conditions are satisfied for both neighbors.

You directly accumulate the sum in the process to reduce the need for a separate summation loop.
 */

// Approach / Intuition : Optimal Solution
/*
The intuition behind this code is based on treating the ratings as a series of "slopes" that either
increase, decrease, or stay flat. The goal is to distribute candies such that each child gets more
candies than their neighbors if they have a higher rating, and fewer candies if they have a lower
rating. Here's the breakdown:

Key Concepts:
1. Flat Surface: If two consecutive ratings are the same, each child gets exactly 1 candy, since
no preference is needed between them.

2. Increasing Slope: When ratings increase (`ratings[i] > ratings[i - 1]`), the child with
the higher rating should get more candies than the previous one. The code handles this by increasing
the "peak" variable (which tracks how many candies should be given) and adding it to the sum.

3. Decreasing Slope: When ratings decrease (`ratings[i] < ratings[i - 1]`), we reverse the logic
and start giving fewer candies as we go down the slope. The "down" variable tracks how many candies
should be given to ensure that children with lower ratings get fewer candies.

4. Peak Adjustment: If the downward slope is longer than the upward slope, the peak value
(where the slope changes from increasing to decreasing) may not have enough candies. In that case,
the code adjusts the sum by adding the difference between the lengths of the downward and upward slopes,
ensuring the peak gets enough candies to maintain the rule.

The Intuition:
- The ratings are viewed as peaks and valleys.
- When moving up (increasing slope), the number of candies increases.
- When moving down (decreasing slope), the number of candies decreases.
- If the downward slope is longer, the peak in the middle needs more candies to satisfy the
condition of being greater than both neighbors. This is handled by adjusting the sum after the down slope.

This method ensures a fair distribution of candies in O(N) time without needing extra arrays,
by tracking the peaks and valleys directly during the traversal.
 */

// Approach / Intuition : Optimal Solution (for Leetcode Problem)
/*
The problem requires us to distribute candies to children standing in a line based on their ratings, ensuring that:

* Each child gets at least one candy.
* Children with higher ratings than their neighbors receive more candies.
To satisfy these conditions, the code approaches the problem by considering three key patterns in
the ratings:

Upward slope: When a child's rating is higher than the previous child.
Downward slope: When a child's rating is lower than the previous child.
Flat regions: When two or more consecutive children have the same rating.
The code traverses the ratings array and adjusts candy distribution based on these three patterns,
using three separate loops to handle each case.

Key Intuitions:
1. Upward Slope (Increasing Ratings):

    1. If a child has a higher rating than the previous child, they should receive more candies.
    2. The code increments the peek value (starting from 1) to give progressively more candies during the
    upward slope.
    3. This is like climbing a "hill," where each subsequent child receives more candies than the previous
    one as long as the ratings keep increasing.

2. Downward Slope (Decreasing Ratings):

    1. If a child has a lower rating than the previous one, they should receive fewer candies.
    2. The code increments the down value (starting from 1) to give progressively fewer candies during
    the downward slope.
    3. However, special care is needed for the "peak" child (the highest rating before the downward slope starts)
    because this child should still have more candies than any child on the downhill.
    4. The code ensures that if the number of candies in the downward slope exceeds or equals the candies
    given at the peak, it compensates for the peak child by adjusting the total number of candies.

3. Flat Regions (Equal Ratings):

    1. If two consecutive children have the same rating, they should both receive 1 candy since they have
    no advantage over each other.
    2. The code ensures this by simply adding 1 candy for each child in the flat region.

4. Peak Adjustment:

    1. If the downward slope is longer or equal to the upward slope, the "peak" child might not have
    enough candies to maintain the rule that children with higher ratings get more candies than their neighbors.
    2. To fix this, the code adds a correction to the total sum of candies by ensuring the peak child has
    more candies than all the children on the downhill.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=IIqVFvKE6RY
