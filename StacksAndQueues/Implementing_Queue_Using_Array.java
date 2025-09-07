package StacksAndQueues;

class Implementing_Queue_Using_Array {

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
            if (front > rear) {
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
