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
        ArrayList<Node> greedySolution = MSI.greedy(graph.getGraph());

        for (Node n : greedySolution) {
            System.out.print( " " + n.node);
        }

        MSI.branchAndBound(greedySolution,graph);

    }
}
