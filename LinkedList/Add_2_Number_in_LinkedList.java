package LinkedList;

public class Add_2_Number_in_LinkedList {

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

    // Method 1 : Optimal Solution (Using Elementary Math)
    // Time Complexity : O(N)
    // Space Complexity : O(1)

    // Function to add two numbers represented as linked lists
    static Node add2(Node head1, Node head2) {
        // Create a dummy node to act as the head of the result list
        Node dummy = new Node(0);
        Node current = dummy;

        Node t1 = head1;
        Node t2 = head2;
        int carry = 0;

        // Traverse both lists
        while (t1 != null || t2 != null) {
            int sum = carry;  // Start with the carry from the previous addition

            // Add the data from the first list if t1 is not null
            if (t1 != null) {
                sum += t1.data;
                t1 = t1.next;
            }

            // Add the data from the second list if t2 is not null
            if (t2 != null) {
                sum += t2.data;
                t2 = t2.next;
            }

            // Update the carry for the next iteration
            carry = sum / 10;
            // Create a new node with the digit part of the sum
            Node node = new Node(sum % 10);
            // Link the new node to the current node
            current.next = node;
            // Move to the next node in the result list
            current = current.next;
        }

        // If there is a carry left after the last addition, create a new node for it
        if (carry > 0) {
            current.next = new Node(carry);
        }

        // Return the next node after the dummy node, which is the head of the result list
        return dummy.next;
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

    // Main function for testing
    public static void main(String[] args) {
        // Create the first linked list representing the number 342 (2 -> 4 -> 3)
        Node head1 = new Node(2);
        head1.next = new Node(4);
        head1.next.next = new Node(3);

        // Create the second linked list representing the number 465 (5 -> 6 -> 4)
        Node head2 = new Node(5);
        head2.next = new Node(6);
        head2.next.next = new Node(4);

        System.out.println("First list:");
        printList(head1);

        System.out.println("Second list:");
        printList(head2);

        // Add the two numbers
        Node result = add2(head1, head2);

        System.out.println("Result list:");
        printList(result);
    }
}

// Approach : Optimal Solution (using Elementary Math)
/*

Intuition:

Keep track of the carry using a variable and simulate digits-by-digits sum starting from the head of the list,
which contains the least significant digit. Just like how you would sum two numbers on a piece of paper,
we begin by summing the least significant digits, which is the head of l1 and l2. Since each digit is in
the range of 0…9, summing two digits may "overflow". For example

5 + 7 = 12. In this case, we set the current digit to 2 and bring over the carry=1 to the next iteration.

carry must be either 0 or 1 because the largest possible sum of two digits (including the carry)
is 9 + 9 + 1 = 19.

Psuedocode:

1. Create a dummy node which is the head of new linked list.
2. Create a node temp, initialise it with dummy.
3. Initialize carry to 0.
4. Loop through lists l1 and l2 until you reach both ends, and until carry is present.
    i. Set sum=l1.val+ l2.val + carry.
   ii. Update carry=sum/10.
  iii. Create a new node with the digit value of (sum%10) and set it to temp node's next, then advance temp node to next.
5. Advance both l1 and l2.
6. Return dummy's next node.

Note that we use a dummy head to simplify the code. Without a dummy head, you would have to write extra
conditional statements to initialize the head's value.

Take extra caution in the following cases:

Test case	Explanation
l1=[0,1], l2=[0,1,2]	When one list is longer than the other.
l1=[], l2=[0,1]	When one list is null, which means an empty list.
l1=[9,9], l2=[1]	The sum could have an extra carry of one at the end, which is easy to forget.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=XmRrGzR6udg