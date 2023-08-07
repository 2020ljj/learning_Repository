import java.util.Arrays;

public class solution_72 {
    public int minDistance(String word1, String word2) {
        int m = word1.length() , n = word2.length();
        char []Word1 = word1.toCharArray() , Word2 = word2.toCharArray();
        int [][]f = new int [m+1][n+1];
        for(int i = 0 ; i<=m ; i++)
            f[i][0] = i;
        for(int j = 0 ; j<=n ; j++)
            f[0][j] = j;
        for(int i = 1 ; i<=m ; i++)
            for(int j = 1 ; j<=n ; j++){
                if(Word1[i-1] == Word2[j-1])
                    f[i][j] = f[i-1][j-1];
                else
                    f[i][j] = Math.min(Math.min(f[i-1][j],f[i][j-1]),f[i-1][j-1]) + 1;
            }
        System.out.println(Arrays.deepToString(f));
        return f[m][n];
    }
    public static void main(String[] args) {
        solution_72 solution = new solution_72();

        //定义入参
        String w1 = "horse",w2 = "ros";
        //输入入参
        int ans = solution.minDistance(w1,w2);
        System.out.println(ans);
    }
}
