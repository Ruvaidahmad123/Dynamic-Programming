//recursion
class Solution {
    public int f(int idx1,int idx2,String text1,String text2){
        if(idx1<0 || idx2<0)return 0;
        if(text1.charAt(idx1)==text2.charAt(idx2)){
            return 1+f(idx1-1,idx2-1,text1,text2);   //same increase length by 1
        }
        return 0+Math.max(f(idx1-1,idx2,text1,text2),f(idx1,idx2-1,text1,text2));   //not same increase length by 0 and find further
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();
        return f(n1-1,n2-1,text1,text2);
    }
}
//memoization
class Solution {
    public int f(int idx1,int idx2,String text1,String text2,int dp[][]){
        if(idx1<0 || idx2<0)return 0;
        if(dp[idx1][idx2]!=-1)return dp[idx1][idx2];
        if(text1.charAt(idx1)==text2.charAt(idx2)){
            return dp[idx1][idx2]=1+f(idx1-1,idx2-1,text1,text2,dp);
        }
        return dp[idx1][idx2]=0+Math.max(f(idx1-1,idx2,text1,text2,dp),f(idx1,idx2-1,text1,text2,dp));
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();
        int dp[][]=new int[n1][n2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n1-1,n2-1,text1,text2,dp);
    }
}
//tabulation
for writting tabulation code we see there's negative index we can't represent it in dp array so we shift the index for n we find for n-1
  write tabulation for ths updated memoized code
  class Solution {
    public int f(int idx1,int idx2,String text1,String text2,int dp[][]){
        if(idx1==0 || idx2==0)return 0;  //cux idx-1 and idx2-1 will be negative indices
        if(dp[idx1][idx2]!=-1)return dp[idx1][idx2];
        if(text1.charAt(idx1-1)==text2.charAt(idx2-1)){
            return dp[idx1][idx2]=1+f(idx1-1,idx2-1,text1,text2,dp);
        }
        return dp[idx1][idx2]=0+Math.max(f(idx1-1,idx2,text1,text2,dp),f(idx1,idx2-1,text1,text2,dp));
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();
        int dp[][]=new int[n1+1][n2+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n1,n2,text1,text2,dp);
    }
}


class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();
        int dp[][]=new int[n1+1][n2+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        for(int idx1=0;idx1<=n1;idx1++){
            for(int idx2=0;idx2<=n2;idx2++){
                if(idx1==0 || idx2==0){
                    dp[idx1][idx2]=0; 
                }
                else if(text1.charAt(idx1-1)==text2.charAt(idx2-1)){
                    dp[idx1][idx2]=1+dp[idx1-1][idx2-1];
                }
                else
                dp[idx1][idx2]=0+Math.max(dp[idx1-1][idx2],dp[idx1][idx2-1]);
            }
        }
        return dp[n1][n2];
    }
}

//space optimized
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        // Ensure the smaller string is text1 to optimize space
        if (n1 < n2) {
            return longestCommonSubsequence(text2, text1);
        }
        // Create two arrays to store current and previous row
        int[] prev = new int[n2 + 1];
        int[] curr = new int[n2 + 1]; 
        // Fill the dp arrays
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            // Swap references to the arrays
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        // The length of the longest common subsequence
        return prev[n2];
    }
}
