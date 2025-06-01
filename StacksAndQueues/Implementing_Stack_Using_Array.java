package StacksAndQueues;

public class Implementing_Stack_Using_Array {
    // Declaration
    int[] arr;
    int topOfStack;

    // Constructor to initialize the stack with a given size
    public Implementing_Stack_Using_Array(int size) {
        arr = new int[size];
        topOfStack = -1; // Stack is initially empty
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return topOfStack == -1;
    }

    // Check if the stack is full
    public boolean isFull() {
        if (topOfStack == arr.length - 1) {
            System.out.println("The Stack is Full");
            return true;
        } else {
            return false;
        }
    }

    // Push a value onto the stack
    public void push(int value) {
        if (isFull()) {
            System.out.println("The Stack is full");
        } else {
            arr[++topOfStack] = value;
            System.out.println("Value inserted successfully");
        }
    }

    // Pop a value from the stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("The stack is empty");
            return -1;
        } else {
            return arr[topOfStack--];
        }
    }

    // Peek at the top value of the stack
    public int peek() {
        if (isEmpty()) {
            System.out.println("The stack is empty");
            return -1;
        } else {
            return arr[topOfStack];
        }
    }

    // Get the current size of the stack
    public int size() {
        return topOfStack + 1;
    }

    // Delete the stack
    public void deleteStack() {
        arr = null;
        topOfStack = -1;
        System.out.println("Stack is successfully deleted");
    }

    // Main Function
    public static void main(String[] args) {
        Implementing_Stack_Using_Array stack = new Implementing_Stack_Using_Array(10);

        // Test isEmpty() method
        System.out.println("Is stack empty? " + stack.isEmpty());

        // Test push() method
        stack.push(21);
        stack.push(20);
        stack.push(10);

        // Test size() method
        System.out.println("Current stack size: " + stack.size());

        // Test isFull() method
        System.out.println("Is stack full? " + stack.isFull());

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
Is stack full? false
Popped value: 10
Top value: 20
Current stack size after pop: 2
Stack is successfully deleted
Is stack empty? true
*/
