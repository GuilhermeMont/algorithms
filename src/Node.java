import java.util.ArrayList;

public class Node {

    public int node;

    public ArrayList<Integer> edges;


    Node (int node, ArrayList<Integer> edges) {

        this.node = node;
        this.edges = edges;

    }

    Node() {
        this.node = 0;
        this.edges = new ArrayList<>();
    }


}
