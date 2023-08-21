import java.util.Locale;

public class solution_125 {

    public boolean isPalindrome(String s) {
        int n = s.length();
        int i = 0 , j = n - 1;
        s = s.toLowerCase();
        while(i < j){
            if((s.charAt(i) < 'a' || s.charAt(i) > 'z')&&(s.charAt(i) < '0' || s.charAt(i) > '9') ){
                i++;
            } else if ((s.charAt(j) < 'a' || s.charAt(j) > 'z') &&(s.charAt(j) < '0' || s.charAt(j) > '9')){
                j--;
            }else if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }else
                break;
        }
        System.out.println(i+":"+j);
        return i >= j ;
    }

    public static void main(String[] args) {
        solution_125 solution = new solution_125();

        //定义入参
        ;
        //输入入参
        System.out.println(solution.isPalindrome("0P0"));
    }
}
