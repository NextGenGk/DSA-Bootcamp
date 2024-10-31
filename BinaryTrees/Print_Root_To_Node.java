package BinaryTrees;

import java.util.ArrayList;
import java.util.List;

public class Print_Root_To_Node {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Time Complexity: O(N) where N is the number of nodes in the binary tree
    // as each node of the binary tree is visited exactly once in the inorder traversal.
    //
    // Space Complexity: O(N), where N is the number of nodes in the binary tree.
    // This is because the stack can potentially hold all nodes in the tree when
    // dealing with a skewed tree (all nodes have only one child), consuming space
    // proportional to the number of nodes.
    // O(H): In the average case or for a balanced tree, the maximum number of nodes
    // that could be in the stack at any given time would be roughly the height of
    // the tree hence O(log2N).

    // Function to find the path from the
    // root to a given node with value 'x'
    public static boolean getPath(TreeNode root, List<Integer> arr, int x) {
        // Base case: If the current
        // node is null, return false
        if (root == null) {
            return false;
        }

        // Add the current node's
        // value to the path list
        arr.add(root.val);

        // If the current node's value is equal
        // to the target value 'x', return true
        if (root.val == x) {
            return true;
        }

        // Recursively search for the target value
        // 'x' in the left and right subtrees
        if (getPath(root.left, arr, x) || getPath(root.right, arr, x)) {
            return true;
        }

        // If the target value 'x' is not found
        // in the current path, backtrack
        arr.remove(arr.size() - 1);
        return false;
    }

    // Function to find and return the path from
    // the root to a given node with value 'B'
    public static List<Integer> util(TreeNode root, int val) {
        // Initialize an empty
        // list to store the path
        List<Integer> arr = new ArrayList<>();

        // If the root node is null,
        // return the empty path list
        if (root == null) {
            return arr;
        }

        // Call the getPath function to find
        // the path to the node with value 'B'
        getPath(root, arr, val);

        // Return the path list
        return arr;
    }

    // Main Function
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        int targetLeafValue = 7;

        List<Integer> path = util(root, targetLeafValue);

        System.out.print("Path from root to leaf with value " +
                targetLeafValue + ": ");
        for (int i = 0; i < path.size(); ++i) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
    }
}

// Output : Path from root to leaf with value 7: 3 -> 5 -> 2 -> 7

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
To find the path from the root to the given node in the tree we use a Depth-First
Traversal. We initialise a vector to store the current path and recursively travel
to each node in the tree. At each step, we check if the current node is null, if it
is we return false and if the data value of the current node is equal to the given
node, we return true signifying the end of the traversal search.

During the recursive calls, we append the current node’s data value to the vector
and explore the left and right children. We backtrack if the target value is not
found as the children return ‘false’ and remove the last node from the path vector.
In the end, we return the vector containing the path from the root to the given node.

Algorithm:

Step 1:Initialise an empty vector to store the current path.

Step 2: Initialise a recursive function to explore the Binary Tree using Depth First
Search. Starting from the root node, we traverse the tree using the inorder sequence.

Base Case: If the current node is null then we return false, indicating the end of
the path. If the current node’s data value is equal to the given node then we return
the true, signifying the completion of the path.

Step 3: Recursive Calls:
    1. During the recursive exploration, the recursive function appends the current node's
       data value to the vector arr.
    2. It checks if the current node's value matches the target value x. If it does,
       the function returns true, indicating the completion of the path to the target node.
    3. We then call the function on the left and right children of the current node.

Step 4: Backtracking:
    1. If the target value x is not found in the current path, the function backtracks
       by removing the last node from the path vector arr.
    2. This means the current node is not part of the valid path from the root to the
       given node ensuring that the algorithm explores all possible paths and doesn't miss
       any valid routes to the target node.
Step 5: In the end, we return the vector containing the path from the root to the given node.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=fmflMqVOC7k
