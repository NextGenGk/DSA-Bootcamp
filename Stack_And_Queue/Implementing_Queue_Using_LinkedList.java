package Stack_And_Queue;

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

    private Node front;
    private Node rear;

    // Constructor to initialize an empty queue
    public Implementing_Queue_Using_LinkedList() {
        this.front = null;
        this.rear = null;
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
            System.out.println("Successfully inserted " + value + " into the queue");
            return;
        }

        // Otherwise, add the new node at the end of the queue and update rear
        rear.next = newNode;
        rear = newNode;
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

    // deleteQueue() - Delete the queue
    public void deleteQueue() {
        front = null;
        rear = null;
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

        // Test peek() method
        System.out.println("Front element: " + queue.peek());

        // Test deQueue() method
        System.out.println("Dequeued element: " + queue.deQueue());

        // Test peek() method again after deQueue
        System.out.println("Front element after dequeue: " + queue.peek());

        // Test deleteQueue() method
        queue.deleteQueue();
    }
}

// Output :
/*
Successfully inserted 10 into the queue
Successfully inserted 20 into the queue
Successfully inserted 30 into the queue
Successfully inserted 40 into the queue
Front element: 10
Dequeued element: 10
Front element after dequeue: 20
Queue deleted successfully
 */