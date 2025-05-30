package LinkedList;

public class Add_1_to_LinkedList {

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

    // Reverse LinkedList Function
    static Node reverseLinkedListIterative(Node head) {
        // Initialize 'temp' at
        // head of linked list
        Node temp = head;

        // Initialize pointer 'prev' to NULL,
        // representing the previous node
        Node prev = null;

        // Traverse the list, continue till
        // 'temp' reaches the end (NULL)
        while (temp != null) {
            // Store the next node in
            // 'front' to preserve the reference
            Node front = temp.next;

            // Reverse the direction of the
            // current node's 'next' pointer
            // to point to 'prev'
            temp.next = prev;

            // Move 'prev' to the current
            // node for the next iteration
            prev = temp;

            // Move 'temp' to the 'front' node
            // advancing the traversal
            temp = front;
        }

        // Return the new head of
        // the reversed linked list
        return prev;
    }

    // Method 1 : Brute Force
    // Time Complexity : O(3N)
    // Space Complexity : O(1)
    // Function to add 1 to a number represented as a linked list
    static Node add1(Node head) {
        // If the list is empty or contains only one node, return the head as it is
        if (head == null || head.next == null) {
            if (head != null) {
                head.data += 1;  // Add 1 if there's only one node
            }
            return head;
        }

        // Reverse the linked list
        head = reverseLinkedListIterative(head);
        int carry = 1;  // Initialize carry as 1 since we are adding 1

        Node temp = head;
        // Traverse the reversed list and add the carry
        while (temp != null) {
            temp.data = temp.data + carry;
            if (temp.data < 10) {
                carry = 0;  // If no carry generated, break the loop
                break;
            } else {
                temp.data = 0;  // If carry is generated, set current node to 0 and carry to 1
                carry = 1;
            }
            temp = temp.next;
        }

        // If carry is still 1 after the loop, add a new node at the front
        if (carry == 1) {
            Node node = new Node(1);
            /*
            We reverse the list back before adding the new node so that the new node is added 
            to the front in the correct (original) order, making it the most significant digit.
            */
            head = reverseLinkedListIterative(head);
            node.next = head;
            return node;
        }

        // Reverse the list again to restore the original order
        head = reverseLinkedListIterative(head);
        return head;
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    // Function to add 1 to a number represented as a linked list using recursion
    static Node add1s(Node head) {
        // Recursive helper function call
        int carry = helper(head);
        // If there's a carry left after processing the entire list, add a new node at the front
        if (carry == 1) {
            Node node = new Node(1);
            node.next = head;
            head = node;
        }
        return head;
    }

    // Helper function to recursively add 1 to the linked list
    private static int helper(Node head) {
        // Base case: If the node is null, return carry 1 (for adding 1 at the end)
        if (head == null) {
            return 1;
        }
        // Recursive call for the next node
        int carry = helper(head.next);
        // Add the carry to the current node's data
        head.data = head.data + carry;
        // If the current node's data is less than 10, no carry is needed, return 0
        if (head.data < 10) {
            return 0;
        }
        // If the current node's data is 10 or more, set the data to 0 and return carry 1
        head.data = 0;
        return 1;
    }

    // Function to print the linked list
    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Main Function
    public static void main(String[] args) {
        // Create the linked list representing the number 129
        Node head = new Node(9);
        head.next = new Node(9);
        head.next.next = new Node(9);

        System.out.println("Original list:");
        printList(head);

        // Add 1 to the number
        head = add1s(head);

        System.out.println("List after adding 1:");
        printList(head);
    }
}

// Output :
/*
Original list:
9 9 9
List after adding 1:
1 0 0 0
 */

// Approach : Brute Force
/*
The problem involves adding 1 to a number represented by a linked list, where each node contains a single digit.
The challenge is to handle the carry that results from adding 1, especially when the digits are 9
(e.g., 1999 + 1 = 2000). By reversing the list, we can start the addition from the least significant
digit (rightmost), making it easier to manage the carry as we traverse the list.

1. Handle Edge Cases:

    i. If the linked list is empty, return the head as it is.
   ii. If the linked list contains only one node, simply add 1 to that node's data and return the head.

2. Reverse the Linked List:

    i. This makes it easier to add 1 to the number since we can start from the least significant digit.

3. Initialize Carry:

    i. Set the carry to 1 because we need to add 1 to the number.

4. Traverse the Reversed List:

    i. For each node in the reversed list, add the carry to the node's data.
   ii. If the resulting data is less than 10, set carry to 0 and break out of the loop.
  iii. If the resulting data is 10 or more, set the node's data to 0 and keep carry as 1 to add to the
       next significant digit.

5. Handle Remaining Carry:

    i. If carry is still 1 after traversing the list, create a new node with data 1 and add it to the
       front of the list. This handles cases where adding 1 results in an additional digit
       (e.g., 999 + 1 = 1000).

6. Reverse the List Again:

    i. Reverse the list back to its original order.

7. Return the Modified Head:

    i. Return the head of the modified list.
 */

// Approach : Optimal Solution
/*
Intuition : Using (Recursion)
The problem involves adding 1 to a number represented by a linked list, where each node contains a single
digit. The challenge is to handle the carry that results from adding 1, especially when the digits are
9 (e.g., 1999 + 1 = 2000). By using recursion, we can start the addition from the least significant
digit (rightmost), making it easier to manage the carry as we traverse the list backward.

Algorithm :

1. Base Case:

i. If the current node is null, it means we have reached the end of the list. In this case, return a carry of
1 because we are adding 1 at the end of the list.

2. Recursive Step:

i. Recursively call the helper function on the next node. This moves the recursion to the end of the list first.

3. Add Carry:

    i. Add the carry returned by the recursive call to the current node's data.
   ii. If the resulting data is less than 10, it means no further carry is needed. Return 0 as the carry.
  iii. If the resulting data is 10 or more, set the current node's data to 0 (since 10 becomes 0 with a carry)
    and return a carry of 1 to propagate it to the next significant digit.

4. Handle Remaining Carry:

i. After the recursion completes, check if there is still a carry left. If carry is 1, create a new node
with data 1 and add it to the front of the list to handle cases like 999 + 1 = 1000.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=aXQWhbvT3w0
