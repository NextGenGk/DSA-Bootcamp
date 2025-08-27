package LinkedList;

import java.util.ArrayList;
import java.util.Collections;

public class Sort_LinkedList_0s_1s_and_2s {

    // Node class represents a node in a linked list
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

    // Method 1 : Brute Force (Using Data Replacement)
    // Time Complexity : O(N) + O(NlogN) + O(N)
    // Space Complexity : O(N) for using an list
    static Node sortLinkedList(Node head) {
        Node temp = head;
        ArrayList<Integer> list = new ArrayList<>();

        while (temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }

        Collections.sort(list);

        temp = head;
        int i=0;
        while (temp != null) {
            temp.data = list.get(i);
            i++;
            temp = temp.next;
        }
        return head;
    }

    // Method 2 : Better Solution
    // Time Complexity : O(2N) ~ O(N)
    // Space Complexity : O(1)
    static Node sortLinkedList1(Node head) {
        // Edge case
        if (head == null || head.next == null) {
            return head;
        }

        // Initialization
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        // Building the list
        Node temp = head;
        while (temp != null) {
            if (temp.data == 0) count0++;
            else if (temp.data == 1) count1++;
            count2++;
            temp = temp.next;
        }

        // Connecting the list
        temp = head;
        while (temp != null) {
            if (count0 > 0) {
                temp.data = 0;
                count0--;
            }
            else if (count1 > 0) {
                temp.data = 1;
                count1--;
            }
            else {
                temp.data = 2;
                count2--;
            }
            temp = temp.next;
        }
        // Return the new head
        return head;
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    static Node sortLinkedList2(Node head) {
        // If list is empty or has a single element, it's already sorted
        if (head == null || head.next == null) return head;
    
        // Create 3 dummy nodes for three separate linked lists:
        // one for 0s, one for 1s, and one for 2s.
        Node dummy0 = new Node(0);
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);
    
        // Pointers to track the current "tail" of each list
        Node zero = dummy0;
        Node one = dummy1;
        Node two = dummy2;
    
        // Traverse the original list and distribute nodes into 0,1,2 lists
        Node temp = head;
        while (temp != null) {
            if (temp.data == 0) {
                zero.next = temp;   // attach current node to 0-list
                zero = zero.next;   // move zero pointer forward
            } else if (temp.data == 1) {
                one.next = temp;    // attach current node to 1-list
                one = one.next;     // move one pointer forward
            } else {
                two.next = temp;    // attach current node to 2-list
                two = two.next;     // move two pointer forward
            }
            temp = temp.next;       // move to next node in original list
        }
    
        // Connect the three lists together:
        // if there are 1s, connect 0-list → 1-list, otherwise connect 0-list → 2-list
        zero.next = (dummy1.next != null) ? dummy1.next : dummy2.next;
    
        // connect 1-list → 2-list
        one.next = dummy2.next;
    
        // mark end of final list
        two.next = null;
    
        // The head of the new sorted list will be dummy0.next
        head = dummy0.next;
        return head;
    }

    // Helper function to print the linked list
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Main Function
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(0);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(0);

        System.out.println("Original list:");
        printList(head);

        head = sortLinkedList2(head);

        System.out.println("Sorted list:");
        printList(head);
    }
}

// Output :
/*
Original list:
1 2 0 1 2 0
Sorted list:
0 0 1 1 2 2
 */

// Approach : Brute Force
// Intuition : Using Data Replacement

// Approach : Better Solution
/*
Initialize Counters:

1. Create three integer variables count0, count1, and count2 to keep track of the number of 0s, 1s, and 2s in the linked
list. Initialize them all to 0.

Count the Occurrences:

1.  the linked list using a pointer temp initialized to head.
2. For each node, increment the corresponding counter (count0, count1, or count2) based on the value of the node (temp.data).
3. Move the pointer to the next node until the end of the list is reached (temp is null).

Repopulate the Linked List:

1.Reinitialize the pointer temp to head to start another traversal of the linked list.
2. For each node, set its value (temp.data) to 0, 1, or 2 based on the values of count0, count1, and count2.
3. Decrease the corresponding counter (count0, count1, or count2) after setting the value of the node.
4. Move the pointer to the next node until the end of the list is reached (temp is null).

Return the Sorted Linked List:

1. Return the head of the sorted linked list.
 */

// Approach : Optimal Solution
/*
Initialization:

1. Three dummy nodes (dummy0, dummy1, dummy2) are created to serve as the starting points for the separate lists
for 0s, 1s, and 2s.
2. Pointers zero, one, and two are used to traverse and build these lists.

Building the Lists:

1. The original list is traversed, and nodes are appended to the respective dummy nodes based on their values.

Connecting the Lists:

1. The separate lists are connected by setting zero.next to the head of the list of 1s or 2s if 1s are not present.
2. one.next is set to the head of the list of 2s.
3. two.next is set to null to terminate the list.

Setting the New Head:

1. The head of the new sorted list is set to dummy0.next.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=gRII7LhdJWc
