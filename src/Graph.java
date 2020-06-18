import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Graph {
    private int id = 1;
    private HashMap<Integer, node_data> NMap = new LinkedHashMap<>();
    private HashMap<Integer,HashMap<Integer, edge_data>> EMap = new LinkedHashMap<>();
    private int sizeE = 0;
    public Graph(){}
    public Graph(Graph copy){
        for (node_data v: copy.NMap.values()) {
            this.NMap.put(v.getId(), new Node(v));
        }
        for (node_data v: copy.NMap.values()){
            if (copy.getEdges(v.getId())!=null)
                for (edge_data e:copy.getEdges(v.getId()))
                    add1Edge(e.getSrc(),e.getDst());
        }
        this.id = copy.id;
        this.sizeE = copy.sizeE;
    }

    public void addNode(){
        NMap.put(id,new Node(id));
        id++;
    }
    public void add2Edge(int src, int dst){
        sizeE++;
        add1Edge(src,dst);
        add1Edge(dst,src);
    }
    private void add1Edge(int src, int dst){
        if (NMap.get(src) == null || NMap.get(dst) == null){
            System.out.println("dst or src dont exist");
            return;
        }
        if (EMap.get(src) == null) {
            HashMap<Integer, edge_data> temp = new LinkedHashMap<>();
            temp.put(dst,new Edge(src,dst));
            EMap.put(src, temp);
        }
        else EMap.get(src).put(dst,new Edge(src,dst));
    }
    public Collection<edge_data> getEdges(int id){
        if (EMap.get(id) != null)
            return EMap.get(id).values();
        return null;
    }

    public int size() {
        return id;
    }

    public int getSizeE() {
        return sizeE;
    }
    public boolean isLeaf(int id){
        Collection<edge_data> temp = getEdges(id);
        return temp == null || temp.size() == 1;
    }
    public node_data removeNode(int id){
        node_data temp = NMap.remove(id);
        EMap.remove(id);
        for (HashMap<Integer, edge_data> lists : EMap.values()){
            lists.remove(id);
        }
        return temp;
    }

    public int deg(int u) {
        if(getEdges(u)==null) return 0;
        return getEdges(u).size();
    }

    public void removeEdge(int u, int v) {
        if (EMap.get(u) != null)
            EMap.get(u).remove(v);
        if (EMap.get(v) != null)
            EMap.get(v).remove(u);
    }
}
