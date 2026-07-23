public class Solution {
    private int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Step 1: Build the adjacency list (Course -> Prerequisites)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            // pre[1] is the prerequisite of pre[0]
            adj.get(pre[0]).add(pre[1]);
        }

        // Tracking array: 0 = Unvisited, 1 = Visiting, 2 = Completed
        int[] visited = new int[numCourses];
        
        // Array to store the final topological order
        int[] result = new int[numCourses];
        index = 0; // Tracks position in the result array

        // Step 2: Traverse every independent course
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(i, adj, visited, result)) {
                    return new int[0]; // Cycle found, impossible to complete all courses
                }
            }
        }

        return result;
    }

    private boolean hasCycle(int course, List<List<Integer>> adj, int[] visited, int[] result) {
        // Step A: If on active path -> Cycle detected
        if (visited[course] == 1) {
            return true;
        }
        // If already processed and marked safe -> Skip
        if (visited[course] == 2) {
            return false;
        }

        // Step B: Mark as currently visiting
        visited[course] = 1;

        // Traverse down into prerequisites
        for (int preReq : adj.get(course)) {
            if (hasCycle(preReq, adj, visited, result)) {
                return true;
            }
        }

        // Step C: All prerequisites completed -> Mark safe & collect in order
        visited[course] = 2;
        result[index++] = course; // Place course into the result array
        
        return false;
    }
}