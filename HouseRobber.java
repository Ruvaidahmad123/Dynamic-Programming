//recursive solution
class Solution {
    public int f(int idx,int nums[]){
        if(idx==0){
            return nums[0];
        }
        if(idx<0)return 0;
        int pick=nums[idx]+f(idx-2,nums);
        int notpick=0+f(idx-1,nums);
        return Math.max(pick,notpick);
    }
    public int rob(int[] nums) {
        int n=nums.length;
        return f(n-1,nums);
    }
}
//memoization
class Solution {
    public int f(int idx,int nums[],int dp[]){
        if(idx==0){
            return nums[0];
        }
        if(idx<0)return 0;
        if(dp[idx]!=-1){
            return dp[idx];
        }
        int pick=nums[idx]+f(idx-2,nums,dp);
        int notpick=0+f(idx-1,nums,dp);
        return dp[idx]=Math.max(pick,notpick);
    }
    public int rob(int[] nums) {
        int n=nums.length;
        int dp[]=new int[n];
        Arrays.fill(dp,-1);
        return f(n-1,nums,dp);
    }
}
//tabulation
class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        int dp[]=new int[n];
        dp[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            int pick=nums[i];
            if(i-2>=0){
                pick+=dp[i-2];
            }
            int notpick=dp[i-1];
            dp[i]=Math.max(pick,notpick);
        }
        return dp[n-1];
    }
}
