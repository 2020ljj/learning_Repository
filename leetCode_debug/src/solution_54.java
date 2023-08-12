import java.util.ArrayList;
import java.util.List;

public class solution_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length , n = matrix[0].length , i = 0, j = 0;
        List<Integer> ans = new ArrayList<>();
        while(ans.size() < m*n ){
            while(j < n && matrix[i][j] != -101){
                ans.add(matrix[i][j]);
                matrix[i][j] = -101;
                j++;
            }
            j--;i++;
            while(i < m && matrix[i][j] != -101){
                ans.add(matrix[i][j]);
                matrix[i][j] = -101;
                i++;
            }
            i--;j--;
            while(j > -1 && matrix[i][j] != -101){
                ans.add(matrix[i][j]);
                matrix[i][j] = -101;
                j--;
            }
            j++;i--;
            while(i > -1 && matrix[i][j] != -101){
                ans.add(matrix[i][j]);
                matrix[i][j] = -101;
                i--;
            }
            i++;j++;
        }
        return  ans;
    }
    public static void main(String[] args) {

        solution_54 solution = new solution_54();

        //定义入参
        int [][]list = {{1,2,3},{4,5,6},{7,8,9}};
        //输入入参
        System.out.println(solution.spiralOrder(list));
    }
}
