package StacksAndQueues;

import java.util.ArrayList;
import java.util.Stack;

public class Stock_Span_Problem {

    // Method 1 : Brute Force
    // Time Complexity : O(N), total no. of next calls.
    // Space Complexity : O(N), to store the prices for each day.
    static class StockSpanner {
        // ArrayList to store the prices of stocks
        ArrayList<Integer> list;

        // Constructor to initialize the ArrayList
        public StockSpanner() {
            list = new ArrayList<Integer>();
        }

        // Method to calculate the span of the current stock price
        public int next(int price) {
            // Add the current price to the list
            list.add(price);

            // Initialize the span count as 1 (the current day itself)
            int count = 1;

            // Loop through the list of prices in reverse order, starting from the second-last element
            for (int i = list.size() - 2; i >= 0; i--) {
                // If the current price is greater than or equal to the price at index i, increment the span count
                if (list.get(i) <= price) {
                    count++;
                } else {
                    // If a price greater than the current price is encountered, stop the loop
                    break;
                }
            }

            // Return the calculated span count
            return count;
        }
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N), total no. of next calls.
    // Space Complexity : O(N), to store the prices for each day.
    static class StockSpanner1 {
        // Stack to store pairs of (span, price)
        Stack<int[]> stack;

        // Constructor to initialize the stack
        public StockSpanner1() {
            stack = new Stack<int[]>();
        }

        // Method to calculate the span of the current stock price
        public int next(int price) {
            // Initialize the span of the current price to 1 (at least includes itself)
            int span = 1;

            // While the stack is not empty and the current price is greater than or equal to the price at
            // the top of the stack
            while (!stack.isEmpty() && stack.peek()[1] <= price) {
                // Add the span of the price at the top of the stack to the current span
                span += stack.peek()[0];
                // Remove the top of the stack as it's no longer needed
                stack.pop();
            }

            // Push the current price and its calculated span onto the stack
            stack.push(new int[]{span, price});

            // Return the calculated span
            return span;
        }
    }

    // Main Function
    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();

        // Example stock prices
        int[] prices = {7, 2, 1, 3, 3, 1, 8};

        // Printing the span for each price
        for (int price : prices) {
            int span = stockSpanner.next(price);
            System.out.println("Price: " + price + ", Span: " + span);
        }
    }
}

// Output :
/*
Price: 7, Span: 1
Price: 2, Span: 1
Price: 1, Span: 1
Price: 3, Span: 3
Price: 3, Span: 4
Price: 1, Span: 1
Price: 8, Span: 7
 */

// Approach : Brute Force
/*
Starting from that day and going backward, for which the stock price was less than or equal to
the price of that day.
 */

// Approach : Optimal Solution
/*
Algorithm :
1. Initialize: Create an empty stack to store pairs of (span, price).
2. Process Each Price:
    1. Start with Span: Set the initial span of the current price to 1.
    2. Adjust Span:
        1. While the stack is not empty and the current price is greater than or equal to the price at the top of the stack:
            1.Add the span of the top element to the current span.
            2. Remove the top element from the stack.
    3. Push Current Price and Span: Push a new pair (current span, current price) onto the stack.
3. Return Span: Return the calculated span for the current price.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=eay-zoSRkVc
