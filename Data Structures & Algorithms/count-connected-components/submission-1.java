
class Solution {
    public int countComponents(int n, int[][] edges) {
        // Step 1: Build the Adjacency List (Undirected Graph)
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        // Step 2: Track visited nodes and component count
        boolean[] visited = new boolean[n];
        int componentCount = 0;

        // Step 3: Master Loop - step onto each node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                componentCount++;         // Found an unexplored island!
                dfs(i, adj, visited);      // Mark all connected nodes in this island
            }
        }

        return componentCount;
    }

    // Step 4: Simple DFS to mark all reachable nodes in the current component
    private void dfs(int curr, List<Integer>[] adj, boolean[] visited) {
        visited[curr] = true;

        for (int neighbor : adj[curr]) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }
}