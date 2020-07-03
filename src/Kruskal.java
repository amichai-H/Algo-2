
import javax.script.Compilable;
import java.util.*;

class EdgeW implements Comparable<EdgeW>  {
    private int src;
    private int dst;
    private int weight;
    public EdgeW(int src, int dst, int weight) {
        this.dst = dst;
        this.src = src;
        this.weight = weight;
    }

    public int getDst() {
        return dst;
    }

    public int getSrc() {
        return src;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(EdgeW o) {
        return weight-o.weight;
    }

    @Override
    public String toString() {
        return src+"->"+dst + " w:"+weight;
    }
}

public class Kruskal{
    private HashMap<Integer, HashMap<Integer, EdgeW>> G = new LinkedHashMap<>();
    public HashMap<Integer, HashMap<Integer, EdgeW>>
    kruskel(HashMap<Integer, HashMap<Integer, EdgeW>> g) throws Exception {
        HashMap<Integer, HashMap<Integer, EdgeW>> Tree = new LinkedHashMap<>();
        Union[] union = new Union[g.size()];
        for (int i = 0;i<g.size();i++) union[i] = new Union();
        PriorityQueue<EdgeW> listE_sorted = new PriorityQueue<>();
        for (int i = 0; i < g.size(); i++) {
            Collection<EdgeW> collection = g.get(i).values();
            for (EdgeW edgeW : collection) {
                listE_sorted.add(edgeW);
                g.get(edgeW.getDst()).remove(i);
            }
        }
        while (Tree.size()<g.size()){
            if (listE_sorted.isEmpty()) {
                return Tree;
            }
            EdgeW edgeW = listE_sorted.poll();
            int src = edgeW.getSrc(),dst= edgeW.getDst();
            if (Union.find(union[src])!=Union.find(union[dst])){
                if (Tree.get(src) == null)
                    Tree.put(src,new HashMap<Integer, EdgeW>());
                Tree.get(src).put(dst,edgeW);
                Union.Union(union[src],union[dst]);
            }
            else {
                System.out.println(src+" "+ dst);
            }
        }

        return Tree;
    }
}
class Union {
        private Union parent;
        private int rank;
        public Union(){
            parent = this;
            rank = 0;
        }

    public static Union find(Union x) {
        if (x.parent != x) {
            x.parent = find(x.parent);
            x = x.parent;
        }
        return x.parent;
    }
    public static void Union(Union x, Union y) {
        Union xRoot = find(x);
        Union yRoot = find(y);
        if(xRoot== yRoot)
            return;
        if(xRoot.rank < yRoot.rank)
            xRoot.parent = yRoot;
        else if(xRoot.rank > yRoot.rank)
            yRoot.parent = xRoot;
        else{
            yRoot.parent = xRoot;
            xRoot.rank = xRoot.rank + 1;
        }

    }
    public static void main(String[] args) throws Exception {
            EdgeW e0 = new EdgeW(0,1,2);
            EdgeW e1 = new EdgeW(1,2,12);
            EdgeW e2 = new EdgeW(2,3,2);
            EdgeW e3 = new EdgeW(2,4,5);
            EdgeW e4 = new EdgeW(3,4,1);
            EdgeW e5 = new EdgeW(3,5,3);
            EdgeW e6 = new EdgeW(3,1,11);
            EdgeW e7 = new EdgeW(2,5,8);
            HashMap<Integer, HashMap<Integer, EdgeW>> g = new LinkedHashMap<>();
            for (int i = 0;i<6;i++){
                g.put(i,new HashMap<>());
            }
            g.get(0).put(1,e0);
            g.get(1).put(2,e1);
            g.get(2).put(3,e2);
            g.get(2).put(4,e3);
            g.get(3).put(4,e4);
            g.get(3).put(5,e5);
            g.get(3).put(1,e6);
            g.get(2).put(5,e7);
            Kruskal k = new Kruskal();
        HashMap<Integer, HashMap<Integer, EdgeW>> ans = k.kruskel(g);
        for (int i = 0; i<6;i++ ){
            if (ans.get(i)!=null)
                for (EdgeW e:ans.get(i).values()){
                    System.out.print(e+" ");
                }
        }
    }
}

