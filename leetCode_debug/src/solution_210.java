import java.util.*;

public class solution_210 {
    public int[] findOrder(int numCourses,int[][] prerequisites){
        List<ArrayList<Integer>> collar = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int []inDegree = new int[numCourses];
        int []courseLine = new int[numCourses];
        int ans = numCourses , j = 0;
        for(int i = 0; i < numCourses ; i++)
            collar.add(new ArrayList<>());
        for(int []temp:prerequisites){
            inDegree[temp[0]]++;
            collar.get(temp[1]).add(temp[0]);
        }
        for(int i = 0 ; i<numCourses ; i++){
            if(inDegree[i] == 0)
                queue.add(i);
        }
        while(!queue.isEmpty()){
            int start = queue.poll();
            courseLine[j++] = start;
            ans--;
            for(int cur:collar.get(start)){
                if(--inDegree[cur] == 0)
                    queue.add(cur);
            }
        }
        return ans == 0?courseLine:new int[0];
    }
    public static void main(String[] args) {
        solution_210 solution = new solution_210();

        //定义入参
        int numCourses = 4;
        int [][]prerequisites = {{1,0},{2,0},{3,1},{3,2}};

        //输入入参
//        Scanner scanner = new Scanner(System.in);
//
//        int numCourses1 = scanner.nextInt();
//        int [][]prerequisites1 = new int[numCourses1][];
//        List<Integer> list= new ArrayList<>();
//
//        while(true){
//            String string = scanner.nextLine();
//            if(string=="") break;
//            String[] arrstring=string.split(" ");
//            for(int i=0;i<arrstring.length;i++){
//                list.add(Integer.parseInt(arrstring[i]));//把字符型转变为整型
//            }
//        }

        int []ans = solution.findOrder(numCourses,prerequisites);
        System.out.println(Arrays.toString(ans));
    }
}
