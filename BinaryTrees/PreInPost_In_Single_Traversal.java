package BinaryTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreInPost_In_Single_Traversal {

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


    // Pair class to store (node, number) pairs
    static class Pair {
        TreeNode node; // This holds a reference to the tree node
        int num;       // This holds the current state of the node (1 for pre-order, 2 for in-order, 3 for post-order)

        // Constructor to initialize the Pair with the node and its state
        public Pair(TreeNode node, int num) {
            this.node = node;
            this.num = num;
        }
    }

    // Method 1 : Optimal Solution
    // Time Complexity: O(3N) where N is the number of nodes in the Binary Tree. Each node is processed
    // once for each traversal type (pre-order, in-order, and post-order). Hence, the algorithm effectively
    // visits each node three times in total across the three traversal types.

    // Space Complexity: O(4N) where N is the number of nodes in the Binary Tree. The following
    // additional data structures are used:A stack to maintain traversal states, requiring additional
    // space proportional to the maximum number of nodes in the stack at any point during traversal.
    // Three vectors to store the preorder, inorder and postorder traversal. These arrays collectively
    // occupy space proportional to the total number of nodes in the tree. Hence, 3N is added to the
    // space complexity.

    // Function to perform preorder, inorder, and postorder traversal iteratively
    public static List<List<Integer>> preInPost(TreeNode root) {
        // Stack to simulate recursive traversal of the tree
        Stack<Pair> stack = new Stack<>();
        // Push the root node with a state of 1 (pre-order traversal)
        stack.push(new Pair(root, 1));

        // Lists to store the pre-order, in-order, and post-order traversal results
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        // Continue the loop until the stack is empty (tree traversal is complete)
        while (!stack.isEmpty()) {
            // Get the top element from the stack
            Pair it = stack.pop();

            // If the node is in the pre-order state
            if (it.num == 1) {
                // Add the node's value to the pre-order list
                pre.add(it.node.val);
                // Increment the state to indicate in-order processing next
                it.num++;
                // Push the node back to the stack with the updated state
                stack.push(it);

                // If there is a left child, push it onto the stack for processing
                // (pre-order visits left subtree first)
                if (it.node.left != null) {
                    stack.push(new Pair(it.node.left, 1));
                }
            }

            // If the node is in the in-order state
            else if (it.num == 2) {
                // Add the node's value to the in-order list
                in.add(it.node.val);
                // Increment the state to indicate post-order processing next
                it.num++;
                // Push the node back to the stack with the updated state
                stack.push(it);

                // If there is a right child, push it onto the stack for processing
                if (it.node.right != null) {
                    stack.push(new Pair(it.node.right, 1));
                }
            }

            // If the node is in the post-order state
            else if (it.num == 3) {
                // Add the node's value to the post-order list
                post.add(it.node.val);
            }
        }

        // This is where you can return the results for pre-order, in-order, and post-order lists
        List<List<Integer>> result = new ArrayList<>();
        result.add(pre);
        result.add(in);
        result.add(post);
        return result;
    }

    // Function to print the elements of a list
    public static void printList(List<Integer> list) {
        // Iterate through the list and print each element
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Getting the pre-order, in-order, and post-order traversals
        List<Integer> pre, in, post;
        List<List<Integer>> traversals = preInPost(root);

        // Extracting the traversals from the result
        pre = traversals.get(0);
        in = traversals.get(1);
        post = traversals.get(2);

        // Printing the traversals
        System.out.print("Preorder traversal: ");
        printList(pre);

        System.out.print("Inorder traversal: ");
        printList(in);

        System.out.print("Postorder traversal: ");
        printList(post);
    }
}

// Output :
/*
Preorder traversal: 1 2 4 5 3
Inorder traversal: 4 2 5 1 3
Postorder traversal: 4 5 2 3 1
 */

// Approach / Intuition
/*
This approach traverses the binary tree in a single pass while computing the preorder, inorder and
postorder traversals at the same time. A stack is used for state management. The stack keeps track of
the traversal state for each node. It stores nodes and their state information allowing the algorithm
to resume traversal from intermediate points.For each node, it identifies its state: if it's in the
pre-order state, it records the node's value and pushes the left child onto the stack. Moving to the
in-order state, it records the node's value and pushes the right child onto the stack. Finally, in the
post-order state, it stores the node's value and pops the node.As the algorithm executes over each node,
it pushes each value in separate arrays for preorder, inorder and postorder traversals depending upon
the current order and sequence. Hence, we are able to traverse the tree just once and get all three
traversals from it.

Algorithm:

Step 1: Initialise a stack that holds a tree node and an integer value representing its state
corresponding to pre order, inorder and postorder. Initialise empty arrays to store the three
traversals as well.Check if the tree is empty. If so, return empty traversals.

Step 2: Push the root node onto the stack along with its state ‘1’ (preorder) to start the traversal.

Step 3:

While the stack isn’t empty, pop the top node of the stack and for each node:

    i. If the state is ‘1’ ie. preorder: store the node’s data in the preorder array and move
       its state to 2 (inorder) for this node. Push this updated state back onto the stack and push
       its left child as well.
   ii. If the state is ‘2’ ie. inorder: store the node’s data is the inorder array and update its
       state to 3 (postorder) for this node. Push the updated state back onto the stack and push the
       right child onto the stack as well.
  iii. If the state is ‘3’ ie. postorder: store the node’s data in the postorder array and pop it.

Step 4: Return the preorder, inorder and postorder array.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=ySp2epYvgTE