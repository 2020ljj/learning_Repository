
/**
 * PASS-2023-7-31
 */
public class solution_11 {
    public int maxArea(int []height){
        int left = 0,right = height.length-1,maxArea = 0;
        while(left < right){
            maxArea = Math.max(maxArea,(right-left)*Math.min(height[left],height[right]));
            if(height[right] > height[left])
                left ++;
            else
                right --;
        }
        return maxArea;
    }
    public static void main(String[] args) {
        solution_11 solution = new solution_11();

        //定义入参
        int []height = {1,8,6,2,5,4,8,3,7};

        //输入入参
        int ans = solution.maxArea(height);
        System.out.println(ans);
    }
}
