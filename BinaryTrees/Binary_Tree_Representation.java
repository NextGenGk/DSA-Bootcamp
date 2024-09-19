package BinaryTrees;

public class Binary_Tree_Representation {

    static class Node {
        // Stores the value of the node
        int data;
        // Reference to the left child node
        Node left;
        // Reference to the right child node
        Node right;

        // Constructor to initialize a node with a given key
        public Node(int key) {
            // Assigns the provided key to the data field of the node
            data = key;
            // Initializes left child reference as null
            left = null;
            // Initializes right child reference as null
            right = null;
        }
    }

    // Main Function
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
    }
}

// Approach
/*
* Node Structure:
In Java, a Binary Tree node is represented using a class that encapsulates the attributes of a node:

1. Data Component: This holds the value of the node, which could be of any data type
(e.g., integer, string, object).

2. Pointers to Children: Two reference variables ie. ‘left’ and ‘right’ point to the left and right
child nodes respectively. These references store the memory addresses of child nodes, allowing
traversal and access to further nodes in the tree structure.

In Java, references to objects act similarly to pointers in C++, allowing nodes to refer to other
nodes without direct memory manipulation.

* Node Constructor:
In Java, the constructor of the Node class initialises a node with a specific value and sets its
left and right references to null to signify that it doesn't have any children initially.

Within the constructor:

data = val; :Sets the data of the node to the provided value (val). This assigns the input integer
to the node's data.

left = right = NULL; : Initialises both left and right references as null. This initialization
ensures that when a node is created, it does not have any immediate connections to other nodes,
indicating the absence of left and right children.

* Node Connection:
Java utilises references between nodes allowing them to refer to other nodes. When creating a new node,
memory is allocated for the node object, and the node’s data is stored within it.

References (`left` and `right`) are initialised as `null` to indicate that the node doesn’t currently
have any children, The nodes are connected by assigning references of a parent node to its respective
left and right child nodes.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=hyLyW7rP24I