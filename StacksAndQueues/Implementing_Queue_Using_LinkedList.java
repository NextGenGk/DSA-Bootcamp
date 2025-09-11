package StacksAndQueues;

public class Implementing_Queue_Using_LinkedList {
    
    // Node class
    class Node {
        int data;
        Node next;

        // Constructor
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Initialize variables
    private Node front;
    private Node rear;
    private int size; // Counter to track the size of the queue

    // Constructor to initialize an empty queue
    public Implementing_Queue_Using_LinkedList() {
        this.front = null;
        this.rear = null;
        this.size = 0; // Initialize size to 0
    }

    // isEmpty() - Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // enQueue() - Add an element to the rear of the queue
    public void enQueue(int value) {
        Node newNode = new Node(value);

        // If the queue is empty, both front and rear point to the new node
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            // Otherwise, add the new node at the end of the queue and update rear
            rear.next = newNode;
            rear = newNode;
        }

        size++; // Increment size
        System.out.println("Successfully inserted " + value + " into the queue");
    }

    // deQueue() - Remove and return the front element from the queue
    public int deQueue() {
        if (isEmpty()) {
            System.out.println("The Queue is empty");
            return -1;
        }

        int result = front.data;
        front = front.next;

        // If the queue becomes empty after dequeuing, reset rear to null
        if (front == null) {
            rear = null;
        }

        size--; // Decrement size
        return result;
    }

    // peek() - Get the front element of the queue without removing it
    public int peek() {
        if (isEmpty()) {
            System.out.println("The Queue is empty");
            return -1;
        }
        return front.data;
    }

    // size() - Get the current size of the queue
    public int size() {
        return size;
    }

    // deleteQueue() - Delete the queue
    public void deleteQueue() {
        front = null;
        rear = null;
        size = 0; // Reset size to 0
        System.out.println("Queue deleted successfully");
    }

    // Main Function
    public static void main(String[] args) {
        Implementing_Queue_Using_LinkedList queue = new Implementing_Queue_Using_LinkedList();

        // Test enQueue() method
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);
        queue.enQueue(40);

        // Test size() method
        System.out.println("Current queue size: " + queue.size());

        // Test peek() method
        System.out.println("Front element: " + queue.peek());

        // Test deQueue() method
        System.out.println("Dequeued element: " + queue.deQueue());

        // Test size() method again after deQueue
        System.out.println("Queue size after dequeue: " + queue.size());

        // Test deleteQueue() method
        queue.deleteQueue();

        // Test size() method after deleting the queue
        System.out.println("Queue size after deletion: " + queue.size());
    }
}

// Output:
/*
Successfully inserted 10 into the queue
Successfully inserted 20 into the queue
Successfully inserted 30 into the queue
Successfully inserted 40 into the queue
Current queue size: 4
Front element: 10
Dequeued element: 10
Queue size after dequeue: 3
Queue deleted successfully
Queue size after deletion: 0
 */
