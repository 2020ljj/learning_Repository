import java.util.Arrays;
import java.util.Comparator;

/**
 * PASS-2023-8-1
 *
 */
public class solution_452 {
    //排序 + 贪心
    public int findMinArrowShots(int [][]points){
        if(points.length == 1)
            return 1;
        Arrays.sort(points, new Comparator<int[]>() {    // 匿名内部类
            @Override
            public int compare(int[] e1, int[] e2) {
                // 如果第一列元素相等，则比较第二列元素
                if (e1[0]==e2[0]) return Integer.compare(e1[1],e2[1]);   // e1[1]-e2[1]表示对于第二列元素进行升序排序
                return Integer.compare(e1[0],e2[0]);                     // e1[0]-e2[0]表示对于第一列元素进行升序排序
            }
        });
        int left = points[0][0],right = points[0][1],ans = 1;
        for(int i = 1 ; i < points.length; i++){
            if(points[i][0] <= right){
                left = points[i][0];
                right = Math.min(right,points[i][1]);
            }
            else{
                left = points[i][0];
                right = points[i][1];
                ans ++;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        solution_452 solution = new solution_452();
        //定义入参
        int [][]points = {{10,16},{2,8},{1,6},{7,12}};
        int [][]points1 = {{1,2},{2,3},{3,4},{4,5}};
        int [][]points2 = {{-2,-1},{2,21}};
        //输入入参
        int ans = solution.findMinArrowShots(points2);
        System.out.println(ans);
        System.out.println();
    }
}
