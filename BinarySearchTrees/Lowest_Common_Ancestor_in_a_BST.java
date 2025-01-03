package BinarySearchTrees;

public class Lowest_Common_Ancestor_in_a_BST {

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

    // Time Complexity : O(H) (height of the tree, for a skewed tree H = N).
    // Space Complexity : O(1) No extra memory is used.

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if the root is null, return null as there's no tree to traverse.
        if (root == null) return null;

        // Store the value of the current node.
        int curr = root.val;

        // If both p and q are smaller than the current node's value,
        // it means both nodes are in the left subtree. Recursively search there.
        if (curr > p.val && curr > q.val)
            return lowestCommonAncestor(root.left, p, q);

        // If both p and q are larger than the current node's value,
        // it means both nodes are in the right subtree. Recursively search there.
        if (curr < p.val && curr < q.val)
            return lowestCommonAncestor(root.right, p, q);

        // If neither of the above cases is true, it means the current node is
        // the lowest common ancestor (LCA), as one node is on one side of the tree
        // and the other node is on the opposite side or equal to the current node.
        return root;
    }

    // Main Function
    public static void main(String[] args) {
        // Create the binary search tree (BST).
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        // Define the two nodes for which the LCA is to be found.
        TreeNode p = root.left;          // Node with value 2
        TreeNode q = root.left.right;   // Node with value 4

        // Find the lowest common ancestor (LCA).
        TreeNode lca = lowestCommonAncestor(root, p, q);

        // Print the result.
        System.out.println("Lowest Common Ancestor of nodes " + p.val + " and " + q.val + " is: " + lca.val);
    }
}

// Output :
/*
Lowest Common Ancestor of nodes 2 and 4 is: 2
 */

// Algorithm / Intuition : Optimal Solution
/*
Intuition
The binary search tree (BST) property ensures that all nodes in the left subtree are smaller
than the root, and all nodes in the right subtree are larger. This property allows us to
decide the direction of traversal based on the values of p and q.

The LCA of two nodes in a BST is the first node that lies between their values or is equal
to one of the nodes. This is because one node will lie in the left subtree and the other in
the right subtree relative to the LCA.

Algorithm
1. Traverse the binary search tree (BST) from the root.
2. Compare the value of the current node (root.val) with the values of the two target
   nodes (p.val and q.val):
    i. If both target nodes are smaller than root.val, move to the left subtree.
   ii. If both target nodes are larger than root.val, move to the right subtree.
  iii. If neither condition is true, the current node is the lowest common ancestor (LCA).
3. Return the current node when the LCA is found.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=cX_kPV_foZc