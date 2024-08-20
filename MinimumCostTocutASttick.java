//recursive
class Solution {
    public int findMinimumCost(int i,int j,int cuts[]){
        if(i>j)return 0;
        int min=Integer.MAX_VALUE;
        for(int k=i;k<=j;k++){
            int cost=cuts[j+1]-cuts[i-1]+findMinimumCost(i,k-1,cuts)+findMinimumCost(k+1,j,cuts);
            min=Math.min(min,cost);
        }
        return min;
    }
    public int insert(int n,int cuts[]){
        int newarr[]=new int[cuts.length+2];
        newarr[0]=0;
        newarr[newarr.length-1]=n;
        for(int i=0;i<cuts.length;i++){
            newarr[i+1]=cuts[i];
        }
        Arrays.sort(newarr);
        return findMinimumCost(1,cuts.length,newarr);
    }
    public int minCost(int n, int[] cuts) {
        return insert(n,cuts);
    }
}
//memoization
class Solution {
    public int findMinimumCost(int i,int j,int cuts[],int dp[][]){
        if(i>j)return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        int min=Integer.MAX_VALUE;
        for(int k=i;k<=j;k++){
            int cost=cuts[j+1]-cuts[i-1]+findMinimumCost(i,k-1,cuts,dp)+findMinimumCost(k+1,j,cuts,dp);
            min=Math.min(min,cost);
        }
        return dp[i][j]=min;
    }
    public int insert(int n,int cuts[]){
        int newarr[]=new int[cuts.length+2];
        newarr[0]=0;
        newarr[newarr.length-1]=n;
        for(int i=0;i<cuts.length;i++){
            newarr[i+1]=cuts[i];
        }
        Arrays.sort(newarr);
        int dp[][]=new int[newarr.length][newarr.length];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return findMinimumCost(1,cuts.length,newarr,dp);
    }
    public int minCost(int n, int[] cuts) {
        return insert(n,cuts);
    }
}
//tabulation
public class TUF {
    // Function to calculate the minimum cost
    static int cost(int n, int c, ArrayList<Integer> cuts) {
        // Modify the cuts array
        cuts.add(n);
        cuts.add(0);
        Collections.sort(cuts);

        int[][] dp = new int[c + 2][c + 2];

        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }

        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {
                if (i > j) continue;

                int mini = Integer.MAX_VALUE;

                for (int ind = i; ind <= j; ind++) {
                    int ans = cuts.get(j + 1) - cuts.get(i - 1) + dp[i][ind - 1] + dp[ind + 1][j];
                    mini = Math.min(mini, ans);
                }

                dp[i][j] = mini;
            }
        }

        return dp[1][c];
    }
