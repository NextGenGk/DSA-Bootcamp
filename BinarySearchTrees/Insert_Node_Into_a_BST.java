package BinarySearchTrees;

public class Insert_Node_Into_a_BST {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        // Constructor for an empty node.
        TreeNode() {
        }

        // Constructor to create a node with a specific value.
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        // Constructor to create a node with a specific value and left and right children.
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Method 1 : Optimal Solution

    // Time Complexity : O(H), where H is the height of the tree
    // For balanced tree O(H) = O(log2N)
    // For skewed tree O(H)) = O(N)

    // Function to insert a node in BST
    public static TreeNode insertNode(TreeNode root, int val) {
        // If the tree is empty, create a new node as the root and return it
        if (root == null) {
            return new TreeNode(val);
        }

        // Start traversal from the root
        TreeNode curr = root;

        // Traverse the tree until we find the correct position to insert the new value
        while (curr != null) {
            // If the value to insert is greater than or equal to the current node's value
            if (curr.val <= val) {
                // If there is a right child, move to the right child
                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    // If the right child is null, insert the new value as the right child
                    curr.right = new TreeNode(val);
                    break; // Exit the loop after insertion
                }
            }
            // If the value to insert is less than the current node's value
            else {
                // If there is a left child, move to the left child
                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    // If the left child is null, insert the new value as the left child
                    curr.left = new TreeNode(val);
                    break; // Exit the loop after insertion
                }
            }
        }

        // Return the root of the updated tree
        return root;
    }

    // Function to perform an in-order traversal
    // of a binary tree and print its nodes
    public static void printInOrder(TreeNode root) {
        // Check if the current node
        // is null (base case for recursion)
        if (root == null) {
            // If null, return and
            // terminate the function
            return;
        }

        // Recursively call printInOrder
        // for the left subtree
        printInOrder(root.left);

        // Print the value of the current node
        System.out.print(root.val + " ");

        // Recursively call printInOrder
        // for the right subtree
        printInOrder(root.right);
    }

    // Main Function
    public static void main(String[] args) {
        // Create a BST
        TreeNode root = new TreeNode(5); // Root node with value 5
        root.left = new TreeNode(3); // Left child of root
        root.right = new TreeNode(7); // Right child of root

        // Print initial tree
        System.out.println("Initial Tree (Inorder Traversal):");
        printInOrder(root); // Expected: 3 5 7
        System.out.println();

        // Insert a new value into the BST
        int valToInsert = 4;
        root = insertNode(root, valToInsert);

        // Print the tree after insertion
        System.out.println("Tree After Insertion (Inorder Traversal):");
        printInOrder(root); // Expected: 3 4 5 7
        System.out.println();

        // Insert another value
        valToInsert = 8;
        root = insertNode(root, valToInsert);

        // Print the tree after second insertion
        System.out.println("Tree After Another Insertion (Inorder Traversal):");
        printInOrder(root); // Expected: 3 4 5 7 8
    }
}

// Algorithm / Intuition : Optimal Solution
/*
Intuition:
A Binary Search Tree (BST) maintains the property:
1. For any node n:
    i. All nodes in the left subtree of n have values less than n.val.
   ii. All nodes in the right subtree of n have values greater than or equal to n.val.
2. The algorithm traverses the tree, comparing val with the current node's value (curr.val):
    i. Moves left if val < curr.val.
   ii. Moves right if val ≥ curr.val.
When a suitable position is found (a null child), the algorithm inserts the new node at that
position, ensuring the BST property remains intact.

Algorithm :

To insert a node into a Binary Search Tree (BST) while maintaining the BST properties,
you can follow these steps:
1. Check if the tree is empty: If the tree is empty, create a new node as the root and return it.
2. Traverse the tree: Start from the root and traverse the tree to find the correct position
   to insert the new node.
3. Compare values: At each node, compare the value to be inserted with the current node's value.
    i. If the value to insert is greater than or equal to the current node's value, move to the right child.
   ii. If the value to insert is less than the current node's value, move to the left child.
4Insert the node: When a suitable position (a null child) is found, insert the new node at that position.
*/

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=FiFiNvM29ps