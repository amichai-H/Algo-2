import java.util.LinkedList;
import java.util.Queue;

public class TreeIsomorphism {
    public static String toCode(int[] tree,int root){
        String[] ans = new String[tree.length];
        AHU(ans,tree,root);
        return ans[root];
    }
    public static void AHU(String[] phi,int [] tree,int v){
        if (isLeaf(tree,v)){
            phi[v] =  "10";
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> temp = new LinkedList<>();
        for (int i = 0; i<tree.length;i++){
            if (tree[i] == v) {
                queue.add(i);
                AHU(phi, tree, i);
            }
        }
        phi[v] = "1";
        while (!queue.isEmpty()) {
            int max = queue.poll();
            while (!queue.isEmpty()) {
                int u = queue.poll();
                if (phi[max].length() < phi[u].length()) {
                    temp.add(max);
                    max = u;
                } else {
                    temp.add(u);
                }
            }
            queue = temp;
            temp = new LinkedList<>();
            phi[v] = phi[v] + phi[max];
        }//not the best sort could be better/
        phi[v] = phi[v] + "0";

    }

    private static boolean isLeaf(int[] tree, int v) {
        for (int i =0; i<tree.length;i++){
            if (tree[i] == v)
                return false;
        }
        return true;
    }
    public static boolean isomorphism(int[] tree1,int root1, int[] tree2,int root2){
        return toCode(tree1,root1).equals(toCode(tree2,root2));
    }
    public static void main(String [] args){
        int[] i = {2,2,3,4,-1,4,4,6,6};
        int[] j = {1,8,1,5,5,6,-1,6,6};
        System.out.println("111101000110100100".equals(toCode(i,4)));
        System.out.println("the code is: " + toCode(i,4));
        System.out.println(isomorphism(i,4,j,6));
    }
}
