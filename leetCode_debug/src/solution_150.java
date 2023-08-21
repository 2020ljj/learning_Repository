import java.util.Objects;
import java.util.Stack;

public class solution_150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int a , b , i = 1;
        stack.add(Integer.valueOf(tokens[0]));
        while(i < tokens.length){
            if(Objects.equals(tokens[i], "+")){
                a = stack.pop();
                b = stack.pop();
                stack.add(a + b);
            }else if (Objects.equals(tokens[i], "-")){
                a = stack.pop();
                b = stack.pop();
                stack.add(b - a);
            }
            else if (Objects.equals(tokens[i], "*")){
                a = stack.pop();
                b = stack.pop();
                stack.add(a * b);
            }
            else if (Objects.equals(tokens[i], "/")){
                a = stack.pop();
                b = stack.pop();
                stack.add(b / a);
            }
            else{
                stack.add(Integer.valueOf(tokens[i]));
            }
            i++;
        }
        return stack.pop();
    }
    public static void main(String[] args) {
        solution_150 solution = new solution_150();

        //定义入参

        //输入入参
        System.out.println();
    }
}
