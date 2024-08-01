package LinkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Sort_a_LinkedList {

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

    static Node sortLL1(Node head) {
        Node temp = head;
        ArrayList<Integer> list = new ArrayList<Integer>();

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

    static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    static Node mergeTwoLists(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                current.next = l1;
                l1 = l1.next;
            }
            else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;;
        }

        if (l1 != null) {
            current.next = l1;
        }
        else {
            current.next = l1;
        }
        return dummy.next;
    }

    static Node sortLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = findMiddle(head);
        // Divide the list into two halves
        Node right = middle.next;
        middle.next = null;
        Node left = head;

        left = sortLL(left);
        right = sortLL(right);

        return mergeTwoLists(left, right);
    }

    // Function to print the linked list
    public static void printLinkedList(Node head) {
        Node temp = head;
        while (temp != null) {
            // Print the data of the current node
            System.out.print(temp.data + " ");
            // Move to the next node
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Linked List: 3 2 5 4 1
        Node head = new Node(3);
        head.next = new Node(2);
        head.next.next = new Node(5);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(1);

        System.out.print("Original Linked List: ");
        printLinkedList(head);

        // Sort the linked list
        head = sortLL1(head);

        System.out.print("Sorted Linked List: ");
        printLinkedList(head);
    }
}
