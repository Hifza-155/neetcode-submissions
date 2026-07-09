
public class Solution {
    public void islandsAndTreasure(int[][] grid) {
        // Edge case: If the grid is empty, there is nothing to process
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        // STEP 1: Find all treasure chests (0) and add them to the queue simultaneously
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    queue.offer(new int[]{r, c});
                }
            }
        }

        // Direction arrays to easily check Up, Down, Left, and Right neighbors
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // STEP 2: Run the Multi-Source BFS Engine
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];

            // Check all 4 immediate neighbors
            for (int[] dir : directions) {
                int nextRow = currRow + dir[0];
                int nextCol = currCol + dir[1];

                // Check bounds limits
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    // Update Condition: Only step into unvisited land cells (INF)
                    if (grid[nextRow][nextCol] == Integer.MAX_VALUE) {
                        
                        // Set distance to: Current cell's distance + 1
                        grid[nextRow][nextCol] = grid[currRow][currCol] + 1;
                        
                        // Push the neighbor into the queue to continue the ripple
                        queue.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
        }
    }
}