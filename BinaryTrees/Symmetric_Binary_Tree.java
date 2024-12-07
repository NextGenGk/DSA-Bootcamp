package BinaryTrees;

public class Symmetric_Binary_Tree {

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

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree.
    // This complexity arises from visiting each node exactly once during the traversal
    // and the function compares the nodes in a symmetric manner.
    //
    // Space Complexity: O(1) as no additional data structures or memory is allocated.
    // O(H): Recursive Stack Space is used to calculate the height of the tree at each
    // node which is proportional to the height of the tree.
    // The recursive nature of the getHeight function, which incurs space on the call
    // stack for each recursive call until it reaches the leaf nodes or the height of the tree.

    // Function to check if
    // two subtrees are symmetric
    private static boolean isSymmetricUtil(TreeNode root1, TreeNode root2) {
        // Check if either subtree is null
        if (root1 == null || root2 == null) {
            // If one subtree is null, the other
            // must also be null for symmetry
            return root1 == root2;
        }
        // Check if the data in the current nodes is equal
        // and recursively check for symmetry in subtrees
        return (root1.val == root2.val)
                && isSymmetricUtil(root1.left, root2.right)
                && isSymmetricUtil(root1.right, root2.left);
    }

    // Public function to check if the
    // entire binary tree is symmetric
    public static boolean isSymmetric(TreeNode root) {
        // Check if the tree is empty
        if (root == null) {
            // An empty tree is
            // considered symmetric
            return true;
        }
        // Call the utility function
        // to check symmetry of subtrees
        return isSymmetricUtil(root.left, root.right);
    }

    // Function to print the Inorder
    // Traversal of the Binary Tree
    private static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);

        System.out.print("Binary Tree (Inorder): ");
        printInorder(root);
        System.out.println();

        boolean res = isSymmetric(root);

        if (res) {
            System.out.println("This Tree is Symmetrical");
        } else {
            System.out.println("This Tree is NOT Symmetrical");
        }
    }
}

// Output : Binary Tree (Inorder): 3 2 4 1 4 2 3 This Tree is Symmetrical

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
A tree is said to be symmetric when its structure exhibits a mirroring pattern,
meaning that the left and right subtrees of any node are identical or mirror images
of each other. In other words, if you could draw a vertical line through the centre
of the tree, the nodes on the left side should be symmetrically aligned with the nodes
on the right side.

For a binary tree to be symmetric:

1. The root node and its two subtrees (left and right) must have the same value.
2. The left subtree of the root should be a mirror image of the right subtree.
3. This mirroring should be consistent throughout the entire tree, not just at the root level.
   When recursively checking the left and right subtrees for symmetry in a binary tree,
   the traversals are mirrored. Specifically, the algorithm compares the left child of the
   left subtree with the right child of the right subtree and the right child of the left
   subtree with the left child of the right subtree.

Algorithm:

Step 1:Check if the given tree is empty ie. root is null. If the tree is empty,
it is considered symmetric by default and we return true.

Step 2: If the tree is not empty, we call a utility function `isSymmetricUtil`,
passing the left and right subtrees of the root. This utility function handles the
recursive checks for symmetry.

Base Case: The base case for recursion is when both the left and right subtrees are empty,
indicating a symmetric structure and we return true. If only one of the subtrees is empty
(while the other is not), we return false as this violates the conditions of symmetry.

Check for Symmetry:

1. Compare the values of the current nodes from the left and right subtrees. For the binary
tree to be symmetric, the corresponding nodes received should have equal values.
2. Recursively check the symmetry of these subtrees. We check if the left subtree of the left
node is symmetric with the right subtree of the right node.
3. Similarly, also check the symmetry of the right subtree of the left node with the left
subtree of the right node.

Hence, we compare the node values and recursively explore the left and right subtrees
in a mirrored fashion.

Step 3:The final result of the isSymmetric function is based on the outcome of the utility
function `isSymmetricUtil` recursive function for the roots left and right subtree.
 */

// Note :
/*
        1
       / \
      2   2
     / \ / \
    3  4 4  3

Here’s a breakdown of how the recursive calls work on this tree:

1. Initial Call:

    1. isSymmetric(root) initiates the check with isSymmetricUtil(root.left, root.right).
    2. In our example, this means calling isSymmetricUtil(2, 2) where both nodes are the
       left and right children of the root 1.

2. First Level of Recursion:

    1. isSymmetricUtil(2, 2) checks if root1.data == root2.data (both 2), which is true.
    2. Then it makes two recursive calls:
        1. isSymmetricUtil(root1.left, root2.right) → isSymmetricUtil(3, 3)
        2. isSymmetricUtil(root1.right, root2.left) → isSymmetricUtil(4, 4)

3. Second Level of Recursion:

    1. isSymmetricUtil(3, 3):

        1. root1.data == root2.data is true (both 3).
        2. It then makes two more recursive calls:
            1. isSymmetricUtil(root1.left, root2.right) → isSymmetricUtil(null, null)
               (since 3 has no children).
            2. isSymmetricUtil(root1.right, root2.left) → isSymmetricUtil(null, null).
        3. Both calls return true because both nodes are null.
        4. So isSymmetricUtil(3, 3) returns true.

    2. isSymmetricUtil(4, 4):

        1. root1.data == root2.data is true (both 4).
        2. It then makes two recursive calls:
            1. isSymmetricUtil(root1.left, root2.right) → isSymmetricUtil(null, null).
            2. isSymmetricUtil(root1.right, root2.left) → isSymmetricUtil(null, null).
        3. Both calls return true because both nodes are null.
        4. So isSymmetricUtil(4, 4) returns true.

4. Returning to First Level:

    1. Since both isSymmetricUtil(3, 3) and isSymmetricUtil(4, 4) returned true,
    the initial call isSymmetricUtil(2, 2) also returns true.

5. Final Result:

    1. Since the initial call returned true, the tree is symmetric, and isSymmetric(root)
       returns true.

    Summary of Recursive Call Stack:
    1. isSymmetricUtil(1.left, 1.right) → isSymmetricUtil(2, 2)
    2. isSymmetricUtil(2.left, 2.right) → isSymmetricUtil(3, 3)
    3. isSymmetricUtil(3.left, 3.right) → isSymmetricUtil(null, null) → true
    4. isSymmetricUtil(3.right, 3.left) → isSymmetricUtil(null, null) → true
    5. isSymmetricUtil(2.right, 2.left) → isSymmetricUtil(4, 4)
    6. isSymmetricUtil(4.left, 4.right) → isSymmetricUtil(null, null) → true
    7. isSymmetricUtil(4.right, 4.left) → isSymmetricUtil(null, null) → true

Each call returns true up the stack, confirming the tree is symmetric.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=nKggNAiEpBE
