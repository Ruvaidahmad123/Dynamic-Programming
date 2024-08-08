//recursion
class Solution {
     public int f(int idx1,int idx2,String text1,String text2){
        if(idx1<0 || idx2<0)return 0;
        if(text1.charAt(idx1)==text2.charAt(idx2)){
            return 1+f(idx1-1,idx2-1,text1,text2);   //same increase length by 1
        }
        return 0+Math.max(f(idx1-1,idx2,text1,text2),f(idx1,idx2-1,text1,text2));   //not same increase length by 0 and find further
    }
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb=new StringBuilder(s);
        String s2=sb.reverse().toString();
        int n=s.length();     //same as lcs only difference is u need to find lcs in string and reversed string cuz its palindromic
        return f(n-1,n-1,s,s2);
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
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb=new StringBuilder(s);
        String s2=sb.reverse().toString();
        int n=s.length();
        int dp[][]=new int[n][n];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n-1,n-1,s,s2,dp);
    }
}
//tabulation
class Solution {
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb=new StringBuilder(s);
        String s2=sb.reverse().toString();
        int n=s.length();
        int dp[][]=new int[n+1][n+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        for(int idx1=0;idx1<=n;idx1++){
            for(int idx2=0;idx2<=n;idx2++){
                if(idx1==0 || idx2==0){
                    dp[idx1][idx2]=0; 
                }
                else if(s.charAt(idx1-1)==s2.charAt(idx2-1)){
                    dp[idx1][idx2]=1+dp[idx1-1][idx2-1];
                }
                else
                dp[idx1][idx2]=0+Math.max(dp[idx1-1][idx2],dp[idx1][idx2-1]);
            }
        }
        return dp[n][n];
    }
}
//space optimized
class TUF {
    // Function to find the length of the Longest Common Subsequence (LCS)
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        // Create two arrays to store the LCS lengths
        int[] prev = new int[m + 1];
        int[] cur = new int[m + 1];
        // Base Case: Initialized to 0, as no characters matched yet.
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                    cur[ind2] = 1 + prev[ind2 - 1];
                else
                    cur[ind2] = Math.max(prev[ind2], cur[ind2 - 1]);
            }      
            // Update prev array to store the current values
            prev = cur.clone();
        }
        return prev[m];
    }
    // Function to find the length of the Longest Palindromic Subsequence
    static int longestPalindromeSubsequence(String s) {
        // Create a reversed version of the input string
        String reversed = new StringBuilder(s).reverse().toString();
        // Calculate the LCS of the original string and its reverse
        return lcs(s, reversed);
    }
    public static void main(String args[]) {
        String s = "bbabcbcab";

        System.out.print("The Length of Longest Palindromic Subsequence is ");
        System.out.println(longestPalindromeSubsequence(s));
    }
}
