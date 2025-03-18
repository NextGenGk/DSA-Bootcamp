package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Number_of_Provinces {

    // Function to convert adjacency matrix to adjacency list and count provinces using DFS
    static int numProvinces(ArrayList<ArrayList<Integer>> adjMatrix, int V) {
        // Step 1: Convert Adjacency Matrix to Adjacency List
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        // Initialize adjacency list with empty lists
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Fill adjacency list by checking connections in the matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                // If there is a connection (1) and it's not a self-loop, add edge
                if (adjMatrix.get(i).get(j) == 1 && i != j) {
                    adjList.get(i).add(j);
                }
            }
        }

        // Step 2: Perform DFS to count number of provinces
        boolean[] isVisited = new boolean[V]; // Track visited nodes
        int countProvinces = 0; // Count of provinces

        // Traverse all nodes and perform DFS on unvisited nodes
        for (int i = 0; i < V; i++) {
            if (!isVisited[i]) { // If a node is not visited, it's a new province
                dfs(i, adjList, isVisited);
                countProvinces++; // Increase province count after a complete DFS
            }
        }
        return countProvinces;
    }

    // Method 1 : Optimal Solution (Using DFS Traversal)
    // Time Complexity: O(N) + O(V+2E), Where O(N) is for outer loop and inner loop
    // runs in total a single DFS over entire graph, and we know DFS takes a time of O(V+2E).
    // Space Complexity: O(N) + O(N),Space for recursion stack space and visited array.
    // Function to count provinces using DFS
    // DFS function to traverse the graph
    static void dfs(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] isVisited) {
        isVisited[node] = true; // Mark node as visited

        // Visit all connected (neighbor) nodes recursively
        for (Integer neighbor : adjList.get(node)) {
            if (!isVisited[neighbor]) {
                dfs(neighbor, adjList, isVisited);
            }
        }
    }

    // Method 1 : Optimal Solution (Using BFS Traversal)
    // Time Complexity: O(N) + O(V+2E), Where O(N) is for outer loop and inner loop
    // runs in total a single BFS over entire graph, and we know BFS takes a time of O(V+2E).
    // Space Complexity: O(N) + O(N), Space for queue data structure and visited array.
    // Function to count provinces using BFS
    // BFS function to traverse the graph
    static void bfs(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] isVisited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node); // Start BFS from given node
        isVisited[node] = true; // Mark as visited

        // Process nodes in BFS order
        while (!queue.isEmpty()) {
            int start = queue.poll(); // Get front node from queue
            for (Integer it : adjList.get(start)) { // Visit all neighbors
                if (!isVisited[it]) {
                    isVisited[it] = true; // Mark as visited
                    queue.add(it); // Add to queue for further processing
                }
            }
        }
    }

    // Main function to test the program
    public static void main(String[] args) {
        // Example adjacency matrix representing the graph
        ArrayList<ArrayList<Integer>> adjMatrix = new ArrayList<>();
        adjMatrix.add(new ArrayList<>(Arrays.asList(1, 0, 1))); // Node 0 is connected to 2
        adjMatrix.add(new ArrayList<>(Arrays.asList(0, 1, 0))); // Node 1 is isolated
        adjMatrix.add(new ArrayList<>(Arrays.asList(1, 0, 1))); // Node 2 is connected to 0

        int V = adjMatrix.size(); // Number of nodes

        // Compute the number of provinces using DFS
        System.out.println("Number of Provinces (DFS): " + numProvinces(adjMatrix, V));
        System.out.println("Number of Provinces (BFS): " + numProvinces(adjMatrix, V));
    }

}

// Output :
// Number of Provinces (DFS): 2
// Number of Provinces (BFS): 2

// Approach :
/*
Approach:
A province is a group of directly or indirectly connected cities and no other cities outside
of the group. Considering the above example, we can go from 1 to 2 as well as to 3, from every
other node in a province we can go to each other. As we cannot go from 2 to 4 so it is not a
province. We know about both the traversals, Breadth First Search (BFS) and Depth First Search (DFS).
We can use any of the traversals to solve this problem because a traversal algorithm visits all the
nodes in a graph. In any traversal technique, we have one starting node and it traverses all the
nodes in the graph. Suppose there is an ‘N’ number of provinces so we need to call the traversal
algorithm ‘N‘ times, i.e., there will be ‘N’ starting nodes. So, we just need to figure out the
number of starting nodes.

The algorithm steps are as follows:

1. We need a visited array initialized to 0, representing the nodes that are not visited.
2. Run the for loop looping from 0 to N, and call the DFS for the first unvisited node.
3. DFS function call will make sure that it starts the DFS call from that unvisited node,
   and visits all the nodes that are in that province, and at the same time, it will also mark them as visited.
4. Since the nodes traveled in a traversal will be marked as visited, they will no further be
   called for any further DFS traversal.
5. Keep repeating these steps, for every node that you find unvisited, and visit the entire province.
6. Add a counter variable to count the number of times the DFS function is called, as in this
   way we can count the total number of starting nodes, which will give us the number of provinces.

Algorithm : Using DFS Traversal
1. Convert the adjacency matrix to an adjacency list.
2. Initialize a visited array to track visited nodes and a count of provinces.
3. Traverse all nodes and perform DFS on unvisited nodes.
4. In the DFS function, mark the node as visited and visit all connected nodes recursively.
5. After a complete DFS, increase the province count.
6. Return the count of provinces.

Algorithm : Using BFS Traversal
1. Convert the adjacency matrix to an adjacency list.
2. Initialize a visited array to track visited nodes and a count of provinces.
3. Traverse all nodes and perform BFS on unvisited nodes.
4. In the BFS function, mark the node as visited and visit all connected nodes using a queue.
5. After a complete BFS, increase the province count.
6. Return the count of provinces.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=ACzkVtewUYA