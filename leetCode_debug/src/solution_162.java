public class solution_162 {
    public int findPeakElement(int []nums){
        int left = 0, right = nums.length - 1;
        while(left<right){
            int m = left + (right - left)/2;
            if(nums[m+1] > nums[m]){
                left = m + 1;
            }else {
                right = m;
            }
        }
        return left;
    }
    public static void main(String[] args) {
        solution_162 solution = new solution_162();

        //定义入参
        int []nums = {1,2,1,3,5,6,4};
        //输入入参
        int ans = solution.findPeakElement(nums);
        System.out.println(ans);
    }

}
