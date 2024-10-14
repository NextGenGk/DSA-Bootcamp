package BinaryTrees;

import java.util.*;

public class Zig_Zag_Traversal {

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

    // Time Complexity: O(N) where N is the number of nodes in the binary tree. Each node
    // of the binary tree is enqueued and dequeued exactly once, hence all nodes need to
    // be processed and visited. Processing each node takes constant time operations which
    // contributes to the overall linear time complexity.

    // Space Complexity: O(N) where N is the number of nodes in the binary tree.
    // In the worst case, the queue has to hold all the nodes of the last level of the
    // binary tree, the last level could at most hold N/2 nodes hence the space complexity
    // of the queue is proportional to O(N). The resultant vector answer also stores the
    // values of the nodes level by level and hence contains all the nodes of the tree
    // contributing to O(N) space as well.

    // Function to perform zigzag level
    // order traversal of a binary tree
    public static List<List<Integer>> zigZagLevelOrder(TreeNode root) {
        // List to store the result of zigzag traversal
        List<List<Integer>> result = new ArrayList<>();

        // Check if the root is null, return an empty result
        if (root == null) {
            return result;
        }

        // Queue to perform level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Flag to determine the direction of traversal (left to right or right to left)
        boolean leftToRight = true;

        // Continue traversal until the queue is empty
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level
            int size = queue.size();

            // List to store the values of nodes at the current level
            List<Integer> row = new ArrayList<>();

            // Traverse nodes at the current level
            for (int i = 0; i < size; i++) {
                // Get the front node from the queue
                TreeNode node = queue.poll();

                // Add the node's value to the row list
                row.add(node.val);

                // Enqueue the left and right children if they exist
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // Reverse the row if the direction is right to left
            if (!leftToRight) {
                Collections.reverse(row);
            }

            // Switch the traversal direction for the next level
            leftToRight = !leftToRight;

            // Add the current level's values to the result list
            result.add(row);
        }

        // Return the final result of zigzag level order traversal
        return result;
    }

    // Print List Function
    static void printResult(List<List<Integer>> result) {
        for (List<Integer> row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Get the zigzag level order traversal
        List<List<Integer>> result = zigZagLevelOrder(root);

        // Print the result
        printResult(result);
    }
}

// Output :
// Input: root = [3,9,20,null,null,15,7]
// Output: [[3],[20,9],[15,7]]

// Algorithm / Intuition : Optimal Solution
/*
Zigzag traversal is a modification of the traditional level order traversal in a binary tree.
Level Order Traversal explores does at each level from left or right but zigzag traversal
adds a twist by alternating the direction of exploration. At odd levels, we proceed from
left to right but for even levels the order is reversed, from right to left. This is
achieved by introducing a `leftToRight` flag which controls the order in which nodes
are processed at each level. When `leftToRight` is true, nodes are inserted into the
level vector from left to right and when its false, nodes are inserted right to left.


Algorithm:

Step 1: Initialise an empty queue data structure to store the nodes during traversal.
Create a 2D array or a vector of a vector to store the level order traversal. If the
tree is empty, return this empty 2D vector.

Step 2: Create a `leftToRight` flag to keep track of the direction of traversal. When
`leftToRight` is true, nodes are inserted into the level vector from left to right and
its false, nodes are inserted right to left.

Step 3: Enqueue the root node ie. Add the root node of the binary tree to the queue.

Step 4: Iterate until the queue is empty:

    1. Get the current size of the queue. This size indicates the number of nodes
       at the current level.
    2. Create a vector ‘level’ to store the nodes at the current level.

Step 5: Iterate through ‘size’ number of nodes at the current level:

    1. Pop the front node from the queue.
    2. Store the node’s value in the level vector. Determine the index to insert the
    node’s value based on the traversal direction ‘leftToRight’.
    3. If ‘leftToRight’ is true, the index is set to ‘i’ which means the node’s value
    will be inserted form left to right. If ‘rightToLeft’ is false, the index is set
    to size - 1 - i, meaning the node’s value will be inserted from right to left.

Step 6: Enqueue the left and right child nodes of the current node (if they exist)
into the queue.

Step 7: After processing all the nodes at the current level, add the ‘level’ vector
to the ‘ans’ 2D vector, representing the current level. Reverse the direction of traversal
for the next level by updating the ‘leftToRight’ flag to its opposite value. This toggling
ensures that the nodes at the next level will be processed in the opposite direction,
alternating between left-to-right and right-to-left.

Step 8: Once the traversal loop completes ie. all levels have been processed, return the
‘ans’ 2D vector containing the level-order traversal.
 */

// Striver's (Video Expalanation) : https://www.youtube.com/watch?v=3OXWEdlIGl4