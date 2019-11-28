
import java.io.*;
import java.util.ArrayList;


public class Graph {


    private ArrayList<Node> graphs;



    Graph() {
        this.graphs = new ArrayList<>();
    }


    Graph(ArrayList<Node> g){
        this.graphs = g;
    }


    public void checkGraph () {

        for (Node graph : graphs) {
            System.out.println("Nó " + graph.node + " - " + " Arestas: " + graph.edges);
        }
    }


    public ArrayList<Node> getGraph () {
        return this.graphs;
    }


    private ArrayList<Integer> checkAdjacencies (String list) {

        ArrayList<Integer> adj = new ArrayList<>();

        String l = list.replaceAll("\\s+","");
        char[] charArray = l.toCharArray();

        for (int i =0 ; i < l.length(); i++){
            if ( charArray[i] == '1'){
                adj.add(i);
            }
        }

        return adj;

    }


    //retorna a lista de adjacências do grafo
    public void loadGraph () throws IOException{

        File file = new File("/Users/gmonteiro/Downloads/algorithms/src/graph.txt");

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = null;
        int numberOfNodes =  Integer.parseInt(br.readLine());

        for (int i =0; i < numberOfNodes ; i ++) {

            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.graphs.add(new Node(i,checkAdjacencies(st)));

        }
    }
}





