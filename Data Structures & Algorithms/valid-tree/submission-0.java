
class Solution {
    public boolean validTree(int n, int[][] edges) {
        // Condition 1: A valid tree with N nodes MUST have exactly N - 1 edges
        if (edges.length != n - 1) {
            return false;
        }

        // Step 1: Create an array of Lists (Memory-efficient Adjacency List)
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Step 2: Build undirected graph (add reciprocal connections)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        boolean[] visited = new boolean[n];

        // Step 3: Run DFS starting from node 0 (parent = -1 since it has no parent)
        if (hasCycle(0, -1, adj, visited)) {
            return false; // Cycle detected
        }

        // Condition 2: Ensure all nodes are connected (no isolated components)
        for (boolean isVisited : visited) {
            if (!isVisited) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(int curr, int parent, List<Integer>[] adj, boolean[] visited) {
        visited[curr] = true;

        for (int neighbor : adj[curr]) {
            // Traversal Context: Skip the edge we literally just walked from
            if (neighbor == parent) {
                continue;
            }

            // If neighbor is already visited (and not our parent), we found a cycle!
            if (visited[neighbor]) {
                return true;
            }

            // Recursive call passing 'curr' as the parent for the neighbor
            if (hasCycle(neighbor, curr, adj, visited)) {
                return true;
            }
        }

        return false;
    }
}