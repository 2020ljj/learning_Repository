import java.util.Arrays;

public class solution_80 {
    public int removeDuplicates(int[] nums) {
        int n = nums.length, lastOne = nums[0] ,times = 1 , j, ans = 0;
        for(int i = 1 ; i < n ; i++){
            if(nums[i] == lastOne)
                times++;
            else{
                lastOne = nums[i];
                times = 1;
            }
            if(times > 2){
                nums[i] = -10001;
                ans ++;
            }
        }
        for(int i = 1 ; i < n ; i++){
            if(nums[i] == -10001){
                j = i;
                while(j < n && nums[j] == -10001){
                    j++;
                }
                if(j < n){
                    nums[i] = nums[j];
                    nums[j] = -10001;
                }
            }
        }
        return n - ans;
    }
    public static void main(String[] args) {
        solution_80 solution = new solution_80();

        //定义入参
        int []nums = {1,1,2,2,3,3,3,4,4,5,5,5,5,5,5,6,6,9,16};
        int ans = solution.removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
        //输入入参
        System.out.println(ans);
    }
}
