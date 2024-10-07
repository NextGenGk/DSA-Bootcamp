package BinaryTrees;

public class Diameter_of_a_Binary_Tree {

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

    // Method 1 : Brute Force

    // Time Complexity: O(N*N) where N is the number of nodes in the Binary Tree.
    // This arises as we calculate the diameter of each node and to calculate the height of
    // its left and right children, we traverse the tree which is proportional to the number of nodes.
    // Since this calculation is performed for each node in the tree, the complexity becomes:
    // O(N x N) ~ O(N2).

    // Space Complexity : O(1) as no additional data structures or memory is allocated.O(H):
    // Recursive Stack Space is used to calculate the height of the tree at each node which is
    // proportional to the height of the tree.The recursive nature of the getHeight function,
    // which incurs space on the call stack for each recursive call until it reaches the leaf
    // nodes or the height of the tree.

    // Helper method to calculate the height of the tree
    static int height(TreeNode root) {
        // Base case: if the node is null, the height is 0
        if (root == null) return 0;

        // Recursively calculate the height of the left and right subtrees
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Return the greater of the two heights plus 1 for the current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Method to calculate the diameter of the tree using a brute-force approach
    static int diameterOfBinaryTree(TreeNode root) {
        // Base case: if the node is null, the diameter is 0
        if (root == null) return 0;

        // Calculate the height of the left and right subtrees
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Diameter through the current node
        int currentDiameter = leftHeight + rightHeight;

        // Recursively calculate the diameter of the left and right subtrees
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        // The diameter is the maximum of the current diameter and the
        // diameters of the left and right subtrees
        return Math.max(currentDiameter, Math.max(leftDiameter, rightDiameter));
    }

    // Method 2 : Optimal Solution

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree. This complexity
    // arises from visiting each node exactly once during the postorder traversal.

    // Space Complexity : O(1) as no additional space or data structures is created that is
    // proportional to the input size of the tree. O(H) Recursive Stack Auxiliary Space :
    // The recursion stack space is determined by the maximum depth of the recursion, which
    // is the height of the binary tree denoted as H. In the balanced case it is log2N and
    // in the worst case its N.

    // Method to calculate the diameter of the binary tree and return the height of the tree
    static int diameterOfBinaryTree1(TreeNode root) {
        // Variable to store the maximum diameter, initialized to 0
        int maxDiameter = 0;

        // Base case: if the node is null, return 0 (indicating no height)
        if (root == null) return 0;

        // Recursively calculate the height of the left subtree
        int leftHeight = diameterOfBinaryTree1(root.left);

        // Recursively calculate the height of the right subtree
        int rightHeight = diameterOfBinaryTree1(root.right);

        // Update the maximum diameter by comparing the current value of maxDiameter with
        // the sum of the left and right subtree heights at this node (diameter at this node)
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return the height of the current node, which is 1 (for the node itself)
        // plus the maximum height of its left or right subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.left.right.right.right = new TreeNode(7);

        // Calculate the diameter of the binary tree
        int diameter = diameterOfBinaryTree1(root);

        System.out.println("The diameter of the binary tree is: " + diameter);
    }
}

// Output : The diameter of the binary tree is: 5

// Algorithm / Intuition : Brute Force
/*
To find the diameter of a binary tree, we can think of every node as a potential `Curving Point`
of the diameter path. This Curving Point is the node along the diameter path that holds the maximum
height and from where the path curves uphill and downhill.

Hence, we can see that the diameter at a specific curving point is determined by adding the height
of the left subtree to the height of the right subtree and adding 1 to account for the level of the
curving point. Diameter = 1 + Left Subtree Height + Right Subtree Height Therefore, we can traverse
the tree recursively considering each node as a potential Curving Point and calculate the height of
the left and right subtrees at each node. This will give us the diameter for the current Curving Point.
Throughout the traversal, we track the maximum diameter encountered and return it as the overall
diameter of the Binary Tree.

Algorithm:

Step 1: Create a global variable `diameter` to store the maximum diameter encountered. At every node,
we will take the maximum of this variable and the current diameter to update it to be the maximum
amongst all.

Step 2: Define the recursive function calculateHeight that takes in a node as an argument and
calculate its height in the Binary Tree.

Step 3: Recursively start traversing from the root, consider the current node to be a potential
Curving Point and for each node:

1. Recursively calculate the height of its left and right subtrees.
2. Compute the diameter at the current node by summing heights of the left and right subtrees.
3. Update the global variable diameter as the max of the current diameter and the
largest diameter encountered so far.

Step 4: Return the maximum diameter found during traversal as the result.
 */

// Algorithm / Intuition : Optimal Solution
/*
The O(N2) time complexity of the previous approach can be optimised by eliminating the steps of
repeatedly calculating subtree heights. The heights of the left and right subtrees are computed
multiple times for each node, which leads to redundant calculations.Instead, we can compute these
heights in a bottom-up manner. The Postorder method allows us to validate balance conditions
efficiently during the traversal.

The postorder traversal operates in a bottom-up manner, calculating subtree information before
moving to the parent node. We efficiently compute the heights of both the subtrees and at the
same time calculate the diameter and update the maximum diameter encountered.

Algorithm:

Step 1: Initialise a variable `diameter` to store the diameter of the tree. Create a function
height that takes a node and a reference to the diameter variable as input.

Step 2: Base Case: If the node is null, return 0 indicating the height of an empty tree.

Step 3: Recursive Function:

1. Recursively calculate the height of the left subtree then height of the right subtree.
2. Set the current diameter as the sum of left subtree, right subtree + 1 for the current level.
3. Update the diameter with the maximum of the current diameter and the global diameter.

Step 4: After the traversal if complete, return the maximum diameter found during the traversal as the result.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=Rezetez59Nk