package BinarySearchTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Two_Sum_in_BST {

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
    // Time Complexity : The inorder traversal takes O(n) time and the two-pointer
    // scan also takes O(n) time.
    // Space Complexity : Additional space is used to store the elements in a list.

    // Helper function to perform inorder traversal and store the node values in a list
    public static void inorderTraversal(TreeNode root, List<Integer> nodes) {
        if (root == null) return;
        inorderTraversal(root.left, nodes);
        nodes.add(root.val); // Add the current node's value to the list
        inorderTraversal(root.right, nodes);
    }

    // Function to find if there are two numbers in the BST that sum up to the target
    public static boolean findTarget(TreeNode root, int k) {
        List<Integer> nodes = new ArrayList<>();
        inorderTraversal(root, nodes); // Get the sorted list of node values

        // Two-pointer approach to find if there are two numbers that sum to k
        int left = 0, right = nodes.size() - 1;
        while (left < right) {
            int sum = nodes.get(left) + nodes.get(right);
            if (sum == k) {
                return true; // Found two numbers that sum to k
            } else if (sum < k) {
                left++; // Move the left pointer to the right
            } else {
                right--; // Move the right pointer to the left
            }
        }
        return false; // No two numbers found that sum to k
    }

    // Method 2 : Optimal Solution
    // Time Complexity: O(N) where N is the number of nodes in the BST as we have to
    // traverse all the nodes using the i and j pointers to find the pair with sum ‘k’.
    // Space Complexity : O(H) where H is the height of the Binary Search Tree as the
    // BSTIterator class uses a stack to store the nodes. At maximum the size of such a
    // stack will be equal to the height of the Binary Tree.

    // BSTIterator class for iterating through BST nodes
    static class BSTIterator {
        // Stack to store nodes
        private Stack<TreeNode> myStack;
        // Flag to determine traversal direction
        private boolean reverse;

        // Constructor initializing BSTIterator with the
        // root of the BST and traversal direction
        BSTIterator(TreeNode root, boolean isReverse) {
            myStack = new Stack<>();
            reverse = isReverse;
            // Initialize the stack with nodes
            pushAll(root);
        }

        // Checks if there exists a
        // next element in the BST
        boolean hasNext() {
            // Returns true if the
            // stack is not empty
            return !myStack.empty();
        }

        // Retrieves the next smallest element
        // ie. inorder successor in the BST
        int next() {
            // Retrieve the top node from the stack
            TreeNode tmpNode = myStack.pop();
            if (!reverse) {
                // If not in reverse mode,
                // add nodes from the right subtree
                pushAll(tmpNode.right);
            } else {
                // If in reverse mode,
                // add nodes from the left subtree
                pushAll(tmpNode.left);
            }
            // Return the value of the retrieved node
            return tmpNode.val;
        }

        // Helper function to push nodes into
        // the stack in a specific order
        private void pushAll(TreeNode node) {
            while (node != null) {
                // Push the node onto the stack
                myStack.push(node);
                if (reverse) {
                    // Move to the right child
                    // if in reverse mode
                    node = node.right;
                } else {
                    // Move to the left child
                    // if not in reverse mode
                    node = node.left;
                }
            }
        }
    }

    // Function to find if there exists
    // a pair with a given sum in the BST
    static boolean findTarget1(TreeNode root, int k) {
        if (root == null) {
            // If the root is empty,
            // return false
            return false;
        }

        // Initialize two BSTIterators for
        // traversal in different directions

        // Left iterator
        BSTIterator l = new BSTIterator(root, false);
        // Right iterator
        BSTIterator r = new BSTIterator(root, true);

        // Get the next element from the left iterator
        int i = l.next();
        // Get the next element from the right iterator
        int j = r.next();

        // Loop to find the pair with the given sum
        while (i < j) {
            if (i + j == k) {
                // If the sum is found,
                // return true
                return true;
            } else if (i + j < k) {
                // Move to the next element
                // from the left iterator
                i = l.next();
            } else {
                // Move to the next element
                // from the right iterator
                j = r.next();
            }
        }
        // If no pair found, return false
        return false;
    }

    // Main Function
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        int target = 9;
        System.out.println("Two Sum in BST (Brute Force) - Target " + target + ": "
                + findTarget1(root, target));
    }
}

// Output :
/*
Two Sum in BST (Brute Force) - Target 9: true
 */

// Algorithm / Intuition : Brute Force
/*
The brute force approach involves performing an inorder traversal to get a sorted list
of node values and then using a two-pointer technique to find if there are two numbers
that add up to the target sum.
 */

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
The previous approach uses O(N) space complexity which can be eliminated by leveraging
the properties of a Binary Search Tree instead. As a prerequisite for this problem, make
sure you are thorough with the concepts of Binary Search Tree Iterator. This BSTIterator
class allows one to access the next and previous elements (in order predecessor and
successor) in a BST.

Using the BSTIterator class implementation, initialise pointers 'i' and 'j' to the first
and last elements of the BST's inorder traversal, respectively. These pointers are navigated
through the BST using the next() and before() functions of the BSTIterator. The 'i' pointer
progresses towards larger values with next(), while 'j' moves towards smaller values
with before(). This approach leverages on the BST properties to efficiently navigate
through the elements and identify the pair satisfying the given sum without using any
additional data structure to store the inorder traversal.

Algorithm :

Step 1: Initialise pointers ‘i’ and ‘j’ to the first and last elements of the BST’s
inorder traversal using the BSTIterator class.

Step 2: Utilise next() function to advance pointer ‘i’ towards larger values and the
before() function to navigate ‘j’ towards smaller values within the BST.

1. If the sum of the values at these pointers is less than the target, increment
the ‘i’ pointer. This will move towards larger values.
2. If the sum is greater than the target, decrement the ‘j’ pointer. This will move
towards smaller values.
3. If the sum is equal to the target, return true.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=ssL3sHwPeb4