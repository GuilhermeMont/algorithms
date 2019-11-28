import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        Graph graph = new Graph();

        try {
            graph.loadGraph();
        }catch (Exception e){
            e.printStackTrace();
        }


        MaxIndependentSet MSI = new MaxIndependentSet();


        System.out.println( "Solução gulosa  ");

        for (Node n : MSI.greedy(graph.getGraph())) {
            System.out.print( " " + n.node);
        }


    }
}
