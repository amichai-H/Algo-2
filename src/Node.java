public class Node implements node_data {
    int id;
    public Node(int id){
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
