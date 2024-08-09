//recursive
class Solution {
    public int f(int idx1,int idx2,String text1,String text2){
        if(idx1<0 || idx2<0)return 0;
        if(text1.charAt(idx1)==text2.charAt(idx2)){
            return 1+f(idx1-1,idx2-1,text1,text2);   //same increase length by 1
        }
        return 0+Math.max(f(idx1-1,idx2,text1,text2),f(idx1,idx2-1,text1,text2));   //not same increase length by 0 and find further
    }
    public int minDistance(String word1, String word2)  {
        int n1=text1.length();
        int n2=text2.length();
        int lcs= f(n1-1,n2-1,text1,text2);
        return n1-lcs+n2-lcs;
    }
}
//memoized
class Solution {
    public int f(int idx1,int idx2,String text1,String text2,int dp[][]){
        if(idx1<0 || idx2<0)return 0;
        if(dp[idx1][idx2]!=-1)return dp[idx1][idx2];
        if(text1.charAt(idx1)==text2.charAt(idx2)){
            return dp[idx1][idx2]=1+f(idx1-1,idx2-1,text1,text2,dp);
        }
        return dp[idx1][idx2]=0+Math.max(f(idx1-1,idx2,text1,text2,dp),f(idx1,idx2-1,text1,text2,dp));
    }
    public int minDistance(String word1, String word2) {
        int n1=word1.length();
        int n2=word2.length();
        int dp[][]=new int[n1][n2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        int lcs= f(n1-1,n2-1,word1,word2,dp);
        return n1-lcs+n2-lcs;
    }
}
//tabulation
class Solution {
    public int minDistance(String word1, String word2) {
        int n1=word1.length();
        int n2=word2.length();
        int dp[][]=new int[n1+1][n2+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        for(int idx1=0;idx1<=n1;idx1++){
            for(int idx2=0;idx2<=n2;idx2++){
                if(idx1==0 || idx2==0){
                    dp[idx1][idx2]=0; 
                }
                else if(word1.charAt(idx1-1)==word2.charAt(idx2-1)){
                    dp[idx1][idx2]=1+dp[idx1-1][idx2-1];
                }
                else
                dp[idx1][idx2]=0+Math.max(dp[idx1-1][idx2],dp[idx1][idx2-1]);
            }
        }
        int lcs= dp[n1][n2];
        return n1-lcs+n2-lcs;
    }
}
