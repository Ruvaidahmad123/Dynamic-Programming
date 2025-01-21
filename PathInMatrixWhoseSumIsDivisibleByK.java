class Solution {
    public int solve(int modSum, int i, int j, int[][] grid, int k, int[][][] dp) {
        if (i < 0 || j < 0) return 0;
        if (i == 0 && j == 0) {
            return (modSum + grid[i][j]) % k == 0 ? 1 : 0;
        }
        if (dp[i][j][modSum] != -1) return dp[i][j][modSum];
        int newModSum = (modSum + grid[i][j]) % k;
        int left = solve(newModSum, i, j - 1, grid, k, dp);
        int up = solve(newModSum, i - 1, j, grid, k, dp);
        return dp[i][j][modSum] = (left + up) % 1_000_000_007; 
    }

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][k];
        for (int[][] rows : dp) {
            for (int[] row : rows) {
                Arrays.fill(row, -1);
            }
        }
        return solve(0, m - 1, n - 1, grid, k, dp);
    }
}
