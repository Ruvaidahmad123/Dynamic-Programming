class Solution {
    Comparator<String> comp = (s1, s2) -> s1.length() - s2.length();
    public boolean compare(String s1,String s2){
        if(s1.length()!=s2.length()+1){
            return false;
        }
        int first=0;
        int second=0;
        while(first<s1.length()){
            if (second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                first++;
                second++;
            } else {
                first++;
            }
        }
        return first == s1.length() && second == s2.length();
    }
    public int longestStrChain(String[] words) {
        int n=words.length;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
        int dp[]=new int[n];
        Arrays.fill(dp,1);
        int maxi=1;
        for(int idx=0;idx<n;idx++){
            for(int prev=0;prev<idx;prev++){
                if(compare(words[idx],words[prev]) && 1+dp[prev]>dp[idx]){
                    dp[idx]=1+dp[prev];
                }
            }
            maxi=Math.max(maxi,dp[idx]);
        }
        return maxi;
    }
}
