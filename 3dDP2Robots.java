//recursive
class Solution {
    public int Solve(int i,int j1,int j2,int grid[][],int n,int m,int dp[][][]){
    if(j1<0 || j2<0 || j1>=m || j2>=m){
        return (int)-1e8;
    }
    if(i==n-1){
        if(j1==j2){
            return grid[i][j1];
        }
        else{
            return grid[i][j1]+grid[i][j2];
        }
    }
    if(dp[i][j1][j2]!=-1){
        return dp[i][j1][j2];
    }
    int maxi=Integer.MIN_VALUE;
    for(int dj1=-1;dj1<=1;dj1++){
        for(int dj2=-1;dj2<=1;dj2++){
            int ans=0;
            if(j1==j2){
                ans=Solve(i+1,j1+dj1,j2+dj2,grid,n,m,dp)+grid[i][j2];
            }
            else{
                ans=Solve(i+1,j1+dj1,j2+dj2,grid,n,m,dp)+grid[i][j2]+grid[i][j1];
            }
            maxi=Math.max(maxi,ans);
        }
    }
    return dp[i][j1][j2]=maxi;
}
    public int solve(int n, int m, int grid[][]) {
        int dp[][][]=new int[n][m][m];
        for (int row1[][] : dp) {
            for (int row2[] : row1) {
                Arrays.fill(row2, -1);
            }
        }
        return Solve(0,0,m-1,grid,n,m,dp);
    }
}
//memoized
class Solution {
    public int Solve(int i,int j1,int j2,int grid[][],int n,int m,int dp[][][]){
    if(j1<0 || j2<0 || j1>=m || j2>=m){
        return (int)-1e8;
    }
    if(i==n-1){
        if(j1==j2){
            return grid[i][j1];
        }
        else{
            return grid[i][j1]+grid[i][j2];
        }
    }
    if(dp[i][j1][j2]!=-1){
        return dp[i][j1][j2];
    }
    int maxi=Integer.MIN_VALUE;
    for(int dj1=-1;dj1<=1;dj1++){
        for(int dj2=-1;dj2<=1;dj2++){
            int ans=0;
            if(j1==j2){
                ans=Solve(i+1,j1+dj1,j2+dj2,grid,n,m,dp)+grid[i][j2];
            }
            else{
                ans=Solve(i+1,j1+dj1,j2+dj2,grid,n,m,dp)+grid[i][j2]+grid[i][j1];
            }
            maxi=Math.max(maxi,ans);
        }
    }
    return dp[i][j1][j2]=maxi;
}
    public int solve(int n, int m, int grid[][]) {
        int dp[][][]=new int[n][m][m];
        for (int row1[][] : dp) {
            for (int row2[] : row1) {
                Arrays.fill(row2, -1);
            }
        }
        return Solve(0,0,m-1,grid,n,m,dp);
    }
}
//tabulation
//if there are 3 states i,j1,j2 3 loops will be made
static int maximumChocolates(int n, int m, int[][] grid) {
    // Create a 3D array to store computed results
    int dp[][][] = new int[n][m][m];

    // Initialize the dp array with values from the last row of the grid
    for (int j1 = 0; j1 < m; j1++) {
      for (int j2 = 0; j2 < m; j2++) {
        if (j1 == j2)
          dp[n - 1][j1][j2] = grid[n - 1][j1];
        else
          dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
      }
    }

    // Outer nested loops to traverse the DP array from the second last row to the first row
    for (int i = n - 2; i >= 0; i--) {
      for (int j1 = 0; j1 < m; j1++) {
        for (int j2 = 0; j2 < m; j2++) {
          int maxi = Integer.MIN_VALUE;

          // Inner nested loops to try out 9 options
          for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
              int ans;

              if (j1 == j2)
                ans = grid[i][j1];
              else
                ans = grid[i][j1] + grid[i][j2];

              // Check if the indices are valid
              if ((j1 + di < 0 || j1 + di >= m) || (j2 + dj < 0 || j2 + dj >= m))
                ans += (int) Math.pow(-10, 9);
              else
                ans += dp[i + 1][j1 + di][j2 + dj];

              // Update maxi with the maximum result
              maxi = Math.max(ans, maxi);
            }
          }
          // Store the result in the dp array
          dp[i][j1][j2] = maxi;
        }
      }
    }

    // The final result is stored at the top row (first row) of the dp array
    return dp[0][0][m - 1];
  }
//space optimized
static int maximumChocolates(int n, int m, int[][] grid) {
    // Create two 2D arrays to store computed results: front and cur
    int[][] front = new int[m][m];
    int[][] cur = new int[m][m];
    // Initialize the front array with values from the last row of the grid
    for (int j1 = 0; j1 < m; j1++) {
      for (int j2 = 0; j2 < m; j2++) {
        if (j1 == j2)
          front[j1][j2] = grid[n - 1][j1];
        else
          front[j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
      }
    }
    // Outer nested loops to traverse the DP array from the second last row to the first row
    for (int i = n - 2; i >= 0; i--) {
      for (int j1 = 0; j1 < m; j1++) {
        for (int j2 = 0; j2 < m; j2++) {
          int maxi = Integer.MIN_VALUE;
          // Inner nested loops to try out 9 options
          for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
              int ans;
              if (j1 == j2)
                ans = grid[i][j1];
              else
                ans = grid[i][j1] + grid[i][j2];
              // Check if the indices are valid
              if ((j1 + di < 0 || j1 + di >= m) || (j2 + dj < 0 || j2 + dj >= m))
                ans += (int) Math.pow(-10, 9);
              else
                ans += front[j1 + di][j2 + dj];

              // Update maxi with the maximum result
              maxi = Math.max(ans, maxi);
            }
          }
          // Store the result in the cur array
          cur[j1][j2] = maxi;
        }
      }
      // Update the front array with the values from the cur array for the next row
      for (int a = 0; a < m; a++) {
        front[a] = cur[a].clone();
      }
    }
    // The final result is stored at the top left corner of the front array
    return front[0][m - 1];
  }
