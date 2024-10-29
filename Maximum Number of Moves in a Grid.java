class Solution {
    public int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); 
        }
        
        int maxMoves = 0;
        for (int i = 0; i < m; i++) {
            maxMoves = Math.max(maxMoves, dfs(i, 0, grid, memo));
        }
        return maxMoves;
    }
    private int dfs(int i, int j, int[][] grid, int[][] memo) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j >= n - 1) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int maxSteps = 0;
        int currentValue = grid[i][j];
        if (i > 0 && j + 1 < n && grid[i - 1][j + 1] > currentValue) {
            maxSteps = Math.max(maxSteps, 1 + dfs(i - 1, j + 1, grid, memo));
        }
        if (j + 1 < n && grid[i][j + 1] > currentValue) {
            maxSteps = Math.max(maxSteps, 1 + dfs(i, j + 1, grid, memo));
        }
        if (i + 1 < m && j + 1 < n && grid[i + 1][j + 1] > currentValue) {
            maxSteps = Math.max(maxSteps, 1 + dfs(i + 1, j + 1, grid, memo));
        }
        
        memo[i][j] = maxSteps;
        return maxSteps;
    }
}
