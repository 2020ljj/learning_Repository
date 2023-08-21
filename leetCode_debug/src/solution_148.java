//public class solution_148 {
//    public ListNode1 sortList(ListNode1 head){
//        if(head == null || head.next == null)
//            return head;
//        else if(head.next.next == null){
//            ListNode1 bHead = head.next;
//            head.next = null;
//            return mergeList(head,bHead);
//        }
//        ListNode1 fast = head , slow = head;
//        while(fast != null && fast.next != null){
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        ListNode1 aHead = head, bHead = slow.next;
//        slow.next = null;
//        aHead = sortList(aHead);
//        bHead = sortList(bHead);
//        return mergeList(aHead, bHead);
//    }
//    public ListNode1 mergeList(ListNode1 aHead, ListNode1 bHead){
//        if(aHead == null)
//            return bHead;
//        else if(bHead == null)
//            return aHead;
//        if(aHead.val < bHead.val)
//            aHead.next = mergeList(aHead.next,bHead);
//        else
//            bHead.next = mergeList(aHead,bHead.next);
//        return aHead.val < bHead.val?aHead:bHead;
//    }
//    public static void main(String[] args) {
//        solution_148 solution = new solution_148();
//
//        //定义入参
//
//        //输入入参
//        System.out.println();
//    }
//}
