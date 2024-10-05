package BinaryTrees;

public class Check_Balanced_Binary_Tree {

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

    // Method 1 : Brute Force

    // Time Complexity: O(N2) where N is the number of nodes in the Binary Tree. This arises as
    // we calculate the height of each node and to calculate the height for each node, we traverse
    // the tree which is proportional to the number of nodes. Since this calculation is performed
    // for each node in the tree, the complexity becomes: O(N x N) ~ O(N2).
    //
    // Space Complexity : O(1) as no additional data structures or memory is allocated.
    // O(H): Recursive Stack Space is used to calculate the height of the tree at each node which
    // is proportional to the height of the tree.The recursive nature of the getHeight function,
    // which incurs space on the call stack for each recursive call until it reaches the leaf nodes
    // or the height of the tree.

    // Function to check if a binary tree is balanced
    public static boolean isBalanced(TreeNode root) {
        // If the tree is empty, it's balanced
        if (root == null) {
            return true;
        }

        // Calculate the height of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Check if the absolute difference in heights
        // of left and right subtrees is <= 1
        if (Math.abs(leftHeight - rightHeight) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right)) {
            return true;
        }

        // If any condition fails, the tree is unbalanced
        return false;
    }

    // Function to calculate the height of a subtree
    public static int getHeight(TreeNode root) {
        // Base case: if the current node is NULL,
        // return 0 (height of an empty tree)
        if (root == null) {
            return 0;
        }

        // Recursively calculate the height
        // of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Return the maximum height of left and right subtrees
        // plus 1 (to account for the current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Method 2 : Optimal Solution

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree.
    // This complexity arises from visiting each node exactly once during the postorder traversal.

    // Space Complexity : O(1) as no additional space or data structures is
    // created that is proportional to the input size of the tree. O(H) Recursive
    // Stack Auxiliary Space : The recursion stack space is determined by the maximum
    // depth of the recursion, which is the height of the binary tree denoted as H.
    // In the balanced case it is log2N and in the worst case its N.

    // Function to check if a binary tree is balanced
    public static boolean isBalancedOptimal(TreeNode root) {
        // Check if the tree's height difference
        // between subtrees is less than 2
        // If not, return false; otherwise, return true
        return dfsHeight(root) != -1;
    }

    // Recursive function to calculate
    // the height of the tree
    public static int dfsHeight(TreeNode root) {
        // Base case: if the current node is NULL,
        // return 0 (height of an empty tree)
        if (root == null) return 0;

        // Recursively calculate the
        // height of the left subtree
        int leftHeight = dfsHeight(root.left);

        // If the left subtree is unbalanced,
        // propagate the unbalance status
        if (leftHeight == -1)
            return -1;

        // Recursively calculate the
        // height of the right subtree
        int rightHeight = dfsHeight(root.right);

        // If the right subtree is unbalanced,
        // propagate the unbalance status
        if (rightHeight == -1)
            return -1;

        // Check if the difference in height between
        // left and right subtrees is greater than 1
        // If it's greater, the tree is unbalanced,
        // return -1 to propagate the unbalance status
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        // Return the maximum height of left and
        // right subtrees, adding 1 for the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.left.right.right.right = new TreeNode(7);

        // Creating an instance of the Solution class
//        Solution solution = new Solution();

        // Checking if the tree is balanced
        if (isBalanced(root)) {
            System.out.println("The tree is balanced.");
        } else {
            System.out.println("The tree is not balanced.");
        }
    }
}

// Algorithm / Intuition : Brute Force
/*
Base Case:
If the root node is null, signifying an empty tree, return true as an empty tree
is considered balanced.

Recursive Algorithm:
The `isBalanced` function is used to check if a Binary Tree is balanced,
accepting a `root` node. Calculate the height of the left subtree and store it in a variable.
Calculate the height of the right subtree and store it in a variable. Check if the absolute
difference in heights of the left and right subtree is less than or equal to 1. If true then
call the `isBalanced` recursive function for both the left and right child.

1. If the condition is satisfied and both the left and right subtrees are balanced (recursive calls
2. to isBalanced return true), return true, indicating a balanced tree.
If the absolute difference of heights is greater than 1 or the recursive calls to left and right
subtrees return false then return false.

Algorithm:
Step 1: Check if the root is null. If so, return true as an empty tree is balanced.
Step 2: Recursively calculate the height of the left and right subtrees using the `getHeight`
function and store them. If their absolute height difference is greater than 1, return false.
Step 3: If their absolute height difference is less than or equal to 1, recursively call the
isBalanced function on the left and right children as well. If the left and right children
are also balanced, return true.
Step 4: If any of the conditions in Step 2 and Step 3 fail, return false.
 */

// Approach / Intuition : Optimal Solution
/*
The O(N*N) time complexity of the previous approach can be optimised by simultaneously checking
the balance condition while traversing the tree. Instead of repeatedly calculating the heights of
left and right subtrees at each node, we can compute these heights in a bottom-up manner. The
Postorder method allows us to validate balance conditions efficiently during the traversal. The
postorder traversal operates in a bottom-up manner, calculating subtree information before moving
to the parent node. We save on time complexity of calling the height of children over and over
again as we have access to the height information of both subtrees when evaluating the balance
condition at the parent.


This also allows early detection of unbalanced nodes without unnecessary height calculate if a
subtree is already found to be unbalanced, hence avoiding unnecessary function calls.

Algorithm:

Step 1: Traverse the Binary Tree is post-order manner using recursion. Visit left subtree,
then right subtree, and finally the root node.
Step 2:During the traversal, for each node, calculate the heights of the its left and right
subtrees. Use the obtained subtree heights to validate the balance conditions for the current node.
Step 3: At each node, if the absolute difference between the heights of the left and right
subtrees is greater than 1 or if any subtree is unbalanced (returns -1), return -1 immediately
indicating an unbalanced tree.
Step 4: If the tree is balanced, return the height of the current node by considering the
maximum height of its left and right subtree plus 1 accounting for the current node.
Step 5: Complete the traversal until all nodes are visited and return the final result -
either the height of the entire tree if balanced or -1 if unbalanced.
 */

// Striver's (Video Explanation) :https://www.youtube.com/watch?v=Yt50Jfbd8Po