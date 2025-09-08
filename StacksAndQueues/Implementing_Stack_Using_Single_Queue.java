package StacksAndQueues;

import java.util.LinkedList;
import java.util.Queue;

public class Implementing_Stack_Using_Single_Queue {
    // Create a queue
    Queue<Integer> queue;

    // Constructor to initialize the stack
    public Implementing_Stack_Using_Single_Queue() {
        queue = new LinkedList<>();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Push a value onto the stack
    public void push(int value) {
        int size = queue.size();
        queue.offer(value);

        // Rotate the queue to make the last inserted element the front element
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }

        System.out.println("Value inserted successfully");
    }

    // Pop a value from the stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("The stack is empty");
            return -1;
        }

        return queue.poll();
    }

    // Peek at the top value of the stack
    public int peek() {
        if (isEmpty()) {
            System.out.println("The stack is empty");
            return -1;
        }

        return queue.peek();
    }

    // Get the current size of the stack
    public int size() {
        return queue.size();
    }

    // Delete the stack
    public void deleteStack() {
        queue.clear();
        System.out.println("Stack is successfully deleted");
    }

    public static void main(String[] args) {
        Implementing_Stack_Using_Single_Queue stack = new Implementing_Stack_Using_Single_Queue();

        // Test isEmpty() method
        System.out.println("Is stack empty? " + stack.isEmpty());

        // Test push() method
        stack.push(21);
        stack.push(20);
        stack.push(10);

        // Test size() method
        System.out.println("Current stack size: " + stack.size());

        // Test pop() method
        System.out.println("Popped value: " + stack.pop());

        // Test size() method again after pop
        System.out.println("Current stack size after pop: " + stack.size());

        // Test peek() method
        System.out.println("Top value: " + stack.peek());

        // Test deleteStack() method
        stack.deleteStack();

        // Check if the stack is empty after deletion
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}

// Output:
/*
Is stack empty? true
Value inserted successfully
Value inserted successfully
Value inserted successfully
Current stack size: 3
Popped value: 10
Current stack size after pop: 2
Top value: 20
Stack is successfully deleted
Is stack empty? true
*/
