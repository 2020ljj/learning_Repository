import java.util.Arrays;

/**
 * PASS-2023-7-27
 */
public class solution_130 {
    int m,n;
    public void solve(char [][]board){
        m = board.length;
        n = board[0].length;
        int i = 0, j = 0;
        for(j = 0 ; j < n ; j++){
            search(board,i,j);
        }
        i = m-1;
        for(j = 0 ; j < n ; j++){
            search(board,i,j);
        }
        j = 0;
        for(i = 0 ; i < m ; i++){
            search(board,i,j);
        }
        j = n-1;
        for(i = 0 ; i < m ; i++){
            search(board,i,j);
        }
        for(i = 0 ; i < m ; i++){
            for(j = 0 ; j < n ; j++){
                if(board[i][j] == 'P')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    private void search(char [][]board, int i, int j){
        if(i < 0 || i > m-1 || j < 0 || j > n-1 || board[i][j] != 'O'){
            return;
        }
        board[i][j] = 'P';
        //按 下 - 右 - 上 - 左 顺序搜索
        search(board, i+1,j);
        search(board, i,j + 1);
        search(board, i - 1,j);
        search(board, i,j - 1);
    }
    public static void main(String[] args) {
        solution_130 solution = new solution_130();
        /**
         * 定义入参
         */
        char [][]board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        /**
         * 输入入参
         */
//        Scanner scanner = new Scanner(System.in);
//        int n = 0;
//        n = scanner.nextInt();
//        char [][]board = new char[n][n];
//        for(int i = 0 ; i < n ; i++){
//            for(int j = 0 ; j < n ; j++){
//                board[i][j] = scanner.next().charAt(0);
//            }
//        }
        solution.solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}
