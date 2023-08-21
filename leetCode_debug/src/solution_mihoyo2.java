//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//class ListNode1 {
//    int val;
//    ListNode1 next;
//    ListNode1(){};
//    ListNode1(int val){this.val = val;};
//    ListNode1(int val, ListNode1 next){this.val = val;this.next = next;};
//}
//public class solution_mihoyo2 {
//
//    private void dfs(int min , List<List<Integer>> nextNode, List<Integer> minlength,int start){
//        for(int i = 0 ; i<nextNode.get(start).size() ; i++){
//            int temp_min = min + 1;
//            if(minlength.get(nextNode.get(start).get(i)) == 0)
//                minlength.set(nextNode.get(start).get(i) , temp_min);
//            else{
//                temp_min = Math.min(min + 1, minlength.get(nextNode.get(start).get(i)));
//                minlength.set(nextNode.get(start).get(i) , temp_min);
//            }
//            dfs(temp_min , nextNode , minlength , nextNode.get(start).get(i));
//        }
//    }
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        int n  = 0 , k = 0;
//        n = in.nextInt();k = in.nextInt();
//        int i = 0;
//        Map<Integer, ListNode1> map = new HashMap<>();
//        while (i < n) { // 注意 while 处理多个 case
//            int start = in.nextInt();
//            int end = in.nextInt();
//            if(!map.containsKey(start)){
//                map.put(start , new ListNode1(end));
//            }
//            else{
//                ListNode1 head = map.get(start);
//            }
//            i++;
//        }
//        solution_mihoyo2 solution = new solution_mihoyo2();
//        solution.dfs(0, nextNode , minLength,1);
//        int ans  = 0;
//        for(int u = 1 ; u < n + 1 ; u++){
//            if(nextNode.get(u).size() == 0){
//                if(minLength.get(u) < k)
//                    ans += k - u;
//            }
//        }
//        System.out.println(ans);
//    }
//}
