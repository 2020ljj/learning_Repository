import java.util.Arrays;

public class solution_48 {
    public void rotate(int[][] matrix) {
        int m = matrix.length, temp;
        for(int i = 0 ; i < m ; i++)
            for(int j = i ; j < m ; j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        for(int i = 0 ; i < m ; i++)
            for(int j = 0 ; j < m/2 ; j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - j];
                matrix[i][m - j] = temp;
            }
    }
    public static void main(String[] args) {
        solution_48 solution = new solution_48();

        //定义入参

        //输入入参
        System.out.println();
    }
}
