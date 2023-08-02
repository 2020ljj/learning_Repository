import java.util.HashMap;
import java.util.Map;

/**
 * PASS-2023-8-2
 */
public class solution_105 {
    int []preorder;
    int []inorder;
    Map<Integer ,Integer> map = new HashMap<>();
    public TreeNode buildTree(int []Preorder , int []Inorder){
        this.inorder = Inorder;
        this.preorder = Preorder;
        for(int i = 0; i < inorder.length ; i++){
            map.put(inorder[i],i);
        }
        return buildTreeHelper(0,preorder.length - 1,0,inorder.length - 1);
    }
    public TreeNode buildTreeHelper(int p_s,int p_e , int i_s , int  i_e){
        //中间结点是preorder[p_s]
        int middle = map.get(preorder[p_s]);
        TreeNode tmp = new TreeNode(preorder[p_s]);
        int leftLength = middle - i_s;
        int rightLength = i_e - middle;
        tmp.left = leftLength > 0 ? buildTreeHelper(p_s + 1 ,p_s + leftLength , i_s , middle - 1):null;
        tmp.right = rightLength > 0 ? buildTreeHelper(p_s + leftLength + 1 , p_e , middle + 1 , i_e ):null;
        return tmp;
    }


    public static void main(String[] args) {
        solution_105 solution = new solution_105();

        //定义入参
        int []preorder = {3,9,20,15,7};
        int []inorder = {9,3,15,20,7};
        //输入入参
        TreeNode ans  = solution.buildTree(preorder,inorder);
        System.out.println(ans);
    }
}
