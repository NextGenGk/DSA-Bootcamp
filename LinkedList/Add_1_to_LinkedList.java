package LinkedList;

public class Add_1_to_LinkedList {

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
        // If the list is empty, just return a new node with value 1
        if (head == null) {
            return new Node(1);
        }

        // Case: only one node in the list
        if (head.next == null) {
            head.data += 1; // Add 1 to the single node

            // If no carry is generated, return the head
            if (head.data < 10) {
                return head;
            } else {
                // If carry is generated (e.g., 9 -> 10)
                head.data = 0; // Set current digit to 0
                Node newHead = new Node(1); // Create a new node for carry
                newHead.next = head;        // Attach original node
                return newHead;
            }
        }

        // Step 1: Reverse the linked list to make addition easier (start from LSB)
        head = reverseLinkedListIterative(head);

        Node temp = head;
        int carry = 1; // We need to add 1

        // Step 2: Traverse list and handle carry
        while (temp != null) {
            temp.data += carry; // Add carry to current digit

            if (temp.data < 10) {
                // No carry generated, break out
                carry = 0;
                break;
            } else {
                // If carry generated (digit >= 10)
                temp.data = 0; // Set digit to 0
                carry = 1;     // Carry forward
            }
            temp = temp.next; // Move to next digit
        }

        // Step 3: If carry is still left after full traversal (e.g., 999 + 1 = 1000)
        if (carry == 1) {
            Node node = new Node(1);          // Create a new node for extra carry
            head = reverseLinkedListIterative(head); // Reverse back the list
            node.next = head;                 // Attach to the front
            return node;
        }

        // Step 4: Reverse back the list to restore original order
        head = reverseLinkedListIterative(head);
        return head;
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
        head = add1(head);

        System.out.println("List after adding 1:");
        printList(head);
    }

    // Method 2 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    // Function to add 1 to a number represented as a linked list using recursion
    public Node addOne(Node head) {
        // Edge case: If the linked list is empty, return a new node with value 1
        if (head == null) {
            return new Node(1);
        }

        // Recursive helper function will update nodes and return carry
        int carry = helper(head);

        // If carry is still 1 after processing all digits (e.g., 999 → 000 with carry 1)
        if (carry == 1) {
            Node newHead = new Node(1);  // create a new node with 1
            newHead.next = head;         // attach old list after it
            head = newHead;              // update head
        }

        // Return the updated linked list
        return head;
    }

    // Recursive helper function to add 1 from the last node
    private int helper(Node head) {
        // Base case: if we reach null (past the last node), return carry = 1
        if (head == null) {
            return 1;
        }

        // Recursively call for the next node (move towards the last node)
        int carry = helper(head.next);

        // Add carry to the current node's data
        head.data += carry;

        // If the sum is less than 10, no carry forward
        if (head.data < 10) {
            return 0;  // no more carry
        }

        // Otherwise, we set current node = 0 and return carry = 1
        head.data = 0;
        return 1;
    }

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
