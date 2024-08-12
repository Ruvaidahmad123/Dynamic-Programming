//intiution is very basic required string will always be of length of 2 string-length of lcs
//if character is common in both string then take it once else take twice if its present only in 1 string
class Solution {
    public int f(int idx1,int idx2,String text1,String text2,int dp[][]){
        if(idx1==0 || idx2==0)return 0;
        if(dp[idx1][idx2]!=-1)return dp[idx1][idx2];
        if(text1.charAt(idx1-1)==text2.charAt(idx2-1)){
            return dp[idx1][idx2]=1+f(idx1-1,idx2-1,text1,text2,dp);
        }
        return dp[idx1][idx2]=0+Math.max(f(idx1-1,idx2,text1,text2,dp),f(idx1,idx2-1,text1,text2,dp));
    }
    public String shortestCommonSupersequence(String str1, String str2) {
        int n1=str1.length();
        int n2=str2.length();
        int dp[][]=new int[n1+1][n2+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        int lcs= f(n1,n2,str1,str2,dp);
        int len = dp[n1][n2];
        int i = n1;
        int j = n2;
        String ans = "";
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            ans += str1.charAt(i-1);
            i--;
            j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {  //we are moving up in dp table so take character of that row
                ans += str1.charAt(i-1);
                i--;
            } else {
                ans += str2.charAt(j-1);  //we are moving left so take character of that coloumn
                j--;
            }
        }
        while(i>0){
            ans += str1.charAt(i-1);
            i--;
        }
        while(j>0){
            ans += str2.charAt(j-1);
            j--;
        }
        String ans2=new StringBuilder(ans).reverse().toString();
        return ans2;
    }
}
