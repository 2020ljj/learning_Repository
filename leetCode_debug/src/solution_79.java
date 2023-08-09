import java.util.Arrays;

public class solution_79 {
    String word;
    int [][]visited;
    char [][]board;
    public boolean exist(char [][]board , String word){
        this.board = board;
        this.word = word;
        int m = board.length , n = board[0].length;
        visited = new int[m][n];
        for(int i = 0 ; i<m ; i++)
            for(int j = 0 ; j<n ;j++){
                if(board[i][j] == word.charAt(0) && search(i,j,0))
                    return true;
            }
        return false;
    }
    public boolean search(int i,int j,int k){
        if(i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != word.charAt(k) ||visited[i][j] == 1)
            return false;
        else if(k == word.length() - 1)
            return true;
        else{
            visited[i][j] = 1;
            boolean flag = search(i-1 ,j ,k+1) || search(i+1,j,k+1) || search(i , j+1, k+1) || search(i,j-1,k+1);
            visited[i][j] = 0;
            return flag;
        }
    }

    public static void main(String[] args) {
        solution_79 solution = new solution_79();

        //定义入参
//        char [][]board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char [][]board = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
        String word = "AAB";
        //输入入参
        boolean ans = solution.exist(board,word);
        //boolean ans1 = solution.search(board,1,1,0);
        System.out.println(ans);
    }
}
