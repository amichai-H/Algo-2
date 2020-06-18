public class Node implements node_data {
    int id;
    public Node(int id){
        this.id = id;
    }

    public Node(node_data v) {
        this.id = v.getId();
    }

    @Override
    public int getId() {
        return this.id;
    }
}
