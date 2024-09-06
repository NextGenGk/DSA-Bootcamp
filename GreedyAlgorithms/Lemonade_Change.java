package GreedyAlgorithms;

public class Lemonade_Change {

    // Method 1 : Optimal Solution
    // O(N) where N is the number of people in queue/ bills we will deal with. We iterate
    // through each customer’s bills exactly once. The loop runs for N iterations and at
    // each iteration the operations performed are constant time.
    // Space Complexity: O(1) as the algorithm uses only a constant amount of extra space
    // regardless of the size of the input array. It does not require any additional data
    // structures that scale with the input size.
    public static boolean lemonadeChange(int[] bills) {
        // Track the number of $5 and $10 bills we have
        int five = 0;
        int ten = 0;

        // Loop through each bill in the input array
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++; // Accept $5 bill, no change needed
            } else if (bills[i] == 10) {
                if (five > 0) { // Give $5 as change if available
                    five--;
                    ten++; // Keep the $10 bill
                } else {
                    return false; // If no $5 bill to give change, return false
                }
            } else { // If bill is $20
                if (ten > 0 && five > 0) { // Prefer to give one $10 and one $5 as change
                    ten--;
                    five--;
                } else if (five >= 3) { // Otherwise, give three $5 bills as change
                    five -= 3;
                } else {
                    return false; // If neither change option is possible, return false
                }
            }
        }

        return true; // If we made it through all transactions successfully
    }

    public static void main(String[] args) {
        // Test cases
        int[] bills1 = {5, 5, 5, 10, 20};  // True - Correct change can be given
        int[] bills2 = {5, 5, 10, 10, 20}; // False - Not enough change for $20 bill
        int[] bills3 = {5, 5, 5, 10, 5, 5, 10, 20, 20}; // True - Correct change possible
        int[] bills4 = {10, 10}; // False - No $5 bills available for the first $10

        // Test and print results
        System.out.println(lemonadeChange(bills1)); // Output: true
        System.out.println(lemonadeChange(bills2)); // Output: false
        System.out.println(lemonadeChange(bills3)); // Output: true
        System.out.println(lemonadeChange(bills4)); // Output: false
    }
}

// Output :
/*
true
false
true
false
 */

// Approach / Intuition : Optimal Solution
/*
We can approach this question by maintaining two counters to keep track of the available 5$ and 10$ bills.
We iterate through the customers and provide change accordingly.

If a customer pays with a 5$ bill, we simply keep it as we will not have to provide any change.
If the customer pays with a 10$ bill, we check if there is at least one 5$ bill available to
provide change and decrement the counter.

If the customer uses a 20$ bill, we provide change using 5$ and 10$ bills or with three 5$ bills.
If at any point, we are not able to provide the required change we return false.

Otherwise, if we successfully serve all customers with the correct change, we can return true.

Algorithm:
Step 1:Initialise two counters, fives and tens to keep track of the number of 5$ and 10$ bills at hand.
Initialise them to zero as we hold no change at the starting.

Step 2: Loop through each bill in the bills array and check the following: If the bill is 5$,
increment the fives counter.

Step 3: If the bill is 10$, check if there are any 5$ bills available.
    1. If yes, provide change by incrementing the 10$ bill counter and decrementing the 5$ bill counter.
    2. If there are no 5$ bills available, return false as we cannot provide change.

Step 4: If the current bill is 20$, check if both 5$ and 10$ bills are available. If yes, provide
change by decreasing the counter of 5$ and 10$ bills.
    1. If there are not enough 10$ bills available, check if there are at atleast three 5$ bills available.
       If yes, provide change by decreasing the 5$ counter by 3.
    2. If not able to provide change, return false.

Step 5: If all customers are served with the correct change, we exit out of the loop and return true.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=n_tmibEhO6Q