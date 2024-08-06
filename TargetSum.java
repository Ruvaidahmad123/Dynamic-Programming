//recursive
class Solution {
     public int f(int idx,int nums[],int target){
        if(idx==0){
            if(nums[0]==0 && target==0)return 2;
            if(target+nums[0]==0 || target-nums[0]==0){
                return 1;
            }
            else return 0;
        }
        int positive=f(idx-1,nums,target-nums[idx]);
        int negative=f(idx-1,nums,target+nums[idx]);
        return positive+negative;
    }
    public int findTargetSumWays(int[] nums, int target) {
        int n=nums.length;
        return f(n-1,nums,target);
    }
}

can also be solved in this way there will be 2 subsets one containing all positive integers and other subset containing all negative integers and difference of these 2 should be equal
to target s1-s2=target where s1+s2=totalsum  so (totalsum-target)/2 =s2 we have to count such subsets
//memoized
  class Solution {
    public static int f(int idx, int arr[], int target, int dp[][]) {
        if(idx==0)
        {
        if(target==0 && arr[0]==0)return 2;   //to handle zeroes as well
        if(target==0 || arr[0]==target)return 1;    
        return 0;
        }
        if (dp[idx][target] != -1) return dp[idx][target];
        int notpick = f(idx - 1, arr, target, dp);
        int pick = 0;
        if (arr[idx] <= target) {
            pick = f(idx - 1, arr, target - arr[idx], dp);
        }
        return dp[idx][target] = (pick + notpick);
    }
    public int findTargetSumWays(int[] nums, int target) {
        int n=nums.length;
        int totalsum=0;
        for(int x:nums){
            totalsum+=x;
        }
        //s1-s2=d
        //totalsum=s1+s2;
        //totalsum-s2-s2=d
        //(totalsum-d)/2  look for such target
        if((totalsum-target)%2==1)return 0;  //should not be decimal
        if((totalsum-target)<0)return 0;    //should not be negative
        int trg=(totalsum-target)/2;
        int dp[][]=new int[n][trg+1];
	    for(int rows[]:dp){
	       Arrays.fill(rows,-1);
	    }
	    return f(n-1,nums,trg,dp);
    }
}
