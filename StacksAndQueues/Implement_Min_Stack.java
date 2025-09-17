package StacksAndQueues;

import java.util.Stack;

public class Implement_Min_Stack {

    // Method 1 : Brute Force
    // Time Complexity : O(1)
    // Space Complexity : O(2 * N) Because we are storing pairs in stack
    
    // Class to represent a pair of integers.
    // x stores the value pushed onto the stack, y stores the minimum value at that point.
    class Pair {
        int x; // Stores the value pushed onto the stack
        int y; // Stores the minimum value at that point in the stack

        // Constructor to initialize Pair object with the value and the current minimum value
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Class to implement a stack that supports push, pop, top, and getMin operations in constant time.
    class MinStack {
        Stack<Pair> stack; // Stack to store pairs of values and their corresponding minimums

        // Constructor to initialize the stack
        MinStack() {
            stack = new Stack<Pair>();
        }

        // Pushes an element onto the stack while maintaining the minimum value.
        void push(int x) {
            int min;
            if (stack.isEmpty()) {
                // If the stack is empty, the current element is the minimum
                min = x;
            } else {
                // If the stack is not empty, compare the current element with the current minimum
                min = Math.min(stack.peek().y, x);
            }
            // Push the value and the updated minimum onto the stack
            stack.push(new Pair(x, min));
        }

        // Removes the top element from the stack and returns its value.
        int pop() {
            return stack.pop().x;
        }

        // Returns the value of the top element without removing it.
        int top() {
            return stack.peek().x;
        }

        // Returns the minimum element in the stack without removing any elements.
        int getMin() {
            return stack.peek().y;
        }
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(1)
    // Space Complexity : O(N)
    
    // Class to implement a stack that supports push, pop, top, and getMin operations in constant time
    static class MinStack1 {
        Stack<Long> st = new Stack<Long>(); // Stack to store modified values for maintaining the minimum
        Long mini; // Variable to store the current minimum value

        /** Initialize your data structure here. */
        public MinStack1() {
            mini = Long.MAX_VALUE; // Initialize the minimum value to the maximum possible Long value
        }

        // Pushes an element onto the stack while maintaining the minimum value
        public void push(int value) {
            Long val = Long.valueOf(value); // Convert the integer value to Long for consistency
            if (st.isEmpty()) {
                mini = val; // If the stack is empty, set the current value as the minimum
                st.push(val); // Push the value onto the stack
            } else {
                if (val < mini) {
                    // If the current value is less than the minimum, store a modified value
                    st.push(2 * val - mini);
                    mini = val; // Update the minimum to the current value
                } else {
                    st.push(val); // Otherwise, push the value as it is
                }
            }
        }

        // Removes the top element from the stack and updates the minimum value if necessary
        public void pop() {
            if (st.isEmpty()) return; // If the stack is empty, do nothing

            Long val = st.pop(); // Pop the top value from the stack
            if (val < mini) {
                // If the popped value is a modified value, restore the previous minimum
                mini = 2 * mini - val;
            }
        }

        // Returns the top element of the stack without removing it
        public int top() {
            Long val = st.peek(); // Peek the top value of the stack
            if (val < mini) {
                // If the top value is a modified value, return the current minimum
                return mini.intValue();
            }
            return val.intValue(); // Otherwise, return the top value
        }

        // Returns the minimum element in the stack
        public int getMin() {
            return mini.intValue(); // Return the current minimum value
        }

        // Main Function
        public static void main(String[] args) {
            MinStack1 minStack = new MinStack1(); // Create a MinStack object

            minStack.push(12);
            System.out.println("Pushed 12, Min: " + minStack.getMin()); // Output: 5

            minStack.push(15);
            System.out.println("Pushed 15, Min: " + minStack.getMin()); // Output: 3

            minStack.push(10);
            System.out.println("Pushed 10, Min: " + minStack.getMin()); // Output: 3

            minStack.pop();
            System.out.println("Popped top, Min: " + minStack.getMin()); // Output: 3

            System.out.println("Top element: " + minStack.top()); // Output: 3

            minStack.pop();
            System.out.println("Popped top, Min: " + minStack.getMin()); // Output: 5
        }
    }
}

// Output :
/*
Pushed 12, Min: 12
Pushed 15, Min: 12
Pushed 10, Min: 10
Popped top, Min: 12
Top element: 15
Popped top, Min: 12
 */

// Approach : Brute Force (Using pairs to store the value and minimum element till now)
/*
Approach: 

The first element in the pair will store the value and the second element will store the
minimum element till now.

When the first push operation comes in we will push the value and store it as minimum itself in the pair.

In the second push operation, we will check if the top element’s minimum is less than the new value.
If it is then we will push the value with minimum as the previous top’s minimum. To get the getMin
element to take the top’s second element.
 */

// Approach : Optimal Solution
/*
Let’s take a variable that stores the minimum number. So whenever a push operation comes in just
take that number put it in the stack and update the variable to the number.

Push operation:

Now if there is a push operation just check whether that number is less than the min number.
If it is smaller than min we will push a modified value which is a push(2 * Val - min) into the
stack and will update min to the value of the original number. If it’s not then we will just push
it as it is.

getMin() operation:

We will just return the value of min.

Top operation:

While returning the top value we know that it is a modified value. We will check if the top value is
lesser than min, If it is then we will return the min as the top value.

Pop operation:

While making pop we will check if the top value is lesser than min, If it is then we must update our
min to its previous value. In order to do that min = (2 * min) - (modified value) and we will pop the element.

# Idea from switching back to the old value, and inserting a new value

            2 × val - prevMini = newVal
            prevMini = 2 × val - newVal

                 10 < 12
                val < mini

                val - mini < 0
            val + val - mini < val (both side taking val)

            2 × val - prevMini = newVal
            prevMini = 2 × val - newVal

 */

// Striver's : (Video Explanation) : https://www.youtube.com/watch?v=NdDIaH91P0g
