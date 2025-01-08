package BinarySearchTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BST_Iterator {

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
    // Time Complexity : O(N) the overall time complexity of this approach is O(N) for
    // constructing the iterator and O(1) for each subsequent operation of hasNext() and next().
    // Space Complexity : O(N)where N is the total number of nodes in the Binary Search Tree
    // as the complete inorder traversal of the BST is stored in an additional data structure array.

    static class BSTIteratorBrute {
        // Store inorder traversal
        private List<Integer> inorderTraversal;
        // Pointer to track current index
        private int pointer;

        // Constructor initializing the BSTIterator
        BSTIteratorBrute(TreeNode root) {
            // Initialize pointer to a non-existent value
            pointer = -1;
            // Initialize the list to store inorder traversal
            inorderTraversal = new ArrayList<>();
            // Store inorder traversal in the list
            inorder(root);
        }

        // Perform inorder traversal of the
        // BST and store in the list
        private void inorder(TreeNode root) {
            if (root == null) return;
            inorder(root.left);
            inorderTraversal.add(root.val);
            inorder(root.right);
        }

        // Checks if there exists a number in
        // the traversal to the right of the pointer
        boolean hasNext() {
            return pointer + 1 < inorderTraversal.size();
        }

        // Moves the pointer to the right,
        // then returns the number at the pointer
        int next() {
            pointer++;
            return inorderTraversal.get(pointer);
        }
    }

    // Method 2 : Optimal Solution
    //

    static class BSTIteratorOptimal {
        // Private stack to store tree nodes
        private Stack<TreeNode> myStack;

        // Constructor initializing the
        // BSTIterator with the root of the BST
        BSTIteratorOptimal(TreeNode root) {
            // Initialize the stack with leftmost nodes
            myStack = new Stack<>();
            pushAll(root);
        }

        // Checks if there is a next
        // smallest number in the BST
        boolean hasNext() {
            // Returns true if stack is not empty
            return !myStack.isEmpty();
        }

        // Returns the next smallest number in the BST
        int next() {
            // Get the top node from stack
            TreeNode tmpNode = myStack.pop();
            // Add leftmost nodes of the right subtree
            pushAll(tmpNode.right);
            // Return the value of the removed node
            return tmpNode.val;
        }

        // Pushes all the leftmost nodes starting
        // from the given node onto the stack
        private void pushAll(TreeNode node) {
            // Iterate through left nodes,
            // pushing each onto the stack
            while (node != null) {
                myStack.push(node);
                node = node.left;
            }
        }
    }

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

    // Main Function
    public static void main(String[] args) {
        // Create a sample binary search tree:
        // 7 3 15 -1 -1 9 20
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        System.out.print("Tree Initialized: ");
        printInOrder(root);
        System.out.println();

        // Create a BSTIterator object
        // initialized with the root of the BST
//        BSTIteratorBrute bstIterator = new BSTIteratorBrute(root);
        BSTIteratorOptimal bstIterator = new BSTIteratorOptimal(root);

        // Function calls and their outputs
        System.out.println("Functions Called:");
        System.out.println("BSTIterator()");
        System.out.println("next(): " + bstIterator.next());
        System.out.println("next(): " + bstIterator.next());
        System.out.println("hasNext(): " + (bstIterator.hasNext() ? "true" : "false"));
        System.out.println("next(): " + bstIterator.next());
        System.out.println("hasNext(): " + (bstIterator.hasNext() ? "true" : "false"));
        System.out.println("next(): " + bstIterator.next());
        System.out.println("hasNext(): " + (bstIterator.hasNext() ? "true" : "false"));
        System.out.println("next(): " + bstIterator.next());
        System.out.println("hasNext(): " + (bstIterator.hasNext() ? "true" : "false"));
    }
}

// Output :
/*
Tree Initialized: 3 7 9 15 20
Functions Called:
BSTIterator()
next(): 3
next(): 7
hasNext(): true
next(): 9
hasNext(): true
next(): 15
hasNext(): true
next(): 20
hasNext(): false
 */

// Algorithm / Intuition : Brute Force
/*
Algorithm / Intuition
Perform an inorder traversal of the Binary Search Tree and store the nodes in an array,
we obtain a sorted array. Maintain a pointer or index variable to keep track of the
current position within the array during iteration. BSTIterator() initiailses this index
to -1 and we access the elements in ascending order incrementing the index every time
next() is called.

Algorithm:

Step 1: Store the inorder traversal of the BST in an array. This can be done recursively
traversing the left subtree, then visiting the current node and finally traversing the
right subtree. Read more about this approach here! Inorder Traversal of a Binary Tree

Step 2:For the constructor BSTIterator(), set a pointer variable to -1 to keep track
of the current index within the inorder traversal array.

Step 3:For function next(), incremen the pointer by 1 and return the value at the
incremented index from the inorder traversal array.

Step 4: For function hasNext(), return true if ‘pointer + 1’ is less than the length
of inorder traversal, else return false.
 */

// Algorithm / Intuition : Optimal Solution
/*
The previous approach uses O(N) space complexity that grows linearly with the number
of nodes in the BST. This can be optimised to a space complexity of O(H) where H is
the height of the tree and O(1) time complexity for the next() and hasNext() operations
by leveraging the properties of a Binary Search Tree.

The optimised approach leverages Binary Search Tree (BST) properties to create an
iterator with O(H) space complexity (where H is the tree height) and O(1) time
complexity for next() and hasNext() operations.

By using a stack and performing an iterative traversal, the constructor initialises
by navigating to the leftmost nodes and storing them in a stack. The next() function
retrieves the top element, explores its right subtree, and adds left descendants to
the stack. hasNext() checks the stack for remaining elements to iterate over, signalling
true if elements exist and false if the stack is empty.

Algorithm

Step 1: Constructor BSTIterator(TreeNode root) Implementation:
    i. Utilise a stack data structure (Last In First Out) within the constructor.
   ii. Initially, traverse to the extreme left of the BST from the given root and
       push each node encountered onto the stack.

Step 2: next () function
    i. Upon calling next () pop the top element from the stack.
   ii. Move to the right of the popped node and traverse down the left subtree of
       this right node, pushing encountered nodes onto the stack.

Step 3: hasNext() function:
    i. Check if the stack is not empty, if the stack contains elements, it implies
       there are nodes that can be iterated over by next(), so return true.
   ii. If the stack is empty, there are no more nodes to iterate over, hence return false.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=D2jMcmxU4bs