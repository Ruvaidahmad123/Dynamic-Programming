class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n=nums.length;
        int dp[]=new int[n];
        int count[]=new int[n];
        Arrays.fill(dp,1);
        Arrays.fill(count,1);
        int maxi=1;
        for(int i=0;i<nums.length;i++){
            for(int prev=0;prev<i;prev++){
                if(nums[i]>nums[prev] && 1+dp[prev]>dp[i]){
                    dp[i]=1+dp[prev];
                    count[i]=count[prev];
                }
                else if(nums[i]>nums[prev] && 1+dp[prev]==dp[i]){
                    dp[i]=1+dp[prev];
                    count[i]+=count[prev];
                }
            }
            maxi=Math.max(dp[i],maxi);
        }
        int nos=0;
        for(int i=0;i<n;i++){
            if(dp[i]==maxi)nos+=count[i];
        }
        return nos;
    }
}
