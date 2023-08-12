import java.util.Arrays;

public class solution_289 {
    public void gameOfLife(int[][] board) {
        int m  = board.length , n = board[0].length;
        int [][]temp = new int[m][n];
        for(int i = 0 ; i < m ; i++)
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 1)
                    record(temp, i , j);
            }
        System.out.println(Arrays.deepToString(temp));
        for(int i = 0 ; i < m ; i++)
            for(int j = 0 ; j < n ; j++){
                if(temp[i][j] == 3)
                    board[i][j] = 1;
                else if (board[i][j] == 1 && temp[i][j] == 2) {
                    board[i][j] = 1;
                }else
                    board[i][j] = 0;
            }
    }
    private void record (int [][] temp, int i, int j){
        if(i - 1 > -1 && j - 1 > -1)
            temp[i-1][j-1]++;
        if(i - 1 > -1)
            temp[i-1][j]++;
        if(i - 1 > -1 && j + 1 < temp[0].length)
            temp[i-1][j+1]++;
        if(j - 1 > -1)
            temp[i][j-1]++;
        if(j + 1 < temp[0].length)
            temp[i][j+1]++;
        if(i + 1 < temp.length && j - 1 > -1)
            temp[i+1][j-1]++;
        if(i + 1 < temp.length)
            temp[i+1][j]++;
        if(i + 1 < temp.length && j + 1 < temp[0].length)
            temp[i+1][j+1]++;
    }

    public static void main(String[] args) {
        solution_289 solution = new solution_289();

        //定义入参
        int [][]board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        //输入入参
        solution.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }
}
