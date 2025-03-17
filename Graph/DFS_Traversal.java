package Graph;

import java.util.ArrayList;

public class DFS_Traversal {

    // Method 1 : Optimal Solution
    // Time Complexity: For an undirected graph, O(N) + O(2E), For a directed graph, O(N) + O(E),
    // Because for every node we are calling the recursive function once, the time taken is O(N) and 2E
    // is for total degrees as we traverse for all adjacent nodes.
    // Space Complexity: O(3N) ~ O(N), Space for dfs stack space, visited array and an adjacency list.
    // Function to return Depth-First Traversal (DFS) of the given graph
    static ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();  // Stores DFS traversal order
        boolean[] visited = new boolean[V];  // Boolean array to track visited nodes

        // Start DFS traversal from node 0
        dfs(0, adj, visited, result);
        return result;
    }

    // Recursive DFS function
    static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> result) {
        visited[node] = true;  // Mark the current node as visited
        result.add(node);  // Add the node to the result list

        // Explore all adjacent nodes
        for (Integer it : adj.get(node)) {
            if (!visited[it]) {  // If the node is not visited, call DFS recursively
                dfs(it, adj, visited, result);
            }
        }
    }

    public static void main(String args[]) {
        // Creating adjacency list for a graph with 5 vertices (0 to 4)
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding edges to the graph (Undirected Graph)
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(2).add(4);
        adj.get(4).add(2);

        // Calling DFS function
        ArrayList<Integer> ans = dfsOfGraph(5, adj);

        // Printing DFS Traversal Order
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }
}

// Output :
// For the given graph, the DFS traversal order is [0, 2, 4, 1, 3]

// Algorithm :
/*
1. Initialize a visited array to track visited nodes.
2. Start DFS from node 0:
    - Mark node 0 as visited.
    - Add node 0 to the result list.
    - Recursively visit all its unvisited neighbors.
3. Recursive DFS Process:
    - For each adjacent node of the current node:
        - If the node is not visited, recursively call DFS on that node.
4. Continue until all reachable nodes from the starting node are visited.
5. Return the DFS traversal order.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=Qzf1a--rhp8