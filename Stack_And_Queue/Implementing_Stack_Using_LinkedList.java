package Stack_And_Queue;

public class Implementing_Stack_Using_LinkedList {

    // Node class
    private class Node {
        int value;
        Node next;
    }

    private Node topOfStack;
    private int size; // Variable to keep track of the size of the stack

    // Constructor to initialize the stack
    public Implementing_Stack_Using_LinkedList() {
        topOfStack = null; // Stack is initially empty
        size = 0; // Initialize size to 0
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return topOfStack == null;
    }

    // Push a value onto the stack
    public void push(int value) {
        Node newNode = new Node();
        newNode.value = value;
        newNode.next = topOfStack;
        topOfStack = newNode;
        size++; // Increment size when a new element is pushed
        System.out.println("Value inserted successfully");
    }

    // Pop a value from the stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("The stack is empty");
            return -1;
        } else {
            int topValue = topOfStack.value;
            topOfStack = topOfStack.next;
            size--; // Decrement size when an element is popped
            return topValue;
        }
    }

    // Peek at the top value of the stack
    public int peek() {
        if (isEmpty()) {
            System.out.println("The stack is empty");
            return -1;
        } else {
            return topOfStack.value;
        }
    }

    // Get the current size of the stack
    public int size() {
        return size;
    }

    // Delete the stack
    public void deleteStack() {
        topOfStack = null;
        size = 0; // Reset size to 0 when the stack is deleted
        System.out.println("Stack is successfully deleted");
    }

    public static void main(String[] args) {
        Implementing_Stack_Using_LinkedList stack = new Implementing_Stack_Using_LinkedList();

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

        // Test peek() method
        System.out.println("Top value: " + stack.peek());

        // Test size() method again after pop
        System.out.println("Current stack size after pop: " + stack.size());

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
Top value: 20
Current stack size after pop: 2
Stack is successfully deleted
Is stack empty? true
*/