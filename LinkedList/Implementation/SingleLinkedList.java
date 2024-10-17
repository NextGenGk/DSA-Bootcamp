package LinkedList.Implementation;

public class SingleLinkedList {

    // Node class definition
    public static class Node {
        public int data;   // value (data)
        public Node next;  // reference to the next node
    }

    // SinglyLinkedList class
    public static class SinglyLinkedList {
        public Node head;  // head of the list
        public Node tail;  // tail of the list
        public int size;   // size of the list

        // Create Singly Linked List
        // Return type Node because we return a reference to the head node
        public Node createSinglyLinkedList(int nodeValue) {
            Node node = new Node();    // create a new node
            node.data = nodeValue;     // assign value
            node.next = null;          // set next reference to null
            head = node;               // head points to this new node
            tail = node;               // tail also points to this new node
            size = 1;                  // list size is 1
            return head;
        }

        // Inserting in Singly Linked List
        public void insertInLinkedList(int nodeValue, int location) {
            Node node = new Node();    // create a new node
            node.data = nodeValue;     // assign value

            // Case 1: If the list is empty, create a new singly linked list
            if (head == null) {
                createSinglyLinkedList(nodeValue);
                return;
            }

            // Case 2: Insert at the beginning of the list
            if (location == 0) {
                node.next = head;
                head = node;
            }

            // Case 3: Insert at the end of the list
            else if (location >= size) {
                node.next = null;
                tail.next = node;
                tail = node;
            }

            // Case 4: Insert at a specific location within the list
            else {
                Node tempNode = head;
                int index = 0;

                // Traverse to the node just before the desired insertion point
                while (index < location - 1) {
                    tempNode = tempNode.next;
                    index++;
                }

                // Insert the new node at the desired location
                node.next = tempNode.next;  // Point the new node's `next` to the node at the location
                tempNode.next = node;       // Link the previous node's `next` to the new node
            }

            // Increment the size after inserting the node
            size++;
        }

        // Traversing in Singly Linked List
        public void traverse() {
            // Check if the list is empty
            if (head == null) {
                System.out.println("The list is empty.");
                return;
            }

            // Start from the head node
            Node tempNode = head;

            // Traverse the list
            while (tempNode != null) {
                System.out.print(tempNode.data + " -> ");
                tempNode = tempNode.next;
            }

            // Indicate end of the list
            System.out.println("null");
        }

        // Searching in Singly Linked List
        public boolean searchValue(int value) {
            // Start from the head node
            Node tempNode = head;

            // Traverse the list
            int index = 0;
            while (tempNode != null) {
                // Check if the current node contains the value
                if (tempNode.data == value) {
                    System.out.println("Value " + value + " found at position " + index);
                    return true;
                }

                // Move to the next node
                tempNode = tempNode.next;
                index++;
            }

            // If the value is not found
            System.out.println("Value " + value + " not found in the list.");
            return false;
        }

        // Deleting a node in Singly Linked List
        public void deleteNode(int location) {
            // If the list is empty
            if (head == null) {
                System.out.println("The singly linked list does not exist.");
                return;
            }

            // Case 1: Deleting the first node (head)
            if (location == 0) {
                head = head.next;
                size--;

                // If the list becomes empty, set tail to null
                if (size == 0) {
                    tail = null;
                }
            }

            // Case 2: Deleting the last node (tail)
            else if (location >= size - 1) {
                Node tempNode = head;

                // Traverse to the second-last node
                for (int i = 0; i < size - 2; i++) {
                    tempNode = tempNode.next;
                }

                // Set the next of the second-last node to null
                tempNode.next = null;
                tail = tempNode;
                size--;
            }

            // Case 3: Deleting a node at a specific location
            else {
                Node tempNode = head;

                // Traverse to the node just before the target node
                for (int i = 0; i < location - 1; i++) {
                    tempNode = tempNode.next;
                }

                // Bypass the node at the given location
                tempNode.next = tempNode.next.next;
                size--;
            }
        }

        // Delete the entire Singly Linked List
        public void deleteSLL() {
            head = null;
            tail = null;
            size = 0;  // Reset the size to zero
            System.out.println("The singly linked list has been deleted successfully.");
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList sLL = new SinglyLinkedList();

        // Create Singly Linked List
        sLL.createSinglyLinkedList(1);

        // Inserting in Singly Linked List
        sLL.insertInLinkedList(10, 0);
        sLL.insertInLinkedList(7, 3);
        sLL.insertInLinkedList(8, 4);
        sLL.insertInLinkedList(90, 1);

        System.out.println("Head: " + sLL.head.data);
        System.out.println("Tail: " + sLL.tail.data);

        // Traversing in Singly Linked List
        sLL.traverse();

        // Searching in Singly Linked List
        sLL.searchValue(1);

        // Deletion in Singly Linked List
        sLL.deleteNode(1);

        // Traversing after deletion
        sLL.traverse();

        // Delete the entire list
        sLL.deleteSLL();
    }
}