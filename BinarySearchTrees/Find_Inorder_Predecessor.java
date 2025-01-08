package BinarySearchTrees;

import java.util.ArrayList;
import java.util.List;

public class Find_Inorder_Predecessor {

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

    // Method 1 : Brute Force Approach
    // Time Complexity: O(n), where n is the number of nodes in the BST.
    // Space Complexity: O(n), for storing the nodes in a list.

    public static void inorderTraversal(TreeNode root, List<TreeNode> nodes) {
        // Inorder Traversal: Traverse the left subtree, visit the current node,
        // and then traverse the right subtree
        if (root == null) return;
        inorderTraversal(root.left, nodes);
        nodes.add(root); // Add the node to the list after visiting it
        inorderTraversal(root.right, nodes);
    }

    // Function to find the inorder predecessor using brute force
    public static TreeNode inorderPredecessorBrute(TreeNode root, TreeNode p) {
        List<TreeNode> nodes = new ArrayList<>();
        inorderTraversal(root, nodes); // Get all nodes in the inorder traversal list

        for (int i = 0; i < nodes.size(); i++) {
            // Loop through the list of nodes to find the target node (p)
            if (nodes.get(i) == p) {
                // If target node is found, check if there is a previous node in the list
                return i - 1 >= 0 ? nodes.get(i - 1) : null; // Return the previous node as the predecessor
            }
        }
        return null; // Return null if no predecessor exists
    }

    // Method 2 : Better Solution
    // Time Complexity: O(H), where H is the height of the tree
    // Space Complexity: O(H), due to the recursion stack.

    public static TreeNode inorderPredecessorBetter(TreeNode root, TreeNode p) {
        if (root == null) return null; // Base case: if root is null, there is no predecessor

        if (p.val <= root.val) {
            // If the target node's value is less than or equal to the current node's value, move left
            return inorderPredecessorBrute(root.left, p); // Recurse to the left subtree
        } else {
            // If the target node's value is greater than the current node's value, move right
            // and update the predecessor to the current node
            TreeNode right = inorderPredecessorBetter(root.right, p);
            return (right != null) ? right : root; // If a right child exists, return it; otherwise, return the current node
        }
    }

    // Method 3 : Optimal Solution
    // Time Complexity: O(H), where H is the height of the tree
    // Space Complexity: O(1)

    public static TreeNode inorderPredecessorOptimal(TreeNode root, TreeNode p) {
        TreeNode predecessor = null; // Initialize the predecessor to null
        while (root != null) {
            if (p.val > root.val) {
                // If target node's value is greater than the current node's value, move right
                predecessor = root; // Update the predecessor to the current node
                root = root.right; // Move to the right subtree
            } else {
                // If target node's value is less than or equal to the current node's value, move left
                root = root.left;
            }
        }
        return predecessor; // Return the final predecessor after traversing the tree
    }

    // Main Function
    public static void main(String[] args) {
        // Create a sample binary search tree (BST)
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(8);
        root.right.right.right = new TreeNode(10);

        // Select a target node (For example, the node with value 3)
        TreeNode target = root.left.right.left; // Node with value 3

        // Using Brute Force Method
        TreeNode predecessorBrute = inorderPredecessorBrute(root, target);
        if (predecessorBrute != null) {
            System.out.println("Brute Force: Inorder Predecessor of " +
                    target.val + " is " + predecessorBrute.val);
        } else {
            System.out.println("Brute Force: Inorder Predecessor does not exist.");
        }

        // Using Better Solution (Recursive)
        TreeNode predecessorBetter = inorderPredecessorBetter(root, target);
        if (predecessorBetter != null) {
            System.out.println("Better Solution: Inorder Predecessor of " +
                    target.val + " is " + predecessorBetter.val);
        } else {
            System.out.println("Better Solution: Inorder Predecessor does not exist.");
        }

        // Using Optimal Solution (Iterative)
        TreeNode predecessorOptimal = inorderPredecessorOptimal(root, target);
        if (predecessorOptimal != null) {
            System.out.println("Optimal Solution: Inorder Predecessor of " +
                    target.val + " is " + predecessorOptimal.val);
        } else {
            System.out.println("Optimal Solution: Inorder Predecessor does not exist.");
        }
    }
}

// Output :
/*
Brute Force: Inorder Predecessor of 15 is 10
Optimal: Inorder Predecessor of 15 is 10
 */

// Algorithm / Intuition : Brute Force
/*
This approach works because performing an inorder traversal of a BST produces
a sorted list of node values. By looking at the previous node before the target
in the list, we can easily find the inorder predecessor.
 */

// Algorithm / Intuition : Better Solution
/*
1. This solution takes advantage of the properties of the BST, where:
    i. All nodes in the left subtree are smaller than the current node.
   ii. All nodes in the right subtree are greater than the current node.
By recursively searching in the left or right subtree based on comparisons with p.val,
we can directly identify the inorder predecessor without needing to traverse the entire
tree or store all nodes.
 */

// Algorithm / Intuition : Better Solution
/*
1. The iterative solution follows the same basic idea as the recursive one, but it avoids
the overhead of recursion by using a simple while loop.

2. The key difference is that by storing the predecessor as we move through the tree,
we are able to track the potential inorder predecessor efficiently. Moving left
indicates that the current node could be the predecessor, while moving right means we
need to continue searching.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=SXKAD2svfmI