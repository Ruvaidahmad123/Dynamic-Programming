class Solution {
    public int countSquares(int[][] arr) {
        int n=arr[0].length;
        int m=arr.length;
        int dp[][]=new int[m][n];
        for (int j = 0; j < n; j++) dp[0][j] = arr[0][j];
        for (int i = 0; i < m; i++) dp[i][0] = arr[i][0];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (arr[i][j] == 0) dp[i][j] = 0;
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += dp[i][j];
            }
        }
        return sum;
    }
}
