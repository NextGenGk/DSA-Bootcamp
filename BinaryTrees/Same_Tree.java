package BinaryTrees;

public class Same_Tree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Method 1 : Optimal Solution

    // Time Complexity: O(N+M) where N is the number of nodes in the first Binary Tree
    // and M is the number of nodes in the second Binary Tree. This complexity arises
    // from visiting each node of the two binary nodes during their comparison.

    // Space Complexity: O(1) as no additional space or data structures is created that
    // is proportional to the input size of the tree. O(H) Recursive Stack Auxiliary Space :
    // The recursion stack space is determined by the maximum depth of the recursion,
    // which is the height of the binary tree denoted as H. In the balanced case it is
    // log2N and in the worst case (its N).

    // Function to check if two
    // binary trees are identical
    public static boolean isIdentical(TreeNode node1, TreeNode node2) {
        // If both nodes are NULL,
        // they are identical
        if (node1 == null && node2 == null) {
            return true;
        }
        // If only one of the nodes is
        // NULL, they are not identical
        if (node1 == null || node2 == null) {
            return false;
        }
        // Check if the current nodes
        // have the same data value
        // and recursively check their
        // left and right subtrees
        return ((node1.val == node2.val)
                && isIdentical(node1.left, node2.left)
                && isIdentical(node1.right, node2.right));
    }

    // Main Function
    public static void main(String[] args) {
        // Node1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);

        // Node2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);

        if (isIdentical(root1, root2)) {
            System.out.println("The binary trees are identical.");
        } else {
            System.out.println("The binary trees are not identical.");
        }
    }
}

// Output : 
// The binary trees are identical.

// Approach / Intuition : Optimal Solution
/*
To determine if two binary trees are identical, we can follow a recursive approach.
We traverse both trees in the preorder manner, meaning that the current node's
value is checked before recursively traversing its left and right subtrees.

The idea is to traverse both trees simultaneously, comparing the values of
corresponding nodes at each step. We need to ensure that the left subtree of
each node in the first tree is identical to the left subtree of the corresponding
node in the second tree, and similarly for the right subtrees.

Base Case: The base case for recursion is reached when both nodes are null,
indicating the end of the subtree. In this case return true. If only one of
the nodes in null while the other is not or vice versa, return false since
they cannot be identical.

Recursive Function:

1. Check if the values of the current nodes in both tree are equal. If not,
return false otherwise check the conditions below.
2. Check if the left subtree of both the trees is identical or not by calling
the recursive function on the left child.
3. Check if the right subtree of both the trees is identical or not by calling
the recursive function on the right child.
4. If all recursive calls return true, indicating that the values and structures
of the subtrees are identical, the function returns true, confirming that the
entire trees are identical.

Algorithm:

Step 1: Start at the root node of both trees (node1 and node2).

Step 2: Check if the values of the current nodes in both trees are equal.
If not return false.

Step 3: Recursively check the left then right subtree of the current node
in both trees is identical.

Step 4: If all the recursive checks return true, then return the trees are
 identical, otherwise they are not.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=BhuvF_-PWS0
