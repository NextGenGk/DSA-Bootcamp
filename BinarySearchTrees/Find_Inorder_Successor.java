package BinarySearchTrees;

import java.util.ArrayList;
import java.util.List;

public class Find_Inorder_Successor {

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

    // Function to find the inorder successor using brute force
    public static TreeNode inorderSuccessorBrute(TreeNode root, TreeNode p) {
        List<TreeNode> nodes = new ArrayList<>();
        inorderTraversal(root, nodes); // Get all nodes in the inorder traversal list

        for (int i = 0; i < nodes.size(); i++) {
            // Loop through the list of nodes to find the target node (p)
            if (nodes.get(i) == p) {
                // If target node is found, check if there is a next node in the list
                return i + 1 < nodes.size() ? nodes.get(i + 1) : null; // Return the next node as the successor
            }
        }
        return null; // Return null if no successor exists
    }

    // Method 2 : Better Solution
    // Time Complexity: O(H), where H is the height of the tree
    // Space Complexity: O(H), due to the recursion stack.

    public static TreeNode inorderSuccessorBetter(TreeNode root, TreeNode p) {
        if (root == null) return null; // Base case: if root is null, there is no successor

        if (p.val >= root.val) {
            // If the target node's value is greater than or equal to the current node's value, move right
            return inorderSuccessorBetter(root.right, p); // Recurse to the right subtree
        } else {
            // If the target node's value is less than the current node's value, move left
            TreeNode left = inorderSuccessorBetter(root.left, p);
            return (left != null) ? left : root; // If a left child exists, return it; otherwise, return the current node
        }
    }

    // Method 3 : Optimal Solution
    // Time Complexity: O(H), where H is the height of the tree
    // Space Complexity: O(1)

    public static TreeNode inorderSuccessorOptimal(TreeNode root, TreeNode p) {
        TreeNode successor = null; // Initialize the successor to null
        while (root != null) {
            if (p.val < root.val) {
                // If target node's value is smaller than the current node's value, move left
                successor = root; // Update the successor to the current node
                root = root.left; // Move to the left subtree
            } else {
                // If target node's value is greater than or equal to the current node's value, move right
                root = root.right;
            }
        }
        return successor; // Return the final successor after traversing the tree
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
        TreeNode successorBrute = inorderSuccessorBrute(root, target);
        if (successorBrute != null) {
            System.out.println("Brute Force: Inorder Successor of " +
                    target.val + " is " + successorBrute.val);
        } else {
            System.out.println("Brute Force: Inorder Successor does not exist.");
        }

        // Using Better Solution (Recursive)
        TreeNode successorBetter = inorderSuccessorBetter(root, target);
        if (successorBetter != null) {
            System.out.println("Better Solution: Inorder Successor of " +
                    target.val + " is " + successorBetter.val);
        } else {
            System.out.println("Better Solution: Inorder Successor does not exist.");
        }

        // Using Optimal Solution (Iterative)
        TreeNode successorOptimal = inorderSuccessorOptimal(root, target);
        if (successorOptimal != null) {
            System.out.println("Optimal Solution: Inorder Successor of " +
                    target.val + " is " + successorOptimal.val);
        } else {
            System.out.println("Optimal Solution: Inorder Successor does not exist.");
        }
    }
}

// Algorithm / Intuition : Brute Force
/*
This approach works because performing an inorder traversal of a BST produces a
sorted list of node values. By looking at the next node after the target in the list,
we can easily find the inorder successor.
 */

// Algorithm / Intuition : Better Solution
/*
1. This solution takes advantage of the properties of the BST, where:
    i. All nodes in the left subtree are smaller than the current node.
   ii. All nodes in the right subtree are greater than the current node.
By recursively searching in the left or right subtree based on comparisons with p.val,
we can directly identify the inorder successor without needing to traverse the entire
tree or store all nodes.
 */

// Algorithm / Intuition : Better Solution
/*
1. The iterative solution follows the same basic idea as the recursive one,
but it avoids the overhead of recursion by using a simple while loop.

2. The key difference is that by storing the successor as we move through the tree,
we are able to track the potential inorder successor efficiently. Moving left
indicates that the current node could be the successor, while moving right means
we need to continue searching.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=SXKAD2svfmI