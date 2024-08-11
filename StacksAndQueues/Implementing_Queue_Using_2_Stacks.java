package StacksAndQueues;

import java.util.Stack;

public class Implementing_Queue_Using_2_Stacks {
    Stack<Integer> input;
    Stack<Integer> output;

    //Initialize your data structure here.
    public Implementing_Queue_Using_2_Stacks() {
        input = new Stack<Integer>();
        output = new Stack<Integer>();
    }

    // Push() - Push element x to the back of queue.
    public void push(int x) {
        // Move all elements from input stack to output stack
        while (!input.empty()) {
            output.push(input.pop());
        }

        // Push the new element onto the input stack
        System.out.println("The element pushed is " + x);
        input.push(x);

        // Move all elements back from output stack to input stack
        while (!output.empty()) {
            input.push(output.pop());
        }
    }

    // Pop() - Removes the element from in front of queue and returns that element.
    public int pop() {
        if (input.empty()) {
            System.out.println("Queue is empty");
            return -1; // Return a default value indicating the queue is empty
        }
        return input.pop();
    }

    // Peek() - Get the front element.
    public int peek() {
        if (input.empty()) {
            System.out.println("Queue is empty");
            return -1; // Return a default value indicating the queue is empty
        }
        return input.peek();
    }

    // Size() - Get the current size of the queue.
    public int size() {
        return input.size();
    }

    // Main Function
    public static void main(String[] args) {
        Implementing_Queue_Using_2_Stacks queue = new Implementing_Queue_Using_2_Stacks();

        // Test push operation
        queue.push(10);
        queue.push(20);
        queue.push(30);

        // Test size operation
        System.out.println("Queue size: " + queue.size());

        // Test peek operation
        System.out.println("Front element: " + queue.peek());

        // Test pop operation
        System.out.println("Popped element: " + queue.pop());

        // Test size operation again after pop
        System.out.println("Queue size after pop: " + queue.size());
    }
}

// Output :
/*
The element pushed is 10
The element pushed is 20
The element pushed is 30
Queue size: 3
Front element: 10
Popped element: 10
Queue size after pop: 2
 */