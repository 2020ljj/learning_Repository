import java.util.ArrayList;
import java.util.List;

/**
 * PASS-2023-7-27
 */
public class solution_22 {
    private List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n){
        String temp = "";
        temp +="(";
        dfs(temp,1,0,n);
        return res;
    }
    private void dfs(String temp,int left,int right,int n){
        if(left > n || right > left)
            return;
        if(left == right && left == n)
            res.add(temp);
        else{
            dfs(temp+"(",left+1,right,n);
            dfs(temp+")",left,right+1,n);
        }
    }
    public static void main(String[] args) {
        solution_22 solution = new solution_22();
        List<String> ans = solution.generateParenthesis(3);
        System.out.println(ans);
    }
}
