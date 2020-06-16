public class Edge implements edge_data {
    private int src;
    private int dst;
    public Edge(int src, int dst){
        this.dst = dst;
        this.src = src;
    }
    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDst() {
        return dst;
    }
}
