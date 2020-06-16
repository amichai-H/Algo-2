import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private Graph graph;
    private int size;
    private enum Color
        {
            WHITE,
            GRAY,
            BLACK
        }
    private Color[] color;
    private int[] d;
    private int[] pi;
    public BFS(Graph g){
        this.graph = g;
    }
    private void reInit() {
        this.size = graph.size();
        color = new Color[size];
        for (int i = 0 ; i<color.length;i++) {
            color[i] = Color.WHITE;
        }
        d = new int[size];
        for (int i = 0 ; i<d.length;i++){
            d[i] = Integer.MAX_VALUE;
        }
        pi = new int[size];
        for (int i = 0 ; i<pi.length;i++){
            pi[i] = -1;
        }
    }
    public void bfs(int id) {
        reInit();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        color[id] = Color.GRAY;
        d[id] = 0;
        queue.add(id);
        while (!queue.isEmpty()){
            int v = queue.poll();
            Collection<edge_data> temp = graph.getEdges(v);
            if (temp != null)
                for (edge_data e: temp){
                    int u = e.getDst();
                    if (color[u] == Color.WHITE){
                        color[u] = Color.GRAY;
                        d[u] = d[v] + 1;
                        pi[u] = v;
                        queue.add(u);
                    }
                }
            color[v] = Color.BLACK;
        }

    }
    public boolean connected(){
        bfs(1);
        int sum = 0;
        for (int i:pi){
            if (i == -1)
                sum++;
        }
        return sum == 2;//include in place 0
    }
    private void reBfsNoReinit(int id) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        color[id] = Color.GRAY;
        d[id] = 0;
        queue.add(id);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            Collection<edge_data> temp = graph.getEdges(v);
            if (temp != null)
                for (edge_data e : temp) {
                    int u = e.getDst();
                    if (color[u] == Color.WHITE) {
                        color[u] = Color.GRAY;
                        d[u] = d[v] + 1;
                        pi[u] = v;
                        queue.add(u);
                    }
                }
            color[v] = Color.BLACK;
        }
    }
    private void fillTable(int[] what, int sine){
        for (int i = 1; i<what.length;i++){
            if (color[i]==Color.BLACK && what[i] == 0)
                what[i] = sine;
        }
    }
    public int linksCounter(){
        bfs(1);
        if (connected()) return 1;
        //not connected --> more than one
        int sum = 1;
        int[] what = new int[size];
        fillTable(what,sum);
        for (int i = 1; i < color.length; i++){
            if (color[i] == Color.WHITE){
                sum ++;
                reBfsNoReinit(i);
                fillTable(what,sum);
            }
        }
        System.out.println(Arrays.toString(what));
        return sum;
    }
    private int getMaxD(boolean maxORindex){
        int max = -1;
        int index = -1;
        for (int i = 1;i < d.length;i++){
            if (d[i] > max) {
                max = d[i];
                index = i;
            }
        }
        if (maxORindex)
            return max;
        else return index;
    }
    public int findDiam(){ //O(|V| * |V+E|)
        int max = -1;
        for (int i = 1; i <size;i++){
            bfs(i);
            int temp = getMaxD(true);
            if (max < temp)
                max = temp;
        }
        return max;
    }
    public int findDiamEfficient(){// O(|V+E|)
        bfs(1);
        int indexMax = getMaxD(false);
        bfs(indexMax);
        return getMaxD(true);


    }

    public static void main(String [] args){
        Graph g = new Graph();
        for (int i=0; i<7 ; i++)
            g.addNode();
        g.add2Edge(1,2);
        g.add2Edge(2,3);
        g.add2Edge(3,4);
        g.add2Edge(1,5);
        g.add2Edge(1,6);
        g.add2Edge(6,7);

        BFS bfs1 = new BFS(g);
        System.out.println(bfs1.linksCounter());
        System.out.println("diam = " + bfs1.findDiam()); // (diam = 5) 4--3--2--1--6--7
        System.out.println("diam = " +bfs1.findDiamEfficient() +" but in O(|V+E|)");

    }


}
