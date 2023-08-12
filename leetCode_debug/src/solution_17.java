import java.util.*;

public class solution_17 {

    public List<String> letterCombinations(String digits){
        Map<Character,String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        Queue<String> temp = new ArrayDeque<>();
        int pre_length , j;
        String top , string;
        temp.add("");
        for(int i = 0 ; i< digits.length() ; i++){
            string = map.get(digits.charAt(i));
            pre_length = temp.size();
            j = 0;
            while(j < pre_length){
                top = temp.poll();
                for(int k = 0 ; k<string.length() ; k++){
                    temp.add(top + string.substring(k,k+1));
                }
                j++;
            }
        }
       return digits.equals("")?new ArrayList<>(): new ArrayList<>(temp);
    }

    public static void main(String[] args) {
        solution_17 solution = new solution_17();

        //定义入参
        String digits = "";
        //输入入参
        System.out.println(solution.letterCombinations(digits));
    }
}
