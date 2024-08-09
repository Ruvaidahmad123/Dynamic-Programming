//recursive
class Solution {
    public int f(int idx1,int idx2,String text1,String text2){
        if(idx1<0 || idx2<0)return 0;
        if(text1.charAt(idx1)==text2.charAt(idx2)){
            return 1+f(idx1-1,idx2-1,text1,text2);
        }
        return 0+Math.max(f(idx1,idx2-1,text1,text2),f(idx1-1,idx2,text1,text2));
    }
    public int minInsertions(String s) {
        StringBuilder sb=new StringBuilder(s);
        String s2=sb.reverse().toString();
        int n=s.length();     //same as lcs only difference is u need to find lcs in string and reversed string cuz its palindromic
        int lps= f(n-1,n-1,s,s2);
        return n-lps;
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
    public int minInsertions(String s) {
        StringBuilder sb=new StringBuilder(s);
        String s2=sb.reverse().toString();
        int n=s.length();
        int dp[][]=new int[n][n];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        int lps= f(n-1,n-1,s,s2,dp);
        return n-lps;
    }
}
//tabulation
class Solution {
    public int minInsertions(String s) {
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
        int lps= dp[n][n];
        return n-lps;
    }
}
