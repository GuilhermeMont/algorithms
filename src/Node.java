import java.util.ArrayList;

public class Node {

    public int node;
    public int variavel;
    public ArrayList<Integer> edges = null;


    Node (int node, ArrayList<Integer> edges) {

        this.node = node;
        this.edges = edges;

    }

    Node() {
        this.node = 0;
        this.edges = new ArrayList<>();
    }

    Node(int node, ArrayList<Integer> edges, int var){
        this.node = node;
        this.edges = edges;
        this.variavel = var;
    }

    Node(int node, int var){
        this.node = node;
        this.variavel = var;
    }
}
