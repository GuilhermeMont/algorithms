import java.util.ArrayList;

public class Node {

    public int node;
    public char variavel;
    public ArrayList<Integer> edges;


    Node (int node, ArrayList<Integer> edges) {

        this.node = node;
        this.edges = edges;

    }

    Node() {
        this.node = 0;
        this.edges = new ArrayList<>();
    }

    Node(int node, ArrayList<Integer> edges, char var){
        this.node = node;
        this.edges = edges;
        this.variavel = var;
    }
}
