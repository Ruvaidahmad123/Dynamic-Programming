//recursive
class Solution {
    public int solve(int i,int j,int grid[][]){
        if(i==0 && j==0){
            return grid[0][0];
        }
        if(i<0 || j<0){
            return Integer.MAX_VALUE;
        }
        int up=solve(i-1,j,grid);
        int left=solve(i,j-1,grid);
        return Math.min(up,left)+grid[i][j];
    }
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        return solve(m-1,n-1,grid);
    }
}
//memoized
class Solution {
    public int solve(int i,int j,int grid[][],int dp[][]){
        if(i==0 && j==0){
            return grid[0][0];
        }
        if(i<0 || j<0){
            return Integer.MAX_VALUE;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int up=solve(i-1,j,grid,dp);
        int left=solve(i,j-1,grid,dp);
        return dp[i][j]=Math.min(up,left)+grid[i][j];
    }
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int dp[][]=new int[m][n];
        for(int row[]:dp){
            Arrays.fill(row,-1);
        }
        return solve(m-1,n-1,grid,dp);
    }
}
//tabulated
class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int dp[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0){
                    dp[i][j]=grid[0][0];
                    continue;
                }
                int up=0;
                int left=0;
                if(i==0){
                    up=Integer.MAX_VALUE;
                }
                if(j==0){
                    left=Integer.MAX_VALUE;
                }
                if(i>=1){
                    up=dp[i-1][j];
                }
                if(j>=1){
                    left=dp[i][j-1];
                }
                dp[i][j]=Math.min(up,left)+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
//space optimized
static int minSumPath(int n, int m, int[][] matrix) {
        // Initialize an array to store the previous row values
        int prev[] = new int[m];
        for (int i = 0; i < n; i++) {
            // Create a temporary array to store the current row values
            int temp[] = new int[m];
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    temp[j] = matrix[i][j]; // If we're at the top-left cell, the minimum sum is its value
                else {
                    int up = matrix[i][j];
                    if (i > 0)
                        up += prev[j]; // Add the value from above if it's not out of bounds
                    else
                        up += (int) Math.pow(10, 9); // Add a large value if out of bounds in the up direction
                    int left = matrix[i][j];
                    if (j > 0)
                        left += temp[j - 1]; // Add the value from the left if it's not out of bounds
                    else
                        left += (int) Math.pow(10, 9); // Add a large value if out of bounds in the left direction

                    // Store the minimum of the two possible paths in the current cell
                    temp[j] = Math.min(up, left);
                }
            }
            // Update the previous row with the values of the current row
            prev = temp;
        }
        // The final result is stored in the last element of the previous row
        return prev[m - 1];
    }
