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

    // Function to find the k-th node in the linked list
    static Node findKthNode(Node temp, int k) {
        int cnt = 1;
        // Traverse the list to find the k-th node
        while (temp != null) {
            if (cnt == k) return temp;
            cnt++;
            temp = temp.next;
        }
        return temp; // This will return null if k is out of bounds
    }

    // Method 1 : Optimal Solution
    // Time Complexity: O(length of list) + O(length of list - (length of list%k))
    // Reason: O(length of the list) for calculating the length of the list.
    // O(length of the list - (length of list%k)) for breaking link.
    // Space Complexity: O(1)
    // Reason: No extra data structure is used for computation.

    // Function to rotate the linked list by k nodes
    static Node rotateLinkedList(Node head, int k) {
        // Base cases: if the list is empty, has only one node, or no rotation is needed
        if (head == null || head.next == null || k == 0) return head;

        // Calculate the length of the linked list
        int len = 1;
        Node tail = head;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }

        // If k is a multiple of the length, no rotation is needed
        if (k % len == 0) return head;

        // Calculate the effective rotations needed
        k = k % len;

        // Link the last node to the first node to make it a circular list
        tail.next = head;

        // Find the node that will be the new tail after rotation
        Node lastNode = findKthNode(head, len - k);

        // The new head is the next node of the new tail
        head = lastNode.next;

        // Break the loop to finalize the rotated list
        lastNode.next = null;

        // return the head of the modified list
        return head;
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

// Output : 4 5 1 2 3

// Approach : Optimal Solution
/*
Steps to the algorithm:-

1. Calculate the length of the list.
2. Connect the last node to the first node, converting it to a circular linked list.
3. Iterate to cut the link of the last node and start a node of k%length of the list rotated list.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=uT7YI7XbTY8
