import java.util.Arrays;

/**
 * PASS-2023-7-28
 */
public class solution_42 {
    public int trap(int []height){
        int rainDrop = 0,n = height.length;
        int []leftHeight = new int[n];          //leftHeight[i]表示下标i自己及左侧的最高高度
        leftHeight[0] = height[0];
        for(int i = 1 ; i<n ; i++){
            leftHeight[i] = Math.max(leftHeight[i-1],height[i]);
        }
        int []rightHeight = new int[n];
        rightHeight[n-1] = height[n-1];
        for(int i = n-2 ; i> -1 ; i--){
            rightHeight[i] = Math.max(rightHeight[i+1],height[i]);
        }
        for(int i = 0 ; i<n ; i++){
            rainDrop += Math.min(leftHeight[i],rightHeight[i]) - height[i];
        }
        return rainDrop;
    }
    public static void main(String[] args) {
        solution_42 solution = new solution_42();
        int []height = {4,2,0,3,2,5};
        int ans = solution.trap(height);
        //定义入参

        //输入入参
        System.out.println(ans);
    }
}
