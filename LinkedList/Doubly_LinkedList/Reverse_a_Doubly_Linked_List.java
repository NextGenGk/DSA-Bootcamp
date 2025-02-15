package LinkedList.Doubly_LinkedList;

import java.util.Stack;

public class Reverse_a_Doubly_Linked_List {

    // Node class represents a node in a linked list
    static class Node {
        int data;  // Data stored in the node
        Node next; // Pointer to the next node in the list
        Node prev; // Pointer to the previous node in the list

        // Constructor with data and next node as parameters
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
            this.prev = null;
        }

        // Constructor with only data as a parameter, sets next and prev to null
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // Method 1 : Brute Force (Using Stack)
    // Time Complexity : O(2N) because we are traversing the list twice
    // Space Complexity : O(N) because we are using a stack to store the data of the nodes
    // Method to reverse a doubly linked list
    static Node reverse(Node head) {
        Stack<Integer> stack = new Stack<Integer>();
        Node temp = head; // Start from the head of the list
        while (temp != null) {
            stack.push(temp.data); // Push the data of the current node onto the stack
            temp = temp.next; // Move to the next node
        }

        temp = head;
        while (temp != null) {
            temp.data = stack.pop(); // Pop the data from the stack and store it in the current node
            temp = temp.next; // Move to the next node
        }
        return head; // Return the modified head
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N) because we are traversing the list only once
    // Space Complexity : O(1) no extra space is using
    // Method to reverse a doubly linked list
    static Node reverse2(Node head) {
        // If the list is empty or has only one node, return the head
        if (head == null || head.next == null) return head;

        // Initialize two pointers
        Node last = null;
        Node current = head;

        // Traverse the list
        while (current != null) {
            last = current.prev; // Store the previous node
            current.prev = current.next; // Swap the prev and next pointers
            current.next = last; // Set the next pointer to the previous node
            current = current.prev; // Move to the next node
        }

        // If the last node is not null, set it as the new head
        return last.prev;
    }

    // Method to print the doubly linked list
    static void printList(Node head) {
        Node temp = head; // Start from the head of the list

        // Traverse the list
        while (temp != null) {
            System.out.print(temp.data + " <-> "); // Print the data of the current node
            temp = temp.next; // Move to the next node
        }

        System.out.println("null"); // Indicate end of the list
    }

    // Main Function
    public static void main(String[] args) {
        // Create a doubly linked list: 1 <-> 2 <-> 3 <-> 4 <-> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;

        // Print the original doubly linked list
        System.out.println("Original Doubly Linked List:");
        printList(head);

        // Reverse the doubly linked list
        head = reverse2(head);

        // Print the reversed doubly linked list
        System.out.println("Reversed Doubly Linked List:");
        printList(head);
    }
}

// Output :
/*
Original Doubly Linked List:
1 <-> 2 <-> 3 <-> 4 <-> 5 <-> null
Reversed Doubly Linked List:
5 <-> 4 <-> 3 <-> 2 <-> 1 <-> null
 */

// Intuition : Brute Force (Using Stack)
/*
Algorithm / Intuition
A brute-force approach involves replacing data in a doubly linked list. First, we traverse
the list and store node data in a stack. Then, in a second pass, we assign elements from the
stack to nodes, ensuring a reverse order replacement since stacks follow the Last-In-First-Out
(LIFO) principle.

Algorithm:
Step 1: Initialization a temp pointer to the head of the doubly linked list and a stack data
structure to store the values from the list.
Step 2: Traverse the doubly linked list with the temp pointer and while traversing push the
value at the current node temp onto the stack. Move the temp to the next node continuing until
temp reaches null indicating the end of the list.
Step 3: Reset the temp pointer back to the head of the list and in thissecond iteration pop
the element from the stack, replace the data at the current node with the popped value from
the top of the stack and move temp to the next node. Repeat this step until temp reaches null
or the stack becomes empty.
 */

// Intuition : Optimal Solution
/*
Algorithm / Intuition
Reverse the Links in a Single Traversal
Instead of performing two separate traversals of the linked list and storing its node values
in an external data structure, we can optimize our approach by directly modifying the links
between the nodes within the doubly linked list in place, as visualized below:

We need to traverse on every node, and for every node change the next pointer and back pointer.
If we can do this for all nodes, at the end of traversal, the doubly linked list will be reversed.

Algorithm:
Step 1: Initialise two pointers that are needed for the reversal. Initialize a current pointer
to the head of the linked list. This pointer will traverse the list as we reverse it. Initialize
a second pointer last to null. This pointer will be used for temporary storage during pointer swapping,
as we need a third variable while swapping two data.
Step 2: Traverse through the DLL by looping over all the nodes..
Step 3: While iterating over all nodes in the linked list, we make changes to set the backward
pointer of a node to the next changing its previous link. Along with this, the forward pointer
is adjusted to point to the previous node, reversing the next link. To prevent losing the last
node in this process, we use a reference to the last node to retain it.

    1. Update the current node's back pointer to point to the next node (current->back = current->next).
       This step reverses the direction of the backward pointer.
    2. Update the current node's next pointer to point to the previous node (current->next = last).
       This step reverses the direction of the forward pointer.
    3. Move the current pointer one step forward (current = current->back). This allows us to continue
       the reversal process.
Step 4: After completing the traversal, the last node ends up at the second node in the reversed doubly
linked list. To obtain the new head of the reversed list, we simply use the backward pointer of the last
node, which points to the new head.

To ensure that we handle the case where the traversal ended at the original list's end (i.e., the last
pointer is not null), we update the head pointer to point to the new head of the reversed list, which
is stored in the last pointer.

Finally, we return the head pointer, now pointing to the head of the fully reversed doubly linked list.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=u3WUW2qe6ww