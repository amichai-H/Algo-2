import java.util.Stack;

public class EulerCycle {
    private Graph graph;
    public EulerCycle(Graph g){
        this.graph = new Graph(g);
    }
    public Stack findCycle(){
        if (!isEuler()){
            return null;
        }
        Stack<Integer> s = new Stack<>() , c = new Stack<>();
        s.push(1);
        while (!s.isEmpty()){
            int u = s.peek();
            if (graph.deg(u) == 0){
                s.pop();
                c.push(u);
            }
            else {
                int v = graph.getEdges(u).iterator().next().getDst();
                s.push(v);
                graph.removeEdge(u,v);

            }

        }
        return c;

    }
    private boolean isEuler() {
        return true;
    }
    public static void main(String[] args){
        Graph g = new Graph();
        for (int i = 0;i<5;i++){
            g.addNode();
        }
        g.add2Edge(1,3);
        g.add2Edge(1,2);
        g.add2Edge(1,5);
        g.add2Edge(2,3);
        g.add2Edge(2,5);
        g.add2Edge(3,5);
        g.add2Edge(3,4);
        g.add2Edge(5,4);
        System.out.println(new EulerCycle(g).findCycle().toString()); // [2, 5, 4, 3, 5, 1, 2, 3, 1]
    }
}
