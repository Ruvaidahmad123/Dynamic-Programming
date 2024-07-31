//memoized code
class Solution {
    public int solve(int i,int j,int row,int col,int dp[][],int obstacleGrid[][]){
        if(i==0 && j==0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        if(obstacleGrid[i][j]==1){
            return 0;                   //only new added condition because we can't go to the row and col which has obtacle
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int left=solve(i,j-1,row,col,dp,obstacleGrid);
        int up=solve(i-1,j,row,col,dp,obstacleGrid);
        return dp[i][j]=left+up;
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1){
            return 0;
        }
        int dp[][]=new int[m][n];
        for(int row[]:dp){
            Arrays.fill(row,-1);
        }
        return solve(m-1,n-1,m,n,dp,obstacleGrid);
    }
}
