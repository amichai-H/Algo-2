import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    Graph graph;
    private int size;
    enum Color
        {
            WHITE,
            GRAY,
            BLACK
        }
    private Color[] color;
    private int[] d;
    private int[] pi;
    public BFS(Graph g, int id){
        this.graph = g;
        this.size = graph.size();
        color = new Color[size+1];
        for (Color c:
             color) {
            c = Color.WHITE;
        }
        d = new int[size+1];
        for (int i:d){
            i = Integer.MAX_VALUE;
        }
        pi = new int[size+1];
        for (int i: pi){
            i = -1;
        }
        bfs(id);
    }

    private void bfs(int id) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        color[id] = Color.GRAY;
        d[id] = 0;
        queue.add(id);
        while (!queue.isEmpty()){
            int v = queue.poll();
            Collection<edge_data> temp = graph.getEdges(v);
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


}
