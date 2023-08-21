public class solution_34 {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length , i = 0, left = -1 ,right = -1;
        while(i < n){
            if(nums[i] == target){
                left = i;
                while(i < n && nums[i] == target){
                    right = i;
                    i++;
                }
                break;
            }
            i++;
        }
        return new int[]{left, right};
    }
    public static void main(String[] args) {
        solution_template solution = new solution_template();

        //定义入参

        //输入入参
        System.out.println();
    }
}
