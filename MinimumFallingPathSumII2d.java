//recursive
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int minPathSum = Integer.MAX_VALUE;

        // Start recursion from each column of the first row
        for (int j = 0; j < n; j++) {
            int pathSum = findMinPath(matrix, 0, j, n);
            minPathSum = Math.min(minPathSum, pathSum);
        }

        return minPathSum;
    }

    private int findMinPath(int[][] matrix, int i, int j, int n) {
        // Base case: If we've reached the last row, return the value
        if (i == n - 1) {
            return matrix[i][j];
        }

        // Recursive case: Calculate minimum path sum recursively for each possible next step
        int minPathSum = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
            if (k != j) { // Ensure non-zero shift condition
                int pathSum = matrix[i][j] + findMinPath(matrix, i + 1, k, n);
                minPathSum = Math.min(minPathSum, pathSum);
            }
        }

        return minPathSum;
    }
}
//memoized
class Solution {
    public int solve(int i, int j, int[][] matrix, int n, int[][] dp) {
        if (i==n) {
            return 0;
        }
        if (j < 0 || j >= n) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }    
        int minPath = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
            if (k != j) { // Ensuring non-zero shift
                minPath = Math.min(minPath, solve(i + 1, k, matrix, n, dp));
            }
        }
        dp[i][j] = matrix[i][j] + (minPath == Integer.MAX_VALUE ? 0 : minPath);
        return dp[i][j];
    }
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int minPathSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minPathSum = Math.min(minPathSum, solve(0, j, matrix, n, dp));
        }
        return minPathSum;
    }
}
