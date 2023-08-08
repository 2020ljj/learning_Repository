import java.util.Arrays;

public class solution_198 {

    public int rob(int []nums){
        int n = nums.length, max_pre = 0, max = nums[0];
        if(n == 1)
            return nums[0];
        for(int i = 2 ; i<n ; i++){
            max_pre = Math.max(max_pre,nums[i-2]);
            nums[i] += max_pre;
            max = Math.max(max,nums[i]);
        }
        return Math.max(max,nums[1]);
    }

    public static void main(String[] args) {
        solution_198 solution = new solution_198();

        //定义入参
        int []nums = {5,2};
        //输入入参
        int ans = solution.rob(nums);
        System.out.println(ans);
    }
}
