package BinaryTrees;

public class Maximum_Path_Sum {

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

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree.
    // This complexity arises from visiting each node exactly once during the
    // recursive traversal.

    // Space Complexity: O(1) as no additional space or data structures is created
    // that is proportional to the input size of the tree. O(H) Recursive Stack
    // Auxiliary Space : The recursion stack space is determined by the maximum
    // depth of the recursion, which is the height of the binary tree denoted as H.
    // In the balanced case it is log2N and in the worst case its N.

    // Recursive function to find the maximum path sum
    // for a given subtree rooted at 'root'
    // The variable 'maxi' is a reference parameter
    // updated to store the maximum path sum encountered
    static int findMaxPathSum(TreeNode root, int[] maxi) {
        // Base case: If the current node is null, return 0
        if (root == null) {
            return 0;
        }

        // Calculate the maximum path sum
        // for the left and right subtrees
        int leftMaxPath = Math.max(0, findMaxPathSum(root.left, maxi));
        int rightMaxPath = Math.max(0, findMaxPathSum(root.right, maxi));

        // Update the overall maximum
        // path sum including the current node
        maxi[0] = Math.max(maxi[0], leftMaxPath + rightMaxPath + root.val);

        // Return the maximum sum considering
        // only one branch (either left or right)
        // along with the current node
        return Math.max(leftMaxPath, rightMaxPath) + root.val;
    }

    // Function to find the maximum
    // path sum for the entire binary tree
    static int maxPathSum(TreeNode root) {
        // Initialize maxi to the
        // minimum possible integer value
        int[] maxi = {Integer.MIN_VALUE};

        // Call the recursive function to
        // find the maximum path sum
        findMaxPathSum(root, maxi);

        // Return the final maximum path sum
        return maxi[0];
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        // Finding and printing the maximum path sum
        int maxPathSum = maxPathSum(root);
        System.out.println("Maximum Path Sum: " + maxPathSum);
    }
}

// Output : Maximum Path Sum: 24

// Approach / Intuition : Optimal Solution
/*
To find the diameter of a binary tree, we can think of every node as a potential `Curving Point`
of the path along which we find the sum. The maximum sum of a path through a turning point
(like a curve) can be found by adding the maximum sum achievable in the left subtree, the
right subtree, and the value of the turning point.

We can recursively traverse the tree, considering each node as a potential turning point
and storing the maximum value (our final answer) in a reference variable. The recursive
function should be defined in such a way that we consider both the possibilities:

1. When the current node is the turning point and in this scenario we calculate the
maximum path sum taking into sum contributions from both the left and right subtrees
along with the value of the current node.
2. When the current node is not the turning point, we consider only the left or the
right subtree. The maximum of the two is returned as the maximum path sum of that subtree.

Base Case: When the current node is null which indicates the end of a path or a lead node,
we return the maximum path sum as 0.

Recursive Function:

1. Calculate the maximum path sum for the left and right subtrees by making recursive
calls to the left and right child of the current node.
2. Calculate the maximum path sum when the current node serves as the turning point:
Maximum Path Sum when Current Node is :
Turning Point = Maximum Path Sum of Left Subtree + Maximum Path Sum of Right Subtree + Current Value of Node
3. Update the overall maximum path sum (maxi) by considering the sum of the current node
 and the left and right subtree sums.
4. Return the maximum sum considering only one branch (either left or right) along with
the value of the current node as the maximum sum up until this node.

Algorithm:

Step 1: Initialise the variable maxi to the minimum possible integer value. This ensures
that the algorithm correctly updates maxi with the first encountered valid path sum
(even if its negative) and subsequently updates it whenever a larger path sum is found.

Step 2: Call the recursive function `findMaxPathSum` with the root of the binary tree and
the reference parameter maxi.

Step 3: Base case: If the current node is null, return 0.

Step 4: Calculate the maximum path sum for the left and right subtree using recursion.

Step 5: Update the overall maximum path sum (maxi) by considering the sum of the left and
right subtree paths plus the current node's value. This represents the sum of the path that
includes the current node. This sum is used to update the overall maximum path sum (maxi)
when the current node serves as the turning point in the path.

Step 6: Return the maximum sum considering only one branch (either left or right) along
with the current node. This represents the maximum sum considering only one branch
(either left or right) along with the current node. This value is returned by the recursive
function to contribute to the calculation of the maximum path sum in the binary tree.
Case Considering Negative Leaf Nodes:
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=WszrfSwMz58