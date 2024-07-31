//recursive
class Solution {
    public int solve(int i,int j,int row,int col){
        if(i==0 && j==0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        int left=solve(i,j-1,row,col);    //not 1+solve() because because we are trying to find out the number of ways we can travel and not steps required to reach the destination.
        int up=solve(i-1,j,row,col);
        return left+up;
    }
    public int uniquePaths(int m, int n) {
        return solve(m-1,n-1,m,n);
    }
}
//memoization
class Solution {
    public int solve(int i,int j,int row,int col,int dp[][]){
        if(i==0 && j==0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int left=solve(i,j-1,row,col,dp);
        int up=solve(i-1,j,row,col,dp);
        return dp[i][j]=left+up;
    }
    public int uniquePaths(int m, int n) {
        int dp[][]=new int[m][n];
        for(int row[]:dp){
            Arrays.fill(row,-1);
        }
        return solve(m-1,n-1,m,n,dp);
    }
}
//MEMOIZATION TO TABULATION---->
step 1.declare base case
step 2.express all state in for loops
step 3.copy recurrence relation and write
//tabulation
static int countWaysUtil(int m, int n, int[][] dp) {
        // Loop through each cell in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Base condition: If we are at the top-left cell (0, 0), there's one way to reach it.
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                // Calculate the number of ways by moving up (if possible) and left (if possible)
                if (i > 0)   //for i-1 case
                    up = dp[i - 1][j];
                if (j > 0)   //for j-1 case
                    left = dp[i][j - 1];
                // Store the total number of ways to reach the current cell in the DP array
                dp[i][j] = up + left;
            }
        }
        // Return the number of ways to reach the bottom-right cell (m-1, n-1)
        return dp[m - 1][n - 1];
    }
//space optimized
//if previous row or previous col are used for finding current answer we can space optimized it
static int countWays(int m, int n) {
        // Create an array to store the results for the previous row
        int prev[] = new int[n];
        for (int i = 0; i < m; i++) {
            // Create a temporary array to store the results for the current row
            int temp[] = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    // Base condition: There's one way to reach the top-left cell (0, 0)
                    temp[j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                // Calculate the number of ways by moving up (if possible) and left (if possible)
                if (i > 0)
                    up = prev[j];
                if (j > 0)
                    left = temp[j - 1];
                // Store the total number of ways to reach the current cell in the temporary array
                temp[j] = up + left;
            }
            // Set the temporary array as the previous array for the next row
            prev = temp;
        }
        // Return the number of ways to reach the bottom-right cell (m-1, n-1)
        return prev[n - 1];
    }
