class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    int currentArea = 1;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[] {r, c});
                    grid[r][c] = -1;

                    while (!stack.isEmpty()) {
                        int[] curr = stack.pop();
                        int currR = curr[0];
                        int currC = curr[1];

                        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
                        for (int[] dir : directions) {
                            int nr = currR + dir[0];
                            int nc = currC + dir[1];
                            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                                && grid[nr][nc] == 1) {
                                grid[nr][nc] = -1;
                                currentArea++;
                                stack.push(new int[] {nr, nc});
                            }
                        }
                    }
                    if (currentArea > maxArea) {
                        maxArea = currentArea;
                    }
                }
            }
        }
        return maxArea;
    }
}