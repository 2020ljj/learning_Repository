import java.util.*;

public class solution_120 {
    public int minimumTotal(List<List<Integer>> triangle){
        int n = triangle.size();
        int k = 0, ans = 0, smallerNum = 0;
        if(n == 1)
            return triangle.get(0).get(0);
        for(int i = 1 ; i < n ; i++){
            for(int j = 0 ; j < i+1 ; j++){
                smallerNum = Math.min(j == 0?1000:triangle.get(i-1).get(j-1),j == i?1000:triangle.get(i-1).get(j));
                List<Integer> temp = new ArrayList<>(triangle.get(i));
                temp.set(j,triangle.get(i).get(j) + smallerNum);
                triangle.set(i,temp);
            }
        }
        ans = Collections.min(triangle.get(n-1));
        return ans;
    }
    public static void main(String[] args) {
        solution_120 Solution = new solution_120();
        List<List<Integer>> triangle = new ArrayList<>(List.of(List.of(2),List.of(3,4),List.of(6,5,7),List.of(4,1,8,3)));
        System.out.println("triangle:" + triangle);
        int ans  = Solution.minimumTotal(triangle);
        System.out.println(ans);
    }
}
