import java.util.Scanner;

public class solution_mihuyo {
    private int search (int m ,int n , int i_s , int j_s , int i_e, int j_e){
        int x_length = i_e - i_s , y_length = j_e - j_s;
        x_length = x_length > 0?x_length:x_length*-1;
        y_length = y_length > 0?y_length:y_length*-1;
        return Math.min(n - x_length , x_length) + Math.min(m - y_length , y_length);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int m = 0, n = 0 , x_m = 0,y_m = 0,x_y = 0,y_y = 0,x_a = 0,y_a = 0;
        n = in.nextInt();
        m = in.nextInt();
        x_m = in.nextInt()- 1;
        y_m = in.nextInt()- 1;
        x_y = in.nextInt()- 1;
        y_y = in.nextInt()- 1;
        x_a = in.nextInt()- 1;
        y_a = in.nextInt()- 1;
        solution_mihuyo solution = new solution_mihuyo();
        int ans = solution.search(m,n ,x_m , y_m , x_y , y_y) + solution.search(m,n , x_y , y_y , x_a , y_a);
        System.out.println(ans);
    }
}
