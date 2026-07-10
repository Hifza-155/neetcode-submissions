
public class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Edge case check
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        // STEP 1: Two independent checklists to permanently track wave reachability
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        // STEP 2: Load Multi-Source Starting Positions from Ocean Borders
        
        // Top and Bottom Borders
        for (int c = 0; c < cols; c++) {
            // Pacific: Top Row (r = 0)
            pacificQueue.offer(new int[]{0, c});
            pacificReachable[0][c] = true;

            // Atlantic: Bottom Row (r = rows - 1)
            atlanticQueue.offer(new int[]{rows - 1, c});
            atlanticReachable[rows - 1][c] = true;
        }

        // Left and Right Borders
        for (int r = 0; r < rows; r++) {
            // Pacific: Left Column (c = 0)
            pacificQueue.offer(new int[]{r, 0});
            pacificReachable[r][0] = true;

            // Atlantic: Right Column (c = cols - 1)
            atlanticQueue.offer(new int[]{r, cols - 1});
            atlanticReachable[r][cols - 1] = true;
        }

        // STEP 3: Run the Uphill BFS for both oceans
        bfs(heights, pacificQueue, pacificReachable);
        bfs(heights, atlanticQueue, atlanticReachable);

        // STEP 4: Intersect the Checklists to find common points
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacificReachable[r][c] && atlanticReachable[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    // Reusable BFS Engine that climbs uphill
    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] reachable) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];

            for (int[] dir : directions) {
                int nextRow = currRow + dir[0];
                int nextCol = currCol + dir[1];

                // Boundary limit check
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    
                    // REVERSED PHYSICS CONDITION: 
                    // 1. Target cell must NOT be visited yet by this wave
                    // 2. Target cell height must be EQUAL OR HIGHER than current cell height
                    if (!reachable[nextRow][nextCol] && heights[nextRow][nextCol] >= heights[currRow][currCol]) {
                        reachable[nextRow][nextCol] = true;
                        queue.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
        }
    }
}