package Graph;

import java.util.Scanner;

public class Representation_of_Graph_AdjacencyList {

    // Adjacency List
    // Time complexity: O(2*E)
    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();
            int m = sc.nextInt();

            // adjacency matrix for undirected graph
            // time complexity: O(n)
            int[][] adj = new int[n + 1][n + 1];

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj[u][v] = 1;
                adj[v][u] = 1; // remove this line in case of a directed graph
            }

            sc.close();
        }
    }
}
