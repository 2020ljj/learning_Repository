public class solution_5 {
    String s;
    int left = 0, ans = 1;
    //1.中心扩散算法
    public String longestPalindrome1(String s) {
        this.s = s;
        for(int i = 0 ; i<s.length() ; i++){
            centerExpand(i);
        }
        left++;
        return s.substring(left , left + this.ans);
    }
    private void centerExpand(int i){
        int ans = 1, left = i-1 ,right = i+1;
        while(right < s.length() && s.charAt(right) == s.charAt(i)){
            ans++;
            right++;
        }
        while(left > -1 && right < s.length() && s.charAt(left) == s.charAt(right)){
            ans += 2;
            left --;
            right ++;
        }
        this.ans = Math.max(this.ans , ans);
        if(this.ans == ans)
            this.left = left;
    }
    //2.dp
    public String longestPalindrome2(String s) {
        int n = s.length(),left = 0,ans = 1;;
        boolean [][]f = new boolean[n][n];
        char[] charArray = s.toCharArray();
        //f[i][j] = f[i-1][j-1] && s[i] == s[j]
        for(int i = 0 ; i < n ; i++)
            f[i][i] = true;
        for(int j = 1 ; j < n ; j++)
            for( int i = 0 ; i < j ; i++){
                if(charArray[i] != charArray[j])
                    f[i][j] = false;
                else{
                    if(i + 1 == j)
                        f[i][j] = true;
                    else
                        f[i][j] = f[i+1][j-1];
                    if(f[i][j] && j-i+1 > ans){
                        ans = j-i+1;
                        left = i;
                    }
                }
            }
        return s.substring(left,left+ans);
    }
    public static void main(String[] args) {
        solution_5 solution = new solution_5();

        //定义入参
        String s = "cbbd";
        //输入入参
        String ans = solution.longestPalindrome2(s);
        System.out.println(ans);
    }
}
