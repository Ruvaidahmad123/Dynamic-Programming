class Solution {
    public int getAns(int idx,String s,HashMap<String,Integer>map,int dp[]){
        if(idx==s.length())return 0;
        if(dp[idx]!=-1)return dp[idx];
        int nottake=1+getAns(idx+1,s,map,dp);
        int take=Integer.MAX_VALUE;
        for(int j=idx;j<s.length();j++){
            String t=s.substring(idx,j+1);
            if(map.containsKey(t)){
                take=Math.min(take,0+getAns(j+1,s,map,dp));
            }
        }
        return dp[idx]=Math.min(take,nottake);
    }
    public int minExtraChar(String s, String[] dictionary) {
        int n=s.length();
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        HashMap<String,Integer>map=new HashMap<>();
        for(String str:dictionary){
            map.put(str,map.getOrDefault(str,0)+1);
        }
        return getAns(0,s,map,dp);
    }
}
