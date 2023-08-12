import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 1; i <= 6; ++i) {
                int nxt = p[0] + i;
                if (nxt > n * n) { // 超出边界
                    break;
                }
                int[] rc = id2rc(nxt, n); // 得到下一步的行列
                if (board[rc[0]][rc[1]] > 0) { // 存在蛇或梯子
                    nxt = board[rc[0]][rc[1]];
                }
                if (nxt == n * n) { // 到达终点
                    return p[1] + 1;
                }
                if (!vis[nxt]) {
                    vis[nxt] = true;
                    queue.offer(new int[]{nxt, p[1] + 1}); // 扩展新状态
                }
            }
        }
        return -1;
    }

    public int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }
}
public class solution_909 {
    public int snakesAndLadders(int [][]boards){
        int m = boards.length;
        int ans = m*m , topEnd ,topTimes, transfer;
        Queue<Integer> endQueue = new ArrayDeque<>();
        Queue<Integer> timesQueue = new ArrayDeque<>();
        List<int []> line = new ArrayList<>();
        line.add(new int[]{-10 , -10});
        for(int i = m - 1 ; i>-1 ; i--){
            if((m-i)%2 == 1)
                for(int j = 0 ; j<m ; j++){
                    line.add(new int[]{i, j});
                }
            else{
                for(int j = m-1 ; j>-1 ; j--){
                    line.add(new int[]{i, j});
                }
            }
        }
        endQueue.add(1);
        timesQueue.add(0);
        while(!endQueue.isEmpty()){
            topEnd = endQueue.poll();
            topTimes = timesQueue.poll();
            if(topEnd == m*m){
                ans = Math.min(ans,topTimes);
            }
            else if(topTimes < ans)
                for(int i = 1 ; i<7 && topEnd + i < m*m+1 ; i++){
                    transfer = boards[line.get(topEnd + i)[0]][line.get(topEnd + i)[1]];
                    if( transfer != -1){
                        endQueue.add(transfer);
                    }else{
                        endQueue.add(topEnd + i);
                    }
                    timesQueue.add(topTimes + 1);
                }
        }
        return ans == m*m? -1:ans;
    }

    public static void main(String[] args) {
        solution_909 solution = new solution_909();
        //定义入参
        int [][]boards = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        //输入入参
        int ans = solution.snakesAndLadders(boards);
        System.out.println(ans);
    }
}
