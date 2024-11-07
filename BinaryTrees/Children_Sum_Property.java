package BinaryTrees;

public class Children_Sum_Property {

    // Definition for a binary tree node.
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

    // Method 1 : Optimal Solution

    // Time Complexity: O(N) where N is the number of nodes in the binary tree.
    // This is because the algorithm traverses each node exactly once, performing
    // constant-time operations at each node.

    // Space Complexity: O(N) where N is the number of nodes in the Binary Tree.

    // In the worst case scenario the tree is skewed and the auxiliary recursion
    // stack space would be stacked up to the maximum height of the tree, resulting
    // in a space complexity of O(N).
    // In the optimal case of a balanced tree, the auxiliary space would take up
    // space proportional to O(log2N).

    // Function to change the values of the nodes
    // based on the sum of its children's values.
    public static void changeTree(TreeNode root) {
        // Base case: If the current node
        // is null, return and do nothing.
        if (root == null) {
            return;
        }

        // Calculate the sum of the values of
        // the left and right children, if they exist.
        int child = 0;
        if (root.left != null) {
            child += root.left.val;
        }
        if (root.right != null) {
            child += root.right.val;
        }

        // Compare the sum of children with
        // the current node's value and update
        if (child >= root.val) {
            root.val = child;
        } else {
            // If the sum is smaller, update the
            // child with the current node's value.
            if (root.left != null) {
                root.left.val = root.val;
            } else if (root.right != null) {
                root.right.val = root.val;
            }
        }

        // Recursively call the function
        // on the left and right children.
        changeTree(root.left);
        changeTree(root.right);

        // Calculate the total sum of the
        // values of the left and right
        // children, if they exist.
        int tot = 0;
        if (root.left != null) {
            tot += root.left.val;
        }
        if (root.right != null) {
            tot += root.right.val;
        }

        // If either left or right child
        // exists, update the current node's
        // value with the total sum.
        if (root.left != null || root.right != null) {
            root.val = tot;
        }
    }

    // Function to print the inorder
    // traversal of the tree
    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    // Main Function
    public static void main(String[] args) {
        // Create the binary tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Print the inorder traversal
        // of tree before modification
        System.out.print("Binary Tree before modification: ");
        inorderTraversal(root);
        System.out.println();

        // Call the changeTree function
        // to modify the binary tree
        changeTree(root);

        // Print the inorder traversal
        // after modification
        System.out.print("Binary Tree after Children Sum Property: ");
        inorderTraversal(root);
        System.out.println();
    }
}

// Output :
// Binary Tree before modification: 6 5 7 2 4 3 0 1 8,
// Binary Tree after Children Sum Property: 6 17 7 11 4 25 0 8 8

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
The constraint is that we cannot decrease the value of any node, only increase it.
Also, the structure of the binary tree cannot be changed. If we follow a bottom-up
approach and try to adjust parent values based on children, we may reach a situation
where the sum of children exceeds the parent's value, requiring us to decrease the
parent's value, which is not allowed.

With just a bottom-up approach, we cannot guarantee that the Children Sum Property
will be satisfied at each level. It might work for some cases but not for all.
There's no clear strategy to ensure that the property holds true for the entire tree.
A key insight here is that there's no restriction on how much we can increase the value
of each node. Hence, we have the flexibility to adjust the values as needed to ensure
that the Children Sum Property holds true at every node in the tree.

This means that if the sum of the values of a node's children is less than the node's
value, we can simply increase the values of the children (and potentially the
grandchildren and so on) until the property is satisfied. Using recursive calls,
we traverse the binary tree, addressing each node and its children iteratively.
At each step, we calculate the sum of the children's values and compare it with
the parent node's value.

Algorithm:

Step 1: Base CaseStart by checking if we've reached the end of a branch in the tree.
If the current node is null, simply return.

Step 2: Calculate Children Sum: For each non-null node, calculate the sum of the
values of its left and right children, if they exist. Add up the values of the left
and right children (if they are not null) and store this sum in a variable called child.

Step 3: Comparison and Value Update: Compare the sum of the children (child)
with the current node's value.

    1. If the sum of children is greater than or equal to the current node's value, we
       update the value of the parent to the sum of the children.

    2. If the sum of children is smaller than the current node's value, we need to make
    an adjustment to ensure the property holds. However, remember that we cannot decrease
    the value of any node. So, instead, we update one of the children's values to match
    the current node's value.

Step 4: Recursive Calls: For each node in the current level: After handling the current
node, we recursively call the function on the left and right children of the current node.

Step 5: Update Current Node's Value: Once both children have been processed, we
recalculate the total sum of the values of the left and right children and update
the current node’s value to match the total sum of its children.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=fnmisPM6cVo
