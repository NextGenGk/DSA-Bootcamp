package Stack_And_Queue;

 class Implementing_Queue_Using_Queue {
    int[] arr;
    int front;
    int rear;
    int size;

    // Constructor
    public Implementing_Queue_Using_Queue(int size) {
        this.arr = new int[size];
        this.front = -1;
        this.rear = -1;
        this.size = size;
        System.out.println("The Queue is created with the size of " + size);
    }

    // isEmpty()
    public boolean isEmpty() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is Empty");
            return true;
        } else {
            return false;
        }
    }

    // isFull()
    public boolean isFull() {
        if (rear == size - 1) {
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
        } else if (isEmpty()) {
            front = 0;
            rear = 0;
            arr[rear] = value;
            System.out.println("Successfully inserted " + value + " into the queue");
        } else {
            rear++;
            arr[rear] = value;
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
            // Reset front and rear if the queue is now empty
            if (front > rear) {
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

    // deleteQueue()
    public void deleteQueue() {
        arr = null;
        front = -1;
        rear = -1;
        System.out.println("Deleted Queue Successfully");
    }

    // Main Function
    public static void main(String[] args) {
        Implementing_Queue_Using_Queue queueArray = new Implementing_Queue_Using_Queue(4);
        queueArray.enQueue(10);
        queueArray.enQueue(20);
        queueArray.enQueue(30);
        queueArray.enQueue(40);

        int result = queueArray.peek();
        System.out.println("Front element: " + result);

        result = queueArray.deQueue();
        System.out.println("Dequeued element: " + result);

        queueArray.deleteQueue();
    }
}

// Output :
/*
The Queue is created with the size of 4
Queue is Empty
Successfully inserted 10 into the queue
Successfully inserted 20 into the queue
Successfully inserted 30 into the queue
Successfully inserted 40 into the queue
Front element: 10
Dequeued element: 10
Deleted Queue Successfully
 */