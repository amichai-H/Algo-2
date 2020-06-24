import java.util.Iterator;
import java.util.Stack;

public class Dfs {
    Graph graph;
    int n ;
    int[] pred;
    int[] start;
    int[] end ;
    private enum Color
    {
        WHITE,
        GRAY,
        BLACK
    }

    public Dfs(Graph g){
        this.graph = new Graph(g);
        this.n =  graph.size();
        this.pred = new int[n];
        this.start = new int[n];
        this.end = new int[n];
        dfsAt(1);
    }
    public void dfsAt(int u){
        Stack<Integer> stack = new Stack<>();
        Iterator<edge_data>[] iters = new Iterator[n];
        for (int i=1; i<n;i++){
            iters[i] = graph.getEdges(i).iterator();
        }
        boolean hasCycle = false;
        int startCycle;
        int endCycle;
        Color[] visted = new Color[n];
        for (int i = 1; i <n; i++){
            visted[i] = Color.WHITE;
        }
        int time = 1;
        stack.push(u);
        while (!stack.isEmpty()){
            u = stack.peek();
            visted[u] = Color.GRAY;
            start[u] = time++;
            if (iters[u].hasNext()){
                int v = iters[u].next().getDst();
                if (visted[v]==Color.WHITE){
                    visted[v] = Color.GRAY;
                    pred[v] = u;
                    start[v] = time++;
                    stack.push(v);
                }
                else if(!hasCycle &&visted[v]==Color.GRAY && pred[u] != v) {
                    hasCycle = true;
                    startCycle = u;
                    endCycle = v;
                }
            }
            else {
                end[u] = time++;
                visted[u] = Color.BLACK;
                stack.pop();
                System.out.print( "<-"+ u);
            }
        }
        System.out.println(" cycle? "+hasCycle);

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
        g.add2Edge(7,4);
//        System.out.println(g.getEdges(1).iterator().hasNext());
        Dfs dfs = new Dfs(g);

    }
}
