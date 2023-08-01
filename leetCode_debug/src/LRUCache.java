import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Deque<Integer> deque;
    private Map<Integer , Integer> map;
    private int size;
    public LRUCache(int capacity){
        size = capacity;
        deque = new ArrayDeque<>(capacity);
        map = new HashMap<>(capacity);
    }
    public int get(int key){
        int ans = map.getOrDefault(key,-1);
        if(ans > -1){
            deque.remove(key);
            deque.add(key);
        }
        System.out.println(ans);
        return ans;
    }
    public void put(int key, int value){
        int ans = map.getOrDefault(key,-1);
        if(ans > 0){ //已经存在了
            deque.remove(key);
        }else if(deque.size() == size){ //不存在且已满了，置换
            Integer temp = deque.pollFirst();
            map.remove(temp);
        }
        deque.add(key);
        map.put(key,value);
        System.out.println("null");
    }

    public static void main(String[] args) {
        LRUCache solution = new LRUCache(2);
        solution.put(1,0);
        solution.put(2,2);
        solution.get(1);
        solution.put(3,3);
        solution.get(2);
        solution.put(4,4);
        //定义入参

        //输入入参
        System.out.println();
    }
}
