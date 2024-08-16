//recursive
class Solution {
    public int lis(int idx,int nums[],int prev){
        if(idx==nums.length)return 0;
        int nottake=0+lis(idx+1,nums,prev);
        int take=0;
        if(prev==-1 || nums[idx]>nums[prev]){
            take=1+lis(idx+1,nums,idx);
        }
        return Math.max(nottake,take);
    }
    public int lengthOfLIS(int[] nums) {
        return lis(0,nums,-1);
    }
}
//memoization
class Solution {
    public int lis(int idx,int nums[],int prev,int dp[][]){
        if(idx==nums.length)return 0;
        if(dp[idx][prev+1]!=-1)return dp[idx][prev+1];
        int nottake=0+lis(idx+1,nums,prev,dp);
        int take=0;
        if(prev==-1 || nums[idx]>nums[prev]){
            take=1+lis(idx+1,nums,idx,dp);
        }
        return dp[idx][prev+1]=Math.max(nottake,take);
    }
    public int lengthOfLIS(int[] nums) {
        int dp[][]=new int[nums.length][nums.length+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return lis(0,nums,-1,dp);
    }
}
//tabulation
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int dp[][]=new int[n+1][n+1];
        for(int idx=n-1;idx>=0;idx--){
            for(int prev=idx-1;prev>=-1;prev--){
                int nottake=dp[idx+1][prev+1];
                int take=0;
                if(prev==-1 || nums[idx]>nums[prev]){
                    take=1+dp[idx+1][idx+1];
                }
                dp[idx][prev+1]=Math.max(nottake,take);
            }
        }
        return dp[0][0];
    }
}
//optimized tabulation
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int dp[]=new int[n];
        Arrays.fill(dp,1);
        int maxi=1;
        for(int idx=0;idx<n;idx++){
            for(int prev=0;prev<idx;prev++){
                if(nums[idx]>nums[prev]){
                    dp[idx]=Math.max(dp[idx],1+dp[prev]);
                }
            }
            maxi=Math.max(maxi,dp[idx]);
        }
        return maxi;
    }
}
//space optimized
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] next = new int[n+1];
        int[] curr = new int[n+1];

        for (int idx = n-1; idx >= 0; idx--) {
            for (int prev = idx-1; prev >= -1; prev--) {
                int nottake = next[prev+1];
                int take = 0;
                if (prev == -1 || nums[idx] > nums[prev]) {
                    take = 1 + next[idx+1];
                }
                curr[prev+1] = Math.max(nottake, take);
            }
            // Move current row to next row for the next iteration
            next = curr.clone();
        }
        return curr[0];
    }
}
