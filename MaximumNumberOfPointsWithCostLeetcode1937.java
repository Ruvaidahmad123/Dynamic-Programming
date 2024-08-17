//recursive
class Solution {
    public long f(int i, int j, int[][] points) {
        if (i == points.length) {
            return 0;
        }
        long maxPoints = Long.MIN_VALUE;
        for (int nextJ = 0; nextJ < points[0].length; nextJ++) {
            long pointsForThisMove = points[i][j] - Math.abs(j - nextJ) + f(i + 1, nextJ, points);
            maxPoints = Math.max(maxPoints, pointsForThisMove);
        }
        return maxPoints;
    }
    public long maxPoints(int[][] points) {
        long maxPoints = Long.MIN_VALUE;
        for (int j = 0; j < points[0].length; j++) {
            maxPoints = Math.max(maxPoints, f(0, j, points));
        }
        return maxPoints;
    }
}
//memoization
class Solution {
    public long f(int i, int j, int[][] points, Long[][] memo) {
        if (i == points.length) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        long maxPoints = Long.MIN_VALUE;
        for (int nextJ = 0; nextJ < points[0].length; nextJ++) {
            long pointsForThisMove = points[i][j] - Math.abs(j - nextJ) + f(i + 1, nextJ, points, memo);
            maxPoints = Math.max(maxPoints, pointsForThisMove);
        }
        memo[i][j] = maxPoints;
        return memo[i][j];
    }
    public long maxPoints(int[][] points) {
        int rows = points.length;
        int cols = points[0].length;
        Long[][] memo = new Long[rows][cols];  
        long maxPoints = Long.MIN_VALUE;
        for (int j = 0; j < cols; j++) {
            maxPoints = Math.max(maxPoints, f(0, j, points, memo));
        }
        return maxPoints;
    }
}
//tabulation
class Solution {
    public long maxPoints(int[][] points) {
        int rows = points.length;
        int cols = points[0].length;
        // DP table to store the maximum points for each cell
        long[][] dp = new long[rows][cols];
        // Initialize the last row of dp with the corresponding points values
        for (int j = 0; j < cols; j++) {
            dp[rows - 1][j] = points[rows - 1][j];
        }
        // Fill the dp table from bottom to top
        for (int i = rows - 2; i >= 0; i--) {
            long[] left = new long[cols];
            long[] right = new long[cols];
            // Fill left array (best from the left)
            left[0] = dp[i + 1][0];
            for (int j = 1; j < cols; j++) {
                left[j] = Math.max(left[j - 1] - 1, dp[i + 1][j]);
            }
            // Fill right array (best from the right)
            right[cols - 1] = dp[i + 1][cols - 1];
            for (int j = cols - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] - 1, dp[i + 1][j]);
            }
            // Calculate the dp values for the current row
            for (int j = 0; j < cols; j++) {
                dp[i][j] = points[i][j] + Math.max(left[j], right[j]);
            }
        }
        // The result is the maximum value in the first row of dp
        long maxPoints = Long.MIN_VALUE;
        for (int j = 0; j < cols; j++) {
            maxPoints = Math.max(maxPoints, dp[0][j]);
        }
        return maxPoints;
    }
}
