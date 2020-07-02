import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Haffman {
    PriorityQueue<Node> queue1 = new PriorityQueue<>();
    PriorityQueue<Node> queue2 = new PriorityQueue<>();
    class Node implements Comparable<Node> {
        int frec;
        char c;
        boolean leaf;
        Node right,left;
        int index;
        String chars;
        Node(int index,char c,int frec,boolean leaf){
            this.index = index;
            this.frec = frec;
            this.c = c;
            this.leaf = leaf;
        }Node(char c,int frec,boolean leaf,Node left,Node right){
            this.frec = frec;
            this.c = c;
            this.leaf = leaf;
            this.left = left;
            this.right = right;
        }

        public void setChars(String chars) {
            this.chars = chars;
        }

        @Override
        public int compareTo(Node o) {
            return this.frec-o.frec;
        }
    }
    public void init(int[] frec, char[] chars){
        for (int i = 0; i <chars.length;i++){
            queue1.add(new Node(i,chars[i],frec[i],true));
        }
        Node temp1;
        Node temp2;
        Node root = null;
        if (!queue1.isEmpty())
            temp1 = queue1.poll();
        else return;
        if (!queue1.isEmpty())
            temp2 = queue1.poll();
        else return;
        queue2.add(new Node('\n',temp1.frec+temp2.frec,false,temp1,temp2));

        while (!queue1.isEmpty()){
            if (queue1.peek().compareTo(queue2.peek())<=0){
                temp1 = queue1.poll();
            }
            else temp1 = queue2.poll();
            if (queue2.isEmpty()&&!queue1.isEmpty()){
                temp2 = queue1.poll();
            }
            else if (!queue1.isEmpty()&&queue1.peek().compareTo(queue2.peek())<=0){
                temp2 = queue1.poll();
            }
            else temp2 = queue2.poll();
            root = new Node('\n',temp1.frec+temp2.frec,false,temp1,temp2);
            queue2.add(root);
        }
        String[][] mat = new String[chars.length][3];
        fillMat(root,mat);


    }

    private void fillMat(Node root, String[][] mat) {
        root.setChars("");
        fillRec(root,mat);
        printMat(mat);
    }

    private void fillRec(Node current,String[][] mat) {
        if( current == null) return;
        if (current.left!=null) {
            current.left.setChars(current.chars + "0");
        }
        if (current.right!=null) {
            current.right.setChars(current.chars + "1");
        }

        fillRec(current.right,mat);
        fillRec(current.left,mat);
        if(current.leaf){
            mat[current.index][0] = current.c+"";
            mat[current.index][1] = current.frec+"";
            mat[current.index][2] = current.chars+"";
        }
    }
    public static<T> void printMat(T mat[][]){
        for (int i = 0; i <mat.length;i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }
    public static void main(String[] args){
        int[] frec = {45,13,12,16,9,5};
        char[] c = {'a','b','c','d','e','f'};
        Haffman haffman = new Haffman();
        haffman.init(frec,c);
    }
}
