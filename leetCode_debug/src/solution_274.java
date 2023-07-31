import java.util.Arrays;

/**
 * PASS-2023-7-31
 */
public class solution_274 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    public static void main(String[] args) {
        solution_274 solution = new solution_274();
        int []citations = {6,6,5,3,1,0};
        int ans = solution.hIndex(citations);
        System.out.println(ans);
        //定义入参

        //输入入参
        System.out.println();
    }
}
