package Stack_And_Queue;

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