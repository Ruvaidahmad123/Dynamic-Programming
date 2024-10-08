//memoization approach
class TUF{
static int solve(int ind,int[] height,int[] dp){
    if(ind==0) return 0;
    if(dp[ind]!=-1) return dp[ind];
    int jumpTwo = Integer.MAX_VALUE;
    int jumpOne= solve(ind-1, height,dp)+ Math.abs(height[ind]-height[ind-1]);
    if(ind>1)
        jumpTwo = solve(ind-2, height,dp)+ Math.abs(height[ind]-height[ind-2]);
    
    return dp[ind]=Math.min(jumpOne, jumpTwo);
}
public static void main(String args[]) {
  int height[]={30,10,60 , 10 , 60 , 50};
  int n=height.length;
  int dp[]=new int[n];
  Arrays.fill(dp,-1);
  System.out.println(solve(n-1,height,dp));
}
}

//tabulation solution
class Solution{
    public int minimumEnergy(int arr[],int n){
        if (n == 0) return 0;
        if (n == 1) return 0;
        int dp[] = new int[n];
        Arrays.fill(dp, 0);
        dp[1] = Math.abs(arr[1] - arr[0]);
        for (int i = 2; i < n; i++) {
            int oneStep = dp[i - 1] + Math.abs(arr[i] - arr[i - 1]);
            int twoStep = dp[i - 2] + Math.abs(arr[i] - arr[i - 2]);
            dp[i] = Math.min(oneStep, twoStep);
        }
        return dp[n - 1];
    }
}
//space optimized
class Solution{
    public int minimumEnergy(int arr[],int n){
        if (n == 0) return 0;
        if (n == 1) return 0;
        int prev=0;
        int prev2=0;
        for(int i=1;i<n;i++){
            int fs=prev+Math.abs(arr[i]-arr[i-1]);
            int ss=Integer.MAX_VALUE;
            if(i>1){
                ss=prev2+Math.abs(arr[i]-arr[i-2]);
            }
            prev2=prev;
            prev=Math.min(fs,ss);
        }
        return prev;
    }
}
