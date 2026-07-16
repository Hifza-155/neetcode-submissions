

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build the adjacency list mapping each course to its prerequisites
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            // pre[1] is the prerequisite of pre[0]
            adj.get(pre[0]).add(pre[1]);
        }

        // Tracking array: 
        // 0 = Unvisited, 1 = Visiting (currently checking branches), 2 = Completed/Visited
        int[] visited = new int[numCourses];

        // Step 2: Iterate through all courses to ensure every independent group is checked
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(i, adj, visited)) {
                    return false; // Found a circle, cannot complete
                }
            }
        }

        return true; // All courses successfully marked as visited
    }

    private boolean hasCycle(int course, List<List<Integer>> adj, int[] visited) {
        // If we hit a node currently being visited, we've found a circle
        if (visited[course] == 1) {
            return true;
        }
        // If it's already fully completed, it's safe
        if (visited[course] == 2) {
            return false;
        }

        // Mark current course as "Visiting" while we go deep down its prerequisites
        visited[course] = 1;

        // Step 3: Go through the adjacency list (prerequisites)
        for (int preReq : adj.get(course)) {
            if (hasCycle(preReq, adj, visited)) {
                return true;
            }
        }

        // All prerequisites completed successfully -> mark this course as fully visited
        visited[course] = 2;
        return false;
    }
}