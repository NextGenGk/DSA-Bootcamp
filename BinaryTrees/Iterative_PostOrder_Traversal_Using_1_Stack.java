package BinaryTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Iterative_PostOrder_Traversal_Using_1_Stack {

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
    // Time Complexity: O(@N) where N is the number of nodes in the binary tree. Every node of the binary
    // tree is visited exactly twice, and for each node, the operations performed (pushing and popping
    // from the stack, accessing node values, etc.) are constant time operations.
    // Space Complexity: O(N) where N is the number of nodes in the binary tree. This is because the
    // stack can potentially hold all nodes in the tree when dealing with a skewed tree (all nodes
    // have only one child), consuming space proportional to the number of nodes.

    // Function to perform postorder traversal
    // of a binary tree iteratively
    public static List<Integer> postOrder(TreeNode root) {
        // Create a stack to help with traversal
        Stack<TreeNode> stack = new Stack<TreeNode>();

        // List to store the result of postorder traversal
        List<Integer> postOrder = new ArrayList<Integer>();

        // Start traversal with the root node
        TreeNode curr = root;

        // Traverse the tree until you finish visiting all nodes
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                // Keep going left, pushing nodes to the stack
                stack.push(curr);
                curr = curr.left;
            } else {
                // Peek at the right subtree of the last node in the stack
                TreeNode temp = stack.peek().right;

                // If there's no right subtree, pop the node and add it to the postOrder list
                if (temp == null) {
                    temp = stack.pop();
                    postOrder.add(temp.val);

                    // Continue popping nodes if the right subtree has already been visited
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        postOrder.add(temp.val);
                    }
                } else {
                    // If there's a right subtree, traverse it
                    curr = temp;
                }
            }
        }

        // Return the postorder traversal result
        return postOrder;
    }

    // Main method
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(4);
        root.left.left.right.right = new TreeNode(5);
        root.left.left.right.right.right = new TreeNode(6);
        root.right.left = new TreeNode(8);


        // Getting postorder traversal
        List<Integer> result = postOrder(root);

        // Printing the postorder traversal result
        System.out.print("Postorder traversal: " + result);
    }
}

// Output : Postorder traversal: [6, 5, 4, 3, 2, 8, 7, 1]

// Approach / Intuition
/*
The intuition behind this iterative postorder traversal is to simulate the natural recursive flow
(Left → Right → Root) using a stack. Normally, recursion handles the backtracking needed to process
nodes in the correct order, but here, a stack is used to manage this manually. The algorithm first
pushes nodes as it traverses left, then checks if the right subtree needs to be visited. If a node
has no right child, or if the right child has already been processed, the node is popped from the
stack and added to the result. This ensures that the children are processed before the root, achieving
postorder traversal without recursion.

Note : For better understanding please refer the video
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=NzIGLLwZBS8