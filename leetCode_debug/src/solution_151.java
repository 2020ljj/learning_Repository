public class solution_151 {
    public String reverseWords(String s) {
        int n = s.length() , i = 0;
        StringBuilder ans = new StringBuilder();
        StringBuilder word = new StringBuilder();
        while(i < n && s.charAt(i) == ' '){
            i++;
        }
        while (i < n){
            if(s.charAt(i) != ' '){
                word.append(s.charAt(i));
            }
            else if(!word.isEmpty()){
                if(!ans.isEmpty()){
                    word.append(" ");
                }
                ans.insert(0,word);
                word.delete(0,word.length());
            }
            i++;
        }
        if(!word.isEmpty() && !ans.isEmpty())
            word.append(" ");
        ans.insert(0,word);
        return new String(ans);
    }
    public static void main(String[] args) {
        solution_151 solution = new solution_151();

        //定义入参
        System.out.println(solution.reverseWords("the"));
        //输入入参
        System.out.println();
    }
}
