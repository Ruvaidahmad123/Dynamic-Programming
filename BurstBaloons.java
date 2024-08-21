//recursive
class Solution {
    public int f(int i, int j, int arr[]) {
        if (i > j) return 0;
        int maxi = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = arr[i - 1] * arr[ind] * arr[j+1]
                       + f(i, ind - 1, arr) + f(ind + 1, j, arr);
            maxi = Math.max(maxi, cost);
        }
        return maxi;
    }
    public int maxCoins(int[] nums) {
        int newarr[]=new int[nums.length+2];
        newarr[0]=1;
        newarr[newarr.length-1]=1;
        for(int i=0;i<nums.length;i++){
            newarr[i+1]=nums[i];
        }
        return f(1,nums.length,newarr);
    }
}
//memoization
class Solution {
    public int f(int i, int j, int arr[],int dp[][]) {
        if (i > j) return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        int maxi = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = arr[i - 1] * arr[ind] * arr[j+1]
                       + f(i, ind - 1, arr,dp) + f(ind + 1, j, arr,dp);
            maxi = Math.max(maxi, cost);
        }
        return dp[i][j]=maxi;
    }
    public int maxCoins(int[] nums) {
        int newarr[]=new int[nums.length+2];
        newarr[0]=1;
        newarr[newarr.length-1]=1;
        int dp[][]=new int[newarr.length][newarr.length];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        for(int i=0;i<nums.length;i++){
            newarr[i+1]=nums[i];
        }
        return f(1,nums.length,newarr,dp);
    }
}
//tabulation
static int maxCoins(ArrayList<Integer> a) {
        int n = a.size();
        
        // Add 1 to the beginning and end of the array
        a.add(0, 1);
        a.add(1);
        
        // Create a 2D DP array
        int[][] dp = new int[n + 2][n + 2];

        // Iterate from the end to the beginning
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                if (i > j) continue;
                int maxi = Integer.MIN_VALUE;
                
                // Iterate through possible indices to split the array
                for (int ind = i; ind <= j; ind++) {
                    int cost = a.get(i - 1) * a.get(ind) * a.get(j + 1) +
                               dp[i][ind - 1] + dp[ind + 1][j];
                    maxi = Math.max(maxi, cost);
                }
                dp[i][j] = maxi;
            }
        }
        return dp[1][n];
    }
