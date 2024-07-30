//memoization
class Solution {
    public int solve(int ind,int arr[],int k,int dp[]){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int min=Integer.MAX_VALUE;
        for(int j=1;j<=k;j++){
            if(ind-j>=0){
            int jump= solve(ind-j,arr,k,dp)+ Math.abs(arr[ind]-arr[ind-j]);
            min=Math.min(jump,min);
            }
        }
        return dp[ind]=min;
    }
    public int minimizeCost(int arr[], int k) {
        int dp[] = new int[arr.length];
        Arrays.fill(dp, -1);
        return solve(arr.length-1,arr,k,dp);
    }
}



//tabulation
class Solution {
    public int solve(int ind,int arr[],int k,int dp[]){
        dp[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            int mmSteps = Integer.MAX_VALUE;
            // Loop to try all possible jumps from '1' to 'k'
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(arr[i] - arr[i - j]);
                    mmSteps = Math.min(jump, mmSteps);
                }
            }
            dp[i] = mmSteps;
        }
        return dp[arr.length - 1]; 
    }
    public int minimizeCost(int arr[], int k) {
        int dp[] = new int[arr.length];
        Arrays.fill(dp, -1);
        return solve(arr.length-1,arr,k,dp);
    }
}
