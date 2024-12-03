package BinaryTrees;

public class Flatten_Binary_Tree_to_Linked_List {

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

    // Method 1 : Brute Force

    class Solution {
        // Initialize a global variable
        // 'prev' to keep track of the
        // previously processed node.
        static TreeNode prev = null;

        // Function to flatten a binary tree
        // to a right next Linked List structure
        public static void flatten(TreeNode root) {
            // Base case: If the current
            // node is null, return.
            if (root == null) {
                return;
            }

            // Recursive call to
            // flatten the right subtree
            flatten(root.right);

            // Recursive call to
            // flatten the left subtree
            flatten(root.left);

            // At this point, both left and right
            // subtrees are flattened, and 'prev'
            // is pointing to the rightmost node
            // in the flattened right subtree.

            // Set the right child of
            // the current node to 'prev'.
            root.right = prev;

            // Set the left child of the
            // current node to null.
            root.left = null;

            // Update 'prev' to the current
            // node for the next iteration.
            prev = root;
        }
    }

    // Print the preorder traversal of the
    // Original Binary Tree
    public static void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // Print the Binary Tree along the
    // Right Pointers after Flattening
    public static void printFlattenTree(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printFlattenTree(root.right);
    }

    // Main Function
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(8);

        System.out.print("Binary Tree Preorder: ");
        printPreorder(root);
        System.out.println();

        Solution.flatten(root);

        System.out.print("Binary Tree After Flatten: ");
        printFlattenTree(root);
        System.out.println();
    }
}

// Output : Binary Tree Preorder: 1 2 4 5 6 3 8 7, Binary Tree After Flatten: 1 2 4 5 6 3 8 7

// Algorithm / Intuition : Brute Force
/*
Algorithm / Intuition
The intuition behind this approach is to perform a reverse pre-order traversal where,
instead of simply visiting nodes, we flatten the tree into a linked list as we traverse it.

We start at the root of the tree and recursively do the following for each node,
we first ensure that the right subtree is flattened into a linked list. This means
that all nodes in the right subtree are processed and attached to the linked list
in the correct order.

Next, we do the same for the left subtree. This ensures that all nodes in the left
subtree are processed and attached to the linked list in the correct order. Once both
subtrees are flattened, we attach the flattened left subtree as the right child of
the current node. Since we're using the right pointer of the binary tree as the next
pointer for the linked list, this effectively attaches the left subtree to the current
node in the linked list. Finally, we attach the flattened right subtree to the rightmost
node of the flattened left subtree. This ensures that the right subtree is properly
attached to the end of the linked list.

Algorithm
Step 1:Initialise a global variable `prev` to keep track of the previously processed node.
Initially set it to null.

Step 2: Base Case: If the current node is null, return null.

Step 3: Flatten the Right and Left Subtree: Recursively flatten the right and left
subtree of the current node by calling the flatten function on the current node's
right and left child.

Step 4: Attach the Right Subtree to the Flattened Left Subtree: Set the right child
f the current node to the value of `prev` since `prev` points to the rightmost node
in the flattened left subtree. This effectively attaches the right subtree to the
right of the rightmost node of the left subtree.

Step 5:Attach the Left Subtree as Right Child: Set the right child of the current
node to the left subtree.

Set the left child of the current node to null since we are flattening the binary
tree to a linked list and there should be no left child.

Step 6:Update `prev` to the current node for the next iteration and recursion step.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=sWf7k1x9XR4