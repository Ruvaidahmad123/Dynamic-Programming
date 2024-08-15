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
