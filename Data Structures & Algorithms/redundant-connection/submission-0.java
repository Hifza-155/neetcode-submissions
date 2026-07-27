class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        
        // 1-indexed adjacency list for nodes 1 to n
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            boolean[] visited = new boolean[n + 1];
            
            // Check if u can already reach v using existing edges
            if (hasPathDFS(u, v, adj, visited)) {
                return edge; // Found the redundant edge!
            }

            // Safe to add
            adj[u].add(v);
            adj[v].add(u);
        }

        return new int[0];
    }

    private boolean hasPathDFS(int curr, int target, List<Integer>[] adj, boolean[] visited) {
        if (curr == target) {
            return true;
        }

        visited[curr] = true;

        for (int neighbor : adj[curr]) {
            if (!visited[neighbor]) {
                if (hasPathDFS(neighbor, target, adj, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}