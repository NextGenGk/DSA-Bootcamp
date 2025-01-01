package BinarySearchTrees;

public class Validate_BST {

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

    // Time Complexity : O(N) where N is the number of nodes in the Binary Search Tree
    // as we traverse in inorder and reverse inorder fashion to get to the required nodes.
    // We visit each node once resulting in time complexity proportional to the number of
    // nodes in the BST.
    // Space Complexity : O(N) for recursive stack space

    // Public method to validate if the binary tree is a BST
    // This method calls the helper method with the initial valid range of values
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Helper method to validate the BST with a range check
    public static boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        // Base case: If the node is null, it satisfies BST properties
        if (root == null) return true;

        // Check if the current node value violates the min or max constraints
        if (root.val >= maxVal || root.val <= minVal) return false;

        // Recursively validate the left and right subtrees
        // Left subtree must have values in the range [minVal, root.val)
        // Right subtree must have values in the range (root.val, maxVal]
        return isValidBST(root.left, minVal, root.val)
                && isValidBST(root.right, root.val, maxVal);
    }

    // Main Function
    public static void main(String[] args) {
        // Construct a valid BST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        // Test case: Valid BST
        System.out.println("Is the tree a valid BST? " + isValidBST(root)); // Output: true

        // Construct an invalid BST
        TreeNode invalidRoot = new TreeNode(5);
        invalidRoot.left = new TreeNode(3);
        invalidRoot.right = new TreeNode(8);
        invalidRoot.left.left = new TreeNode(6); // Violates BST properties
        invalidRoot.left.right = new TreeNode(4);

        // Test case: Invalid BST
        System.out.println("Is the tree a valid BST? " + isValidBST(invalidRoot)); // Output: false
    }
}

// Output :
/*
Is the tree a valid BST? true
Is the tree a valid BST? false
 */

// Algorithm / Intuition : Optimal Solution
/*
The key idea here is to validate the tree by ensuring each node’s value is within a
valid range that depends on its position in the tree. By recursively applying
this constraint to all nodes, we can determine whether the tree is a valid BST or not.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=f-sj7I5oXEI