
public class Solution {
    public int orangesRotting(int[][] grid) {
        // Edge case check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        int freshFruitCount = 0;
        int minutes = 0;

        // STEP 1: Scan the entire grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    // Push initial rotten fruits into the queue (Layer 0)
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    // Count how many fresh fruits we need to save/infect
                    freshFruitCount++;
                }
            }
        }

        // If there are no fresh fruits to begin with, 0 minutes are needed
        if (freshFruitCount == 0) {
            return 0;
        }

        // Direction vectors for moving Up, Down, Left, and Right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // STEP 2: The Multi-Source BFS Engine
        // Keep running as long as there is rot to spread AND fresh fruit left to infect
        while (!queue.isEmpty() && freshFruitCount > 0) {
            // Take the snapshot barrier of the current layer's size
            int size = queue.size();
            boolean infectedAny = false;

            // STEP 3: Process the current layer (everything happening in this 1 minute)
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int currRow = current[0];
                int currCol = current[1];

                // Check all 4 neighbors
                for (int[] dir : directions) {
                    int nextRow = currRow + dir[0];
                    int nextCol = currCol + dir[1];

                    // Check boundaries
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                        // If neighbor is a fresh fruit, infect it!
                        if (grid[nextRow][nextCol] == 1) {
                            grid[nextRow][nextCol] = 2; // Turn it rotten
                            freshFruitCount--;         // One less fresh fruit remaining
                            queue.offer(new int[]{nextRow, nextCol}); // Add to queue for next minute
                            infectedAny = true;        // Mark that rot successfully spread
                        }
                    }
                }
            }

            // STEP 4: Tick the clock if the wave actually expanded
            if (infectedAny) {
                minutes++;
            }
        }

        // STEP 5: Final verification
        // If fresh fruits are still left, it means they were isolated. Return -1.
        return freshFruitCount == 0 ? minutes : -1;
    }
}