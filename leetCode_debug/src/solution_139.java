import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class solution_139 {
    public boolean wordBreak(String s, List<String> wordDict){
        boolean []dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 0 ; i<dp.length ; i++){
            for(int j = 0 ; j<i ; j++){
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[s.length()];
    }
    public static void main(String[] args) {
        solution_139 solution = new solution_139();
        String s = "car";
        List<String> dict = new ArrayList<>();
        dict.add("ca");
        dict.add("rrs");
        dict.add("r");
        boolean ans = solution.wordBreak(s,dict);
        //定义入参

        //输入入参
        System.out.println(ans);
    }
}
