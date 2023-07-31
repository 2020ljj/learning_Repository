import java.util.ArrayList;
import java.util.List;

public class solution_46 {
    public static List<List<Integer>> permute(int []nums){
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(nums[0]);
        ans.add(temp);
        int j = 0;
        for(int i = 1 ; i < nums.length ; i++){
            int size = ans.size();
            for(j = 0 ; j < size ; j++){
                temp = ans.get(0);
                for(int k = 0 ; k < i + 1 ; k++){
                    temp.add(k,nums[i]);
                    ans.add(temp);
                }
                ans.remove(0);
            }

        }
        return ans;
    }
    public static void main(String[] args) {
        int []nums = {1,2,3};
        List<List<Integer>> ans = permute(nums);
        System.out.println(ans);
    }
}
