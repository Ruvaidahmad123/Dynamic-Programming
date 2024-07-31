//recursive
class Solution {
    public int solve(int i,int j,List<List<Integer>> triangle,int n){
        if(i==n-1){
            return triangle.get(i).get(j);
        }
        // if(j>triangle.get(n).size()){
        //     return Integer.MAX_VALUE;  //not required because everytime we move from 0,0 there will be element present diagonally
        // }
        int down=triangle.get(i).get(j)+solve(i+1,j,triangle,n);
        int diagonal=triangle.get(i).get(j)+solve(i+1,j+1,triangle,n);
        return Math.min(down,diagonal);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        return solve(0,0,triangle,n);
    }
}
//memoization
class Solution {
    public int solve(int i,int j,List<List<Integer>> triangle,int n,int dp[][]){
        if(i==n-1){
            return triangle.get(i).get(j);
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int down=triangle.get(i).get(j)+solve(i+1,j,triangle,n,dp);
        int diagonal=triangle.get(i).get(j)+solve(i+1,j+1,triangle,n,dp);
        return dp[i][j]=Math.min(down,diagonal);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        int dp[][]=new int[n][n];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return solve(0,0,triangle,n,dp);
    }
}
//tabulation
static int minimumPathSum(int[][] triangle, int n) {
        // Create a 2D array to store intermediate results
        int dp[][] = new int[n][n];
        // Initialize the bottom row of dp with the values from the bottom row of the triangle
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n - 1][j];
        }
        // Starting from the second to last row, calculate the minimum path sum for each element
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Calculate the two possible paths: moving down or moving diagonally
                int down = triangle[i][j] + dp[i + 1][j];
                int diagonal = triangle[i][j] + dp[i + 1][j + 1];

                // Store the minimum of the two paths in dp
                dp[i][j] = Math.min(down, diagonal);
            }
        }
        // The result is stored at the top of dp array
        return dp[0][0];
    }
//space optimized
// thers's need of previous row so obviously it can be optimized
static int minimumPathSum(int[][] triangle, int n) {
        // Create two arrays to store intermediate results: front and cur
        int[] front = new int[n]; // Stores the results for the current row
        int[] cur = new int[n];   // Stores the results for the next row
        // Initialize the front array with the values from the bottom row of the triangle
        for (int j = 0; j < n; j++) {
            front[j] = triangle[n - 1][j];
        }
        // Starting from the second to last row, calculate the minimum path sum for each element
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Calculate the two possible paths: moving down or moving diagonally
                int down = triangle[i][j] + front[j];
                int diagonal = triangle[i][j] + front[j + 1];

                // Store the minimum of the two paths in the cur array
                cur[j] = Math.min(down, diagonal);
            }
            
            // Update the front array with the values from the cur array for the next row
            front = cur.clone();
        }
        // The result is stored at the top of the front array
        return front[0];
    }
