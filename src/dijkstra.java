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
                cost[tDst] = cost[tSrc]+wTemp;
                System.out.println(cost[tSrc]);
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
        return path[dst];

    }
    public static void main(String[] args) {
        HashMap<Integer, HashMap<Integer, EdgeD>> g = new LinkedHashMap<>();
        EdgeD e01 = new EdgeD(0,1,3);
        EdgeD e02 = new EdgeD(0,2,1);

        EdgeD e10 = new EdgeD(1,0,3);
        EdgeD e13 = new EdgeD(1,3,2);
        EdgeD e12 = new EdgeD(1,2,1);

        EdgeD e20 = new EdgeD(2,0,1);
        EdgeD e21 = new EdgeD(2,1,1);
        EdgeD e24 = new EdgeD(2,4,5);

        EdgeD e34 = new EdgeD(3,4,1);
        EdgeD e31 = new EdgeD(3,1,2);
        EdgeD e35 = new EdgeD(3,5,9);
        EdgeD e36 = new EdgeD(3,6,5);

        EdgeD e42 = new EdgeD(4,2,5);
        EdgeD e43 = new EdgeD(4,3,1);
        EdgeD e46 = new EdgeD(4,6,5);

        EdgeD e53 = new EdgeD(5,3,9);
        EdgeD e56 = new EdgeD(5,6,8);
        EdgeD e57 = new EdgeD(5,7,2);

        EdgeD e65 = new EdgeD(6,5,8);
        EdgeD e63 = new EdgeD(6,3,5);
        EdgeD e64 = new EdgeD(6,4,5);
        EdgeD e67 = new EdgeD(6,7,1);

        EdgeD e76 = new EdgeD(7,6,1);
        EdgeD e75 = new EdgeD(7,5,2);


        for (int i = 0;i<8;i++){
            g.put(i,new HashMap<>());
        }
        g.get(0).put(1,e01);
        g.get(0).put(2,e02);

        g.get(1).put(0,e10);
        g.get(1).put(2,e12);
        g.get(1).put(3,e13);

        g.get(2).put(0,e20);
        g.get(2).put(1,e21);
        g.get(2).put(4,e24);

        g.get(3).put(1,e31);
        g.get(3).put(4,e34);
        g.get(3).put(5,e35);
        g.get(3).put(6,e36);

        g.get(4).put(2,e42);
        g.get(4).put(3,e43);
        g.get(4).put(6,e46);

        g.get(5).put(3,e53);
        g.get(5).put(6,e56);
        g.get(5).put(7,e57);

        g.get(6).put(3,e63);
        g.get(6).put(4,e64);
        g.get(6).put(5,e65);
        g.get(6).put(7,e67);

        g.get(7).put(5,e75);
        g.get(7).put(6,e76);

        dijkstra dijkstra = new dijkstra();
        System.out.println(dijkstra.shortestPath(0,5,g));
    }

}
