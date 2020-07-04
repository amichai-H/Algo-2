import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Revers_Kruskal {
    public static ArrayList<edge_data> find_min_span_Tree(Graph g) {
        ArrayList<edge_data> arrayList = new ArrayList<>();
        ArrayList<edge_data> Tree = new ArrayList<>();
        for (int i = 1; i < g.size(); i++) {
            for (Iterator<edge_data> it = g.getEdges(i).iterator(); it.hasNext(); ) {
                edge_data e = it.next();
                arrayList.add(e);
            }
        }
        arrayList.sort((edge_data d, edge_data x) -> x.getWeight() - d.getWeight());
        int i = 0;
        while (Tree.size() < g.size()-1){
            edge_data t  =arrayList.get(i);
            g.removeEdge(t.getSrc(),t.getDst());
            BFS bfs = new BFS(g);
            if (!bfs.connected()){
                Tree.add(t);
            }
            i++;
        }
        return Tree;
    }
}
