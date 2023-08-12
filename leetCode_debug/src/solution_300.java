import java.util.Arrays;

public class solution_300 {
    public int lengthOfLIS(int []nums){
        int []dp = new int[nums.length];
        int max, ans = 1;
        dp[0] = 1;
        for(int i = 1 ; i<nums.length ; i++){
            max = 0;
            for(int j = 0 ; j<i ; j++){
                if(nums[j] < nums[i])
                    max = Math.max(max , dp[j]);
                dp[i] = max + 1;
                ans = Math.max(ans,dp[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return ans;
    }
    public static void main(String[] args) {
        solution_300 solution = new solution_300();

        //定义入参
        int []nums = {10,9,2,5,3,7,101,18};
        //输入入参
        int ans = solution.lengthOfLIS(nums);
        System.out.println(ans);
    }
}
