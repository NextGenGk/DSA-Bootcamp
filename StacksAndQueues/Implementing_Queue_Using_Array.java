package StacksAndQueues;

class Implementing_Queue_Using_Array {
    int[] arr;
    int front;
    int rear;
    int capacity;
    int currentSize; // Variable to keep track of the current size of the queue

    // Constructor
    public Implementing_Queue_Using_Array(int capacity) {
        this.arr = new int[capacity];
        this.front = -1;
        this.rear = -1;
        this.capacity = capacity;
        this.currentSize = 0; // Initialize size to 0
        System.out.println("The Queue is created with the size of " + capacity);
    }

    // isEmpty()
    public boolean isEmpty() {
        if (currentSize == 0) {
            System.out.println("Queue is Empty");
            return true;
        } else {
            return false;
        }
    }

    // isFull()
    public boolean isFull() {
        if (currentSize == capacity) {
            System.out.println("Queue is Full");
            return true;
        } else {
            return false;
        }
    }

    // enQueue()
    public void enQueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (isEmpty()) {
                front = 0;
            }
            rear++;
            arr[rear] = value;
            currentSize++; // Increment size when a new element is enqueued
            System.out.println("Successfully inserted " + value + " into the queue");
        }
    }

    // deQueue()
    public int deQueue() {
        if (isEmpty()) {
            System.out.println("The Queue is empty");
            return -1;
        } else {
            int result = arr[front];
            front++;
            currentSize--; // Decrement size when an element is dequeued
            // Reset front and rear if the queue is now empty
            if (currentSize == 0) {
                front = -1;
                rear = -1;
            }
            return result;
        }
    }

    // peek()
    public int peek() {
        if (isEmpty()) {
            System.out.println("The Queue is empty");
            return -1;
        } else {
            return arr[front];
        }
    }

    // size() method to return the current size of the queue
    public int size() {
        return currentSize;
    }

    // deleteQueue()
    public void deleteQueue() {
        arr = null;
        front = -1;
        rear = -1;
        currentSize = 0; // Reset size to 0 when the queue is deleted
        System.out.println("Deleted Queue Successfully");
    }

    // Main Function
    public static void main(String[] args) {
        Implementing_Queue_Using_Array queueArray = new Implementing_Queue_Using_Array(4);
        queueArray.enQueue(10);
        queueArray.enQueue(20);
        queueArray.enQueue(30);
        queueArray.enQueue(40);

        System.out.println("Current queue size: " + queueArray.size());

        int result = queueArray.peek();
        System.out.println("Front element: " + result);

        result = queueArray.deQueue();
        System.out.println("Dequeued element: " + result);

        System.out.println("Current queue size after dequeue: " + queueArray.size());

        queueArray.deleteQueue();
    }

    // Implementation v2
    static class Queue {
        private static int[] queue; // Array to store elements
        private static int front, rear, capacity;

        // Constructor to initialize queue
        public Queue(int capacity) {
            this.capacity = capacity;
            queue = new int[capacity];
            front = rear = -1; // Initially empty
        }

        // Check if the queue is empty
        public static boolean isEmpty() {
            return front == -1;
        }

        // Check if the queue is full
        public static boolean isFull() {
            return rear == capacity - 1;
        }

        // Enqueue operation (add element at the rear)
        public static void enqueue(int item) {
            if (isFull()) {
                System.out.println("Queue is full! Cannot enqueue.");
                return;
            }
            if (isEmpty()) {
                front = 0; // Set front when first element is inserted
            }
            queue[++rear] = item;
            System.out.println("Enqueued: " + item);
        }

        // Dequeue operation (remove element from the front)
        public static void dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty! Cannot dequeue.");
                return;
            }
            System.out.println("Dequeued: " + queue[front]);
            if (front == rear) {
                // Reset queue if last element is removed
                front = rear = -1;
            } else {
                front++;
            }
        }

        // Get the front element
        public static int front() {
            if (isEmpty()) {
                System.out.println("Queue is empty!");
                return -1;
            }
            return queue[front];
        }

        // Display the queue elements
        public static void display() {
            if (isEmpty()) {
                System.out.println("Queue is empty!");
                return;
            }
            System.out.print("Queue: ");
            for (int i = front; i <= rear; i++) {
                System.out.print(queue[i] + " ");
            }
            System.out.println();
        }

        // Main method to test the queue
        public static void main(String[] args) {
            Queue q = new Queue(5);

            q.enqueue(10);
            q.enqueue(20);
            q.enqueue(30);
            q.enqueue(40);
            q.enqueue(50);
            q.enqueue(60); // Should display "Queue is full!"

            q.display();

            q.dequeue();
            q.dequeue();

            q.display();

            System.out.println("Front element: " + q.front());
        }
    }

}

// Output:
/*
The Queue is created with the size of 4
Successfully inserted 10 into the queue
Successfully inserted 20 into the queue
Successfully inserted 30 into the queue
Successfully inserted 40 into the queue
Current queue size: 4
Front element: 10
Dequeued element: 10
Current queue size after dequeue: 3
Deleted Queue Successfully
*/