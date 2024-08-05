//memoized
class Solution {
    static int mod=(int)1e9+7;
    public static int f(int idx, int arr[], int target, int dp[][]) {
        if(idx==0)
        {
        if(target==0 && arr[0]==0)return 2;   //to handle zeroes as well
        if(target==0 || arr[0]==target)return 1;    
        return 0;
        }
        if (dp[idx][target] != -1) return dp[idx][target];
        int notpick = f(idx - 1, arr, target, dp)%mod;
        int pick = 0;
        if (arr[idx] <= target) {
            pick = f(idx - 1, arr, target - arr[idx], dp)%mod;
        }
        return dp[idx][target] = (pick + notpick)%mod;
    }
    public static int countPartitions(int n, int d, int[] arr) {
        int totalsum=0;
        for(int x:arr){
            totalsum+=x;
        }
        //s1-s2=d
        //totalsum=s1+s2;
        //totalsum-s2-s2=d
        //(totalsum-d)/s2  look for such target
        if((totalsum-d)%2==1)return 0;  //should not be decimal
        if((totalsum-d)<0)return 0;    //should not be negative
        int target=(totalsum-d)/2;
        int dp[][]=new int[n][target+1];
	    for(int rows[]:dp){
	       Arrays.fill(rows,-1);
	    }
	    return f(n-1,arr,target,dp);
    }
}
