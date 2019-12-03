
import java.io.*;
import java.util.ArrayList;
import java.util.List;


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
    public void loadGraph (File file) throws IOException{

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

    //Função para transformar Sat em grafo e armazenar o grafo
    public void loadGraphSat (File file) throws IOException{

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int st = -2;
        int numberOfvars =  Integer.parseInt(br.readLine());
        ArrayList<Integer> expressao = null;
        int i=0;
        while(st != -1) {
            expressao.clear();
            for(int j=1; j<=numberOfvars; j++) {
                try {
                    if (((st = br.read()) == -1)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (st == 2) {
                    i--;
                } else if (st == 1) {
                    Node aux = new Node(i, j);
                    this.graphs.add(aux);
                    expressao.add(i);
                } else if (st == 0) {
                    Node aux = new Node(i, -j);
                    this.graphs.add(aux);
                    expressao.add(i);
                }
                i++;
            }
            for(int k = 0; k<expressao.size(); k++){
                int numAux = expressao.get(k);
                this.graphs.get(numAux).edges=expressao;
                this.graphs.get(numAux).edges.removeIf(n -> n== numAux);
            }
        }
    }
}





