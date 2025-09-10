package StacksAndQueues;

import java.util.Stack;

public class Implementing_Queue_using_Single_Stack_Recursive {

    // Create a stack
    Stack<Integer> stack = new Stack<>();

    // Main Function
    public static void main(String[] args) {
        Implementing_Queue_using_Single_Stack_Recursive q = new Implementing_Queue_using_Single_Stack_Recursive();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);

        System.out.println(q.dequeue()); // 10
        System.out.println(q.dequeue()); // 20
        System.out.println(q.dequeue()); // 30

        q.enqueue(50);

        System.out.println(q.dequeue()); // 40
        System.out.println(q.dequeue()); // 50
        System.out.println(q.dequeue()); // Queue is empty → -1
    }

    // Enqueue operation (push)
    public void enqueue(int x) {
        stack.push(x);
    }

    // Dequeue operation (recursive)
    public int dequeue() {
        if (stack.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        // Pop an item from stack
        int x = stack.pop();

        // If stack becomes empty, this is the front of the queue
        if (stack.isEmpty()) {
            return x;
        }

        // Otherwise, recurse
        int res = dequeue();

        // Push the popped item back after recursion
        stack.push(x);

        return res;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

// Time and Space Complexity:
/*
Enqueue:
Just push() → O(1) time
Space: O(1) (only stack push)

Dequeue:
We recursively pop elements until the bottom (n elements in worst case).
Recursion depth = O(n).
Each element is popped and pushed once → O(n) time.
Space: extra O(n) due to recursion call stack.

Time Complexity:
enqueue(x) → O(1)
dequeue() → O(n)

Space Complexity:
enqueue(x) → O(1)
dequeue() → O(n) (recursion call stack)
 */