//recursive
class Solution {
    public int solve(int i,int j,int matrix[][],int n){
        if(j<0 || j>=n){
            return Integer.MAX_VALUE;
        }
        if(i==n-1){
            return matrix[n-1][j];
        }
        int diagR=solve(i+1,j+1,matrix,n);
        int diagL=solve(i+1,j-1,matrix,n);
        int down=solve(i+1,j,matrix,n);
        int ans=Math.min(diagR,diagL);
        ans=Math.min(ans,down)+matrix[i][j];
        return ans;
    }
    public int minFallingPathSum(int[][] matrix) {
        int n=matrix.length;
        int minPathSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minPathSum = Math.min(minPathSum, solve(0, j, matrix, n));
        }
        return minPathSum;
    }
}
//memoization
class Solution {
    public int solve(int i, int j, int[][] matrix, int n, int[][] dp) {
        if (i == n) {
            return 0;
        }
        if (j < 0 || j >= n) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }    
        int down = solve(i + 1, j, matrix, n, dp);
        int diagL = solve(i + 1, j - 1, matrix, n, dp);
        int diagR = solve(i + 1, j + 1, matrix, n, dp);
        
        dp[i][j] = matrix[i][j] + Math.min(down, Math.min(diagL, diagR));
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

//tabulation
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            dp[0][i]=matrix[0][i];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                int up = matrix[i][j] + dp[i - 1][j];
                int leftDiagonal = matrix[i][j];
                if (j - 1 >= 0) {
                    leftDiagonal += dp[i - 1][j - 1];
                } else {
                    leftDiagonal += (int) Math.pow(10, 9);
                }
                int rightDiagonal = matrix[i][j];
                if (j + 1 < n) {
                    rightDiagonal += dp[i - 1][j + 1];
                } else {
                    rightDiagonal += (int) Math.pow(10, 9);
                }
                // Store the minimum of the three paths in dp
                dp[i][j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }
        }
        int mini = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            mini = Math.min(mini, dp[n - 1][j]);
        }
        return mini;
    }
}
//space optimized
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] prev = new int[n];
        int[] curr = new int[n];
        
        // Initialize the previous row with the first row of the matrix
        for (int j = 0; j < n; j++) {
            prev[j] = matrix[0][j];
        }        
        // Fill in the rest of the rows
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int up = matrix[i][j] + prev[j]; 
                int leftDiagonal = matrix[i][j];
                if (j - 1 >= 0) {
                    leftDiagonal += prev[j - 1];
                } else {
                    leftDiagonal = Integer.MAX_VALUE;
                }     
                int rightDiagonal = matrix[i][j];
                if (j + 1 < n) {
                    rightDiagonal += prev[j + 1];
                } else {
                    rightDiagonal = Integer.MAX_VALUE;
                }        
                // Store the minimum of the three paths in the current row
                curr[j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }
            // Update the previous row to be the current row for the next iteration
            System.arraycopy(curr, 0, prev, 0, n);
        }      
        // Find the minimum value in the last row
        int mini = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            mini = Math.min(mini, prev[j]);
        }
        return mini;
    }
}
