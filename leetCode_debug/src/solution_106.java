import java.util.HashMap;
import java.util.Map;

public class solution_106 {
    int []postorder;
    int []inorder;
    Map<Integer ,Integer> map = new HashMap<>();
    public TreeNode buildTree(int []Inorder ,int []Postorder ){
        this.inorder = Inorder;
        this.postorder = Postorder;
        for(int i = 0; i < inorder.length ; i++){
            map.put(inorder[i],i);
        }
        return buildTreeHelper(0,inorder.length - 1,0,postorder.length - 1);
    }
    public TreeNode buildTreeHelper(int i_s , int  i_e,int p_s,int p_e){
        int middle = map.get(postorder[p_e]);
        TreeNode tmp = new TreeNode(postorder[p_e]);
        int leftLength = middle - i_s;
        int rightLength = i_e - middle;
        tmp.left = leftLength > 0 ? buildTreeHelper(i_s , middle - 1 , p_s , p_s + leftLength - 1):null;
        tmp.right = rightLength > 0 ? buildTreeHelper(middle + 1 , i_e , p_s + leftLength , p_e - 1):null;
        return tmp;
    }
    public static void main(String[] args) {
        solution_106 solution = new solution_106();

        //定义入参
        int []inorder = {9,3,15,20,7};
        int []postorder = {9,15,7,20,3};
        //输入入参
        System.out.println();
    }
}
