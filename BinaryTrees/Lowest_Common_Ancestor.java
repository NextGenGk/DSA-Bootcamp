package BinaryTrees;

public class Lowest_Common_Ancestor {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Function to find the
    // Lowest Common Ancestor of Two Node
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null or we find one of the target nodes (p or q)
        if (root == null || root == p || root == q) return root;

        // Recursively search for p and q in the left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If p and q are found in different subtrees, root is the LCA
        if (left == null) return right;
        else if (right == null) return left;
        else return root;
    }

    // Main Function
    public static void main(String[] args) {
        // Create nodes
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Define nodes for which to find the LCA
        TreeNode p = root.left;         // Node with value 5
        TreeNode q = root.left.right.right; // Node with value 4

        // Find the LCA
        TreeNode lca = lowestCommonAncestor(root, p, q);

        // Print the result
        if (lca != null) {
            System.out.println("The Lowest Common Ancestor of nodes "
                    + p.val + " and " + q.val + " is: " + lca.val);
        } else {
            System.out.println("No common ancestor found.");
        }
    }
}

// Input :
/*
        3
       / \
      5   1
     / \ / \
    6  2 0  8
       / \
      7   4
 */

// Output : The Lowest Common Ancestor of nodes 5 and 4 is: 5
// Here, p is the node with value 5 and q is the node with value 4.
// Since 5 is an ancestor of 4, the lowest common ancestor of nodes 5 and 4 is 5 itself.

// Algorithm / Intuition : Optimal Solution
/*
Algorithm for Finding the Lowest Common Ancestor (LCA)

Base Case:
1. If the current node (root) is null, return null (no ancestor here).
2. If root is either p or q, return root (one of the nodes has been found).

Recursive Calls:
3. Recursively search for p and q in the left subtree by calling
lowestCommonAncestor(root.left, p, q) and store the result in left.
4. Recursively search in the right subtree by calling
lowestCommonAncestor(root.right, p, q) and store the result in right.

Analyze the Results:
5. If both left and right are non-null:
    1. This means p and q are found in different subtrees, so the current root
    is their Lowest Common Ancestor. Return root.
6. If only one of left or right is non-null (the other is null):
    1. Return the non-null result, as it contains the LCA within that subtree.
7. If both left and right are null, return null (neither p nor q is present in this subtree).
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=_-QHfMDde90