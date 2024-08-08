package Stack_And_Queue;

public class Implementing_Stack_Using_LinkedList {

    private class Node {
        int value;
        Node next;
    }

    private Node topOfStack;

    // Constructor to initialize the stack
    public Implementing_Stack_Using_LinkedList() {
        topOfStack = null; // Stack is initially empty
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

    // Delete the stack
    public void deleteStack() {
        topOfStack = null;
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

        // Test pop() method
        System.out.println("Popped value: " + stack.pop());

        // Test peek() method
        System.out.println("Top value: " + stack.peek());

        // Test deleteStack() method
        stack.deleteStack();

        // Check if the stack is empty after deletion
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}

// Output :
/*
Is stack empty? true
Value inserted successfully
Value inserted successfully
Value inserted successfully
Popped value: 10
Top value: 20
Stack is successfully deleted
Is stack empty? true
 */