/***
 * Pass-2023-8-6
 */
public class solution_64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0 ; i<m ; i++)
            for(int j = 0 ; j<n ; j++){
                if(i == 0 && j == 0)
                    continue;
                else if(i == 0)
                    grid[i][j] += grid[i][j-1];
                else if(j == 0)
                    grid[i][j] += grid[i-1][j];
                else
                    grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
            }
        return grid[m-1][n-1];
    }
    public static void main(String[] args) {
        solution_64 solution = new solution_64();

        //定义入参

        //输入入参
        System.out.println();
    }
}
