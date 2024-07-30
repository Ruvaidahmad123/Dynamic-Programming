/*My take from the video:
1. Recursion: each function calls give rise to 2 more: therefore O(2^n)
2. Memoization: stores in an array: O(n)
   Subproblem results are stored in an array, ensuring that each subproblem is solved only once.
3. Tabulation: iterative approach and takes O(n) subproblems from the smallest to the largest.
RECURSION: Top down: We start from answer, go to the base case and then go back
MEMOIZATION: TOP DOWN:  avoids redundant calls done in recursion reducing time complexity
TABULATION IS: Bottom up: We start from the base case and we try to go to the required answer*/

// STEP1   Create a dp[n+1] array initialized to -1.
// STEP2   Whenever we want to find the answer of a particular value (say n), we first check whether the answer is already calculated using the dp array(i.e dp[n]!= -1 ). If yes, simply return the value from the dp array.
// STEP3   If not, then we are finding the answer for the given value for the first time, we will use the recursive relation as usual but before returning from the function, we will set dp[n] to the solution we get.

// fiboancci number TOP TO DOWN  memoization approach   o(n) time and O(n) space for recursive stack and O(n) space for hashmap/array
  class Solution {
    private static HashMap<Integer, Long> memo = new HashMap<>();

    static long topDown(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long result = topDown(n - 1) + topDown(n - 2);
        memo.put(n, result);
        return result;
    }
}
//fibonacci number BOTTOM TO UP tabulation approach   //O(n) time and O(n) space only for array no recursion
public int fib(int n) {
        if(n<2){
            return n;
        }
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
// O(n) time and O(1) space approach
class TUF{
public static void main(String args[]) {
 int n=5;
  
  int prev2 = 0;
  int prev = 1;
  
  for(int i=2; i<=n; i++){
      int cur_i = prev2+ prev;
      prev2 = prev;
      prev= cur_i;
  }
  System.out.println(prev);
}
