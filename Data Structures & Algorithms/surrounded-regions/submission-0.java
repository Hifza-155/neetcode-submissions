
public class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // STEP 1: Find all 'O's on the borders and load them into the queue
        
        // Left and Right Columns
        for (int r = 0; r < rows; r++) {
            if (board[r][0] == 'O') {
                visited[r][0] = true;
                queue.offer(new int[]{r, 0});
            }
            if (board[r][cols - 1] == 'O') {
                visited[r][cols - 1] = true;
                queue.offer(new int[]{r, cols - 1});
            }
        }

        // Top and Bottom Rows (skip corners since they were checked above)
        for (int c = 1; c < cols - 1; c++) {
            if (board[0][c] == 'O') {
                visited[0][c] = true;
                queue.offer(new int[]{0, c});
            }
            if (board[rows - 1][c] == 'O') {
                visited[rows - 1][c] = true;
                queue.offer(new int[]{rows - 1, c});
            }
        }

        // STEP 2: Run BFS to mark all edge-connected 'O's as visited (safe)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];

            for (int[] dir : directions) {
                int nextRow = currRow + dir[0];
                int nextCol = currCol + dir[1];

                // Check grid boundaries
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    // Only step onto unvisited 'O' cells
                    if (board[nextRow][nextCol] == 'O' && !visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = true;
                        queue.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
        }

        // STEP 3: Flip all unvisited 'O's to 'X'
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O' && !visited[r][c]) {
                    board[r][c] = 'X';
                }
            }
        }
    }
}