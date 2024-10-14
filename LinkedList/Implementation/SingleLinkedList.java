package LinkedList.Implementation;

public class SingleLinkedList {

    // properties of node class
    public static class Node {
        public int value;     // value (type)
        public Node next;     // reference (type)
    }

    public static class SinglyLinkedList {
        public Node head;     // Return type Node because both head and tail point to the node
        public Node tail;
        public int size;

        // Create Singly Linked List
        //  Return type Node because (always return a head reference)
        public Node createSinglyLinkedList(int nodeValue) {
//            initialize head with the type of node
//            head = new Node();
//            create an instance of this node class here

            // create blank node
            Node node = new Node();
            // assign value
            node.value = nodeValue;
            // assign reference to null
            node.next = null;
            // link head & tail with these nodes
            head = node;
            tail = node;
            size = 1;
            return head;
        }

        // Inserting In Singly Linked List
        static void insert(int location, int nodeValue) {
            Node node = new Node();
            node.data = nodeValue;
        
            // Case 1: If the list is empty, create a new singly linked list
            if (head == null) {
                createSinglyLinkedList(nodeValue);
                return;
            }
            // Case 2: Insert at the beginning of the list
            else if (location == 0) {
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
        
                // Traverse the list to the node just before the desired insertion point
                while (index < location - 1) {
                    tempNode = tempNode.next;
                    index++;
                }
        
                // Insert the new node at the desired location
                node.next = tempNode.next; // Point the new node's `next` to the current node at the location
                tempNode.next = node; // Point the previous node's `next` to the new node
            }
        
            // Increment the size after inserting the node
            size++;
        }

        // Traversing in Singly Linked List
        static void traverse() {
            // Check if the list is empty
            if (head == null) {
                System.out.println("The list is empty.");
                return;
            }
        
            // Start at the head node
            Node tempNode = head;
        
            // Traverse through the list
            while (tempNode != null) {
                // Print the data of the current node
                System.out.print(tempNode.data + " -> ");
        
                // Move to the next node
                tempNode = tempNode.next;
            }
        
            // End of the list
            System.out.println("null");
        }


        // Searching in Singly Linked List
        static boolean search(int value) {
            // Start at the head node
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

        // Deletion in Singly Linked List
        public void deleteNode(int location) {
            if (head == null) {
                System.out.println("The singly linked list does not exist.");
                return;
            }
        
            // Case 1: Deleting the first node (head)
            if (location == 0) {
                head = head.next;
                size--;
        
                // If the list becomes empty, update tail to null as well
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
        
                // If there's only one node left
                if (tempNode == head && head.next == null) {
                    head = null;
                    tail = null;
                } else {
                    // Set the next of the second-last node to null
                    tempNode.next = null;
                    tail = tempNode;
                }
                size--;
            }
        
            // Case 3: Deleting a node at a specific location (between head and tail)
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

        // Delete the entire singly linked list
            public void deleteSLL() {
                head = null;
                tail = null;
                size = 0; // Reset the size to zero
                System.out.println("The singly linked list has been deleted successfully.");
            }
        }

    public static void main(String[] args) {
        SinglyLinkedList sLL = new SinglyLinkedList();

        // Create Singly Linked List
        sLL.createSinglyLinkedList(1);

        // Inserting In Singly Linked List
        sLL.insertInLinkedList(10, 0);
//            sLL.insertInLinkedList(6, 0);
        sLL.insertInLinkedList(7, 3);
        sLL.insertInLinkedList(8, 4);
        sLL.insertInLinkedList(90, 1);

        System.out.println(sLL.head.value);
        System.out.println(sLL.tail.value);

        // Traversing in Singly Linked List
        sLL.traverse();

        // Searching in Singly Linked List
        sLL.searchValue(1);

        // Deletion in Singly Linked List
        sLL.deleteNode(1);

        // All Single list deleted
        sLL.deleteSLL();
    }
}
