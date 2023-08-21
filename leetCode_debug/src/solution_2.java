///**
// * PASS-2023-8-1
// */
//public class solution_2 {
//    public ListNode1 addTwoNumbers(ListNode1 l1, ListNode1 l2){
//        int sum = 0;
//        ListNode1 head = l1,l1Last = l1;
//        while(l1!=null && l2!=null){
//            sum += l1.val + l2.val;
//            l1.val = sum % 10;
//            l2.val = l1.val;
//            sum /= 10;
//            l1Last = l1;
//            l1 = l1.next;
//            l2 = l2.next;
//        }
//        if(l2 != null){
//            l1Last.next = l2;
//            l1 = l2;
//        }
//        while (l1 != null){
//            sum += l1.val;
//            l1.val = sum % 10;
//            sum /= 10;
//            l1Last = l1;
//            l1 = l1.next;
//        }
//        if(sum > 0){
//            l1Last.next = new ListNode1(sum);
//        }
//        return head;
//    }
//    public static void main(String[] args) {
//        solution_2 solution = new solution_2();
//
//        //定义入参
//
//        //输入入参
//        System.out.println();
//    }
//}

