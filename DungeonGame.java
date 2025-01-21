class Solution {
    private int getVal(int[][] mat, int[][] dp, int i, int j) {
        int n = mat.length;
        int m = mat[0].length;
        if (i == n || j == m) {
            return (int) 1e9;
        }
        if (i == n - 1 && j == m - 1) {
            return (mat[i][j] <= 0) ? -mat[i][j] + 1 : 1;
        }
        if (dp[i][j] != (int) 1e9) {
            return dp[i][j];
        }
        int IfWeGoRight = getVal(mat, dp, i, j + 1);
        int IfWeGoDown = getVal(mat, dp, i + 1, j);
        int minHealthRequired = Math.min(IfWeGoRight, IfWeGoDown) - mat[i][j];
        dp[i][j] = (minHealthRequired <= 0) ? 1 : minHealthRequired;
        return dp[i][j];
    }
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, (int) 1e9);
        }
        return getVal(dungeon, dp, 0, 0);
    }
}
