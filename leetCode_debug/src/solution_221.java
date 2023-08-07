import java.util.Arrays;

public class solution_221 {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length , n = matrix[0].length;
        int [][]temp = new int[m][n];
        for(int i = 0 ; i < m ; i++)
            for( int j = 0 ; j < n ; j++){
                temp[i][j] = matrix[i][j] == '1'?1:0;
            }
        int max_side = 0,min = 0;
        for(int i = 0 ; i < m ; i++)
            for( int j = 0 ; j < n ; j++){
                if(i == 0 || j == 0){
                    max_side = Math.max(max_side , temp[i][j]);
                }
                else {
                    min = Math.min(Math.min(temp[i-1][j],temp[i][j-1]),temp[i-1][j-1]);
                    if(temp[i][j] > 0 && min > 0){
                        temp[i][j] = min + 1;
                    }
                    max_side = Math.max(max_side,temp[i][j]);
                }
            }
        return max_side*max_side;
    }

    public static void main(String[] args) {
        solution_221 solution = new solution_221();

        //定义入参
        char [][]matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        //输入入参
        int ans = solution.maximalSquare(matrix);
        System.out.println(ans);
    }
}
