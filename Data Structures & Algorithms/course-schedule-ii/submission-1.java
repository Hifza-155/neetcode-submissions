

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Step 1: Create an array of Lists (Memory-efficient Adjacency List)
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        // Step 2: Build the graph (Course -> List of Prerequisites)
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int preReq = pre[1];
            adj[course].add(preReq);
        }

        // Step 3: Track states using visited array
        // 0 = Unvisited, 1 = Visiting (In current recursion stack), 2 = Visited (Safe)
        int[] visited = new int[numCourses];
        
        // Output array and pointer
        int[] result = new int[numCourses];
        int[] index = new int[]{0}; 

        // Step 4: Run DFS for every unvisited course
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(i, adj, visited, result, index)) {
                    return new int[0]; // Return empty array if cycle exists
                }
            }
        }

        return result;
    }

    private boolean hasCycle(int course, List<Integer>[] adj, int[] visited, int[] result, int[] index) {
        // Found a cycle (back edge detected)
        if (visited[course] == 1) return true;
        
        // Already fully processed and safe
        if (visited[course] == 2) return false;

        // Mark as "Visiting"
        visited[course] = 1;

        // Traverse all prerequisites
        for (int preReq : adj[course]) {
            if (hasCycle(preReq, adj, visited, result, index)) {
                return true;
            }
        }

        // Mark as "Visited/Safe"
        visited[course] = 2;
        
        // Topological Sort: Add to output array after all prereqs are done
        result[index[0]++] = course;

        return false;
    }
}