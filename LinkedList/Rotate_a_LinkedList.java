package LinkedList;

public class Rotate_a_LinkedList {

    // Node class represents
    // a node in a linked list
    static class Node {
        // Data stored in the node
        int data;
        // Pointer to the next node in the list
        Node next;

        // Constructor with both data
        // and next node as parameters
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        // Constructor with only data as
        // a parameter, sets next to null
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Method 1 : Optimal Solution
    // Time Complexity: O(length of list) + O(length of list - (length of list%k))
    // Reason: O(length of the list) for calculating the length of the list.
    // O(length of the list - (length of list%k)) for breaking link.
    // Space Complexity: O(1)
    // Reason: No extra data structure is used for computation.

    // Function to rotate the linked list to the right by k positions
    staic Node rotateRight(Node head, int k) {
        // Edge cases: empty list, single node list, or no rotation needed
        if (head == null || head.next == null || k == 0) {
            return head;
        }
    
        // Find the length of the list and the last node (tail)
        int length = 1;
        Node tail = head;
    
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
    
        // Connect the tail to the head to form a circular linked list
        tail.next = head;
    
        // Reduce unnecessary rotations
        // Rotating by length times results in the same list
        k = k % length;
    
        // Find the position of the new tail
        // New tail will be at (length - k)th node
        int stepsToNewTail = length - k;
        Node newTail = head;
    
        // Move to the node just before the new head
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
    
        // The node after newTail becomes the new head
        Node newHead = newTail.next;
    
        // Break the circular linked list
        newTail.next = null;
    
        // Return the rotated list
        return newHead;
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample linked list 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Number of rotations
        int k = 2;

        // Rotating the linked list
        head = rotateLinkedList(head, k);

        // Printing the rotated linked list
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
}

// Output : 
// 4 5 1 2 3

// Approach : Optimal Solution
/*
Steps to the algorithm:-

1. Calculate the length of the list.
2. Connect the last node to the first node, converting it to a circular linked list.
3. Iterate to cut the link of the last node and start a node of k%length of the list rotated list.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=uT7YI7XbTY8
