package BinarySearchTrees;

public class Search_in_BST {

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

    // This function searches for a node with
    // a specified value in a Binary Search Tree (BST).
    public static TreeNode searchBST(TreeNode root, int val) {
        // Loop until either the tree is
        // exhausted (null) or the value is found.
        while (root != null && root.val != val) {
            // Check if the target value is
            // less than the current node's value.
            // If so, move to the left subtree
            // (values smaller than the current node).
            // Otherwise, move to the right subtree
            // (values larger than the current node).
            root = val < root.val ? root.left : root.right;
        }
        // Return the node containing the target value,
        // if found; otherwise, return null.
        return root;
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a BST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(10);

        System.out.println("Binary Search Tree: ");
        printInOrder(root);
        System.out.println();

        // Searching for a value in the BST
        int target = 6;
        TreeNode result = searchBST(root, target);

        // Displaying the search result
        if (result != null) {
            System.out.println("Value " + target + " found in the BST!");
        } else {
            System.out.println("Value " + target + " not found in the BST.");
        }
    }
}

// Output :
/*
Binary Search Tree:
2 3 4 5 6 8 10
Value 6 found in the BST!
 */

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
Starting from the root, continuously compare the key value with the current node’s value,
traverse to left or right based on these comparisons. If the current node's value matches
the target value, the algorithm stops and returns that node. Otherwise, it moves down the
tree, choosing the left or right child depending on whether the target value is smaller or
larger than the current node's value, respectively. This process continues until either
the target value is found within a node or the algorithm reaches a nullptr node, indicating
that the value is not present in the BST.

Algorithm:
Step 1:Start at the root of the Binary Search Tree.
Step 2: While the current node is not null and the current node’s value is not equal to the
key recursively travel the tree by:

    i. If the target value is less than the current node’s value, move to the left child as smaller
       values are on the left subtree in the BST.
   ii. If the target value is greater than the current node’s value, move to the right child
       as larger values are on the right subtree in the BST.

Step 3: Return the current node if it contains the target value, or NULL if the value is not
found in the BST.
 */

// Striver's Video Explanation : https://www.youtube.com/watch?v=KcNt6v_56cca