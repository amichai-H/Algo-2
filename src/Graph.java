import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Graph {
    private int id = 1;
    private HashMap<Integer, node_data> NMap = new LinkedHashMap<>();
    private HashMap<Integer,HashMap<Integer, edge_data>> EMap = new LinkedHashMap<>();

    public void addNode(){
        NMap.put(id,new Node(id));
        id++;
    }
    public void add2Edge(int src, int dst){
        add1Edge(src,dst);
        add1Edge(dst,src);
    }
    private void add1Edge(int src, int dst){
        if (NMap.get(src) == null || NMap.get(dst) == null){
            System.out.println("dst or src dont exist");
            return;
        }
        if (EMap.get(src) == null)
            EMap.put(src, (HashMap<Integer, edge_data>) new LinkedHashMap<>().put(dst,new Edge(src,dst)));
        else EMap.get(src).put(dst,new Edge(src,dst));
    }
    public Collection<edge_data> getEdges(int id){
        return EMap.get(id).values();
    }

    public int size() {
        return id;
    }
}
