import java.util.ArrayList;
import java.util.Stack;

public class EulerCycle {
    private Graph graph;
    public EulerCycle(Graph g){
        this.graph = new Graph(g);
    }
    public ArrayList<Integer> findCycle(){
        if (!isEuler()){
            return null;
        }
        Stack<Integer> s = new Stack<>();
        ArrayList<Integer> arrayList = new ArrayList();
        s.push(1);
        while (!s.isEmpty()){
            int u = s.peek();
            if (graph.deg(u) == 0){
                s.pop();
                arrayList.add(u);
            }
            else {
                int v = graph.getEdges(u).iterator().next().getDst();//
                s.push(v);
                graph.removeEdge(u,v);
            }
        }
        return arrayList;
    }
    private boolean isEuler() {
        return true;
    }
    public static void main(String[] args){
        Graph g = new Graph();
        for (int i = 0;i<6;i++){
            g.addNode();
        }
        g.add2Edge(1,3);
        g.add2Edge(1,6);
        g.add2Edge(1,2);
        g.add2Edge(6,2);
        g.add2Edge(1,5);
        g.add2Edge(2,3);
        g.add2Edge(2,5);
        g.add2Edge(3,5);
        g.add2Edge(3,4);
        g.add2Edge(5,4);
        System.out.println("Euler Circle: " + new EulerCycle(g).findCycle().toString()); // Euler Circle: [1, 5, 4, 3, 5, 2, 6, 1, 2, 3, 1]
    }
}

