import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
public class TreeIsomorphism {
    class Node implements Comparable<Node>{
        Integer length;
        int index;
        Node(int index,int length){
            this.index = index;
            this.length = length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Node o) {
            return o.length-length;
        }
    }

    public String toCode(int[] tree, int root){
        String[] ans = new String[tree.length];
        AHU(ans,tree,root,null);
        return ans[root];
    }
    public void AHU(String[] phi, int[] tree, int v,PriorityQueue<Node> insert_me){
        if (isLeaf(tree,v)){
            insert_me.add(new Node(v,2));
            phi[v] =  "10";
            return;
        }
        PriorityQueue<Node> queue1 = new PriorityQueue<>();
        for (int i = 0; i<tree.length;i++){
            if (tree[i] == v) {
                AHU(phi, tree, i,queue1);
            }
        }
        phi[v] = "1";
        while (!queue1.isEmpty()) {
            int max = queue1.poll().getIndex();
            phi[v] = phi[v] + phi[max];
        }//best sort
        phi[v] = phi[v] + "0";
        if (insert_me!=null)
            insert_me.add(new Node(v,phi[v].length()));

    }

    private static boolean isLeaf(int[] tree, int v) {
        for (int i =0; i<tree.length;i++){
            if (tree[i] == v)
                return false;
        }
        return true;
    }
    public boolean isomorphism(int[] tree1, int root1, int[] tree2, int root2){
        return toCode(tree1,root1).equals(toCode(tree2,root2));
    }
    public static void main(String [] args){
        int[] i = {2,2,3,4,-1,4,4,6,6};
        int[] j = {1,8,1,5,5,6,-1,6,6};
        System.out.println("111101000110100100".equals(new TreeIsomorphism().toCode(i,4)));
        System.out.println("the code is: " + new TreeIsomorphism().toCode(i,4));
        System.out.println(new TreeIsomorphism().isomorphism(i,4,j,6));
    }
}
