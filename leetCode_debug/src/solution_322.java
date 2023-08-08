import java.util.HashMap;
import java.util.Map;

public class solution_322 {
    private Map<Integer ,Integer> map = new HashMap<>();
    public int coinChange(int coins[] , int amount){
        for(int i : coins)
            map.put(i,1);
        int []ans = new int[amount + 1];
        ans[0] = 0;
        ans[1] = map.getOrDefault(1,-1);
        if(amount<2)
            return ans[amount];
        for(int i = 2 ; i < amount+2 ; i++){
            ans[i] = ans[i-1] + ans[i-2];
        }
        return 0;
    }

    /**
     *     0 1 2 3 4 5 6 7 8 9
     *  0  0 0 0 0
     *  1    1 2 3 4
     *  3        1
     *  5           1
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        solution_322 solution = new solution_322();

        //定义入参
        int []coins = {1,2,5};
        int amount = 11;
        //输入入参
        solution.coinChange(coins,amount);
        System.out.println();
    }
}
