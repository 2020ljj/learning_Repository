public class solution_122 {
    public int maxProfit(int[] prices) {
        int n = prices.length , benefit = 0;
        if(n == 1)
            return benefit;
        int buy = 0, sell = 1;
        while(sell < n){
            if(prices[buy] < prices[sell]){
                benefit += prices[sell] - prices[buy];
            }
            buy++;
            sell++;
        }
        return benefit;
    }

    public static void main(String[] args) {
        solution_122 solution = new solution_122();

        //定义入参

        //输入入参
        System.out.println();
    }
}
