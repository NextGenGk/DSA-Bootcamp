package Graph;

import java.util.ArrayList;

public class Cycle_Detection_Undirected_DFS {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) + O(V+E), Where O(N) is for outer loop and inner loop
    // runs in total a single BFS over entire graph, and we know BFS takes a time of O(V+E).
    // Space Complexity: O(N) + O(N), Space for queue data structure and visited array.
    // Helper class to store a node and its parent during BFS traversal
    // Function to detect cycle using DFS
    static boolean hasCycle(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node] = true;  // Mark the current node as visited

        // Traverse all adjacent nodes
        for (Integer neighbor : adj.get(node)) {
            if (!visited[neighbor]) {  // If the node is not visited, call DFS recursively
                if (hasCycle(neighbor, node, adj, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {  // If visited and not the parent, it's a cycle
                return true;
            }
        }
        return false;
    }

    // Function to check if a cycle exists in an undirected graph
    static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];  // Boolean array to track visited nodes

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {  // If node is not visited, perform DFS
                if (hasCycle(i, -1, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Main Function
    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(1).add(0);

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(2).add(3);
        adj.get(3).add(2);

        adj.get(0).add(2);
        adj.get(2).add(0);

        System.out.println(isCycle(V, adj));
    }
}

// Output:
// true

// Intuition : Optimal Solution
/*
The cycle in a graph starts from a node and ends at the same node. DFS is a traversal technique
that involves the idea of recursion and backtracking. DFS goes in-depth, i.e., traverses all
nodes by going ahead, and when there are no further nodes to traverse in the current path, then
it backtracks on the same path and traverses other unvisited nodes. The intuition is that we
start from a source and go in-depth, and reach any node that has been previously visited in the
past; it means there's a cycle.

Algorithm:
1. Initialize a visited array to track visited nodes.
2. Iterate over all vertices.
3. For each vertex, if it's not visited, perform DFS.
4. If DFS returns true, it means there's a cycle in the graph.
5. If no cycle is detected, return false.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=zQ3zgFypzX4