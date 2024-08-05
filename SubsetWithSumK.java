//recursive
class Solution{
    public int f(int idx,int arr[],int target){
        if(target==0)return 1;
        if(idx==0)return target==arr[idx]?1:0;
        int notpick=f(idx-1,arr,target);
        int pick=0;
        if(arr[idx]<=target){
            pick=f(idx-1,arr,target-arr[idx]);
        }
        return pick+notpick;
    }
	public int perfectSum(int arr[],int n, int sum) 
	{ 
	   return f(n-1,arr,sum);
	} 
}
//memoization
class Solution{
    int mod=(int)1e9+7;
    public int f(int idx, int arr[], int target, int dp[][]) {
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
	public int perfectSum(int arr[],int n, int sum) 
	{ 
	   int dp[][]=new int[n][sum+1];
	   for(int rows[]:dp){
	       Arrays.fill(rows,-1);
	   }
	   return f(n-1,arr,sum,dp);
	} 
}
//tabulation
class Solution{
	public int perfectSum(int num[],int n, int k) 
	{ 
	    // Your code goes here
        int[][] dp = new int[n][k + 1];
        // for every target 0 fill 1
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        if (num[0] <= k) {
            dp[0][num[0]] = 1;
        }
        // special case if array contains 0 at first element
        if (num[0] == 0) {
            dp[0][0] = 2;
        }
        // form nested loops
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                int notPick = dp[i - 1][j] % (int) (1e9 + 7);
                int pick = 0;
                if (num[i] <= j) {
                    pick = dp[i - 1][j - num[i]] % (int) (1e9 + 7);
                }
                dp[i][j] = (notPick + pick) % (int) (1e9 + 7);
            }
        }
        return dp[n - 1][k];
	} 
}
//space optimized
public int perfectSum(int num[], int n, int k) { 
        int MOD = 1000000007;
        // Create an array to store the number of ways to achieve each target sum
        int[] prev = new int[k + 1];
        // Initialize the first element of the array
        prev[0] = 1;
        // Initialize the array for the first element
        if (num[0] <= k) {
            prev[num[0]]++;
        }
        // Handle the special case if the first element is 0
        if (num[0] == 0) {
            prev[0] = 2; // Considering both the case of picking and not picking the zero
        }
        // Fill in the array using bottom-up dynamic programming
        for (int ind = 1; ind < n; ind++) {
            // Create a temporary array to store the number of ways for the current element
            int[] cur = new int[k + 1];
            // Initialize the first element of the current array
            cur[0] = 1;
            for (int target = 1; target <= k; target++) {
                // Calculate the number of ways when the current element is not taken
                int notTaken = prev[target];
                // Calculate the number of ways when the current element is taken
                int taken = 0;
                if (num[ind] <= target) {
                    taken = prev[target - num[ind]];
                }
                // Update the current array for the current element and target sum
                cur[target] = (notTaken + taken) % MOD;
            }
            // Update the previous array with the current array for the next iteration
            prev = cur;
        }
        // The result is stored in the last element of the array
        return prev[k];
    }
