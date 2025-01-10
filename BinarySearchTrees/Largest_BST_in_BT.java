package BinarySearchTrees;

public class Largest_BST_in_BT {

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

    // Time Complexity: O(N*N) where N is the number of nodes in the Binary Tree. O(N) to
    // traverse through each node in the tree and for each node, the validation ot check whether
    // its subtree is a valid BST takes O(N) time hence the overall time complexity is O(N * N).

    // Space Complexity : O(1)as the there no additional space required for storing variables
    // or data structures. The recursive calls for validation can reach a depth of the entire
    // tree’s height hence the auxiliary space can be O(H) for the recursive stack.

    static class SolutionBrute {
        // Function to find the size
        // of the largest BST subtree
        public static int largestBSTSubtree(TreeNode root) {
            if (root == null)
                return 0;
            if (isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
                // If the current node is a valid BST,
                // return the size of the entire subtree
                return countNodes(root);
            } else {
                // If not, explore left and right subtrees
                int left = largestBSTSubtree(root.left);
                int right = largestBSTSubtree(root.right);
                return Math.max(left, right);
            }
        }

        // Function to check if a given root is a valid BST
        public static boolean isValidBST(TreeNode root, int minVal, int maxVal) {
            if (root == null)
                return true;
            if (root.val >= maxVal || root.val <= minVal)
                return false;
            return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
        }

        // Function to count the number of
        // nodes given the root of a subtree
        public static int countNodes(TreeNode root) {
            if (root == null)
                return 0;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    // Method 2 : Optimal Solution

    // Time Complexity: O(N)where N is the number of nodes in the Binary tree as we traverse
    // through all the nodes in the tree. The information update for each nodes takes constant
    // time hence the overall time complexity is O(N) as the tree is traversed once.

    // Space Complexity : O(N) where N is number of nodes in the Binary Tree as for each node
    // we store additional information using a struct class whose new object is initialised.
    // This additional space for variables is proportional to the number of nodes. An additional
    // memory stack space proportional to the height of the Binary Tree O(H) is used by the
    // recursive called to reach the leaf nodes.

    // Class to hold information
    // about the subtree
    static class NodeValue {
        int maxNode, minNode, maxSize;

        // Constructor to initialize
        // the NodeValue object
        NodeValue(int minNode, int maxNode, int maxSize) {
            this.maxNode = maxNode;
            this.minNode = minNode;
            this.maxSize = maxSize;
        }
    }

    static class Solution {
        // Helper function to find the
        // largest BST subtree recursively
        private static NodeValue largestBSTSubtreeHelper(TreeNode root) {
            // An empty tree is a BST of size 0
            if (root == null) {
                return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
            }

            // Get values from left and right
            // subtrees of the current tree.
            NodeValue left = largestBSTSubtreeHelper(root.left);
            NodeValue right = largestBSTSubtreeHelper(root.right);

            // Check if the current tree is a BST based
            // on its left and right subtrees' properties
            if (left.maxNode < root.val && root.val < right.minNode) {
                // It is a BST, update the values for the current tree
                return new NodeValue(Math.min(root.val, left.minNode),
                        Math.max(root.val, right.maxNode), left.maxSize + right.maxSize + 1);
            }

            // If the current tree is not a BST,
            // return values indicating invalid tree properties
            return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
        }

        // Function to find the size
        // of the largest BST subtree
        public static int largestBSTSubtree(TreeNode root) {
            return largestBSTSubtreeHelper(root).maxSize;
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Create a binary tree
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);

        // Test the brute force solution
        SolutionBrute bruteForceSolution = new SolutionBrute();
        System.out.println("Largest BST subtree size (Brute Force): "
                + bruteForceSolution.largestBSTSubtree(root));

        // Test the optimal solution
        Solution optimalSolution = new Solution();
        System.out.println("Largest BST subtree size (Optimal): "
                + optimalSolution.largestBSTSubtree(root));
    }
}

// Output :
/*
Largest BST subtree size (Brute Force): 3
Largest BST subtree size (Optimal): 3
 */

// Algorithm / Intuition : Brute Force
/*
Algorithm / Intuition
A brute force approach for finding the largest Binary Search Tree subtree within a given
binary tree would be to traverse each node in the tree and validating whether the subtree
rooted at that node conforms to the rules of the BST.

This can be done by recursively checking each node and its children to enure that the left
child’s values are less than the nodes values and the right child’s values are greater than
the nodes values.

Validate a BST is a prerequisite to this question ans is ised as a function call for each
node in the tree evaludaitng whether the subtree rooted at the node is a valid BST.
Whenever a valid BST is confirmed, the size of that particular subtree is calculated and
compared against the current maximum subtree size stored. Ultimately, the global maximum
BST subtree size is returned.

Algorithm:

Step 1: Define a function `isValidBST` that checks if a given subtree rooted at a node
is a valid BST by performing a recursive traversal while maintaining range constraints.
Read more about this approach here.

Step 2: Initialise a variable `maxSize` to 0 to track the maximum size encountered and
recursively traverse each node of the given Binary Tree.

Step 3:At each node, call the `isValidBST` function to determine if the subtree rooted
t that node is a valid BST. If the subtree is a valid BST, calculate the size of the
current subtree and update `maxSize` to the greater value.

Step 4: After traversing all the node, return `maxSize` as it contains the size of the
largest BST subtree encountered.
 */

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
A more optimised approach would be to traverse the tree and simultaneously check is each
subtree is a BST. Utilise a bottom up recursive approach to traverse the tree efficiently.

For each node the minimum value, maximum value, size of the BST and whether or not it is a
BST which the node as root is passed and updated based on its children’s information. In the
end, the size of the largest BST subtree found is returned.

Algorithm

Step 1:Define a `NodeValue` class to hold the following information about each subtree:

`minNode``: minimum value of the subtree
`maxNode`: maximum value of the subtree.
`maxSize`: maximum size of the BST encountered so far

Step 2: next () function

Implement a helper function `largestBSTSubtreeHelper` that takes the root as input and
recursively gathers information (`minNode`, `maxNode` and `maxSize`) for each subtree.

    i. The `NodeValue` information fo the current node is updated based on the information
       from the left and right subtree properties ie. the left subtree’s maximum node is less
       than the current ndoe and the right subtree’s minimum node is greater than the current node.
   ii. If the current subtree satisfied the BST property, update the size of the node ie.
       `maxSize` as `maxSize` from left subtree + `maxSize` from right subtree + 1.
  iii. If the current subtree is not a BST, pass on the current maxSize but return minNode
       as int min and minSize as int max.

Step 3: Return the maxSize of the largest BST subtree found.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=X0oXMdtUDwo