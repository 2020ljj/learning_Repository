import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * pass-2023-8-6
 */
public class solution_97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length(), p;
        if (n + m != t) {
            return false;
        }
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if(i > 0)
                     f[i][j] = f[i - 1][j]&&(s1.charAt(i-1) == s3.charAt(i + j - 1));
                if(j > 0)
                     f[i][j] = f[i][j]||(f[i][j - 1]&&(s2.charAt(j-1) == s3.charAt(i + j - 1)));
            }
        }
        System.out.println(Arrays.deepToString(f));
        return f[n][m];
    }

    public static void main(String[] args) {
        solution_97 solution = new solution_97();

        //定义入参
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        //输入入参
        boolean ans = solution.isInterleave(s1,s2,s3);
        System.out.println(ans);
    }
}
