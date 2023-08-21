import java.util.Arrays;

public class solution_123 {
    private int getMin(int[] prices ,int i , int j){
        int ans = prices[i];
        while(i <= j){
            ans = Math.min(ans , prices[i]);
            i++;
        }
        return ans;
    }
    private void max(int[] prices ,int[][][] benefit , int i , int j){
        if(i == j)
            benefit[i][j][0] = 0;
        else if (i == j - 1) {
            benefit[i][j][0] = prices[j] - prices[i];
        } else{
            int k = i;
            while(k < j){
                benefit[i][j][1] = Math.max(benefit[i][j][1] , benefit[i][k][0] + benefit[k + 1][j][0]);
                benefit[i][j][0] = Math.max(benefit[i][k][0] , prices[j] - getMin(prices , i , k));
                k++;
            }
        }
    }
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int [][][]benefit = new int[n][n][2];
        int i = 0 , j = 0 , k = 0;
        while(k < n){
            while(i < n && j < n){
                max(prices, benefit , i , j);
                i++;
                j++;
            }
            k++;
            i = 0;
            j = i + k;
        }
        //System.out.println(Arrays.deepToString(benefit));
        return Math.max(benefit[0][n-1][0] , benefit[0][n-1][1]);
    }
    public static void main(String[] args) {
        solution_123 solution = new solution_123();

        //定义入参
        int []prices = {3,3,5,0,0,3,1,4};
        //输入入参
        System.out.println(solution.maxProfit(prices));
    }
}
