package BinaryTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Iterative_PostOrder_Traversal_Using_2_Stacks {

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
    // Time Complexity: O(2N) where N is the number of nodes in the Binary Tree. The traversal process visits
    // each node in the Binary Tree exactly once to push into stack1 and stack2. Then after the tree is
    // traversed and the nodes are popped from stack2 to push into the postorder array.
    // Space Complexity: O(2N) where N is the number of nodes in the Binary Tree. The space occupied by
    // the two stacks depend on the height of the binary tree. In the worst-case scenario, if the tree
    // is skewed, the space complexity would be O(N) as both stacks could potentially hold all nodes at
    // different points during traversal.The postorder array also holds all nodes from the binary tree
    // hence giving another O(N) + O(N) ~ O(2N).

    // Function to perform postorder traversal
    // of a binary tree iteratively
    public static List<Integer> postOrder(TreeNode root) {
        // Create two stacks: stack1 is used for traversal, stack2 stores the reverse of postorder traversal
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        // List to store the result of postorder traversal
        List<Integer> postOrder = new ArrayList<>();

        // If the tree is empty, return the empty list
        if (root == null) return postOrder;

        // Start traversal by pushing the root to stack1
        stack1.push(root);

        // Traverse the tree
        while (!stack1.isEmpty()) {
            // Pop the top node from stack1 and push it to stack2
            root = stack1.pop();
            stack2.push(root);

            // If the current node has a left child, push it to stack1
            if (root.left != null) stack1.push(root.left);

            // If the current node has a right child, push it to stack1
            if (root.right != null) stack1.push(root.right);
        }

        // Stack2 now contains nodes in reverse postorder, so we pop them to get the correct order
        while (!stack2.isEmpty()) {
            root = stack2.pop();
            // Add the node's value to the postOrder list
            postOrder.add(root.val);
        }

        // Return the postorder traversal
        return postOrder;
    }

    // Main method
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Getting postorder traversal
        List<Integer> result = postOrder(root);

        // Printing the postorder traversal result
        System.out.print("Postorder traversal: " + result);
    }
}

// Output : Postorder traversal: [4, 5, 2, 3, 1]

// Approach / Intuition
/*
This approach performs a modified postorder traversal (left, right then root) and then reverses the
result to obtain the postorder traversal sequence. This approach uses two stacks, one for traversal and
another to store the nodes in a manner that facilitates obtaining the postorder sequence in reverse.The
root node is pushed onto the first stack to initiate traversal and nodes are iteratively processed by
popping them from the first stack, pushing them onto the second and adding their left and right children
onto the first stack. This process continues until all nodes have been processed.Lastly, all nodes from
the second stack are popped and retrieved in reverse order, effectively getting the postorder traversal
sequence which is stored in an array and returned.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=2YBhNLodD8Q