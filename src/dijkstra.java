import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;


class EdgeD implements Comparable<EdgeD>{
    int src;
    int dst;
    int weight;
    public EdgeD(int src,int dst, int weight){
        this.dst = dst;
        this.src = src;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeD o) {
        return this.weight - o.weight;
    }
}
public class dijkstra {
    public static int inf = Integer.MAX_VALUE;
    private HashMap<Integer, HashMap<Integer, EdgeD>> G = new LinkedHashMap<>();

    public ArrayList<Integer> shortestPath(int src,int dst ,HashMap<Integer, HashMap<Integer, EdgeD>> g ){
        int[] cost = new int[g.size()];
        ArrayList<Integer>[] path = new ArrayList[g.size()];
        for (int i = 0; i<cost.length;i++){
            cost[i] = inf;
            path[i] = new ArrayList<>();
        }
        cost[src] =0;
        path[src].add(src);
        if (src==dst) return new ArrayList<>(src);
        if (g.get(src)==null||g.get(src).size()==0)return new ArrayList<>();
        PriorityQueue<EdgeD> minEq = new PriorityQueue<>();
        for (EdgeD edge:g.get(src).values()){
            minEq.add(edge);
        }
        boolean finish = false;
        while (!finish) {
            EdgeD  eTemp= minEq.poll();
            int tDst=eTemp.dst,tSrc = eTemp.src,wTemp = eTemp.weight;
            if (cost[tDst]==inf||cost[tDst] > cost[tSrc]+wTemp ){
                cost[tDst] =  cost[tSrc]+wTemp;
                for (int i: path[tSrc]){
                    path[tDst].add(i);
                }
                path[tDst].add(tDst);
                for (EdgeD edge:g.get(tDst).values()){
                    minEq.add(edge);
                }
            }
            finish = minEq.isEmpty() || tDst == dst;
        }
        return path[src];

    }

}
