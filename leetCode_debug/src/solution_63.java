public class solution_63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length , n = obstacleGrid[0].length;
        for(int i = 0 ; i<m ; i++)
            for(int j = 0 ; j<n ; j++){
                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = -1;
                    continue;
                }
                if(i == 0 && j == 0)
                    obstacleGrid[i][j] = 1;
                else if (i == 0)
                    obstacleGrid[i][j] = obstacleGrid[i][j-1];
                else if (j == 0)
                    obstacleGrid[i][j] = obstacleGrid[i-1][j];
                else
                    obstacleGrid[i][j] = Math.max(obstacleGrid[i-1][j],0) + Math.max(obstacleGrid[i][j-1],0);
            }
        return Math.max(obstacleGrid[m-1][n-1],0);
    }
    public static void main(String[] args) {
        solution_63 solution = new solution_63();

        //定义入参
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        //输入入参
        int ans  = solution.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(ans);
    }
}
