package BinarySearchTrees;

public class Delete_Node_in_a_BST {

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

    // Function to delete a node with a given key in a BST
    static TreeNode deleteNode(TreeNode root, int key) {
        // If the root is null, return null
        if (root == null) return null;

        // If the key matches the root's value, handle deletion
        if (root.val == key) {
            return helper(root);
        }

        // Pointer to keep the original root of the tree
        TreeNode dummy = root;

        // Traverse the tree to find the node to delete
        while (root != null) {
            if (root.val > key) {
                // If key is in the left subtree
                if (root.left != null && root.left.val == key) {
                    // Update the left child by handling the deletion
                    root.left = helper(root.left);
                    break;
                } else {
                    root = root.left; // Move to the left subtree
                }
            } else {
                // If key is in the right subtree
                if (root.right != null && root.right.val == key) {
                    // Update the right child by handling the deletion
                    root.right = helper(root.right);
                    break;
                } else {
                    root = root.right; // Move to the right subtree
                }
            }
        }

        // Return the original root after deletion
        return dummy;
    }

    // Helper function to delete the current node and restructure the tree
    static TreeNode helper(TreeNode root) {
        // Case 1: If there is no left child, return the right child
        if (root.left == null) {
            return root.right;
        }

        // Case 2: If there is no right child, return the left child
        else if (root.right == null) {
            return root.left;
        }

        // Case 3: Node has both left and right children
        TreeNode rightChild = root.right; // Save the right subtree
        TreeNode lastRight = findLastChild(root.left); // Find the rightmost node in the left subtree
        lastRight.right = rightChild; // Attach the original right subtree to the rightmost node of the left subtree
        return root.left; // Return the left subtree as the new root
    }

    // Helper function to find the rightmost child of a subtree
    static TreeNode findLastChild(TreeNode root) {
        // Traverse to the rightmost node
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }


    // Function to perform an in-order traversal
    // of a binary tree and print its nodes.
    public static void printInOrder(TreeNode root) {
        // Check if the current node is null (base case for recursion).
        if (root == null) {
            return;
        }

        // Recursively call printInOrder for the left subtree.
        printInOrder(root.left);

        // Print the value of the current node.
        System.out.print(root.val + " ");

        // Recursively call printInOrder for the right subtree.
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        // Create a BST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        // Print original tree (Inorder Traversal)
        System.out.println("Original Tree:");
        printInOrder(root); // Expected: 2 3 4 5 6 7
        System.out.println();

        // Delete a node
        int key = 3;
        root = deleteNode(root, key);

        // Print updated tree after deletion
        System.out.println("Tree After Deletion:");
        printInOrder(root); // Expected: 2 4 5 6 7
    }
}

// Output :
/*
Original Tree:
2 3 4 5 6 7
Tree After Deletion:
2 4 5 6 7
 */

// Algorithm / Intuition : Optimal Solution
/*
Intuition :

Traversal Logic:

The deletion logic first checks if the current node matches the key to be deleted.
It then moves left or right based on the comparison (key < root.val or key > root.val).

Helper Function:

The helper function handles the removal of the node by checking for its children.
If the node has only one child or no child, we return the child (or null). If the
node has both left and right children, we use the largest node in the left subtree
(inorder predecessor) to replace the node's value and then delete the predecessor node.

Reusing Subtrees:

In case the node has both left and right children, we take the largest node from the
left subtree (rightmost node), attach the right subtree to it, and return the left
subtree to maintain the BST structure. This ensures that the tree remains valid after deletion.

No Extra Space for Tree Structure:

The algorithm performs the operation in constant space, except for recursion, as we are not
storing any auxiliary data structures to aid the operation.

Algorithm :
Steps to Delete a Node in BST

Base Case (Root is Null):
1. If the root is null, it means the tree is empty, so we return null immediately.

Finding the Node to Delete:

1. If the current node (root) matches the key to be deleted, we call the helper()
   function to handle the deletion of this node.
2. If the key to be deleted is smaller than the current node’s value, we move to the left subtree.
3. If the key to be deleted is larger than the current node’s value, we move to the right subtree.
This traversal ensures that we find the node with the key in a binary search fashion.

Helper Function for Node Deletion:

1. This function handles the actual deletion by checking the number of children the node has (left or right).
Cases in the helper() function:

Case 1: Node with No Left Child:
If the node to be deleted has no left child, we simply return the right child. This
effectively removes the node from the tree and links its parent directly to its right child.
Case 2: Node with No Right Child:
If the node has no right child, we return the left child, thus linking the parent of
the node to its left child.
Case 3: Node with Both Left and Right Children:
If the node has both left and right children, we cannot simply remove it. The algorithm
follows the "inorder predecessor" or "inorder successor" approach:

    1. We find the rightmost node in the left subtree, which is the largest node in the left subtree.
    2. We attach the original right subtree to this rightmost node (as it will be the
       largest node, it doesn’t violate the BST property).
    3. We return the left subtree as the new root.

Reattach the Subtree and Return the Root:
After the deletion, the function returns the root of the tree to maintain the structure.
A dummy variable (dummy) is used to keep track of the original root since the root might
change if the node to be deleted is the root itself.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=kouxiP_H5WE