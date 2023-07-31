import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class solution_207 {
    public boolean canFinish(int numCourses,int[][] prerequisites){
        int [][]coursesTable = new int[numCourses][numCourses];
        for (int[] prerequisite : prerequisites) {
            coursesTable[prerequisite[0]][prerequisite[1]] = 1;
        }
        List<Integer> finished = new ArrayList<>();
        for(int i = 0 ; i<numCourses; i++){
            int j;
            for(j = 0 ; j < numCourses ;j++){
                if(coursesTable[j][i] == 1)
                    break;
            }
            if(j == numCourses){
                finished.add(i);
                for(int t = 0 ; t < numCourses ; t++ )
                    coursesTable[i][t] = 0;
            }
            if(finished.size() == numCourses)
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        solution_207 solution = new solution_207();

        //定义入参
        int numCourses = 2;
        int [][]prerequisites = {{1,0}};

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

        boolean ans = solution.canFinish(numCourses,prerequisites);
        System.out.println(ans);
    }
}