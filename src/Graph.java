
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;


public class Graph {


    private ArrayList<Node> graphs;



    Graph() {
        this.graphs = new ArrayList<>();
    }


    Graph(ArrayList<Node> g){
        this.graphs = g;
    }


    public void checkGraph () {
        System.out.println("Nó " + " - " + " Arestas: ");

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
        ArrayList[] positivos = new ArrayList[numberOfvars];
        ArrayList[] negativos = new ArrayList[numberOfvars];
        ArrayList<Integer> expressao = new ArrayList<>();
        int i=0;
        while(st != -1) {
            expressao.clear();
            for(int j=1; j<=numberOfvars; j++) {
                try {
                    if (((st = br.read()) == -1)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(st);
                if (st == 2) {
                    i--;
                } else if (st == 1) {
                    this.graphs.add(new Node(i, j));
                    expressao.add(i);
                    positivos[j].add(i);
                } else if (st == 0) {
                    this.graphs.add(new Node(i, -j));
                    expressao.add(i);
                    negativos[j].add(i);
                }
                /*else{
                    System.out.println("Entrada de Valor inválida");
                    exit(0);
                }*/
                i++;
            }
            for(int k = 0; k < expressao.size(); k++){
                int numAux = expressao.get(k);
                this.graphs.get(numAux).edges=expressao;
                this.graphs.get(numAux).edges.removeIf(n -> n== numAux);
            }
        }

        /*for(int a =0; a<this.graphs.size(); a++){
            int var = this.graphs.get(a).variavel;
            if(var>0) {
                this.graphs.get(a).edges.addAll(negativos[var]);
            }
            else{
                this.graphs.get(a).edges.addAll(positivos[-var]);
            }
        }*/
        this.checkGraph();
    }
}





